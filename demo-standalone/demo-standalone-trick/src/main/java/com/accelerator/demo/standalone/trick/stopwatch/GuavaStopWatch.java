package com.accelerator.demo.standalone.trick.stopwatch;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

public class GuavaStopWatch {

    public static void main(String[] args) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.start();
        Thread.sleep(1020);
        stopwatch.stop();


        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
    }

}
