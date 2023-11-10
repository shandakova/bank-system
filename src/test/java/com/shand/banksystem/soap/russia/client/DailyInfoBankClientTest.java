package com.shand.banksystem.soap.russia.client;

import com.shand.banksystem.soap.russia.client.gen.GetCursOnDateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}