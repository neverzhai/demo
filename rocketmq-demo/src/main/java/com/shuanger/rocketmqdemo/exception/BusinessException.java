package com.shuanger.rocketmqdemo.exception;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-06 14:35
 * @description:
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
