package com.accelerator.demo.standalone.common.ftp;

public class FTPLoginException extends FTPException {

    public FTPLoginException(String username, String password) {
        super("ftp login failure! username:" + username + "; password:" + password);
    }

}
