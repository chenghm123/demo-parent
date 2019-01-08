package com.accelerator.demo.standalone.rocket.util;

import com.accelerator.demo.standalone.rocket.Constants;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

public abstract class RocketUtils {

    public static DefaultMQProducer createDefaultMQProducer(String producerGroup) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(Constants.NAMESRV_ADDR);
        producer.start();
        return producer;
    }

}
