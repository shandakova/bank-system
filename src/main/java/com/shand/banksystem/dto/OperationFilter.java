package com.shand.banksystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Schema(description = "Фильтр поиска по операциям")
public class OperationFilter {
    @Schema(description = "Валюта выдачи результата", defaultValue = "RUB")
    private String currency;
    @Schema(description = "Старт периода")
    private ZonedDateTime start;
    @Schema(description = "Конец периода")
    private ZonedDateTime end;
}
