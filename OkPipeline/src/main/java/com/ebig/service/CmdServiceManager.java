package com.ebig.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.IntDef;

import com.ebig.socket.common.PipeBus;
import com.ebig.socket.common.SocketIoManager;
import com.ebig.socket.entity.RemoteIndex;
import com.ebig.socket.entity.RomoteCmd;
import com.ebig.utils.AppGlobals;
import com.google.gson.Gson;
import com.minjie.libcmd.ICmdServiceCall;
import com.minjie.libcmd.IResultListenner;

public class CmdServiceManager implements ICmdService {
    private static class L {
        private static CmdServiceManager manager = new CmdServiceManager();
    }

    public static CmdServiceManager l() {
        return L.manager;
    }

    private static Context AppContext;
    private static ICmdServiceCall iCmdServiceCall;

    @Override
    public void begin(Context context) {
        AppContext = context;
         //if (!PackUtils.isServiceRunning(context, "com.ebig.service.PiplelineService")) {
        Intent intent = new Intent(context, PiplelineService.class);
        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
//        } else {
//            Log.i("onServiceConnected", "服务已经启动");
//        }

    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iCmdServiceCall = ICmdServiceCall.Stub.asInterface(service);
        try {
            iCmdServiceCall.regist(new IResultListenner.Stub() {
                @Override
                public void onResult(@RemoteIndex int index, String json) throws RemoteException {
                    if (index == RemoteIndex.messageRead) {
                        RomoteCmd info = new Gson().fromJson(json, RomoteCmd.class);
                        SocketIoManager.load().accept(info.getUuid(), info.getHost(), info.getCmd());

                        PipeBus.l().deviceConnect(info.getUuid(), info.getHost());
                    } else if (index == RemoteIndex.rabbitMsg) {

                    } else if (index == RemoteIndex.deviceConnect) {
                        RomoteCmd info = new Gson().fromJson(json, RomoteCmd.class);
                        PipeBus.l().deviceConnect(info.getUuid(), info.getHost());
                    } else if (index == RemoteIndex.deviceDisConnect) {
                        RomoteCmd info = new Gson().fromJson(json, RomoteCmd.class);
                        PipeBus.l().deviceDisConnect(info.getUuid(), info.getHost());
                    }
                }
            });
        } catch (RemoteException e) {


        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }


    public void sendCmd(String cmd) {
        if (iCmdServiceCall != null) {
            try {
                iCmdServiceCall.sendCmd(cmd);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void release() {
        Intent intent = new Intent(AppGlobals.getApplication(), PiplelineService.class);
        AppGlobals.getApplication().unbindService(this);
        AppGlobals.getApplication().stopService(intent);

    }
}
