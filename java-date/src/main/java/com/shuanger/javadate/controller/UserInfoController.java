package com.shuanger.javadate.controller;

import com.shuanger.javadate.domain.UserInfo;
import com.shuanger.javadate.requests.CreatUserInfoRequest;
import com.shuanger.javadate.requests.QueryByIdRequest;
import com.shuanger.javadate.requests.UpdateUserInfoRequest;
import com.shuanger.javadate.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-20 11:45
 * @description: 用户信息controller
 */
@Slf4j
@RestController
@RequestMapping("/date/user")
public class UserInfoController {


    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("/save")
    public boolean createUserInfo(@RequestBody @Validated CreatUserInfoRequest request) {

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(request, userInfo);

        boolean sucess = userInfoService.save(userInfo);
        return sucess;
    }

    @RequestMapping("/queryById")
    public UserInfo queryById(@RequestBody @Validated QueryByIdRequest request) {

        UserInfo userInfo = userInfoService.getById(request.getId());

        return userInfo;
    }

    @RequestMapping("/update1")
    public boolean updateUserInfo1(@RequestBody @Validated UpdateUserInfoRequest request) {

        UserInfo userInfo = userInfoService.getById(request.getId());
        Assert.notNull(userInfo, "编辑的信息不存在");

        BeanUtils.copyProperties(request, userInfo);
        boolean success = userInfoService.updateById(userInfo);

        return success;
    }

    @RequestMapping("/update")
    public boolean updateUserInfo(@RequestBody @Validated UpdateUserInfoRequest request) {

        UserInfo userInfo = new UserInfo();

        BeanUtils.copyProperties(request, userInfo);
        boolean success = userInfoService.updateById(userInfo);

        return success;
    }
}
