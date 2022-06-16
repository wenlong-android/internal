package com.ebig.service;

import android.annotation.SuppressLint;
import android.os.RemoteCallbackList;

import com.ebig.log.ELog;
import com.ebig.socket.common.SocketInterfaces;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.minjie.libcmd.IResultListenner;

public class SocketListenner implements SocketInterfaces {
    private RemoteCallbackList<IResultListenner> reomteResult;

    public SocketListenner(RemoteCallbackList<IResultListenner> reomteResult) {
        this.reomteResult = reomteResult;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void deviceConnect(String uuid, String ipHost) {
        RemoteFixUtils.deviceConnect(uuid,ipHost,reomteResult);
    }

    @Override
    public void deviceDisConnect(String uuid, String ipHost) {
        RemoteFixUtils.deviceDisConnect(uuid,ipHost,reomteResult);
    }

    @Override
    public void messageRead(String uuid, String ipHost, String cmd) {
        ELog.print("messageRead uuid:" + uuid+" ipHost:"+ipHost+" cmd:"+cmd);
//        LiveEventBus
//                .get("some_key")
//                .postAcrossProcess("some_value");
        RemoteFixUtils.cmd(uuid, ipHost, cmd, reomteResult);
    }

    @Override
    public void writeOutTime(String uuid, String ipHost) {
        RemoteFixUtils.writeOutTime(uuid,ipHost,reomteResult);
    }

    @Override
    public void readOutTime(String uuid, String ipHost) {
        RemoteFixUtils.readOutTime(uuid,ipHost,reomteResult);
    }

    @Override
    public void outTime(String uuid, String ipHost) {
        RemoteFixUtils.outTime(uuid,ipHost,reomteResult);
    }
}
