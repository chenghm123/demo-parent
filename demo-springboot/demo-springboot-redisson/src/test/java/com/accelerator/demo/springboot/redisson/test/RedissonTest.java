package com.accelerator.demo.springboot.redisson.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedissonTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void test() throws Exception {
        RLock rLock = redissonClient.getLock("test");
        int threadCount = 10;
        CountDownLatch latch = new CountDownLatch(threadCount);
        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    rLock.lock();
                    String threadName = "Thread" + count.getAndIncrement();
                    System.out.println(threadName);
                } finally {
                    rLock.unlock();
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
    }

}
