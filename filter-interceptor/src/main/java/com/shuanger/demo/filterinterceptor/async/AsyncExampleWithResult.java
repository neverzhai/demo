package com.shuanger.demo.filterinterceptor.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-29 18:25
 * @description:
 */
@Slf4j
@Component
public class AsyncExampleWithResult {
    public static Random random =new Random();

    @Async
    public Future<String> doTaskOne() throws Exception {
        long start = System.currentTimeMillis();
        log.info("begin the task one, start time: {}", start);

        Thread.sleep(random.nextInt(10));

        long end = System.currentTimeMillis();
        log.info("task one total time：" + (end - start) + "ms");

        return new AsyncResult<>("finish task one");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        long start = System.currentTimeMillis();
        log.info("begin the task two, start time: {}", start);

        Thread.sleep(random.nextInt(100));

        long end = System.currentTimeMillis();
        log.info("task two total time：" + (end - start) + "ms");

        return new AsyncResult<>("finish task two");

    }

    @Async
    public Future<String> doTaskThree() throws Exception {
        long start = System.currentTimeMillis();
        log.info("begin the task three, start time: {}", start);

        Thread.sleep(random.nextInt(10));

        long end = System.currentTimeMillis();

        log.info("task three total time：" + (end - start) + "ms");

        return new AsyncResult<>("finish task two");
    }
}
