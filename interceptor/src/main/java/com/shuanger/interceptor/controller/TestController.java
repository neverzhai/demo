package com.shuanger.interceptor.controller;

import com.shuanger.interceptor.request.TestRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-28 17:32
 * @description:
 */
@RestController
@RequestMapping("/interceptor")
public class TestController {

    @PostMapping("/test")
    public String test(@RequestBody TestRequest request) {

        return request.getCode();
    }
}
