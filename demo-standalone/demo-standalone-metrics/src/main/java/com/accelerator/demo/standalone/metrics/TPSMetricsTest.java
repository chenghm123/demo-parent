package com.accelerator.demo.standalone.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TPSMetricsTest {

    private static MetricRegistry metrics = new MetricRegistry();

    static {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    private static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 2; i++) {
            // metrics.meter("a/admin").mark(); // 统计tps
            Timer.Context time = metrics.timer("b/admin").time();
            if (count.getAndAdd(1) < 1)
                Thread.sleep(10000);
            time.stop();
        }
        Thread.sleep(Long.MAX_VALUE);
    }

}
