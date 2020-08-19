package com.shuanger.javadate.controller;

import com.shuanger.javadate.requests.CreateActivityRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-19 11:43
 * @description: 活动信息controller
 */
@RestController
@ResponseBody
@RequestMapping("/date/activity")
public class ActivityInfoController {


    @ResponseStatus(HttpStatus.CREATED)
    public String createActivity(@RequestBody @Validated CreateActivityRequest request) {


        return "success";
    }
}
