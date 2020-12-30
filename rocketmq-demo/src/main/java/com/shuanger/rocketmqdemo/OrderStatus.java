package com.shuanger.rocketmqdemo;

import lombok.Getter;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-30 16:35
 * @description:
 */
public enum OrderStatus {

//    0-未支付、1-已支付、2-已取消(终态)、3-已完成(终态)、4-支付失败

    UNPAYED(0, "未支付"),
    PAYED(1, "已支付"),
    CANCEL(2, "未取消"),
    FINISHED(3, "已完成"),
    FAILED(4, "支付失败"),
    ;


    OrderStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Getter
    private Integer code;

    @Getter
    private String  name;
}
