package com.accelerator.demo.standalone.common.ftp;

public class FTPConnException extends FTPException {

    public FTPConnException(int replyCode) {
        super("FTP Connect Failure! ReplyCode:" + replyCode);
    }

}
