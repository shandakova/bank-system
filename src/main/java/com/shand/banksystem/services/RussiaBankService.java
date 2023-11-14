package com.shand.banksystem.services;

import com.shand.banksystem.dto.CurrencyRateDto;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.model.CurrencyRate;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RussiaBankService {
    void updateLocalCurrency();

    BaseResponse<List<CurrencyRateDto>> getCurrencyRateList();

    CurrencyRate findRateByName(String currency);

    Map<ZonedDateTime, BigDecimal> getCurseForPeriod(ZonedDateTime start, ZonedDateTime end, String currency);

    Set<String> getAllCurrency();
}
