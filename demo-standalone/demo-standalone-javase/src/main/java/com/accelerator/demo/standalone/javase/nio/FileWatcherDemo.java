package com.accelerator.demo.standalone.javase.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileWatcherDemo {

    public static void main(String[] args) throws IOException, InterruptedException {

        IntBuffer intBuffer = IntBuffer.allocate(1024);
        FloatBuffer floatBuffer = FloatBuffer.allocate(1024);
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        DoubleBuffer doubleBuffer = DoubleBuffer.allocate(1024);
        ShortBuffer shortBuffer = ShortBuffer.allocate(1024);
        LongBuffer longBuffer = LongBuffer.allocate(1024);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);



        FileSystem fileSystem = FileSystems.getDefault();
        WatchService watchService = fileSystem.newWatchService();
        Paths.get("C:/Users/Accelerator/Desktop").register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
        while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("count:" + event.count());
                System.out.println("context:" + event.context());
                System.out.println("kind:" + event.kind());
            }
            if (!key.reset()) {
                break;
            }
        }
    }

}
