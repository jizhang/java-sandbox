package com.shzhangji.javasandbox.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.handler.codec.replay.VoidEnum;


public class TimeDecoder extends ReplayingDecoder<VoidEnum> {

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel,
            ChannelBuffer buffer, VoidEnum state) throws Exception {

        return new UnixTime((int) ((long) (buffer.readInt() & 0xffffffffL) - 2208988800L));
    }

}
