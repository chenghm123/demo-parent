package com.accelerator.demo.standalone.common.ftp.pool;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.net.ftp.FTP;


@Getter @Setter
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

}
