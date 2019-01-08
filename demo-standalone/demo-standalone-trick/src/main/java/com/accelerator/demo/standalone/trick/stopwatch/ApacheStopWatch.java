package com.accelerator.demo.standalone.trick.stopwatch;

import org.apache.commons.lang3.time.StopWatch;

public class ApacheStopWatch {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(1000);
        stopWatch.stop();

        System.out.println(stopWatch.getTime());
    }

}
