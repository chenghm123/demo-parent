package com.accelerator.demo.standalone.dubbo.aop;

import com.accelerator.demo.standalone.dubbo.aop.action.AopAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopConsumer.class);

    public static void main(String[] args) throws Exception {
        String[] configLocations = new String[]{"classpath:common.xml", "classpath:aop/aop-consumer.xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        context.start();
        final AopAction aopAction = (AopAction) context.getBean("aopAction");
        String hello = aopAction.doSayHello("world");
        LOGGER.info("result:{}", hello);
    }

}
