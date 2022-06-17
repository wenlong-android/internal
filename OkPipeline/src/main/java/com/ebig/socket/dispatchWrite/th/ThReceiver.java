package com.ebig.socket.dispatchWrite.th;

import com.ebig.idl.Common3Call;
import com.ebig.idl.CommonCall3;
import com.ebig.socket.listenner.Listenner4Th;

public class ThReceiver {

    public  void addReciveCall(CommonCall3<Double, Double, Long> common3Call) {
        Listenner4Th.setCommon3Call(common3Call);
    }
}
