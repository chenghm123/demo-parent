package com.accelerator.demo.standalone.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RedisTemplateTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RedisTemplate<String, String> redisTemplate1;

    @Resource
    private StringRedisTemplate stringRedisTemplate2;

    @Test
    public void set() {
        ValueOperations<String, String> valueOperations = redisTemplate1.opsForValue();
        valueOperations.set("a", "a");
    }

    @Test
    public void get() {
        ValueOperations<String, String> valueOperations = redisTemplate1.opsForValue();
        logger.info("value:{}", valueOperations.get("a"));
    }


    @Test
    public void del() {
        redisTemplate1.delete("a");
    }

    @Test
    public void queueSend() {
        while (true)
        stringRedisTemplate2.convertAndSend("demo", "测试消息");
    }

    @Test
    public void queueListener() throws InterruptedException{
        Thread.sleep(Long.MAX_VALUE);
    }
}
