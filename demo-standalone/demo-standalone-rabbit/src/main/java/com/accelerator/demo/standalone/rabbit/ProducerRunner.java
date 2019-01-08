package com.accelerator.demo.standalone.rabbit;

import com.google.common.collect.Maps;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class ProducerRunner {

    private static final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-producer.xml");

    public static void main(String[] args) {
        Map<String, String> mqData = Maps.newHashMap();
        mqData.put("a", "a");
        mqData.put("b", "b");
        mqData.put("c", "c");
        AmqpTemplate amqpTemplate = applicationContext.getBean("amqpTemplate", AmqpTemplate.class);
        amqpTemplate.convertAndSend("test_queue_key", mqData);
    }

}
