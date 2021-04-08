package com.shuanger.threadlocal;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-04-08 16:58
 * @description:
 */
public class ThreadDemoController {

    // how to stop a thread

    public static void main(String[] args) {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i=0; i< 10000000; i++) {
                    if (this.isInterrupted()) {
                        break;
                    }
                    System.out.println(i);
                }
            }
        };

        thread1.start();

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                thread1.interrupt();
            }
        };

        thread.start();
    }

}
