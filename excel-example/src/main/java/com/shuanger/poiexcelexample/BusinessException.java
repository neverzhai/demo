package com.shuanger.poiexcelexample;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-05 18:05
 * @description:  自定义异常
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
