package com.accelerator.demo.springboot.dubbo.consumer;

import com.accelerator.demo.springboot.dubbo.consumer.client.DemoServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DubboConsumerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DubboConsumerApplication.class);
        DemoServiceClient demoServiceClient = applicationContext.getBean(DemoServiceClient.class);
        demoServiceClient.execute();
    }

}
