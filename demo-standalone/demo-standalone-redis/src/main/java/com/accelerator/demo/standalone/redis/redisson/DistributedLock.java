package com.accelerator.demo.standalone.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class DistributedLock {

    public static void main(String[] args)  {
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000) // 两秒钟一次检查节点状态
                .addNodeAddress() // 添加节点    主从 逗号隔开表示一个节点
                .addNodeAddress();

        RedissonClient redisson = Redisson.create(config);  // 创建redisson 相当于获取jediscluser对象

        RLock bizIdLock = redisson.getLock("bizId");

        bizIdLock.lock();

        try {
            if (bizIdLock.tryLock(1, TimeUnit.MINUTES)){ // 尝试获取BizId锁、一分钟没有获取到跳过

            }
        } catch (InterruptedException e){
            // ignore
        }


    }

}
