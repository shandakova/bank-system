package com.shand.banksystem.controllers;

import com.shand.banksystem.dto.FinancialOperationBaseDto;
import com.shand.banksystem.dto.FinancialOperationFullDto;
import com.shand.banksystem.dto.OperationFilter;
import com.shand.banksystem.dto.base.BasePageRequest;
import com.shand.banksystem.dto.base.BasePageResponse;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.services.FinancialOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operation")
@Tag(name = "Financial Operation", description = "Контроллер работы операциями")
public class FinancialOperationController {

    private final FinancialOperationService service;

    public FinancialOperationController(FinancialOperationService service) {
        this.service = service;
    }

    @PostMapping("/new")
    @Operation(summary = "Создать новую операцию")
    public BaseResponse<FinancialOperationFullDto> makeOperation(@RequestBody(required = true) FinancialOperationBaseDto dto) {
        return service.makeOperation(dto);
    }

    @PostMapping("/search")
    @Operation(summary = "Страницчный поиск по фильтру")
    public BasePageResponse<List<FinancialOperationFullDto>> getList(@RequestBody(required = true) BasePageRequest<OperationFilter> request) {
        return service.getListOperation(request);
    }

}
