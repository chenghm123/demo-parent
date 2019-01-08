package com.accelerator.demo.standalone.dubbo.generic;

import com.accelerator.demo.standalone.dubbo.generic.api.IUserService;
import com.accelerator.demo.standalone.dubbo.generic.api.IUserService.Params;
import com.accelerator.demo.standalone.dubbo.generic.api.IUserService.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GenericConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericConsumer.class);

    public static void main(String[] args) throws Exception {
        String[] configLocations = new String[]{"classpath:common.xml", "classpath:generic/generic-consumer.xml"};
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
        context.start();
        IUserService userService = (IUserService) context.getBean("userService");
        User user = userService.get(new Params("a=b"));
        LOGGER.info("user:{}", user);
    }
}
