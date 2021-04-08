package com.shuanger.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-04-08 17:25
 * @description: 设置全局的的ExceptionHandler
 */
@Slf4j
public class ExceptionHandlerOverview  implements Runnable{

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
        new Thread(new ExceptionInChildThread()).start();
        TimeUnit.MILLISECONDS.sleep(200L);
        new Thread(new ExceptionInChildThread()).start();
        TimeUnit.MILLISECONDS.sleep(200L);
        new Thread(new ExceptionInChildThread()).start();
        TimeUnit.MILLISECONDS.sleep(200L);
        new Thread(new ExceptionInChildThread()).start();
        TimeUnit.MILLISECONDS.sleep(200L);
    }

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new CustomThreadUncaughtExceptionHandler());
        try {
            exceptionThread();
        } catch (InterruptedException e) {
          log.info("捕获异常: {}", e.getMessage());
        }
    }


}
