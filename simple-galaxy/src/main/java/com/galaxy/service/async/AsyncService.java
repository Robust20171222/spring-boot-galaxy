package com.galaxy.service.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {

    @Scheduled(fixedRate = 5000)
    @Async("threadPoolExecutor")
    public void createUserWithThreadPoolExecutor(){
        log.info("Currently Executing thread name - " + Thread.currentThread().getName());
        log.info("User created with thread pool executor");
    }

    @Scheduled(fixedRate = 5000)
    @Async("ConcurrentTaskExecutor")
    public void createUserWithConcurrentExecutor(){
        log.info("Currently Executing thread name - " + Thread.currentThread().getName());
        log.info("User created with concurrent task executor");
    }
}
