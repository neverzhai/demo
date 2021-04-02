package com.shuanger.nettydemo.nettyHello;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-04-02 11:05
 * @description:
 */
public class NettyServer {

    public static void main(String[] args) {
        new NettyServer().bing(8081);
    }

    private void bing(int port) {
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childrenGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(parentGroup, childrenGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childHandler(new MyChannelInitializer());

        try {
            ChannelFuture sync = bootstrap.bind(port).sync();
            sync.channel().closeFuture().sync();

            System.out.println("Hello Netty");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            childrenGroup.shutdownGracefully();
        }
    }
}
