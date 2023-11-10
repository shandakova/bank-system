package com.shand.banksystem.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RussiaBankService {
    void updateLocalCurrency();


    Map<String, BigDecimal> getCurrencyMapByNames(List<String> names);
}
