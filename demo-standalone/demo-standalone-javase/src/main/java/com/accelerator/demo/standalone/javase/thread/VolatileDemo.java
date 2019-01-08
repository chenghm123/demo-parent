package com.accelerator.demo.standalone.javase.thread;

public class VolatileDemo {

    private static volatile boolean stop = false;
    // private static boolean stop = false;


    /**
     * 需要使用-server模式运行
     * jdk64位默认server模式 32位需要手动指定
     */
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (!stop) {
                    i++;
                    //  线程中如果是用同步IO、会改变JMM内存模型
                    // http://blog.csdn.net/qq_38724295/article/details/72801624
                    // http://blog.csdn.net/zq602316498/article/details/40398247
                    // 具体可以查看VolatileDemo2 这个问题非常原理性
                    /*
                     * public void println(String x) {
                     *      synchronized (this) {
                     *          print(x);
                     *          newLine();
                     *      }
                     * }
                     */
                    System.out.println("hello");
                }
            }
        });
        t.start();
        // Thread.sleep(1000);
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        stop = true;
        System.out.println("Stoped Thread");
    }
}
