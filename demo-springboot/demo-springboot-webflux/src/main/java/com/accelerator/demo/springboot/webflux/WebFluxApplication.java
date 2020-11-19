package com.accelerator.demo.springboot.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class WebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxApplication.class, args);
    }

}
