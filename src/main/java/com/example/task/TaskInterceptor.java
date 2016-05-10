package com.example.task;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.base.Stopwatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class TaskInterceptor {

    @Value("${batch.exec.host}")
    private String batchExecHost;

    // com.example.task パッケージ（サブパッケージ含む）で、@Scheduled が付いているすべてのメソッド
    @Around("execution(* com.example.task..*.*(..)) && @annotation(org.springframework.scheduling.annotation.Scheduled)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String currentThread = Thread.currentThread().getName();

        // 実行チェック
        if (!allowedBatchExec(methodName)) {
            log.info("Skip  batch ({}): {}", currentThread, methodName);
            return null;
        }

        log.info("Begin batch ({}): {}", currentThread, methodName);

        Stopwatch stopWatch = Stopwatch.createStarted();
        try {
            return pjp.proceed();
        } catch (Exception e) {
            log.error("batch error: {}", methodName, e);
            return null;
        } finally {
            stopWatch.stop();
            log.info("End   batch ({}): {}, elapsed = {} (ms)", currentThread, methodName, stopWatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }

    private boolean allowedBatchExec(String methodName) {
        if (getHostName().equals(batchExecHost)) {
            return true;
        }

        return false;
    }

    private String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            log.error(e.getMessage(), e);
            return "Unknown";
        }
    }
}