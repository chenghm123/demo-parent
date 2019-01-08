package com.accelerator.demo.standalone.javase.nio;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

    public static void main(String[] args) throws Exception {
        Resource resource = new ClassPathResource("nio-data.txt");
        String name = resource.getURI().getPath();

        RandomAccessFile aFile = new RandomAccessFile(name, "rw");

        FileChannel inChannel = aFile.getChannel();
        // 建立48字节的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(48);
        // 读取48字节
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            // Buffer从写状态切换到读状态
            // 把limit设置成当前位置 即写操作写到位置
            // position设置为0 表示从头读 mark标记清除掉
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            // 清理缓冲区
            buf.clear();
            // 再次从通道读取缓冲大小的数据 直到-1退出循环
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

}
