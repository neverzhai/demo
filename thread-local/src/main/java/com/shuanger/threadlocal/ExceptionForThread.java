package com.shuanger.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-04-08 17:33
 * @description: 为指定线程设置异常处理器
 */
@Slf4j
public class ExceptionForThread implements Runnable {

    @Override
    public void run() {
        throw  new RuntimeException("子线程异常了");
    }

    /**
     * 模拟子线程发生异常
     *
     * @throws InterruptedException
     */
    private static void exceptionThread() throws InterruptedException {
        Thread thread = new Thread(new ExceptionInChildThread());
        thread.setUncaughtExceptionHandler(new CustomThreadUncaughtExceptionHandler());
        thread.start();

        TimeUnit.MILLISECONDS.sleep(200L);
        new Thread(new ExceptionInChildThread()).start();
        TimeUnit.MILLISECONDS.sleep(200L);
        new Thread(new ExceptionInChildThread()).start();
        TimeUnit.MILLISECONDS.sleep(200L);
        new Thread(new ExceptionInChildThread()).start();
        TimeUnit.MILLISECONDS.sleep(200L);
    }

    public static void main(String[] args) {
//        Thread.setDefaultUncaughtExceptionHandler(new CustomThreadUncaughtExceptionHandler());
        try {
            exceptionThread();
        } catch (InterruptedException e) {
            log.info("捕获异常: {}", e.getMessage());
        }
    }
}
