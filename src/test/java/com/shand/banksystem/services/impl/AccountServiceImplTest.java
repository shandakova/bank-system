package com.shand.banksystem.services.impl;

import com.shand.banksystem.dto.AccountDto;
import com.shand.banksystem.dto.base.BasePageRequest;
import com.shand.banksystem.dto.base.BasePageResponse;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    AccountService accountService;

    @Test
    void createNewAccount() {
        BaseResponse<AccountDto> newAccount = accountService.createNewAccount();
        assertTrue(newAccount.isSuccess());
        assertNotNull(newAccount.getValue());
    }

    @Test
    void getAccountsPage_successful() {
        BasePageResponse<List<AccountDto>> accountsPage = accountService.getAccountsPage(BasePageRequest.<Void>builder().page(0)
                .size(Integer.MAX_VALUE).build());
        int size = accountsPage.getValue().size();

        BaseResponse<AccountDto> newAccount = accountService.createNewAccount();
        accountsPage = accountService.getAccountsPage(BasePageRequest.<Void>builder().page(0)
                .size(Integer.MAX_VALUE).build());
        assertEquals(size + 1, accountsPage.getValue().size());
        assertTrue(accountsPage.getValue().contains(newAccount.getValue()));
    }

    @Test
    void getAccountsPage_wrongRequest() {
        BasePageResponse<List<AccountDto>> accountsPage = accountService.getAccountsPage(null);
        assertFalse(accountsPage.isSuccess());
    }

    @Test
    void changeAccountBalance_failed() {
        BaseResponse<AccountDto> response = accountService.changeAccountBalance("fake uuid", BigDecimal.ONE);
        assertFalse(response.isSuccess());
        assertEquals("Account not found", response.getMessage());

        BaseResponse<AccountDto> newAccount = accountService.createNewAccount();
        response = accountService.changeAccountBalance(newAccount.getValue().getUuid(), BigDecimal.valueOf(-10));
        assertFalse(response.isSuccess());
        assertEquals("There are insufficient funds in the account", response.getMessage());
    }

    @Test
    void changeAccountBalance_successful() {
        BaseResponse<AccountDto> newAccount = accountService.createNewAccount();
        BaseResponse<AccountDto> response = accountService.changeAccountBalance(newAccount.getValue().getUuid(), BigDecimal.valueOf(10));
        assertTrue(response.isSuccess());
        assertEquals(0, BigDecimal.valueOf(10).compareTo(response.getValue().getBalance()));
    }

}