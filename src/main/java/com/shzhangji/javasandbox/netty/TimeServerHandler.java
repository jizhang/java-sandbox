package com.shzhangji.javasandbox.netty;

import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class TimeServerHandler extends SimpleChannelHandler {

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
            throws Exception {

        TimeServer.allChannels.add(e.getChannel());
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
            throws Exception {

        UnixTime time = new UnixTime((int) (System.currentTimeMillis() / 1000 + 2208988800L));
        ChannelFuture future = e.getChannel().write(time);
        future.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
            throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }

}
