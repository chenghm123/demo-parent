package com.accelerator.demo.standalone.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.async.RedisAdvancedClusterAsyncCommands;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.junit.After;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class LettuceTest {


    private static final RedisClusterClient clusterClient = RedisClusterClient.create(
            Arrays.asList(
                    RedisURI.create("10.230.54.50", 6379),
                    RedisURI.create("10.230.54.50", 6380),
                    RedisURI.create("10.230.54.53", 6379),
                    RedisURI.create("10.230.54.53", 6380),
                    RedisURI.create("10.230.54.58", 6379),
                    RedisURI.create("10.230.54.58", 6380)
            ));

    private static final RedisClient client = RedisClient.create(
            RedisURI.builder()
                    .withTimeout(Duration.ofSeconds(3))
                    .withHost("10.230.44.5")
                    .withPort(7000)
                    .build()
    );

    @Test
    public void standalone() throws Exception {
        StatefulRedisConnection<String, String> conn = client.connect();
        RedisAsyncCommands<String, String> async = conn.async();
        RedisFuture<String> retFuture = async.get("lettuce-test");
        System.out.println(retFuture.get());

        RedisCommands<String, String> sync = conn.sync();
        String ret = sync.get("lettuce-test");
        System.out.println(ret);
    }


    @Test
    public void cluster() throws Exception {
        StatefulRedisClusterConnection<String, String> conn = clusterClient.connect();

        RedisAdvancedClusterAsyncCommands<String, String> async = conn.async();
        RedisFuture<String> retFuture = async.get("lettuce-test");
        System.out.println(retFuture.get());

        RedisAdvancedClusterCommands<String, String> sync = conn.sync();
        String ret = sync.get("lettuce-test");
        System.out.println(ret);
    }

    @After
    public void after() {
        clusterClient.shutdown();
        client.shutdown();
    }

}
