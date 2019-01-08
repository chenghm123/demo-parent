package com.accelerator.demo.standalone.elasticjob;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:simpleJob.xml"})
public class SpringSimpleJob {

    @Test
    public void simpleJob() throws Exception {
        Thread.sleep(Long.MAX_VALUE);
    }

}
