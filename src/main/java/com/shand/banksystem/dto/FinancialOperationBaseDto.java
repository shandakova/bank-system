package com.shand.banksystem.dto;

import com.shand.banksystem.model.enums.FinancialOperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Операция над счетом")
public class FinancialOperationBaseDto {
    @Schema(description = "uuid аккаунта над которым производится операция")
    private String account;
    @Schema(description = "Вид операции")
    private FinancialOperationType type;
    @Schema(description = "Описание операции")
    private String description;
    @Schema(description = "Сумма операции")
    private BigDecimal amount;
}
