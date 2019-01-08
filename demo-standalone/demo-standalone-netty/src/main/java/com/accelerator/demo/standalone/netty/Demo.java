package com.accelerator.demo.standalone.netty;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Demo {

    public static void main(String[] args) throws Exception {
        try (
                Socket socket = new Socket("localhost", 8080);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();
        ) {
            IOUtils.write("aaa", out, StandardCharsets.UTF_8);
        }
    }

}
