package com.shuanger.demo.filterinterceptor.controller;

import com.shuanger.demo.filterinterceptor.request.TestRequest;
import com.shuanger.demo.filterinterceptor.response.BusinessException;
import com.shuanger.demo.filterinterceptor.response.RespCode;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-28 17:32
 * @description:
 */
@RestController
@RequestMapping("/include/filter")
public class IncludeFilterController {

    @PostMapping("/test")
    public String test(@RequestBody TestRequest request) {

        if(true) {
            throw new BusinessException(RespCode.FAILED);
        }
        return request.getCode();
    }
}
