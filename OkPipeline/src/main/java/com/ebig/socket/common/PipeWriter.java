package com.ebig.socket.common;


import com.ebig.log.ELog;
import com.ebig.socket.entity.RtError;
import com.ebig.socket.entity.CmdRequestInfo;
import com.ebig.socket.idl.SenderListenner;
import com.ebig.socket.netty.GatewayService;
import com.ebig.utils.HexUtils;

import io.netty.channel.socket.SocketChannel;

public class PipeWriter {
    private static class Lazy {
        private static PipeWriter sender = new PipeWriter();
    }


    private volatile boolean isDispatching = false;

    private CmdRequestInfo mTask;

    public static PipeWriter make() {
        return Lazy.sender;
    }

    public void send(CmdRequestInfo task) {
        this.mTask = task;
        isDispatching = true;
        SenderListenner listenner = task.getListenner();
        SocketChannel channel = GatewayService.getGatewayChannel(PipeBus.l().getHost());
        if (channel != null) {
            channel.writeAndFlush(HexUtils.hexStringToBytes(task.cmd()));
            ELog.print(" 写超时：" + Indicate.isWriteOutTime() + " ,读写超时：" + Indicate.rwOutTime);
        } else {
            mTask = null;
            isDispatching = false;
            if (listenner != null)
                listenner.onFail(RtError.client_lost);
            ELog.print(RtError.client_lost);
        }
    }

    public void cancleIfMatch(String order) {
        if (mTask != null) {
            String hex = HexUtils.makeReciveHex(mTask.order());
            if (hex.equals(order)) {

            }
        }

    }



    public boolean isDispatching() {
        return isDispatching;
    }

    public CmdRequestInfo getTask() {
        return mTask;
    }
}
