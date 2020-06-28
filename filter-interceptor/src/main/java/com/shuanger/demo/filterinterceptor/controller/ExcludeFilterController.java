package com.shuanger.demo.filterinterceptor.controller;

import com.shuanger.demo.filterinterceptor.request.TestRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-28 17:30
 * @description:
 */
@RestController
@RequestMapping("/exclude/filter")
public class ExcludeFilterController {

    @PostMapping("/test")
    public String test(@RequestBody TestRequest request) {
        return request.getCode();
    }
}
