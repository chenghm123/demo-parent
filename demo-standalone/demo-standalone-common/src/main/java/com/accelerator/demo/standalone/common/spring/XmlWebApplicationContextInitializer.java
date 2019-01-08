package com.accelerator.demo.standalone.common.spring;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class XmlWebApplicationContextInitializer implements ApplicationContextInitializer<XmlWebApplicationContext> {

    @Override
    public void initialize(XmlWebApplicationContext applicationContext) {
        applicationContext.setAllowBeanDefinitionOverriding(false);
    }

}
