package com.shuanger.demo.filterinterceptor.controller;

import com.shuanger.demo.filterinterceptor.async.AsyncExample;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-29 11:10
 * @description: 测试spring boot 的异步调用功能
 */
@RestController
@RequestMapping("/test")
public class AsyncTestController {

    @Resource
    private AsyncExample asyncExample;

    @GetMapping("/async")
    public String asyncTest() throws Exception {

        asyncExample.doTaskOne();
        asyncExample.doTaskTwo();
        asyncExample.doTaskThree();

        return "hello spring async";
    }
}
