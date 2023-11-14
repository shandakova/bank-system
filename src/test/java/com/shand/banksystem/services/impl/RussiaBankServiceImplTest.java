package com.shand.banksystem.services.impl;

import com.shand.banksystem.dto.CurrencyRateDto;
import com.shand.banksystem.dto.base.BaseResponse;
import com.shand.banksystem.model.CurrencyRate;
import com.shand.banksystem.repositories.CurrencyRateRepository;
import com.shand.banksystem.services.RussiaBankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RussiaBankServiceImplTest {
    @Autowired
    RussiaBankService service;

    @Autowired
    CurrencyRateRepository rateRepository;

    @BeforeEach
    void clearBase() {
        rateRepository.deleteAll();
    }

    @Test
    void getCurrencyRateList_containTwoCurrencyInBase() {
        BaseResponse<List<CurrencyRateDto>> currencyRateList = service.getCurrencyRateList();
        assertTrue(currencyRateList.isSuccess());
        assertNotNull(currencyRateList.getValue());
        assertEquals(0, currencyRateList.getValue().size());

        service.updateLocalCurrency();
        currencyRateList = service.getCurrencyRateList();
        assertNotNull(currencyRateList.getValue());
        assertEquals(2, currencyRateList.getValue().size());
    }

    @Test
    void findCurrencyRate_UsdInBase() {
        service.updateLocalCurrency();
        CurrencyRate usd = service.findRateByName("USD");
        assertNotNull(usd);
    }

    @Test
    void getCurseForPeriod() {
        Map<ZonedDateTime, BigDecimal> usd = service.getCurseForPeriod(ZonedDateTime.now().minusDays(10), ZonedDateTime.now(), "USD");
        assertTrue(usd.size() > 0);
        System.out.println(usd);
    }
}