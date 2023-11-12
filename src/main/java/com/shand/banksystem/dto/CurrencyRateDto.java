package com.shand.banksystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Schema(description = "Курс валюты")
public class CurrencyRateDto {
    @Schema(description = "Название")
    private String name;
    @Schema(description = "Курс")
    private BigDecimal rate;
}
