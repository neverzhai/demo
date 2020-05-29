package com.shuanger.demo.filterinterceptor.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-29 11:34
 * @description: 异步调用例子
 */
@Slf4j
@Component
public class AsyncExample {

    public static Random random =new Random();

    @Async
    public void doTaskOne() throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("begin the task one, start time: {}");
        log.info("begin the task one, start time: {}", start);

        Thread.sleep(random.nextInt(10));

        long end = System.currentTimeMillis();
        log.info("finish the task one, end time: {}", end);
        log.info("task one total time：" + (end - start) + "ms");
    }

    @Async
    public void doTaskTwo() throws Exception {
        long start = System.currentTimeMillis();
        log.info("begin the task two, start time: {}", start);

        Thread.sleep(random.nextInt(100));

        long end = System.currentTimeMillis();
        log.info("finish the task two, end time: {}", end);
        log.info("task two total time：" + (end - start) + "ms");

    }

    @Async
    public void doTaskThree() throws Exception {
        long start = System.currentTimeMillis();
        log.info("begin the task three, start time: {}", start);

        Thread.sleep(random.nextInt(10));

        long end = System.currentTimeMillis();
        log.info("finish the task three, end time: {}", end);
        log.info("task three total time：" + (end - start) + "ms");
    }
}
