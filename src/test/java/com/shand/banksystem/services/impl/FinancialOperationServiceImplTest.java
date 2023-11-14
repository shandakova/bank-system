package com.shand.banksystem.services.impl;

import com.shand.banksystem.dto.AccountDto;
import com.shand.banksystem.dto.FinancialOperationBaseDto;
import com.shand.banksystem.dto.FinancialOperationFullDto;
import com.shand.banksystem.dto.OperationFilter;
import com.shand.banksystem.dto.base.BasePageRequest;
import com.shand.banksystem.dto.base.BasePageResponse;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.model.Account;
import com.shand.banksystem.model.FinancialOperation;
import com.shand.banksystem.model.enums.FinancialOperationType;
import com.shand.banksystem.repositories.FinancialOperationRepository;
import com.shand.banksystem.services.AccountService;
import com.shand.banksystem.services.FinancialOperationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FinancialOperationServiceImplTest {
    @Autowired
    private FinancialOperationService financialOperationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FinancialOperationRepository repository;

    @Test
    void makeOperation_failed() {
        FinancialOperationBaseDto dto = new FinancialOperationBaseDto();
        BaseResponse<FinancialOperationFullDto> response = financialOperationService.makeOperation(dto);
        assertFalse(response.isSuccess());
        assertEquals("Invalid dto", response.getMessage());

        dto.setAmount(BigDecimal.valueOf(-11));
        dto.setAccount("fake-acc");
        dto.setType(FinancialOperationType.WITHDRAWAL);
        response = financialOperationService.makeOperation(dto);
        assertFalse(response.isSuccess());
        assertEquals("Amount should be positive.", response.getMessage());

        dto.setAmount(new BigDecimal("10.00000"));
        response = financialOperationService.makeOperation(dto);
        assertFalse(response.isSuccess());
        assertEquals("For rubles operation scale should be less 3", response.getMessage());

        dto.setAmount(BigDecimal.ZERO);
        dto.setAccount("fake-acc");
        dto.setType(FinancialOperationType.WITHDRAWAL);
        response = financialOperationService.makeOperation(dto);
        assertFalse(response.isSuccess());
        assertEquals("Account not found", response.getMessage());
    }

    @Test
    void makeOperation_success() {
        BaseResponse<AccountDto> accResp = accountService.createNewAccount();
        FinancialOperationBaseDto dto = new FinancialOperationBaseDto();
        dto.setAmount(BigDecimal.valueOf(10));
        dto.setAccount(accResp.getValue().getUuid());
        dto.setType(FinancialOperationType.DEPOSIT);
        BaseResponse<FinancialOperationFullDto> response = financialOperationService.makeOperation(dto);
        assertTrue(response.isSuccess());
        assertNotNull(response.getValue());

        OperationFilter filter = new OperationFilter();
        filter.setStart(ZonedDateTime.now().minusDays(1));
        filter.setEnd(ZonedDateTime.now().plusDays(1));
        BasePageResponse<List<FinancialOperationFullDto>> listOperation = financialOperationService.getListOperation(BasePageRequest.<OperationFilter>builder().
                page(0).size(10).filter(filter).build());
        int listSize = listOperation.getValue().size();
        assertTrue(listOperation.isSuccess());
        assertTrue(listOperation.getValue().stream().anyMatch(d -> d.getId().equals(response.getValue().getId())));

        filter.setCurrency("USD");
        listOperation = financialOperationService.getListOperation(BasePageRequest.<OperationFilter>builder().
                page(0).size(10).filter(filter).build());

        assertTrue(listOperation.isSuccess());
        assertEquals(listSize, listOperation.getValue().size());
    }

    @Test
    void getListOperation_failed() {
        OperationFilter filter = new OperationFilter();
        BasePageResponse<List<FinancialOperationFullDto>> listOperation = financialOperationService.getListOperation(BasePageRequest.<OperationFilter>builder().
                page(0).size(10).filter(filter).build());
        assertFalse(listOperation.isSuccess());

        listOperation = financialOperationService.getListOperation(null);
        assertFalse(listOperation.isSuccess());

        listOperation = financialOperationService.getListOperation(BasePageRequest.<OperationFilter>builder().page(0).size(10).build());
        assertFalse(listOperation.isSuccess());

        filter.setStart(ZonedDateTime.now().minusDays(1));
        filter.setEnd(ZonedDateTime.now().plusDays(1));
        filter.setCurrency("Strange");
        listOperation = financialOperationService.getListOperation(BasePageRequest.<OperationFilter>builder().
                page(0).size(10).filter(filter).build());
        assertFalse(listOperation.isSuccess());

        filter.setStart(ZonedDateTime.now().plusDays(2));
        filter.setCurrency("RUB");
        listOperation = financialOperationService.getListOperation(BasePageRequest.<OperationFilter>builder().
                page(0).size(10).filter(filter).build());
        assertFalse(listOperation.isSuccess());

    }

    @Test
    void getListOperation_withDatesOnWeekends() {
        BaseResponse<AccountDto> accResp = accountService.createNewAccount();
        Account account = new Account();
        account.setId(UUID.fromString(accResp.getValue().getUuid()));
        ZonedDateTime firstTime = ZonedDateTime.now().with(firstDayOfYear()).plusDays(1);
        for (int i = 0; i < 10; i++) {
            FinancialOperation op = new FinancialOperation();
            op.setType(FinancialOperationType.DEPOSIT);
            op.setAmount(BigDecimal.valueOf(1000));
            op.setAccount(account);
            op.setDateTime(firstTime);
            firstTime = firstTime.plusDays(2);
            repository.save(op);
        }
        OperationFilter filter = new OperationFilter();
        filter.setStart(firstTime.minusDays(20));
        filter.setEnd(firstTime.plusDays(5));
        filter.setCurrency("USD");
        BasePageResponse<List<FinancialOperationFullDto>> listOperation = financialOperationService.getListOperation(BasePageRequest.<OperationFilter>builder().
                page(0).size(10).filter(filter).build());
        assertTrue(listOperation.isSuccess());
        listOperation.getValue().forEach(d -> System.out.println(d.getDateTime() + " " + d.getAmount() + "\n"));
    }
}