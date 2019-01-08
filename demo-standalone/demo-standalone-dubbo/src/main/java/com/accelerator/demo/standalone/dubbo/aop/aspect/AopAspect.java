package com.accelerator.demo.standalone.dubbo.aop.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AopAspect {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public void before() {
        logger.info("aspect before------------");
    }
}
