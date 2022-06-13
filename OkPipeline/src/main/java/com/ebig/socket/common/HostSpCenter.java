package com.ebig.socket.common;

import com.ebig.sp.BaseSp;

public class HostSpCenter {
    private static final String clientIp="clientIp";
    public static String getClientIp(){
        return BaseSp.l().getString(clientIp);
    }
    public static void saveClientIp(String ip){
        BaseSp.l().putString(clientIp,ip);
    }
}
