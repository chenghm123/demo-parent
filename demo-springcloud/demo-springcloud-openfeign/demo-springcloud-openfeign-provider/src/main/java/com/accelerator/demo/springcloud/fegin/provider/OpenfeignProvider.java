package com.accelerator.demo.springcloud.fegin.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication @EnableEurekaClient
@RestController
public class OpenfeignProvider {

    @RequestMapping("/index")
    public String index(@RequestParam String name, @Value("${server.port}") String port) {
        return name + ",i am from port:" + port;
    }

    public static void main(String[] args) {
        SpringApplication.run(OpenfeignProvider.class, args);
    }


}
