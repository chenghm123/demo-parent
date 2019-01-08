package com.accelerator.demo.standalone.javase.reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class A {

    private static Logger logger = LoggerFactory.getLogger(A.class);

    private int[] a;

    A() {
        a = new int[100000000];
    }

    @Override
    protected void finalize() throws Throwable {
        logger.info("对象A被回收！");
        super.finalize();
    }

}
