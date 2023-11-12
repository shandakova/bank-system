package com.shand.banksystem.services;

import com.shand.banksystem.dto.FinancialOperationBaseDto;
import com.shand.banksystem.dto.FinancialOperationFullDto;
import com.shand.banksystem.dto.OperationFilter;
import com.shand.banksystem.dto.base.BasePageRequest;
import com.shand.banksystem.dto.base.BasePageResponse;
import com.shand.banksystem.dto.base.BaseResponse;

import java.util.List;

public interface FinancialOperationService {
    BaseResponse<FinancialOperationFullDto> makeOperation(FinancialOperationBaseDto dto);

    BasePageResponse<List<FinancialOperationFullDto>> getListOperation(BasePageRequest<OperationFilter> request);
}
