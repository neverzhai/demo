package com.shuanger.rocketmqdemo.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-11-23 11:11
 * @description:
 */
@Data
public class SyncUserRequest {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态
     */
    private String accountStatus;

    /**
     * 0 未知 1 男 2 女
     */
    private String sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 注册时间
     */
    private LocalDateTime registeredTime;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 身份证号
     */
    private String idCard;

}
