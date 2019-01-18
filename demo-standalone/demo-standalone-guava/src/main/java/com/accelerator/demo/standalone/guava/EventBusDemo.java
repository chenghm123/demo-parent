package com.accelerator.demo.standalone.guava;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class EventBusDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 所有调用线程共用一个ExecutorService执行 并发模式取决于Executor对象
        AsyncEventBus asyncEventBus = new AsyncEventBus(executorService);

        // 每个调用线程一个队列 不同调用线程的任务并发执行 同一个调用线程并行
        EventBus eventBus = new EventBus();

        EventBusDemo.execute(eventBus);
        executorService.shutdown();
    }

    public static void execute(EventBus eventBus) {
        eventBus.register(new EventBusListener() {
            private AtomicInteger count = new AtomicInteger();

            @Override
            public void consumer(Object message) {
                log.info("{}==>收到消息！Object：{}",
                        count.incrementAndGet(),
                        message);
            }
        });
        for (int i = 0; i < 100; i++) {
            int message = i;
            new Thread(() -> {
                eventBus.post(message);
            }).start();
        }
    }

}


interface EventBusListener {

    /**
     * AllowConcurrentEvents是否保证Listener对象线程安全
     * 例如成员变量
     */
    @Subscribe @AllowConcurrentEvents
    void consumer(Object message);

}
