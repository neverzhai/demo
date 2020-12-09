package com.shuanger.mysqllockdemo.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-09 11:32
 * @description:
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}
