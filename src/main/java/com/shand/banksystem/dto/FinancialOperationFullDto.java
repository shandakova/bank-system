package com.shand.banksystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Операция над счетом")
public class FinancialOperationFullDto extends FinancialOperationBaseDto {
    @Schema(description = "id операции")
    private Long id;

    @Schema(description = "Время исполнения")
    private ZonedDateTime dateTime;

    @Schema(description = "Валюта")
    private String currency;
}
