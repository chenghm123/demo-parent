package com.accelerator.demo.springcloud.fegin.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication @EnableFeignClients
public class OpenfeignConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication
                .run(OpenfeignConsumerApplication.class, args);

        ConsumerClient consumerClient = applicationContext.getBean(ConsumerClient.class);
        System.out.println(consumerClient.index());
    }

}
