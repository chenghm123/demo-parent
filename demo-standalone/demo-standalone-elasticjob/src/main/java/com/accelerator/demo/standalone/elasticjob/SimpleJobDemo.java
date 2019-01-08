package com.accelerator.demo.standalone.elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleJobDemo implements SimpleJob {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private AtomicInteger count = new AtomicInteger();

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("============================execute===================================="
                + System.lineSeparator()
                + shardingContext.getTaskId() + " ===> " + shardingContext.getShardingItem() + " ===> order: " + count.getAndIncrement()
                + System.lineSeparator()
                + "===========================execute====================================");
    }

    public static void main(String[] args) {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("zookeeper:2181", "elasticjob-lite-demo"));
        regCenter.init();

        // 定义作业核心配置
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder("simpleJobDemo", "0/15 * * * * ?", 2).build();
        // 定义SIMPLE类型配置
        SimpleJobConfiguration jobConfig = new SimpleJobConfiguration(coreConfig, SimpleJobDemo.class.getCanonicalName());
        // 定义Lite作业根配置
        LiteJobConfiguration liteJobConfig = LiteJobConfiguration.newBuilder(jobConfig)
                .overwrite(true) // 允许重写
                .build();

        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://mysql:3306/demo?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setSuppressClose(true);
        JobEventConfiguration jobEventConfig = new JobEventRdbConfiguration(dataSource);

        new JobScheduler(regCenter, liteJobConfig, jobEventConfig).init();

        // new JobScheduler(regCenter, liteJobConfig).init();
    }

}
