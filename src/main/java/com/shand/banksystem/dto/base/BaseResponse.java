package com.shand.banksystem.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@Schema(description = "Ответ")
public class BaseResponse<T> {
    @Schema(description = "Значение")
    private T value;
    @Schema(description = "Успешность")
    private boolean success;
    @Schema(description = "Сообщение об ошибке (если есть)")
    private String message;
}
