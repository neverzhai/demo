package com.shuanger.wechat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-10-21 10:51
 * @description:
 */
@Data
@TableName("user_info")
public class UserInfo {

    private Long id;

    private String username;

    private String openId;

    private String mobile;

    private Boolean deleted;

    private LocalDate createdTime;

    private LocalDate modifiedTime;
}
