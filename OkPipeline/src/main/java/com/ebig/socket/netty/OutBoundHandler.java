package com.ebig.socket.netty;


import com.ebig.log.ELog;
import com.ebig.utils.HexUtils;
import com.ebig.socket.common.Indicate;
import com.ebig.socket.utils.IpUtils;


import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class OutBoundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg,
                      ChannelPromise promise) throws Exception {

        if (msg instanceof byte[]) {
            byte[] bytesWrite = (byte[]) msg;
            ByteBuf buf = ctx.alloc().buffer(bytesWrite.length);
            //ELog.print("Netty CLIENT向设备下发的信息为："+ HexUtils.bytesToHex(bytesWrite));
            buf.writeBytes(bytesWrite);
            ctx.writeAndFlush(buf).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    /*下发成功，重置写出流超时状态*/
                    String uuid = IpUtils.getUuid(ctx);
                    String ip = IpUtils.getIPString(ctx);
                    Indicate.l().writeOutTime(uuid, ip, false);
                    String cmd = HexUtils.bytesToHex(bytesWrite);
                    /*读取成功，重置输入流超时状态*/
                    Indicate.l().messageRead(uuid, ip, cmd);
                    ELog.print("Netty CLIENT" + "下发成功！" + cmd);
                }
            });

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        if (channel.isActive()) ctx.close();
    }
}

