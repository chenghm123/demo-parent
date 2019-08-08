package com.accelerator.demo.standalone.netty.server;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class ServerDecoder extends LengthFieldBasedFrameDecoder {

    public static final ServerDecoder INSTANCE = new ServerDecoder();

    private ServerDecoder() {
        super(Integer.MAX_VALUE, 1, 4, 4, 5, true);
    }

}
