package com.accelerator.demo.standalone.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ServerRunner {

    public void start() {
        // NioEventLoopGroup是个线程组 它包含了一组NIO线程 专门用于网络事件的处理 实际上它们就是Reactor线程组
        // parentGroup用于服务端接受客户端的连接  childGroup进行SocketChannel的网络读写
        EventLoopGroup parentGroup = new NioEventLoopGroup(), childGroup = new NioEventLoopGroup();
        try {
            // Netty用于启动NIO服务端的辅助启动类 目的是降低服务端的开发复杂度
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 设置创建的Channel为NioServerSocketChannel 对应JDK NIO类库中的ServerSocketChannel类
            bootstrap.group(parentGroup, childGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel channel) throws Exception {
                            channel.pipeline().addLast(ServerDecoder.INSTANCE, ServerHandler.INSTANCE);
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.bind(1117).sync();
            // 等待服务端监听端口关闭，等待服务端链路关闭之后main函数才退出
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        } finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }

    }

}
