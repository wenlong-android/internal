package com.ebig.socket.common;


import com.ebig.log.ELog;
import com.ebig.utils.HexUtils;
import com.ebig.socket.entity.RtError;
import com.ebig.socket.entity.CmdRequestInfo;
import com.ebig.socket.idl.SenderListenner;
import com.ebig.socket.netty.GatewayService;

import java.util.Timer;
import java.util.TimerTask;

import io.netty.channel.socket.SocketChannel;

public class PipeWriterCopy {
    private static class Lazy {
        private static PipeWriterCopy sender = new PipeWriterCopy();
    }

    private Timer timer;
    private volatile boolean isDispatching = false;
    private volatile int sendCount = 0;
    private CmdRequestInfo mTask;

    public static PipeWriterCopy l() {
        return Lazy.sender;
    }

    public void send(CmdRequestInfo task) {
        this.mTask = task;
        isDispatching = true;
        SenderListenner listenner = task.getListenner();
        SocketChannel channel = GatewayService.getGatewayChannel("192.168.1.189" );
        if (channel != null) {
            sendCount = 0;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    channel.writeAndFlush(HexUtils.hexStringToBytes(task.cmd()));
                    sendCount++;
                    if (sendCount >= 5) {
                        if (listenner != null) {
                            listenner.onFail(RtError.resendLimit);
                            isDispatching = false;
                        }
                        sendCount = 0;
                        mTask = null;
                        cancle();
                        ELog.print(RtError.resendLimit);
                    } else {
                        ELog.print("调用次数：" + sendCount + " 写超时：" + Indicate.isWriteOutTime() + " ,读写超时：" + Indicate.rwOutTime);
                    }
                }
            }, 0, 200);

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
                cancle();
            }
        }

    }

    private void cancle() {
        if (timer != null) {
            mTask = null;
            isDispatching = false;
            timer.cancel();
        }
    }

    public boolean isDispatching() {
        return isDispatching;
    }

    public CmdRequestInfo getTask() {
        return mTask;
    }
}
