package com.ebig.socket.listenner;

import com.ebig.idl.CommonCall;
import com.ebig.log.ELog;

public class Listenner4Scanner {
    private static CommonCall<String> scanCall;

    public static void setReaderCall(CommonCall<String> call) {
        scanCall = call;
    }

    public static void onScanResult(String data){
        ELog.print("读卡器收到信息："+data);
        if (scanCall!=null){
            scanCall.onCommonCall(data);
        }
    }
}
