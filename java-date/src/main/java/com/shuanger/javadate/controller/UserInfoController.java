package com.shuanger.javadate.controller;

import com.shuanger.javadate.dao.UserInfoMapper;
import com.shuanger.javadate.domain.UserInfo;
import com.shuanger.javadate.requests.CreatUserInfoRequest;
import com.shuanger.javadate.requests.QueryByIdRequest;
import com.shuanger.javadate.requests.UpdateUserInfoRequest;
import com.shuanger.javadate.service.UserInfoService;
import com.shuanger.javadate.utils.ExcelWriteUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Callable;

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

    @RequestMapping("/update")
    public boolean updateUserInfo(@RequestBody @Validated UpdateUserInfoRequest request) {

        UserInfo userInfo = userInfoService.getById(request.getId());
        Assert.notNull(userInfo, "编辑的信息不存在");

        BeanUtils.copyProperties(request, userInfo);

        boolean success = userInfoService.updateById(userInfo);

        return success;
    }

    @RequestMapping("/update1")
    public boolean updateUserInfo2(@RequestBody @Validated UpdateUserInfoRequest request) {

        UserInfo userInfo = new UserInfo();

        BeanUtils.copyProperties(request, userInfo);
        boolean success = userInfoService.updateById(userInfo);

        return success;
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        Workbook workbook = userInfoService.exportList();

        try {
            ExcelWriteUtil.export(response, workbook, "用户信息列表");
        } catch (Exception e) {
            log.error("导出用户信息列表异常: {}", e.getMessage());
        }

    }

    @RequestMapping("/async/test")
    public Callable<String> handleTestRequest1 (HttpServletResponse response) {

        Callable<String> callable = () -> {
            Workbook workbook = userInfoService.exportList();

            try {
                ExcelWriteUtil.export(response, workbook, "用户信息列表");
            } catch (Exception e) {
                log.error("异步导出用户信息列表异常: {}", e.getMessage());
            }

            return "async result";
        };
        return callable;
    }

}
