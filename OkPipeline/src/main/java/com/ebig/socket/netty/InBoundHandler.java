package com.ebig.socket.netty;


import com.ebig.log.ELog;
import com.ebig.socket.common.AndPipe;
import com.ebig.utils.HexUtils;
import com.ebig.socket.common.Indicate;
import com.ebig.socket.idl.PipeSocketMonitorCall;
import com.ebig.socket.utils.IpUtils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class InBoundHandler extends SimpleChannelInboundHandler<byte[]> {
    private static PipeSocketMonitorCall inBoundMessageCall;

    public static InBoundHandler getInstance(PipeSocketMonitorCall call) {
        inBoundMessageCall = call;
        return new InBoundHandler();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        //往channel map中添加channel信息
        String uuid = IpUtils.getUuid(ctx);
        String ip = IpUtils.getIPString(ctx);
        GatewayService.addGatewayChannel(ip, (SocketChannel) ctx.channel());
        ELog.print("Netty CLIENT" + ctx.channel().id().asLongText());
        ELog.print("Netty CLIENT" + IpUtils.getRemoteAddress(ctx) + " 接入连接");
        AndPipe.l().deviceConnect(uuid, ip);
        inBoundMessageCall.deviceConnect(uuid, ip);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //删除Channel Map中的失效Client
        String ip = IpUtils.getIPString(ctx);
        String uuid = IpUtils.getUuid(ctx);
        inBoundMessageCall.deviceDisConnect(uuid, ip);
        GatewayService.removeGatewayChannel(ip);
        AndPipe.l().deviceDisConnect(uuid, ip);
        ELog.print("Netty CLIENT" + IpUtils.getRemoteAddress(ctx) + " 断开连接");
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        //删除Channel Map中的失效Client
        String ip = IpUtils.getIPString(ctx);
        String uuid = IpUtils.getUuid(ctx);
        inBoundMessageCall.deviceDisConnect(uuid, ip);
        GatewayService.removeGatewayChannel(ip);
        ELog.print("Netty CLIENT" + IpUtils.getRemoteAddress(ctx) + "exceptionCaught断开连接");
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        String ip = IpUtils.getIPString(ctx);
        String uuid = IpUtils.getUuid(ctx);
        String cmd = HexUtils.bytesToHex(msg);
        inBoundMessageCall.messageRead(uuid, ip, cmd);

        /*读取成功，重置输入流超时状态*/
        Indicate.l().messageRead(uuid, ip, cmd);
    }

    /**
     * 超时监听，socket意义上的超时，并不是硬件通信的超时
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        String ip = IpUtils.getIPString(ctx);
        String uuid = IpUtils.getUuid(ctx);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                AndPipe.l().readOutTime(uuid, ip);
                ELog.print("Netty Client: " + ip + " READER_IDLE 读超时");
                inBoundMessageCall.readOutTime(uuid, ip);
                ctx.disconnect();
                Indicate.l().readOutTime(uuid, ip, true);
                AndPipe.l().readOutTime(uuid, ip);
            } else if (event.state() == IdleState.WRITER_IDLE) {
                AndPipe.l().writeOutTime(uuid, ip);
                ELog.print("Netty Client: " + ip + " WRITER_IDLE 写超时");
                inBoundMessageCall.writeOutTime(uuid, ip);
                ctx.disconnect();
                Indicate.l().writeOutTime(uuid, ip, true);
                AndPipe.l().writeOutTime(uuid, ip);
            } else if (event.state() == IdleState.ALL_IDLE) {
                AndPipe.l().outTime(uuid, ip);
                AndPipe.l().outTime(uuid, ip);
                ELog.print("Netty Client: " + ip + " ALL_IDLE 总超时");
                inBoundMessageCall.outTime(uuid, ip);
                ctx.disconnect();
                Indicate.l().outTime(uuid, ip, true);
            }
        }
    }


}
