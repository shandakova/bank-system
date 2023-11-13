package com.shand.banksystem.services.impl;

import com.shand.banksystem.dto.AccountDto;
import com.shand.banksystem.dto.FinancialOperationBaseDto;
import com.shand.banksystem.dto.FinancialOperationFullDto;
import com.shand.banksystem.dto.OperationFilter;
import com.shand.banksystem.dto.base.BasePageRequest;
import com.shand.banksystem.dto.base.BasePageResponse;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.model.enums.FinancialOperationType;
import com.shand.banksystem.services.AccountService;
import com.shand.banksystem.services.FinancialOperationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FinancialOperationServiceImplTest {
    @Autowired
    private FinancialOperationService financialOperationService;

    @Autowired
    private AccountService accountService;

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
        filter.setStart(LocalDateTime.now().minusDays(1));
        filter.setEnd(LocalDateTime.now().plusDays(1));
        BasePageResponse<List<FinancialOperationFullDto>> listOperation = financialOperationService.getListOperation(BasePageRequest.<OperationFilter>builder().
                page(0).size(10).filter(filter).build());
        int listSize = listOperation.getValue().size();
        assertTrue(listOperation.isSuccess());
        assertTrue(listOperation.getValue().contains(response.getValue()));

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

        filter.setStart(LocalDateTime.now().minusDays(1));
        filter.setEnd(LocalDateTime.now().plusDays(1));
        filter.setCurrency("Strange");
        listOperation = financialOperationService.getListOperation(BasePageRequest.<OperationFilter>builder().
                page(0).size(10).filter(filter).build());
        assertFalse(listOperation.isSuccess());

        filter.setStart(LocalDateTime.now().plusDays(2));
        filter.setCurrency("RUB");
        listOperation = financialOperationService.getListOperation(BasePageRequest.<OperationFilter>builder().
                page(0).size(10).filter(filter).build());
        assertFalse(listOperation.isSuccess());

    }

}