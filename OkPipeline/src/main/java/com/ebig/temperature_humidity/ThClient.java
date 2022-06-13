package com.ebig.temperature_humidity;

import com.ebig.idl.CommonCall;
import com.ebig.log.ELog;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class ThClient {
    private final String host;
    private final int port;
    private final ThWritheReadListenner listenner;


    private static Bootstrap bootstrap = null;
    private static EventLoopGroup workerGroup = null;
    private static ThClientHandler clientHandler;
    private static Channel channelMap = null;
    public static final String threceiveKey = "threceiveKey";

    public ThClient(Config config) {
        this.host = config.host;
        this.port = config.port;
        this.listenner = config.listenner;

    }

    public static class Config {
        private String host = "192.168.10.88";
        private int port = 9413;
        private ThWritheReadListenner listenner;


        public Config(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public Config addListenner(ThWritheReadListenner l) {
            this.listenner = l;
            return this;
        }



        public ThClient build() {
            return new ThClient(this);
        }
    }


    private void init() {
        if (bootstrap == null || workerGroup == null) {
            bootstrap = new Bootstrap();
            workerGroup = new NioEventLoopGroup();
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
                            pipeline.addLast(new ThOutBoundHandler(listenner));
                            clientHandler = new ThClientHandler(ThClient.this,listenner);
                            pipeline.addLast(clientHandler);
                        }
                    });
        }

    }

    private ChannelFuture f = null;

    public void start() {
        ELog.print("ThClient start connect service ...");
        init();
        f = null;
        try {
            f = bootstrap.connect(host, port);
            // f = bootstrap.connect(host, port).sync();
            if (f.isSuccess()) {
                ELog.print("ThClient f.isSuccess(): " + f.isSuccess());

            }
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
                        }, 10, TimeUnit.SECONDS);
                    } else {
                        ELog.print("ThClient 连接成功");

                    }
                }
            });
            f.channel().closeFuture().sync();
            //断线重连

        } catch (InterruptedException e) {
            ELog.print("ThClient e：" + e.getMessage());
        } finally {
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
    private static long pauseTime = 0;

    public static void send(String message) {
        if (channelMap != null) {
//            if (pauseTime==0){
//                pauseTime=System.currentTimeMillis();
            channelMap.writeAndFlush(message.getBytes());
         //   ELog.print("时间:" + System.currentTimeMillis() + "   ,ThClient channel start  ===" + message + "  ===end");
//            }else if ((System.currentTimeMillis()-pauseTime)>240000){
//                pauseTime=System.currentTimeMillis();
//                channelMap.writeAndFlush(message.getBytes());
//                ELog.print("时间:"+System.currentTimeMillis()+"   ,ThClient channel start  ===" + message+"  ===end");
//            }
        }
    }

    public Channel getChannelMap() {
        return channelMap;
    }

    public void setChannelMap(Channel channelMap) {
        this.channelMap = channelMap;
    }


}
