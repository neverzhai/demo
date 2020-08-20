package com.shuanger.javadate.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-20 11:53
 * @description:
 */
@Data
public class CreatUserInfoRequest {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "手机号不能为空")
    private String mobile;
}
