package com.shand.banksystem.services.impl;

import com.shand.banksystem.dto.AccountDto;
import com.shand.banksystem.dto.base.BasePageRequest;
import com.shand.banksystem.dto.base.BasePageResponse;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.model.Account;
import com.shand.banksystem.repositories.AccountRepository;
import com.shand.banksystem.services.AccountService;
import com.shand.banksystem.services.utils.ValidationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    public BaseResponse<AccountDto> createNewAccount() {
        Account newAccount = repository.save(new Account());
        return BaseResponse.<AccountDto>builder()
                .success(true)
                .value(new AccountDto(newAccount.getId().toString(),
                        newAccount.getBalance().setScale(2, RoundingMode.DOWN))).build();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BaseResponse<AccountDto> changeAccountBalance(String uuid, BigDecimal amount) {
        Optional<Account> accountOpt;
        try {
            UUID uuidObj = UUID.fromString(uuid);
            accountOpt = repository.findById(uuidObj);
        } catch (IllegalArgumentException e) {
            accountOpt = Optional.empty();
        }
        if (accountOpt.isEmpty()) {
            return BasePageResponse.<AccountDto>builder()
                    .success(false).message("Account not found").build();
        }
        Account account = accountOpt.get();
        BigDecimal newBalance = account.getBalance().setScale(2, RoundingMode.DOWN).add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            return BasePageResponse.<AccountDto>builder()
                    .success(false).message("There are insufficient funds in the account").build();
        }
        account.setBalance(newBalance);
        repository.save(account);
        return BaseResponse.<AccountDto>builder()
                .success(true)
                .value(new AccountDto(account.getId().toString(),
                        account.getBalance())).build();
    }


    public BasePageResponse<List<AccountDto>> getAccountsPage(BasePageRequest<Void> request) {
        String message = ValidationUtils.validatePageRequest(request);
        if (message != null) {
            return BasePageResponse.<List<AccountDto>>builder()
                    .success(false).message(message).build();
        }
        Page<Account> all = repository.findAll(PageRequest.of(request.getPage(), request.getSize()));
        List<AccountDto> accountDtos = all.getContent().stream().map(account ->
                new AccountDto(account.getId().toString(),
                        account.getBalance().setScale(2))).toList();
        return BasePageResponse.<List<AccountDto>>builder()
                .success(true)
                .value(accountDtos)
                .page(all.getNumber())
                .size(accountDtos.size())
                .totalPage(all.getTotalPages())
                .build();
    }
}
