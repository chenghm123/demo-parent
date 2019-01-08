package com.accelerator.demo.standalone.common.ftp;

import com.accelerator.demo.standalone.common.ftp.pool.FTPClientPool;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class FTPTemplate implements InitializingBean {

    private FTPClientPool ftpClientPool;


    public void setFtpClientPool(FTPClientPool ftpClientPool) {
        this.ftpClientPool = ftpClientPool;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(ftpClientPool,
                "ftpClientPool is required; it must not be null");
    }
}
