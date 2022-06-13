package com.ebig.temperature_humidity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.ebig.idl.CommonCall;
import com.ebig.log.ELog;
import com.ebig.service.ICmdService;
import com.ebig.socket.bean.ThEntity;
import com.ebig.utils.StrUtils;
import com.minjie.libcmd.ITHServiceCall;
import com.minjie.libcmd.IthListenner;

public class ThService extends Service implements ICmdService, CommonCall<String> {
    private String host;
    private int port;
    private ThClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        ELog.print("ThClient onCreate:");
    }

    @Override
    public void onCommonCall(String json) {
        ELog.print("ThClient onCommonCall:" + json);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        ELog.print("ThClient onBind:");
        host = intent.getStringExtra("host");
        port = intent.getIntExtra("port", 0);
        if (StrUtils.notEmpty(host) && port != 0) {
            ELog.print("ThClient ip:" + host + " port:" + port);
//            client = new ThClient.Config(host, port).addReadCall(this).build();
//            client.start();
        }

        return serviceCall.asBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void begin(Context context) {

    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    private ITHServiceCall serviceCall = new ITHServiceCall.Stub() {
        @Override
        public void regist(IthListenner call) throws RemoteException {

        }

        @Override
        public void sendJson(String json) {
            ELog.print("ThClient sendJson:" + json+" client:"+client);
            if (client != null) {
                client.send(json);
            }
        }
    };
}
