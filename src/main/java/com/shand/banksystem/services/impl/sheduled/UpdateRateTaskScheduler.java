package com.shand.banksystem.services.impl.sheduled;

import com.shand.banksystem.services.RussiaBankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Component
public class UpdateRateTaskScheduler {
    private final RussiaBankService bankService;

    private final TaskScheduler taskScheduler;

    @Value("#{'${update-time.list}'.split(',')}")
    private List<String> times;

    private final String EVERYDAY_CRON_PATTERN = "0 %d %d * * *";

    public UpdateRateTaskScheduler(RussiaBankService bankService, TaskScheduler taskScheduler) {
        this.bankService = bankService;
        this.taskScheduler = taskScheduler;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void scheduleTask() {
        new HashSet<>(times).forEach(time -> {
            try {
                LocalTime localTime = LocalTime.parse(time);
                String cron = String.format(EVERYDAY_CRON_PATTERN,
                        localTime.getMinute(), localTime.getHour());
                taskScheduler.schedule(bankService::updateLocalCurrency, new CronTrigger(cron));
            } catch (DateTimeParseException e) {
                log.info("Failed to read time: " + time);
            }
        });
    }
}
