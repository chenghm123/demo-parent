package com.accelerator.demo.standalone.other.aggregate;

import org.junit.Test;

import java.io.Serializable;

public class AggregateSlotTest {

    public static class AggregateSlotValue implements AggregateSupport<AggregateSlotValue> {

        @Override
        public void aggregateFrom(AggregateSlotValue value) {

        }

    }

    @Test
    public void test() {
        AggregateSlot<>
    }



}
