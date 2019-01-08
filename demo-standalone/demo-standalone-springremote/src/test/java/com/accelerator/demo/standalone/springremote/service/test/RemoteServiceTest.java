package com.accelerator.demo.standalone.springremote.service.test;

import com.accelerator.demo.standalone.springremote.service.RemoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Map.Entry;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RemoteServiceTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RemoteService remoteService;

    @Test
    public void test() {
        Map<String, Object> infoMap = remoteService.getInfoMap();
        for (Entry<String, Object> infoEntry : infoMap.entrySet()) {
            logger.info("=========" + infoEntry.getKey() + infoEntry.getValue() + "=========");
        }
    }

}
