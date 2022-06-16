package com.ebig.service;

import android.annotation.SuppressLint;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.ebig.log.ELog;
import com.ebig.socket.entity.RemoteIndex;
import com.ebig.socket.entity.RomoteCmd;
import com.google.gson.Gson;
import com.minjie.libcmd.IResultListenner;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RemoteFixUtils {
    private static Lock mLock= new ReentrantLock();
    public static synchronized void cmd(String uuid, String host, String cmd, RemoteCallbackList<IResultListenner> r) {
        common(RemoteIndex.messageRead,  uuid,host,cmd,r);
    }

    @SuppressLint("WrongConstant")
    public static void deviceConnect(String uuid, String ipHost,  RemoteCallbackList<IResultListenner> r){
        common( RemoteIndex.deviceConnect,uuid,ipHost,null,r);
    }

    public static void deviceDisConnect(String uuid, String ipHost, RemoteCallbackList<IResultListenner> r){
        common(RemoteIndex.deviceDisConnect,uuid,ipHost,null,r);
    }

    public static void writeOutTime(String uuid, String ipHost,  RemoteCallbackList<IResultListenner> r) {
        common(RemoteIndex.writeOutTime,uuid,ipHost,null,r);
    }


    public static void readOutTime(String uuid, String ipHost,  RemoteCallbackList<IResultListenner> r) {
        common(RemoteIndex.readOutTime,uuid,ipHost,null,r);
    }


    public static void outTime(String uuid, String ipHost,  RemoteCallbackList<IResultListenner> r) {
        common(RemoteIndex.outTime,uuid,ipHost,null,r);
    }


    private static void common(@RemoteIndex int index,String uuid, String host, String cmd, RemoteCallbackList<IResultListenner> r) {
        try {
            final RomoteCmd remote=new RomoteCmd(uuid,host,cmd);
            mLock.lock();
            int n = r.beginBroadcast();
            for (int i = 0; i < n; i++) {
               String packName= r.getBroadcastItem(i).asBinder().getClass().getPackage().getName();
               if (packName.equals("android.os")){
                   r.getBroadcastItem(i).onResult(index,  new Gson().toJson(remote));
               }


            }
            r.finishBroadcast();
        } catch (RemoteException e) {
            e.printStackTrace();
        }finally {
            mLock.unlock();
        }
    }
}
