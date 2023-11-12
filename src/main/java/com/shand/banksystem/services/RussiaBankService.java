package com.shand.banksystem.services;

import com.shand.banksystem.dto.CurrencyRateDto;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.model.CurrencyRate;

import java.util.List;

public interface RussiaBankService {
    void updateLocalCurrency();

    BaseResponse<List<CurrencyRateDto>> getCurrencyRateList();

    CurrencyRate findRateByName(String currency);
}
