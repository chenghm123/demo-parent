package com.accelerator.demo.standalone.common.ftp.pool;

import com.accelerator.demo.standalone.common.ftp.FTPClientUtils;
import com.accelerator.demo.standalone.common.ftp.FTPConnException;
import com.accelerator.demo.standalone.common.ftp.FTPLoginException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.util.Assert;

import java.io.IOException;

class FTPClientFactory implements PooledObjectFactory<FTPClient> {

    private String controlEncoding;

    private boolean autodetect;


    private String hostname;

    private int port;

    private String username;

    private String password;


    private boolean listHiddenFiles;

    private int dataTimeout;

    private int bufferSize;

    private boolean passiveMode;

    private int activeMinPort;

    private int activeMaxPort;


    FTPClientFactory(FTPConfig ftpConfig) {
        Assert.hasText(ftpConfig.getHostname(), "hostname must have text; it must not be null, empty, or blank");

        controlEncoding = ftpConfig.getControlEncoding();
        autodetect = ftpConfig.isAutodetect();

        hostname = ftpConfig.getHostname();
        port = ftpConfig.getPort();
        username = ftpConfig.getUsername();
        password = ftpConfig.getPassword();

        listHiddenFiles = ftpConfig.isListHiddenFiles();
        dataTimeout = ftpConfig.getDataTimeout();
        bufferSize = ftpConfig.getBufferSize();
        passiveMode = ftpConfig.isPassiveMode();
        activeMinPort = ftpConfig.getActiveMinPort();
        activeMaxPort = ftpConfig.getActiveMaxPort();
    }

    @Override
    public PooledObject<FTPClient> makeObject() throws Exception {
        FTPClient ftpClient = new FTPClient();
        try {
            // 基本参数设置（连接前设置）
            ftpClient.setControlEncoding(controlEncoding);
            ftpClient.setAutodetectUTF8(autodetect);

            ftpClient.connect(hostname, port); // 开始连接
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                FTPClientUtils.destroy(ftpClient);
                throw new FTPConnException(replyCode);
            }

            // 开始登陆
            if (StringUtils.isNoneEmpty(username, password)
                    && !ftpClient.login(username, password)) {
                throw new FTPLoginException(username, password);
            }

            if (passiveMode) {  // 传输方式设置
                ftpClient.enterLocalPassiveMode();
            } else {
                ftpClient.setActivePortRange(activeMinPort, activeMaxPort);
                ftpClient.enterLocalActiveMode();
            }

            // 基本参数设置（连接后设置）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setListHiddenFiles(listHiddenFiles);
            ftpClient.setDataTimeout(dataTimeout);
            ftpClient.setBufferSize(bufferSize);

            ftpClient.afterPropertiesSet();

            return new DefaultPooledObject<>(ftpClient);
        } catch (Throwable e) {
            FTPClientUtils.destroy(ftpClient);
            throw e;
        }
    }

    @Override
    public void destroyObject(PooledObject<FTPClient> pooledObject) throws Exception {
        FTPClientUtils.destroy(pooledObject.getObject());
    }

    @Override
    public boolean validateObject(PooledObject<FTPClient> pooledObject) {
        FTPClient ftpClient = pooledObject.getObject();
        if (ftpClient.isConnected()) {
            try {
                return ftpClient.sendNoOp();
            } catch (IOException e) {
                // ignore
            }
        }
        return false;
    }

    @Override
    public void activateObject(PooledObject<FTPClient> pooledObject) throws Exception {
        // TODO maybe should print log? Not sure right now.
    }

    @Override
    public void passivateObject(PooledObject<FTPClient> pooledObject) throws Exception {
        FTPClient ftpClient = pooledObject.getObject();
        String homePathname = ftpClient.getHomePathname();
        ftpClient.changeWorkingDirectory(homePathname);
    }

}
