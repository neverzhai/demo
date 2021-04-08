package com.shuanger.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-04-08 17:20
 * @description:
 */
@Slf4j
public class CustomThreadUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.info("捕获异常线程: {}, exception: {}", t.getName(),e.getMessage());
    }
}
