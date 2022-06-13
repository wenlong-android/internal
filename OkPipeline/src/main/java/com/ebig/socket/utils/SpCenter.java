package com.ebig.socket.utils;

import com.ebig.sp.BaseSp;

public class SpCenter {
    public static class H{
        //连接柜子ip和端口号
        private static final String cabinectHost="cabinectHost";
    }
    public static void saveCabinectHost(String host,int port){
        BaseSp.l().putString(H.cabinectHost,host+":"+port);
    }
    public static String getCabinectHost(){
        return BaseSp.l().getString(H.cabinectHost);
    }
}
