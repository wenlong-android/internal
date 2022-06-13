package com.ebig.socket.netty;
import com.ebig.socket.common.SocketInterfaces;
import com.ebig.socket.idl.PipeSocketMonitorCall;
import com.ebig.socket.idl.PipeServerInterfaces;
import com.ebig.socket.idl.PipeServerCall;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class PipeSocketServer implements PipeServerInterfaces, PipeSocketMonitorCall {
    private ChannelFuture channelFuture = null;
    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;
    private SocketInterfaces mInBoundMessageCall;
    private PipeServerCall mPipeServerCall;
    private static Channel serverChannel;

    public static PipeSocketServer getInstance() {
        return new PipeSocketServer();
    }

    @Override
    public PipeSocketServer withPort(int port) {
        if (serverChannel==null) {
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.SO_BACKLOG, 100)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast("bytesDecoder", new ByteArrayDecoder());
                                ch.pipeline().addLast("bytesEncoder", new ByteArrayEncoder());
                                ch.pipeline().addLast(new OutBoundHandler());
                                ch.pipeline().addLast(
                                        new IdleStateHandler(
                                                0,
                                                0,
                                                60),
                                        InBoundHandler.getInstance(PipeSocketServer.this));
                            }
                        });
                channelFuture = b.bind(port);
            } catch (Exception e) {
                // Shut down all event loops to terminate all threads.
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
                shutDown();
            }
            return PipeSocketServer.this;
        }else {
            return null;
        }

    }

    @Override
    public void deviceConnect(String uuid, String ipHost) {
        mInBoundMessageCall.deviceConnect(uuid, ipHost);
    }

    @Override
    public void deviceDisConnect(String uuid, String ipHost) {
        mInBoundMessageCall.deviceDisConnect(uuid, ipHost);
    }

    @Override
    public void messageRead(String uuid, String ipHost, String cmd) {
        mInBoundMessageCall.messageRead(uuid, ipHost, cmd);
    }

    @Override
    public void writeOutTime(String uuid, String ipHost) {
        mInBoundMessageCall.writeOutTime(uuid, ipHost);
    }

    @Override
    public void readOutTime(String uuid, String ipHost) {
        mInBoundMessageCall.readOutTime(uuid, ipHost);
    }

    @Override
    public void outTime(String uuid, String ipHost) {
        mInBoundMessageCall.outTime(uuid, ipHost);
    }

    @Override
    public void start() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    channelFuture.sync();
                    // 将ServerChannel保存下来
                    serverChannel = channelFuture.channel();
                    // 阻塞至channel关闭
                    serverChannel.closeFuture().sync();
                    if (mPipeServerCall != null) {
                        mPipeServerCall.onResult(channelFuture.isSuccess());
                    }
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public PipeSocketServer addListenner(SocketInterfaces listenner) {
        this.mInBoundMessageCall = listenner;
        return PipeSocketServer.this;
    }

    @Override
    public PipeSocketServer addStartResult(PipeServerCall listenner) {
        this.mPipeServerCall = listenner;
        return PipeSocketServer.this;
    }
    /**
     * 关闭当前server
     */
    @Override
    public void shutDown() {
        if (serverChannel != null) {
            serverChannel.close();
            serverChannel = null;
        }
    }

    public boolean isServerAlive(){
     return serverChannel!=null;
    }
}
