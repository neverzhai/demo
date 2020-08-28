package com.shuanger.interceptor.controller;

import com.shuanger.interceptor.request.TestRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Callable;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-28 17:32
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/interceptor")
public class TestController {

    @PostMapping("/test")
    public String test(@RequestBody TestRequest request) {

        return request.getCode();
    }


    @RequestMapping("/async/test")
    @ResponseBody
    public Callable<String> handleTestRequest1 (HttpServletResponse response) {

        log.info("controller#handler called. Thread: " +
                Thread.currentThread()
                        .getName());

        Callable<String> callable = new Callable<String>() {
            public String call () throws Exception {
                log.info("controller#async task started. Thread: " +
                        Thread.currentThread() .getName());

                Thread.sleep(300);

                log.info("controller#async task finished");
                return "async result";
            }
        };

        log.info("controller#handler finished");
        return callable;
    }


    @RequestMapping("/test/test")
    public DeferredResult<String> handleTestRequest() {

        log.info("handler started");
        final DeferredResult<String> deferredResult = new DeferredResult<>();

        new Thread(new Runnable() {
            public void run() {
            log.info("async task started");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            log.info("async task finished");
                deferredResult.setResult("test async result");
            }
        }).start();

        log.info("handler finished");
        return deferredResult;
    }
}
