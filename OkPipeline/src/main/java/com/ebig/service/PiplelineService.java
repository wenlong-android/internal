package com.ebig.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.ebig.log.ELog;
import com.ebig.socket.common.PipeWriter;
import com.ebig.socket.entity.CmdRequestInfo;
import com.ebig.socket.idl.PipeServerCall;
import com.ebig.socket.netty.PipeSocketServer;
import com.google.gson.Gson;
import com.minjie.libcmd.ICmdServiceCall;
import com.minjie.libcmd.IResultListenner;

import java.util.Timer;


public class PiplelineService extends Service implements PipeServerCall {
    private Timer timer;
    private RemoteCallbackList<IResultListenner> reomteResult=new RemoteCallbackList<>();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        ELog.print("onServiceConnected onBind");
        return cmdServiceCall.asBinder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        ELog.print("onServiceConnected onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        ELog.print("onServiceConnected onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        ELog.print("onServiceConnected onCreate");
        super.onCreate();
        PipeSocketServer server= PipeSocketServer.getInstance().withPort(8080);
        if (server!=null){
            server.addStartResult(this).addListenner(new SocketListenner(reomteResult)).start();
        }

    }


    @Override
    public void onResult(boolean isSuccess) {

    }

    /**
     * @param intent
     * @param flags
     * @param startId
     * @return
     */

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ELog.print("onServiceConnected onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // HermesEventBus.getDefault().destroy();
    }

     private final ICmdServiceCall cmdServiceCall=new ICmdServiceCall.Stub() {
         @Override
         public void regist(IResultListenner call) throws RemoteException {
             reomteResult.register(call);
         }

         @Override
         public void sendCmd(String cmd) throws RemoteException {
             ELog.print("onServiceConnected  "+"cendCmd:"+cmd);
             CmdRequestInfo task=new Gson().fromJson(cmd,CmdRequestInfo.class);
             PipeWriter.make().send(task);
         }
     };

}
