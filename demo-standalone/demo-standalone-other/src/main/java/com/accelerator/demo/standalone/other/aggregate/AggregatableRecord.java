package com.accelerator.demo.standalone.other.aggregate;

import java.io.Serializable;

public interface AggregatableRecord<T extends AggregatableRecord> extends AggregateSupport<T>, BinarySupport, Serializable {
}
