package com.accelerator.demo.springcloud.openfegin.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class OpenfeignProviderApplication {

    @RequestMapping("/index")
    public List<String> index() {
        return Collections.singletonList("I'm is OpenfeignProvider !");
    }

    public static void main(String[] args) {
        SpringApplication.run(OpenfeignProviderApplication.class, args);
    }


}
