package com.accelerator.demo.standalone.aop;

import com.accelerator.demo.standalone.aop.service.AopDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainRunner.class);

    private static final ClassPathXmlApplicationContext CONTEXT = new ClassPathXmlApplicationContext("applicationContext.xml");

    public static void main(String[] args) {
        AopDemoService aopDemoService = CONTEXT.getBean("aopDemoService", AopDemoService.class);
        String retVal = aopDemoService.doSomething();
        LOGGER.info("正常调用获取的返回值： {}", retVal);
    }

}
