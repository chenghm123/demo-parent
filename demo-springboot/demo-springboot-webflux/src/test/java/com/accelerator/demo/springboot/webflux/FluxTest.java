package com.accelerator.demo.springboot.webflux;

import com.accelerator.demo.springboot.webflux.reactor.TestSubscriber;
import org.junit.Test;
import reactor.core.publisher.Flux;

public class FluxTest {

    @Test
    public void test() {
        Flux<Integer> ints3 = Flux.range(1, 4);
        ints3.subscribe(new TestSubscriber());
    }

}
