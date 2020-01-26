package com.shzhangji.javasandbox.netty;

import java.lang.management.ManagementFactory;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class TimeServer {

    public static final ChannelGroup allChannels = new DefaultChannelGroup("time-server");

    public static void main(String[] args) throws Exception {

        ChannelFactory factory = new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(), Executors.newCachedThreadPool());

        ServerBootstrap bootstrap = new ServerBootstrap(factory);

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(
                        new TimeServerHandler(),
                        new TimeEncoder());
            }

        });

        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);

        System.out.println("start server at port 37 - " + ManagementFactory.getRuntimeMXBean().getName());
        Channel channel = bootstrap.bind(new InetSocketAddress(37));
        allChannels.add(channel);

//        TimeUnit.MINUTES.sleep(1);
//
//        System.out.println("shutting down");
//
//        ChannelGroupFuture future = allChannels.close();
//        future.awaitUninterruptibly();
//        factory.releaseExternalResources();
//
//        System.out.println("server stopped");
    }

}
