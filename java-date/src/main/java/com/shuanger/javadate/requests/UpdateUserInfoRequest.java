package com.shuanger.javadate.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-20 11:56
 * @description:
 */
@Data
public class UpdateUserInfoRequest {

    @NotNull(message = "用户id不能为空")
    private Long id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "手机号不能为空")
    private String mobile;
}
