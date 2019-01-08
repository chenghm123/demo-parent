package com.accelerator.demo.standalone.javase.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * Created by Accelerator on 2016/7/24.
 */
public class SelectorDemo {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();

        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        // 将ServerSocketChannel设置为非阻塞的 ()
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

}
