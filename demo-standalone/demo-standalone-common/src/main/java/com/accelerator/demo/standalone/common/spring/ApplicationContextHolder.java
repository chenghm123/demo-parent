package com.accelerator.demo.standalone.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Objects;

public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContextHolder instance;

    private static ApplicationContext applicationContext;

    public static ApplicationContext getRequiredApplicationContext() {
        if (Objects.isNull(getApplicationContext())) {
            throw new IllegalStateException(
                    "ApplicationContextHolder instance [" + instance + "] does not run in an ApplicationContext");
        }
        return getApplicationContext();
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
        instance = this;
    }

}

