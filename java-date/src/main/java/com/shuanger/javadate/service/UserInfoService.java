package com.shuanger.javadate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shuanger.javadate.domain.UserInfo;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-20 11:44
 * @description:
 */
public interface UserInfoService extends IService<UserInfo> {
    Workbook exportList();

    Workbook exportAsyncList(HttpServletResponse response);
}
