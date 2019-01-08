package com.accelerator.demo.springboot.dubbo.provider.impl;

import com.accelerator.demo.springboot.dubbo.service.DemoService;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service(protocol = "dubbo")
public class DemoServiceImpl implements DemoService {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String execute(String value) {
        log.info("value: {}", value);
        return "value: " + value;
    }

}
