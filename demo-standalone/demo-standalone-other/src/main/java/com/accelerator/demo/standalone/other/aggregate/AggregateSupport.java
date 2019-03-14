package com.accelerator.demo.standalone.other.aggregate;

public interface AggregateSupport<T extends AggregateSupport> {
    void aggregateFrom(T value);
}
