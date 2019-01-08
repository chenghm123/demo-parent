package com.accelerator.demo.standalone.mockito.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private String name = "test";

    private int age = 10;

    public String getName() {
        logger.info("User.getName() 被调用！");
        return name;
    }

    public int getAge() {
        logger.info("User.getAge() 被调用！");
        return age;
    }
}
