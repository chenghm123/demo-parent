package com.accelerator.demo.standalone.netty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TempTest {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);

        while (true) {
            Socket socket = server.accept();
            new Thread(() -> {
                try (
                        OutputStream out = socket.getOutputStream();
                        InputStream in = socket.getInputStream();
                ) {
                    // doSomething
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        //  ignored
                    }
                }
            }).start();
        }
    }

}
