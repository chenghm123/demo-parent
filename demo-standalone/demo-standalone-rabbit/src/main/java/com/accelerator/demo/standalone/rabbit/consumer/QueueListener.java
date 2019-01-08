package com.accelerator.demo.standalone.rabbit.consumer;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class QueueListener  {

    public void receive(Map<String, String> msg) {
        try {
            System.out.print(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
