package com.accelerator.demo.standalone.javase.thread;

public class VolatileDemo2 {

    public static volatile int count = 0;

    public static void increase() {
        count++;
        System.out.println(count);
    }

    private static final int THREADS_COUNT = 20;

    /**
     * 需要使用-server模式运行
     * jdk64位默认server模式 32位需要手动指定
     */
    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(count);
    }

}
