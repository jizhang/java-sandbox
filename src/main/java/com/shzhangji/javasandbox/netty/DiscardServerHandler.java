package com.shzhangji.javasandbox.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class DiscardServerHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
            throws Exception {

        ChannelBuffer buf = (ChannelBuffer) e.getMessage();
        while (buf.readable()) {
            System.out.print((char) buf.readByte());
        }
        System.out.println();

        buf.resetReaderIndex();
        e.getChannel().write(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
            throws Exception {

        Channel ch = e.getChannel();
        ch.close();
    }

}
