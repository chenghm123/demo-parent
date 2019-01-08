package com.accelerator.demo.standalone.canal;

import com.alibaba.otter.canal.protocol.FlatMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CanalListenerContainerTest {

    private AtomicInteger consumeNumber = new AtomicInteger(0);

    @Test
    public void test() throws Exception {
        Map<String, CanalMessageListener> listenerMap = Collections.singletonMap(
                "canal_newvss-vender_info", flatMessages -> {
                    int times = consumeNumber.getAndIncrement();
                    log.info("=====consume start=====> times:" + times);
                    for (FlatMessage flatMessage : flatMessages) {
                        log.info(flatMessage.toString());
                    }
                    log.info("=====consume end=====> times:" + times);
                });

        CanalListenerContainer canalListenerContainer = new CanalListenerContainer();
        canalListenerContainer.setListenerMap(listenerMap);
        canalListenerContainer.setNameServer("10.0.90.178:9876;10.0.90.179:9876");
        canalListenerContainer.setGroupName("demo");
        canalListenerContainer.afterPropertiesSet();

        Thread.sleep(Long.MAX_VALUE);
    }

}
