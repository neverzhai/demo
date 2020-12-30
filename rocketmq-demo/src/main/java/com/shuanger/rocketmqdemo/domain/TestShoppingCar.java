package com.shuanger.rocketmqdemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-30 15:10
 * @description:
 */
@Data
public class TestShoppingCar {

    /**
     * 主键ID自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    private String orderId;

    private String userId;

    private BigDecimal paymentAmount;

    private Integer orderStatus;
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
