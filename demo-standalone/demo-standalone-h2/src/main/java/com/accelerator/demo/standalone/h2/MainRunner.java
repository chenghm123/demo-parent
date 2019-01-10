package com.accelerator.demo.standalone.h2;

import org.h2.server.web.WebServer;

public class MainRunner {

    public static void main(String[] args) {
        WebServer webServer = new WebServer();
        webServer.init("-webPort", "1992");
        webServer.start();
        webServer.listen();
    }

}
