package com.accelerator.demo.springboot.webflux.reactor;

import reactor.core.publisher.BaseSubscriber;

public class TestSubscriber extends BaseSubscriber<Integer> {

    @Override
    protected void hookOnNext(Integer value) {
        System.out.println(value);
    }


}
