package com.shand.banksystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Schema(description = "Банковский аккаунт")
public class AccountDto {
    @Schema(description = "Идентификатор")
    private String uuid;
    @Schema(description = "Баланс счета")
    private BigDecimal balance;
}
