package com.shand.banksystem.soap.russia.client;

import com.shand.banksystem.soap.russia.client.gen.GetCursDynamicResponse;
import com.shand.banksystem.soap.russia.client.gen.GetCursOnDateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DailyInfoBankClientTest {
    @Autowired
    DailyInfoBankClient client;

    @Test
    void getCursOnDate_successful() {
        GetCursOnDateResponse cursOnDate = client.getCursOnDate(ZonedDateTime.now());
        assertNotNull(cursOnDate);
    }

    @Test
    void getCursDynamic_successful() {
        GetCursDynamicResponse cursDynamic = client.getCursDynamic(ZonedDateTime.now().minusDays(10),
                ZonedDateTime.now().minusDays(2), "R01235");
        assertNotNull(cursDynamic);
    }

    @Test
    void getCursDynamic_newYear() {
        ZonedDateTime time = ZonedDateTime.of(2022, 1, 1, 5, 0, 0, 0, ZoneId.systemDefault());
        GetCursDynamicResponse cursDynamic = client.getCursDynamic(
                time.minusDays(2),
                time.plusDays(10), "R01235");
        assertNotNull(cursDynamic);
        System.out.println(cursDynamic);
    }

}