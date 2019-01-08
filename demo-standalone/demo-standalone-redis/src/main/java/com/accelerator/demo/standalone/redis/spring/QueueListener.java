package com.accelerator.demo.standalone.redis.spring;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

public class QueueListener implements MessageListener {

    @Resource
    private StringRedisTemplate stringRedisTemplate2;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] channel = message.getChannel();
        byte[] body = message.getBody();
        System.out.println(stringRedisTemplate2.getKeySerializer().deserialize(channel));
        System.out.println(stringRedisTemplate2.getValueSerializer().deserialize(body));
    }
}
