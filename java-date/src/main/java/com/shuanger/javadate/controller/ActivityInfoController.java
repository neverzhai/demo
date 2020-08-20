package com.shuanger.javadate.controller;

import com.shuanger.javadate.domain.ActivityInfo;
import com.shuanger.javadate.domain.UserInfo;
import com.shuanger.javadate.requests.CreateActivityRequest;
import com.shuanger.javadate.requests.QueryByIdRequest;
import com.shuanger.javadate.requests.UpdateUserInfoRequest;
import com.shuanger.javadate.service.ActivityInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-19 11:43
 * @description: 活动信息controller
 */
@RestController
@RequestMapping("/date/activity")
public class ActivityInfoController {

    @Resource
    private ActivityInfoService activityInfoService;

    @RequestMapping("/save")
    public boolean createActivity(@RequestBody @Validated CreateActivityRequest request) {

        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(request, activityInfo);

        boolean sucess = activityInfoService.save(activityInfo);
        return sucess;
    }

    @RequestMapping("/queryById")
    public ActivityInfo queryById(@RequestBody @Validated QueryByIdRequest request) {

        ActivityInfo activityInfo = activityInfoService.getById(request.getId());

        return activityInfo;
    }

    @RequestMapping("/update")
    public boolean updateActivityInfo(@RequestBody @Validated UpdateUserInfoRequest request) {

        ActivityInfo activityInfo = activityInfoService.getById(request.getId());
        Assert.notNull(activityInfo, "编辑的信息不存在");

        BeanUtils.copyProperties(request, activityInfo);
        boolean success = activityInfoService.updateById(activityInfo);

        return success;
    }
}
