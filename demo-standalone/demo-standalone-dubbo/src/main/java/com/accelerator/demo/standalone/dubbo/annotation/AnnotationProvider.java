package com.accelerator.demo.standalone.dubbo.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationProvider {

    public static void main(String[] args) throws Exception {
        String[] configLocations = new String[]{"classpath:common.xml", "classpath:annotation/aop-provider.xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        context.start();
        Thread.sleep(Long.MAX_VALUE);
    }

}
