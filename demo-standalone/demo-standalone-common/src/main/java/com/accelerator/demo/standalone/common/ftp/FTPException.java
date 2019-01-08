package com.accelerator.demo.standalone.common.ftp;

public class FTPException extends RuntimeException {

    public FTPException(String message) {
        super(message);
    }

    public FTPException(Throwable cause) {
        super(cause);
    }

    public FTPException() {}
}
