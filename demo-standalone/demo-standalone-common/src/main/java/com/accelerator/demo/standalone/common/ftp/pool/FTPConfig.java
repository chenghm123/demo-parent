package com.accelerator.demo.standalone.common.ftp.pool;

import org.apache.commons.net.ftp.FTP;

public class FTPConfig {

    private String controlEncoding = FTP.DEFAULT_CONTROL_ENCODING;

    private boolean autodetect;


    private String hostname;

    private int port = FTP.DEFAULT_PORT;

    private String username;

    private String password;


    private boolean listHiddenFiles;

    private int dataTimeout;

    private int bufferSize;

    private boolean passiveMode;

    private int activeMinPort;

    private int activeMaxPort;

    public String getControlEncoding() {
        return controlEncoding;
    }

    public void setControlEncoding(String controlEncoding) {
        this.controlEncoding = controlEncoding;
    }

    public boolean isAutodetect() {
        return autodetect;
    }

    public void setAutodetect(boolean autodetect) {
        this.autodetect = autodetect;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isListHiddenFiles() {
        return listHiddenFiles;
    }

    public void setListHiddenFiles(boolean listHiddenFiles) {
        this.listHiddenFiles = listHiddenFiles;
    }

    public int getDataTimeout() {
        return dataTimeout;
    }

    public void setDataTimeout(int dataTimeout) {
        this.dataTimeout = dataTimeout;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public boolean isPassiveMode() {
        return passiveMode;
    }

    public void setPassiveMode(boolean passiveMode) {
        this.passiveMode = passiveMode;
    }

    public int getActiveMinPort() {
        return activeMinPort;
    }

    public void setActiveMinPort(int activeMinPort) {
        this.activeMinPort = activeMinPort;
    }

    public int getActiveMaxPort() {
        return activeMaxPort;
    }

    public void setActiveMaxPort(int activeMaxPort) {
        this.activeMaxPort = activeMaxPort;
    }

}
