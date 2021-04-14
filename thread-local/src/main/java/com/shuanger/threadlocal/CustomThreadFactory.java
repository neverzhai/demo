package com.shuanger.threadlocal;

import java.util.concurrent.ThreadFactory;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-04-08 17:37
 * @description:
 */
public class CustomThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return null;

    }
}
