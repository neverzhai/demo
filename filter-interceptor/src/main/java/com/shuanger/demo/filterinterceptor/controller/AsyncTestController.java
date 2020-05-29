package com.shuanger.demo.filterinterceptor.controller;

import com.shuanger.demo.filterinterceptor.async.AsyncExample;
import com.shuanger.demo.filterinterceptor.async.AsyncExampleWithResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-29 11:10
 * @description: 测试spring boot 的异步调用功能
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class AsyncTestController {

    @Resource
    private AsyncExample asyncExample;

    @Resource
    private AsyncExampleWithResult asyncExampleWithResult;

    @GetMapping("/async")
    public void asyncTest() throws Exception {

        asyncExample.doTaskOne();
        asyncExample.doTaskTwo();
        asyncExample.doTaskThree();

    }

    @GetMapping("/asyncWithResult")
    public String asyncWithResult() throws Exception {

        Future<String> taskOne = asyncExampleWithResult.doTaskOne();
        Future<String> taskTwo = asyncExampleWithResult.doTaskTwo();
        Future<String> taskThree = asyncExampleWithResult.doTaskThree();

        String s = taskOne.get() + taskTwo.get() + taskThree.get();
        return s;

    }


    @GetMapping("/asyncCallable")
    public Callable<String> asyncCallable() throws Exception {
        log.info("api thread name, {}", Thread.currentThread().getName());

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("async callable----------------------");
                log.info("callable thread name, {}", Thread.currentThread().getName());
                return "test";
            }
        };

        return callable;

    }

}
