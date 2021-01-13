package com.shuanger.snowworkiddemo.exception;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-13 14:42
 * @description:
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
