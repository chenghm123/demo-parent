package com.accelerator.demo.standalone.trick.stopwatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

@Slf4j
public class SpringStopWatch {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("one task");
        Thread.sleep(1000);
        stopWatch.stop();

        stopWatch.start("two task");
        Thread.sleep(500);
        stopWatch.stop();

        System.out.println("============prettyPrint()=============");
        System.out.println(stopWatch.prettyPrint());
        System.out.println("============toString()================");
        System.out.println(stopWatch.toString());
        System.out.println("============getTaskInfo()=============");
        for (TaskInfo taskInfo : stopWatch.getTaskInfo()) {
            System.out.printf("%s:%sms/%ss\n",
                    taskInfo.getTaskName(),
                    taskInfo.getTimeMillis(),
                    taskInfo.getTimeSeconds()
            );
        }

        String s = stopWatch.currentTaskName();
        boolean running = stopWatch.isRunning();
        String s1 = stopWatch.shortSummary();
        System.out.println("");
    }

}
