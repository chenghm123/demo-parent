package com.accelerator.demo.standalone.cache.service;

import com.accelerator.demo.standalone.cache.model.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    @Test
    public void test01setUser() throws Exception {
        userService.setUser(new User("accelerator", "accelerator01"));
    }

    @Test
    public void test02getUser() throws Exception {
        logger.info(userService.getUser("accelerator").toString());
    }

}