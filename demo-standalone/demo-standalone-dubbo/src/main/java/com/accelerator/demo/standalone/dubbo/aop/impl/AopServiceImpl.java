package com.accelerator.demo.standalone.dubbo.aop.impl;

import com.accelerator.demo.standalone.dubbo.aop.api.AopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AopServiceImpl implements AopService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public String sayHello(String name) {
        logger.info("aop provider received: " + name);
        return "aop annotation: hello, " + name;
    }

}