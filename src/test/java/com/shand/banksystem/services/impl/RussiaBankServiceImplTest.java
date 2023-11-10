package com.shand.banksystem.services.impl;

import com.shand.banksystem.services.RussiaBankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RussiaBankServiceImplTest {
    @Autowired
    RussiaBankService service;

    @Test
    void updateLocalCurrency_containTwoCurrencyInBase() {
        List<String> names = List.of("USD", "EUR");
        Map<String, BigDecimal> currencyMapByNames = service.getCurrencyMapByNames(names);
        assertEquals(0, currencyMapByNames.size());

        service.updateLocalCurrency();
        currencyMapByNames = service.getCurrencyMapByNames(names);
        assertEquals(2, currencyMapByNames.size());
        for (String currency : names) {
            assertTrue(currencyMapByNames.containsKey(currency));
            assertNotNull(currencyMapByNames.get(currency));
        }

        service.updateLocalCurrency();
        currencyMapByNames = service.getCurrencyMapByNames(names);
        assertEquals(2, currencyMapByNames.size());
    }
}