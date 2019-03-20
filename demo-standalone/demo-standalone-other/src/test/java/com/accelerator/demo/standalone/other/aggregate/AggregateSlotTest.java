package com.accelerator.demo.standalone.other.aggregate;

import org.junit.Test;

public class AggregateSlotTest {

    @Test
    public void test() {
        CallStat cs1 = new CallStat("a", 0, 1, 2);
        CallStat cs2 = new CallStat("b", 2, 3, 4);

        AggregateSlot<Metric, CallStat> slot = new AggregateSlot<>();

        Metric mKey1 = Metric.of(new String[]{"x"}, System.currentTimeMillis(), new String[]{});
        Metric mKey2 = Metric.of(new String[]{"y"}, System.currentTimeMillis(), new String[]{});
        slot.addToSlot(mKey1, cs1);
        slot.addToSlot(mKey1, cs2);


    }


}
