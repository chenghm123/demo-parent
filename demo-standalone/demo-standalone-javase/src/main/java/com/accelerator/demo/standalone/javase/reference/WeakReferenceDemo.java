package com.accelerator.demo.standalone.javase.reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {

    private static Logger logger = LoggerFactory.getLogger(WeakReferenceDemo.class);


    public static void main(String[] args) {
        logger.info("开始弱引用测试");
        Runtime runtime = Runtime.getRuntime();
        logger.info("未创建对象A空闲内存：{}", runtime.freeMemory());
        A a = new A();
        WeakReference<A> sr = new WeakReference<>(a);
        logger.info("创建对象A后空闲内存：{}", runtime.freeMemory());
        a = null;
        runtime.gc();
        logger.info("回收对象A后空闲内存：{}", runtime.freeMemory());
        a = sr.get();
        logger.info("A对象是否存在:" + (a != null));
        logger.info("成功回收对象A后空闲内存：{}", runtime.freeMemory());
        logger.info("结束弱引用测试");
    }
}

