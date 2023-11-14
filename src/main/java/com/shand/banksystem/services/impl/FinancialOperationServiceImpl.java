package com.shand.banksystem.services.impl;

import com.shand.banksystem.dto.AccountDto;
import com.shand.banksystem.dto.FinancialOperationBaseDto;
import com.shand.banksystem.dto.FinancialOperationFullDto;
import com.shand.banksystem.dto.OperationFilter;
import com.shand.banksystem.dto.base.BasePageRequest;
import com.shand.banksystem.dto.base.BasePageResponse;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.model.FinancialOperation;
import com.shand.banksystem.model.enums.FinancialOperationType;
import com.shand.banksystem.repositories.FinancialOperationRepository;
import com.shand.banksystem.repositories.spec.OperationSpecification;
import com.shand.banksystem.services.AccountService;
import com.shand.banksystem.services.FinancialOperationService;
import com.shand.banksystem.services.RussiaBankService;
import com.shand.banksystem.services.utils.FinancialOperationUtils;
import com.shand.banksystem.services.utils.ValidationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Stream;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

@Service
public class FinancialOperationServiceImpl implements FinancialOperationService {
    private AccountService accountService;
    private FinancialOperationRepository operationRepository;

    private RussiaBankService bankService;

    public FinancialOperationServiceImpl(AccountService accountService, FinancialOperationRepository operationRepository, RussiaBankService bankService) {
        this.accountService = accountService;
        this.operationRepository = operationRepository;
        this.bankService = bankService;
    }


    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BaseResponse<FinancialOperationFullDto> makeOperation(FinancialOperationBaseDto dto) {
        var result = validateOperationDto(dto);
        if (result != null) {
            return result;
        }

        BigDecimal amount = dto.getType().equals(FinancialOperationType.DEPOSIT)
                ? dto.getAmount() : dto.getAmount().negate();
        BaseResponse<AccountDto> response = accountService.changeAccountBalance(dto.getAccount(), amount);

        if (!response.isSuccess()) {
            return BaseResponse.<FinancialOperationFullDto>builder()
                    .success(false).message(response.getMessage()).build();
        }

        FinancialOperation operation = operationRepository.save(FinancialOperationUtils.mapOperationFromDto(dto));
        FinancialOperationFullDto fullDto = FinancialOperationUtils.mapFullDtoFromOperation(operation);
        return BaseResponse.<FinancialOperationFullDto>builder().success(true).value(fullDto).build();
    }

    public BasePageResponse<List<FinancialOperationFullDto>> getListOperation(BasePageRequest<OperationFilter> request) {
        var result = validatePageRequestWithFilter(request);
        if (result != null) {
            return result;
        }
        OperationFilter filter = request.getFilter();
        if (filter.getCurrency() != null && !bankService.getAllCurrency().contains(filter.getCurrency())) {
            return BasePageResponse.<List<FinancialOperationFullDto>>builder()
                    .success(false).message("Not found currency. Update currency or request in 'RUB'").build();
        }
        Page<FinancialOperation> all = operationRepository.findAll(OperationSpecification.findByFilter(request.getFilter()),
                PageRequest.of(request.getPage(), request.getSize()));
        List<FinancialOperationFullDto> listDto;

        if (all.getContent().isEmpty()) {
            listDto = Collections.emptyList();
        } else if (filter.getCurrency() != null && !filter.getCurrency().equals("RUB")) {
            Map<ZonedDateTime, BigDecimal> map = getRates(all.getContent(), filter.getCurrency());
            listDto = mapRatesToOperation(map, all.get(), filter.getCurrency());
        } else {
            listDto = all.get().map(FinancialOperationUtils::mapFullDtoFromOperation).toList();
        }
        return BasePageResponse.<List<FinancialOperationFullDto>>builder().success(true)
                .page(all.getNumber())
                .totalPage(all.getTotalPages())
                .value(listDto).build();
    }

    private Map<ZonedDateTime, BigDecimal> getRates(List<FinancialOperation> content, String currency) {
        ZonedDateTime firstBound = content.get(0).getDateTime();
        ZonedDateTime startOfYear = firstBound.with(firstDayOfYear());
        if (firstBound.isBefore(startOfYear.plusDays(12)) && firstBound.isAfter(startOfYear)) {
            firstBound = startOfYear;
        }
        ZonedDateTime secondBound = content.get(content.size() - 1).getDateTime();
        //Первая граница на случай праздников\выходных
        return bankService.getCurseForPeriod(firstBound.minusDays(2), secondBound, currency);
    }

    private List<FinancialOperationFullDto> mapRatesToOperation(Map<ZonedDateTime, BigDecimal> value, Stream<FinancialOperation> stream, String currency) {
        List<FinancialOperationFullDto> result = new ArrayList<>();
        Iterator<Map.Entry<ZonedDateTime, BigDecimal>> iter = value.entrySet().iterator();
        Iterator<FinancialOperation> operationIter = stream.iterator();
        Map.Entry<ZonedDateTime, BigDecimal> current = null;
        Map.Entry<ZonedDateTime, BigDecimal> next = null;
        if (iter.hasNext()) {
            current = iter.next();
        }
        if (iter.hasNext()) {
            next = iter.next();
        }
        while (operationIter.hasNext()) {
            FinancialOperation oper = operationIter.next();
            FinancialOperationFullDto dto;
            if (current == null) {
                dto = FinancialOperationUtils.mapFullDtoFromOperation(oper);
            } else if (next == null) {
                dto = FinancialOperationUtils.mapFullDtoFromOperationWithRate(oper, currency, current.getValue());
            } else {
                while (iter.hasNext() && !(current.getKey().isBefore(oper.getDateTime()) && next.getKey().isAfter(oper.getDateTime()))) {
                    current = next;
                    next = iter.next();
                }
                if (next.getKey().isAfter(oper.getDateTime())) {
                    dto = FinancialOperationUtils.mapFullDtoFromOperationWithRate(oper, currency, current.getValue());
                } else {
                    dto = FinancialOperationUtils.mapFullDtoFromOperationWithRate(oper, currency, next.getValue());
                }
            }
            result.add(dto);
        }
        return result;
    }

    private BasePageResponse<List<FinancialOperationFullDto>> validatePageRequestWithFilter(BasePageRequest<OperationFilter> request) {
        BasePageResponse<List<FinancialOperationFullDto>> result = null;
        String message = ValidationUtils.validatePageRequest(request);
        if (message == null) {
            if (request.getFilter() == null) {
                message = "Filter is null.";
            } else if (request.getFilter().getStart() == null || request.getFilter().getEnd() == null) {
                message = "Filter start/end is null.";
            } else if (request.getFilter().getStart().isAfter(request.getFilter().getEnd())) {
                message = "Filter start should not be after end.";
            }
        }
        if (message != null) {
            result = BasePageResponse.<List<FinancialOperationFullDto>>builder()
                    .success(false).message(message).build();
        }
        return result;
    }

    private BaseResponse<FinancialOperationFullDto> validateOperationDto(FinancialOperationBaseDto dto) {
        BaseResponse<FinancialOperationFullDto> result = null;
        String message = null;
        if (dto == null || dto.getAccount() == null || dto.getAmount() == null || dto.getType() == null) {
            message = "Invalid dto";
        } else if (dto.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            message = "Amount should be positive.";
        } else if (dto.getAmount().scale() > 3) {
            message = "For rubles operation scale should be less 3";
        }
        if (message != null) {
            result = BaseResponse.<FinancialOperationFullDto>builder()
                    .success(false).message(message).build();
        }
        return result;
    }

}
