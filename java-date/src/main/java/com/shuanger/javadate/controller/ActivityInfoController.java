package com.shuanger.javadate.controller;

import com.shuanger.javadate.domain.ActivityInfo;
import com.shuanger.javadate.domain.UserInfo;
import com.shuanger.javadate.requests.CreateActivityRequest;
import com.shuanger.javadate.requests.QueryByIdRequest;
import com.shuanger.javadate.requests.UpdateUserInfoRequest;
import com.shuanger.javadate.service.ActivityInfoService;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-19 11:43
 * @description: 活动信息controller
 */
@Slf4j
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

    @RequestMapping("/uploadFile")
    public String uploadFile(@RequestParam(name = "file", required = false) MultipartFile file) {
        ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
        String path = defaultClassLoader.getResource("c").getPath();


        log.info("path: {}", path);

        return path;
    }
}
