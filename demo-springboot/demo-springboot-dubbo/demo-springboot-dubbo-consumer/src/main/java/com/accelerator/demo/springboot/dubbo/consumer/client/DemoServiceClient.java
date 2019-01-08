package com.accelerator.demo.springboot.dubbo.consumer.client;

import com.accelerator.demo.springboot.dubbo.service.DemoService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DemoServiceClient {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Reference
    private DemoService demoService;

    public void execute() {
        log.info(demoService.execute("Hello World !"));
    }


}
