package com.shuanger.democommon.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-08-20 11:42
 * @description: 用户信息表
 */
@Data
public class UserInfo {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String mobile;

    private Integer deleted;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;
}
