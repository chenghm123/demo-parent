package com.accelerator.demo.standalone.dubbo.generic.api;

public interface IService<P, V> {

    V get(P params);

}
