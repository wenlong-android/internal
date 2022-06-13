package com.ebig.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.ebig.log.ELog;
import com.ebig.socket.common.PipeBus;
import com.ebig.socket.common.SocketIoManager;
import com.ebig.socket.entity.RemoteIndex;
import com.ebig.socket.entity.RomoteCmd;
import com.ebig.temperature_humidity.ThService;
import com.ebig.utils.AppGlobals;
import com.google.gson.Gson;
import com.minjie.libcmd.IResultListenner;
import com.minjie.libcmd.ITHServiceCall;
import com.minjie.libcmd.IthListenner;

public class THServiceManager implements ServiceConnection     {
    private static class L {
        private static THServiceManager manager = new THServiceManager();
    }

    public static THServiceManager l() {
        return L.manager;
    }

    private static Context AppContext;
    private static ITHServiceCall ithServiceCall;


    public void begin(String host,int port) {
        release();
         //if (!PackUtils.isServiceRunning(context, "com.ebig.service.PiplelineService")) {
        Intent intent = new Intent(AppGlobals.getApplication(), ThService.class);
        intent.putExtra("host",host);
        intent.putExtra("port",port);
        AppGlobals.getApplication().bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        ELog.print("ThClient onServiceConnected " +name);
        ithServiceCall = ITHServiceCall.Stub.asInterface(service);
        try {
            ithServiceCall.regist(new IthListenner.Stub() {
                @Override
                public void onResult(int index, String json) throws RemoteException {
                    ELog.print("ThClient onResult :" +json);
                }
            });
        } catch (RemoteException e) {


        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }


    public void sendCmd(String json) {
       // ELog.print("ThClient sendCmd :" +json);
        if (ithServiceCall != null) {
            try {
                ithServiceCall.sendJson(json);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void release() {
        if (PackUtils.isServiceRunning(AppGlobals.getApplication(), "com.ebig.temperature_humidity.ThService")){
            Intent intent = new Intent(AppGlobals.getApplication(), ThService.class);
            AppGlobals.getApplication().unbindService(this);
            AppGlobals.getApplication().stopService(intent);
        }


    }
}
