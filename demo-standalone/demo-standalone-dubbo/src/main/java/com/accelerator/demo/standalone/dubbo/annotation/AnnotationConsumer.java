package com.accelerator.demo.standalone.dubbo.annotation;

import com.accelerator.demo.standalone.dubbo.annotation.action.AnnotationAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationConsumer.class);

    public static void main(String[] args) throws Exception {
        String[] configLocations = new String[]{"classpath:common.xml", "classpath:annotation/aop-consumer.xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        context.start();
        AnnotationAction annotationAction = (AnnotationAction) context.getBean("annotationAction");
        String hello = annotationAction.doSayHello("world");
        LOGGER.info("result:{}", hello);
    }

}
