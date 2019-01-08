package com.accelerator.demo.standalone.dubbo.annotation.impl;

import com.accelerator.demo.standalone.dubbo.annotation.api.AnnotationService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class AnnotationServiceImpl implements AnnotationService {

    public String sayHello(String name) {
        System.out.println("async provider received: " + name);
        return "annotation: hello, " + name;
    }

}