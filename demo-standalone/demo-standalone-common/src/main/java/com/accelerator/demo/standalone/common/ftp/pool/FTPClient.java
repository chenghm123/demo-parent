package com.accelerator.demo.standalone.common.ftp.pool;

import com.accelerator.demo.standalone.common.ftp.FTPClientUtils;
import org.springframework.beans.factory.InitializingBean;

import java.io.Closeable;
import java.io.IOException;

public class FTPClient extends org.apache.commons.net.ftp.FTPClient implements InitializingBean, Closeable {

    private String homePathname;

    private FTPClientPool pool;

    @Override
    public void afterPropertiesSet() throws Exception {
        homePathname = super.printWorkingDirectory();
    }

    @Override
    public void close() {
        if (pool == null) {
            FTPClientUtils.destroy(this);
        } else {
            pool.returnFTPClient(this);
        }
    }

    public void setPool(FTPClientPool pool) {
        this.pool = pool;
    }

    public String getHomePathname() throws IOException {
        return homePathname;
    }

}
