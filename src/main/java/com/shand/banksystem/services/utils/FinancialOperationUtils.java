package com.shand.banksystem.services.utils;

import com.shand.banksystem.dto.FinancialOperationBaseDto;
import com.shand.banksystem.dto.FinancialOperationFullDto;
import com.shand.banksystem.model.Account;
import com.shand.banksystem.model.FinancialOperation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.UUID;

public class FinancialOperationUtils {
    private FinancialOperationUtils() {
    }

    public static FinancialOperationFullDto mapFullDtoFromOperation(FinancialOperation operation) {
        FinancialOperationFullDto dto = new FinancialOperationFullDto();
        dto.setId(operation.getId());
        dto.setAccount(operation.getAccount().getId().toString());
        dto.setAmount(operation.getAmount().setScale(2, RoundingMode.HALF_DOWN));
        dto.setDescription(operation.getDescription());
        dto.setDateTime(operation.getDateTime());
        dto.setType(operation.getType());
        dto.setCurrency("RUB");
        return dto;
    }

    public static FinancialOperationFullDto mapFullDtoFromOperationWithRate(FinancialOperation operation, String currency, BigDecimal rate) {
        FinancialOperationFullDto dto = mapFullDtoFromOperation(operation);
        dto.setAmount(dto.getAmount().divide(rate, 2, RoundingMode.HALF_DOWN));
        dto.setCurrency(currency);
        return dto;
    }

    public static FinancialOperation mapOperationFromDto(FinancialOperationBaseDto dto) {
        FinancialOperation operation = new FinancialOperation();
        Account account = new Account();
        account.setId(UUID.fromString(dto.getAccount()));
        operation.setAccount(account);
        operation.setAmount(dto.getAmount());
        operation.setDescription(dto.getDescription());
        operation.setDateTime(ZonedDateTime.now());
        operation.setType(dto.getType());
        return operation;
    }
}
