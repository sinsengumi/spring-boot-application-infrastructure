package com.example.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SampleTask1 {

    @Scheduled(cron = "*/5 * * * * *")
    public void task1_1() {
        log.info("Do batch.. {}", new Date());
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void task1_2() {
        log.info("Do batch.. {}", new Date());
    }
}
