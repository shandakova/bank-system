package com.shand.banksystem.controllers;

import com.shand.banksystem.dto.CurrencyRateDto;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.services.RussiaBankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
@Tag(name = "Currency", description = "Контроллер работы с курсами валют")
public class CurrencyRateController {
    private final RussiaBankService service;

    public CurrencyRateController(RussiaBankService service) {
        this.service = service;
    }

    @Operation(summary = "Загрузить курсы валют")
    @PostMapping("/upload")
    public BaseResponse<Void> loadCurrency() {
        service.updateLocalCurrency();
        return BaseResponse.<Void>builder().success(true).build();
    }

    @Operation(summary = "Получить список курсов валют")
    @GetMapping
    public BaseResponse<List<CurrencyRateDto>> getAllCurrencyRate() {
        return service.getCurrencyRateList();
    }
}
