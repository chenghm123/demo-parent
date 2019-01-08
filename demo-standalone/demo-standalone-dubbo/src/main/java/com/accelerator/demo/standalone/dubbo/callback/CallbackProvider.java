package com.accelerator.demo.standalone.dubbo.callback;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CallbackProvider {

    public static void main(String[] args) throws Exception {
        String[] configLocations = new String[]{"classpath:common.xml", "classpath:callback/callback-provider.xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        context.start();
        Thread.sleep(Long.MAX_VALUE);
    }

}
