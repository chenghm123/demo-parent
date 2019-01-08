package com.accelerator.demo.standalone.dubbo.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopProvider {

    public static void main(String[] args) throws Exception {
        String[] configLocations = new String[]{"classpath:common.xml", "classpath:aop/aop-provider.xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        context.start();
        Thread.sleep(Long.MAX_VALUE);
    }

}
