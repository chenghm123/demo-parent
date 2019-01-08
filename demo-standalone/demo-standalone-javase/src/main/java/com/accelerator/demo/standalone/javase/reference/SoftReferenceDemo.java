package com.accelerator.demo.standalone.javase.reference;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.SoftReference;
import java.util.List;

public class SoftReferenceDemo {

    private static Logger logger = LoggerFactory.getLogger(SoftReferenceDemo.class);

    public static void main(String[] args) throws InterruptedException {
        int count = 1;
        logger.info("开始软引用测试");
        Runtime runtime = Runtime.getRuntime();
        logger.info("未创建对象A空闲内存：{}", runtime.freeMemory());
        A a = new A();
        SoftReference<A> sr = new SoftReference<>(a);
        logger.info("创建对象A后空闲内存：{}", runtime.freeMemory());
        a = null;
        List<A> aList = Lists.newArrayList();
        while (sr.get() != null) {
            logger.info(String.format("第%d次回收对象A后空闲内存：%d", count, runtime.freeMemory()));
            count++;
            aList.add(new A());
            runtime.gc();
        }
        logger.info("用来占用内存List的大小：{}", aList.size());
        logger.info("成功回收对象A后空闲内存：{}", runtime.freeMemory());
        logger.info("结束软引用测试");
    }

}
