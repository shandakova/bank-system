package com.shand.banksystem.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Schema(description = "Страничный запрос")
public class BasePageRequest<T> {
    @Schema(description = "Фильтр запроса")
    private T filter;
    @Schema(description = "Запрашиваемая страница")
    private int page;
    @Schema(description = "Элементов на странице")
    private int size;
}
