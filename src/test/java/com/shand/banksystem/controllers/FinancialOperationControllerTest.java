package com.shand.banksystem.controllers;

import com.shand.banksystem.dto.*;
import com.shand.banksystem.dto.base.BasePageRequest;
import com.shand.banksystem.dto.base.BasePageResponse;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.model.enums.FinancialOperationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FinancialOperationControllerTest {
    @Autowired
    FinancialOperationController finController;
    @Autowired
    AccountController accController;
    @Autowired
    CurrencyRateController currencyController;

    @Test
    void makeOperation_generalTest() {
        currencyController.loadCurrency();
        BaseResponse<List<CurrencyRateDto>> allCurrencyRate =
                currencyController.getAllCurrencyRate();

        assertTrue(allCurrencyRate.getValue().size() > 0);

        BaseResponse<AccountDto> accResp = accController.createNewAccount();
        assertNotNull(accResp.getValue());
        BasePageResponse<List<AccountDto>> pageList = accController.getPageList(0, 10);
        assertTrue(pageList.getValue().contains(accResp.getValue()));

        FinancialOperationBaseDto dto = new FinancialOperationBaseDto();
        dto.setAmount(BigDecimal.valueOf(10));
        dto.setAccount(accResp.getValue().getUuid());
        dto.setType(FinancialOperationType.DEPOSIT);
        BaseResponse<FinancialOperationFullDto> operationResp = finController.makeOperation(dto);
        assertNotNull(operationResp.getValue());

        OperationFilter filter = new OperationFilter();
        filter.setCurrency("RUB");
        filter.setStart(ZonedDateTime.now().minusDays(1));
        filter.setEnd(ZonedDateTime.now().plusDays(1));
        BasePageResponse<List<FinancialOperationFullDto>> listOperation = finController.getList(BasePageRequest.<OperationFilter>builder().
                page(0).size(10).filter(filter).build());
        assertTrue(listOperation.getValue().stream().anyMatch(d -> d.getId().equals(operationResp.getValue().getId())));
    }
}