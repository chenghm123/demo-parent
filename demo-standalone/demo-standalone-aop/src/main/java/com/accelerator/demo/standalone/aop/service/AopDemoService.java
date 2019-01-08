package com.accelerator.demo.standalone.aop.service;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Service
public class AopDemoService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public String doSomething() {
        logger.info("doSomething()正被调用！！");
        ((AopDemoService) AopContext.currentProxy()).innerMethod();
        int i = RandomUtils.nextInt(1, 10);
        if (i > 5)
            throw new RuntimeException("巧合性异常！");
        return "returnStr";
    }

    public void innerMethod() {
        logger.info("innerMethod！");
    }

}
