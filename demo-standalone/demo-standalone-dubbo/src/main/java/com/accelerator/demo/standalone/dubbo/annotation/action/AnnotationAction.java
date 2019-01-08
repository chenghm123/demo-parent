package com.accelerator.demo.standalone.dubbo.annotation.action;

import com.accelerator.demo.standalone.dubbo.annotation.api.AnnotationService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component("annotationAction")
public class AnnotationAction {

    @Reference
    private AnnotationService annotationService;

    public String doSayHello(String name) {
        return annotationService.sayHello(name);
    }

    public void setAnnotationService(AnnotationService annotationService) {
        this.annotationService = annotationService;
    }
}
