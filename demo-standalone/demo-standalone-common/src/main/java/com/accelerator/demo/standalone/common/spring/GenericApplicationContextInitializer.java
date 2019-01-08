package com.accelerator.demo.standalone.common.spring;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

public class GenericApplicationContextInitializer implements ApplicationContextInitializer<GenericApplicationContext> {

    @Override
    public void initialize(GenericApplicationContext applicationContext) {
        applicationContext.setAllowBeanDefinitionOverriding(false);
    }

}
