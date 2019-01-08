package com.accelerator.demo.standalone.common.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ApplicationContextHolderTest {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void getRequiredApplicationContext() throws Exception {
        Assert.assertNotNull(ApplicationContextHolder.getRequiredApplicationContext());
    }

    @Test
    public void getApplicationContext() throws Exception {
        Assert.assertNotNull(ApplicationContextHolder.getApplicationContext());
    }


}