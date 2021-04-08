package com.shuanger.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-04-08 17:17
 * @description: 例子: 无法在一个线程中通过try catch捕获另外一个线程的异常。
 *
 */
@Slf4j
public class ExceptionInChildThread implements Runnable{


    @Override
    public void run() {
        throw new RuntimeException("子线程发生了异常...");
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

    /**
     * 在主线程尝试通过try catch捕获异常
     */
    private static void catchInMain() {
        try {
            exceptionThread();
        } catch (Exception e) {
            //无法捕获发生在其他线程中的异常
            log.error("捕获到了异常?", e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExceptionInChildThread.catchInMain();
    }

}
