package com.accelerator.demo.springcloud.openfegin.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@RestController
public class OpenfeignConsumerApplication {

    @Resource
    private ConsumerClient consumerClient;

    @RequestMapping("/index")
    public List<String> index() {
        List<String> result = consumerClient.index();
        result.add("I'm is OpenfeignConsumer !");
        return result;
    }

    public static void main(String[] args) {
        SpringApplication.run(OpenfeignConsumerApplication.class, args);
    }

}
