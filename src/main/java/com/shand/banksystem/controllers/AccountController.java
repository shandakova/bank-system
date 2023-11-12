package com.shand.banksystem.controllers;

import com.shand.banksystem.dto.AccountDto;
import com.shand.banksystem.dto.base.BasePageRequest;
import com.shand.banksystem.dto.base.BasePageResponse;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account", description = "Контроллер работы с аккаунтами")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/new")
    @Operation(summary = "Создать новый аккаунт")
    public BaseResponse<AccountDto> createNewAccount() {
        return accountService.createNewAccount();
    }

    @GetMapping
    @Operation(summary = "Получить список аккаунтов")
    public BasePageResponse<List<AccountDto>> getPageList(@RequestParam("page") int page,
                                                          @RequestParam("size") int size) {
        BasePageRequest<Void> request = BasePageRequest.<Void>builder()
                .page(page).size(size).build();
        return accountService.getAccountsPage(request);
    }


}
