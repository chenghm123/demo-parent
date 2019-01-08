package com.accelerator.demo.standalone.common.ftp.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class FTPClientPoolConfig extends GenericObjectPoolConfig {

    public FTPClientPoolConfig() {
        // defaults to make your life with connection pool easier :)
        super.setTimeBetweenEvictionRunsMillis(30000);
        super.setMinEvictableIdleTimeMillis(60000);
        super.setNumTestsPerEvictionRun(-1);
        super.setTestWhileIdle(true);
    }

}
