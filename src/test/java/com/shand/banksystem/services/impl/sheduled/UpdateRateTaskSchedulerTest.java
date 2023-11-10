package com.shand.banksystem.services.impl.sheduled;

import com.shand.banksystem.services.RussiaBankService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UpdateRateTaskSchedulerTest {
    @SpyBean
    UpdateRateTaskScheduler scheduler;

    @SpyBean
    RussiaBankService bankService;

    @Test
    void scheduleTask_runAfterOneMinute() throws InterruptedException, IllegalAccessException, NoSuchFieldException {
        LocalTime time = LocalTime.now().plusMinutes(2);
        setTime(time.toString());
        scheduler.scheduleTask();
        verify(bankService, timeout(3 * 60_000).only()).updateLocalCurrency();
    }

    @Test
    void scheduleTask_notFallByWrongTime() throws NoSuchFieldException, IllegalAccessException {
        setTime("wrong:time");
        assertDoesNotThrow(() -> scheduler.scheduleTask());
    }

    private void setTime(String time) throws NoSuchFieldException, IllegalAccessException {
        Field times = scheduler.getClass().getDeclaredField("times");
        times.setAccessible(true);
        times.set(scheduler, List.of(time));
    }
}