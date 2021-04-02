package com.shuanger.nettydemo.discardServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-04-02 14:59
 * @description:
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {  // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        //method1--- Discard the received data silently.
//        ((ByteBuf) msg).release(); // (3)


        //method2 --- look into the received data
//        ByteBuf in = (ByteBuf) msg;
//        try {
//            while (in.isReadable()) { // (1)
//                System.out.print((char) in.readByte());
//                System.out.flush();
//            }
//        } finally {
//            ReferenceCountUtil.release(msg); // (2)
//        }


        // method3-- writing an Echo server
        // how to write a response message to a client by implementing the ECHO protocol, where any received data is sent back.
//        ctx.write(msg); // (1)
//        ctx.flush(); // (2)

        ctx.writeAndFlush(msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}

