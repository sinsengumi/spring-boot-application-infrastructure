package com.example.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SampleTask2 {

    @Scheduled(cron = "*/3 * * * * *")
    public void task2_1() {
        log.info("Do batch.. {}", new Date());
    }

    @Scheduled(cron = "*/15 * * * * *")
    public void task2_2() {
        log.info("Do batch.. {}", new Date());
    }
}
