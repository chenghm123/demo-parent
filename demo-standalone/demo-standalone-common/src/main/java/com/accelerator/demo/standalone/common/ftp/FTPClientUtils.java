package com.accelerator.demo.standalone.common.ftp;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public abstract class FTPClientUtils {

    public static void close(FTPClient ftpClient) {
        if (ftpClient instanceof com.accelerator.demo.standalone.common.ftp.pool.FTPClient) {
            ((com.accelerator.demo.standalone.common.ftp.pool.FTPClient) ftpClient).close();
        } else {
            FTPClientUtils.destroy(ftpClient);
        }
    }

    public static void destroy(FTPClient ftpClient) {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
            } catch (IOException e) {
                // ignore
            } finally {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

}
