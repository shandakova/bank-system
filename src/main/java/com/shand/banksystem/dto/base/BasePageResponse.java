package com.shand.banksystem.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@Schema(description = "Страничный ответ")
public class BasePageResponse<T> extends BaseResponse<T>  {
    @Schema(description = "Текущая страница")
    private int page;
    @Schema(description = "Всего страниц")
    private int totalPage;
    @Schema(description = "Элементов на странице")
    private int size;
}
