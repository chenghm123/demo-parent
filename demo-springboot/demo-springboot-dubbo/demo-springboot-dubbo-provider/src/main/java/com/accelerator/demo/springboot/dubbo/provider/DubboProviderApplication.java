package com.accelerator.demo.springboot.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DubboProviderApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DubboProviderApplication.class);
        Thread.sleep(Long.MAX_VALUE);
    }

}
