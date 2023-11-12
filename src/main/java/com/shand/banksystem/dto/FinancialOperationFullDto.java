package com.shand.banksystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Операция над счетом")
public class FinancialOperationFullDto extends FinancialOperationBaseDto {
    @Schema(description = "id операции")
    private Long id;

    @Schema(description = "Время исполнения")
    private LocalDateTime dateTime;

    @Schema(description = "Валюта")
    private String currency;
}