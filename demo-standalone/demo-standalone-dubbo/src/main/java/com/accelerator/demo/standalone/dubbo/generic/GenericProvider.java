package com.accelerator.demo.standalone.dubbo.generic;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GenericProvider {

    public static void main(String[] args) throws Exception {
        String[] configLocations = new String[]{"classpath:common.xml", "classpath:generic/generic-provider.xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        context.start();
        Thread.sleep(Long.MAX_VALUE);
    }

}
