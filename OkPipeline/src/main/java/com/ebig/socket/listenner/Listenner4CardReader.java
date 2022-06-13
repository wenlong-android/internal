package com.ebig.socket.listenner;

import com.ebig.idl.CommonCall;
import com.ebig.log.ELog;

public class Listenner4CardReader {
    private static CommonCall<String> readerCall;

    public static void setReaderCall(CommonCall<String> call) {
         readerCall = call;
    }

    public static void onReceive(String data){
        ELog.print("读卡器收到信息："+data);
        if (readerCall!=null){
            readerCall.onCommonCall(data);
        }
    }
}
