package com.accelerator.demo.standalone.common.ftp.pool;

import com.accelerator.demo.standalone.common.ftp.FTPException;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.InitializingBean;

public class FTPClientPool implements InitializingBean {

    private GenericObjectPool<FTPClient> internalPool;

    private FTPConfig ftpConfig = new FTPConfig();

    private FTPClientPoolConfig poolConfig;

    private AbandonedConfig abandonedConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        FTPClientFactory factory = new FTPClientFactory(ftpConfig);
        if (poolConfig != null) {
            internalPool = new GenericObjectPool<>(factory, poolConfig);
        } else {
            internalPool = new GenericObjectPool<>(factory);
        }
        internalPool.setAbandonedConfig(abandonedConfig);
    }

    public void invalidateFTPClient(FTPClient ftpClient) {
        try {
            internalPool.invalidateObject(ftpClient);
        } catch (Exception e) {
            throw new FTPException(e);
        }
    }

    public void returnFTPClient(FTPClient ftpClient) {
        try {
            internalPool.returnObject(ftpClient);
        } catch (Exception e) {
            throw new FTPException(e);
        }
    }

    public FTPClient borrowFTPClient() {
        try {
            FTPClient ftpClient = internalPool.borrowObject();
            ftpClient.setPool(this);
            return ftpClient;
        } catch (Exception e) {
            throw new FTPException(e);
        }
    }

    public void destroy() {
        try {
            internalPool.close();
        } catch (Exception e) {
            throw new FTPException(e);
        }
    }

    public void setPoolConfig(FTPClientPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

    public void setAbandonedConfig(AbandonedConfig abandonedConfig) {
        this.abandonedConfig = abandonedConfig;
    }

    public void setControlEncoding(String controlEncoding) {
        ftpConfig.setControlEncoding(controlEncoding);
    }

    public void setAutodetect(boolean autodetect) {
        ftpConfig.setAutodetect(autodetect);
    }

    public void setHostname(String hostname) {
        ftpConfig.setHostname(hostname);
    }

    public void setPort(int port) {
        ftpConfig.setPort(port);
    }

    public void setUsername(String username) {
        ftpConfig.setUsername(username);
    }

    public void setPassword(String password) {
        ftpConfig.setPassword(password);
    }

    public void setListHiddenFiles(boolean listHiddenFiles) {
        ftpConfig.setListHiddenFiles(listHiddenFiles);
    }

    public void setDataTimeout(int dataTimeout) {
        ftpConfig.setDataTimeout(dataTimeout);
    }

    public void setBufferSize(int bufferSize) {
        ftpConfig.setBufferSize(bufferSize);
    }

    public void setPassiveMode(boolean passiveMode) {
        ftpConfig.setPassiveMode(passiveMode);
    }

    public void setActiveMinPort(int activeMinPort) {
        ftpConfig.setActiveMinPort(activeMinPort);
    }

    public void setActiveMaxPort(int activeMaxPort) {
        ftpConfig.setActiveMaxPort(activeMaxPort);
    }

}
