package com.accelerator.demo.standalone.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventBusDemo {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register((EventBusListener) message -> {
            log.info("收到消息！{}", message);
        });

        eventBus.post("");
    }

}

interface EventBusListener {

    @Subscribe
    void consumer(String message);

}
