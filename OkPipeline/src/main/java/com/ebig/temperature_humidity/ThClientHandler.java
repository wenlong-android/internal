package com.ebig.temperature_humidity;

import com.ebig.log.ELog;
import com.ebig.socket.bean.ThEntity;
import com.ebig.utils.GsonUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ThClientHandler extends SimpleChannelInboundHandler {
    private ThClient nettyClient;
    private String tenantId;
    private int attempts = 0;
    private ChannelPromise promise;
    private ChannelHandlerContext context;
    private String ip;
    private ThWritheReadListenner listenner;
    public ThClientHandler(ThClient nettyClient,ThWritheReadListenner listenner){
        this.nettyClient = nettyClient;
        this.listenner=listenner;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ELog.print("ThClient service send read"+o.toString());
        ThEntity entity= GsonUtils.fromJson(o.toString(),ThEntity.class);
        ThCacheFactory.submitConfirmation(entity);
        if (listenner!=null){
            listenner.onRead(o.toString());
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ELog.print("ThClient service channelReadComplete");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ELog.print("ThClient output connected!");
        nettyClient.setChannelMap(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ELog.print("ThClient channelInactive 使用过程中断线重连 "+ctx.toString());
        //使用过程中断线重连
         nettyClient.start();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ELog.print("ThClient exceptionCaught "+cause.getMessage());
        ctx.close();
        nettyClient.setChannelMap(null);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        nettyClient.setChannelMap(null);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state().equals(IdleState.READER_IDLE)){
              //  ELog.print("ThClient READER_IDLE");
            }else if (event.state().equals(IdleState.WRITER_IDLE)){
                //发送心跳，保持长连接
                String s = "NettyClient"+System.getProperty("line.separator");
                ctx.channel().writeAndFlush(s);  //发送心跳成功
             //   ELog.print("ThClient WRITER_IDLE");
            }else if (event.state().equals(IdleState.ALL_IDLE)){
              //  ELog.print("ThClient ALL_IDLE");
            }
        }
        super.userEventTriggered(ctx,evt);
    }

}
