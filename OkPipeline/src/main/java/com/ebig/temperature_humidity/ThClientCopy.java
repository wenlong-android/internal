package com.ebig.temperature_humidity;

import com.ebig.log.ELog;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class ThClientCopy {
    private String host = "192.168.10.88";
    private int port = 9413;
    private static Bootstrap bootstrap = null;
    private static EventLoopGroup workerGroup = null;
    private static ThClientHandler clientHandler;
    private Channel channelMap=null;

    private static class L {
        private static ThClientCopy client = new ThClientCopy();
    }

    public static ThClientCopy load() {
        return L.client;
    }

    private void init() {
        if (bootstrap==null){
            bootstrap = new Bootstrap();
        }
        if (workerGroup==null){
            workerGroup = new NioEventLoopGroup();
        }
        bootstrap.group(workerGroup).option(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //字符串编码解码
                        pipeline.addLast("decoder", new StringDecoder());
                        pipeline.addLast("encoder", new StringEncoder());
                        //心跳检测
                        pipeline.addLast(new IdleStateHandler(10, 6, 0, TimeUnit.SECONDS));
                        pipeline.addLast(new ThOutBoundHandler(null));
                      //  clientHandler = new ThClientHandler(ThClientCopy.this);
                        pipeline.addLast(clientHandler);
                        //客户端的逻辑

                    }
                });
    }

    public void start() {
        init();
        ChannelFuture f = null;
        try {
            f= bootstrap.connect(host, port);
           // f = bootstrap.connect(host, port).sync();
            //断线重连
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()) {
                        final EventLoop loop = channelFuture.channel().eventLoop();
                        loop.schedule(new Runnable() {
                            @Override
                            public void run() {
                                ELog.print("ThClient not connect service " + channelFuture.toString());
                                start();
                            }
                        }, 1L, TimeUnit.SECONDS);
                        ELog.print("ThClient not connect service " + channelFuture.toString());
                    } else {
                        ELog.print("ThClient 连接成功");
                    }
                }
            });
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }

    }


    /**
     * 客户端发送消息
     *
     * @param message
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public void send(String message) {
        if (getChannelMap()!=null) {
            getChannelMap().writeAndFlush(message.getBytes());
            ELog.print("ThClient channel is  :"+message);
        }
    }

    public Channel getChannelMap() {
        return channelMap;
    }

    public void setChannelMap(Channel channelMap) {
        this.channelMap = channelMap;
    }
}
