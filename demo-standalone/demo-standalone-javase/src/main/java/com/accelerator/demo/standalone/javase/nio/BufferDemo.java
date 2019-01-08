package com.accelerator.demo.standalone.javase.nio;

import java.nio.ByteBuffer;

import static java.nio.charset.StandardCharsets.UTF_8;

public class BufferDemo {

    public static void main(String[] args) {
        // 定义写入数据
        String str = "Hello Buffer !";

        /*
         * 创建1024的ByteBuffer
         *
         * 此时为写模式：
         * capacity = limit = 1024
         * position = 0
         */
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        /* 写入数据 */
        for (byte strByte : str.getBytes(UTF_8)) {
            /* 每写一次position + 1 */
            buffer.put(strByte);
        }

        /*
         *  切换到读模式
         *  capacity = 1024;
         *  limit = position; // 之前写入的数据大小
         *  position = 0;
         */
        buffer.flip();

        byte[] readBytes = new byte[1024];

        /* 读取数据到bytes数组 */
        for (int i = buffer.position(); i < buffer.limit(); i++) {
            /* 每读一次position + 1 */
            readBytes[i] = buffer.get();
        }

        // 输出读取的数据
        System.out.println(new String(readBytes, UTF_8));
    }

}
