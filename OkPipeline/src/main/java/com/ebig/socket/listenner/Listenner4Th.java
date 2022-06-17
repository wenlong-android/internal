package com.ebig.socket.listenner;

import com.ebig.idl.Common3Call;
import com.ebig.idl.CommonCall3;

public class Listenner4Th {
    private static CommonCall3<Double,Double,Long> common3Call;

    public static void setCommon3Call(CommonCall3<Double, Double, Long> call) {
        common3Call = call;
    }
    public static void onThRecive(double t,double h,long interval){
        if (common3Call!=null){
            common3Call.onCommonCall(t,h,interval);
        }
    }
}
