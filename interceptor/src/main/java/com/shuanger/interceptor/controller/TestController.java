package com.shuanger.interceptor.controller;

import com.shuanger.interceptor.request.TestRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public Callable<String> handleTestRequest () {

        log.info("controller#handler called. Thread: " +
                Thread.currentThread()
                        .getName());

        Callable<String> callable = new Callable<String>() {
            public String call () throws Exception {
                log.info("controller#async task started. Thread: " +
                        Thread.currentThread()
                                .getName());
                Thread.sleep(300);
                log.info("controller#async task finished");
                return "async result";
            }
        };

        log.info("controller#handler finished");
        return callable;
    }
}
