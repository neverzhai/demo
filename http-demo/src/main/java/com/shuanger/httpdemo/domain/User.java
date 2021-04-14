package com.shuanger.httpdemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-04-14 15:46
 * @description:
 */
@Data
@Accessors(chain = true)
public class User {

    /**
     * 主键ID自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 0 未知 1 男 2 女
     */
    private Integer gender;

    /**
     * 地址
     */
    private String address;

    /**
     * 注册时间
     */
    private LocalDateTime registeredTime;


    /**
     * 0：未删除 1：已删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    @TableField(update = "now()")
    private LocalDateTime modifiedTime;
}
