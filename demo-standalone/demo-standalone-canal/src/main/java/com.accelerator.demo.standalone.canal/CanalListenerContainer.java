package com.accelerator.demo.standalone.canal;

import com.alibaba.otter.canal.client.CanalMQConnector;
import com.alibaba.otter.canal.client.rocketmq.RocketMQCanalConnector;
import com.alibaba.otter.canal.protocol.FlatMessage;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CanalListenerContainer implements InitializingBean, DisposableBean {

    private List<CanalMQConnector> connectors;
    private ExecutorService executorService;

    @Setter
    private String nameServer;

    @Setter
    private String groupName;

    @Setter
    private String accessKey;

    @Setter
    private String secretKey;

    @Setter
    private Map<String, CanalMessageListener> listenerMap;

    @Override
    public void afterPropertiesSet() {
        Assert.notEmpty(listenerMap, "listenerMap must not be empty");
        executorService = Executors.newFixedThreadPool(listenerMap.size(),
                new ThreadFactoryBuilder().setDaemon(true)
                        .setNameFormat("canal-subscribe-pool-%d")
                        .build());
        connectors = new ArrayList<>(listenerMap.size());

        for (String topic : listenerMap.keySet()) {
            RocketMQCanalConnector connector = new RocketMQCanalConnector(
                    nameServer, topic, groupName,
                    accessKey, secretKey,
                    true
            );
            connector.connect();
            connector.subscribe();
            connectors.add(connector);

            CanalMessageListener listener = listenerMap.get(topic);
            executorService.execute(() -> {
                while (true) {
                    List<FlatMessage> flatMessages = connector
                            .getFlatListWithoutAck(1L, TimeUnit.DAYS);
                    listener.consumeMessages(flatMessages);
                    connector.ack();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // ignored
                    }
                }
            });
        }
    }

    @Override
    public void destroy() {
        for (CanalMQConnector connector : connectors) {
            connector.unsubscribe();
            connector.disconnect();
        }
        executorService.shutdown();
    }

}
