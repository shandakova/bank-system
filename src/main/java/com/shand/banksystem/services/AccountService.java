package com.shand.banksystem.services;

import com.shand.banksystem.dto.AccountDto;
import com.shand.banksystem.dto.base.BasePageRequest;
import com.shand.banksystem.dto.base.BasePageResponse;
import com.shand.banksystem.dto.base.BaseResponse;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    BaseResponse<AccountDto> createNewAccount();

    BasePageResponse<List<AccountDto>> getAccountsPage(BasePageRequest<Void> request);

    BaseResponse<AccountDto> changeAccountBalance(String uuid, BigDecimal amount);


}
