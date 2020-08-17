package com.accelerator.demo.springcloud.fegin.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "openfeign-provider")
public interface ConsumerClient {

    @GetMapping(value = "/index") String index();

}
