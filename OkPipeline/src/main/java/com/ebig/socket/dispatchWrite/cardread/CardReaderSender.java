package com.ebig.socket.dispatchWrite.cardread;

import com.ebig.idl.CommonCall;
import com.ebig.socket.listenner.Listenner4CardReader;

public class CardReaderSender {
    CommonCall<String> call;

    public void setCall(CommonCall<String> call) {
        Listenner4CardReader.setReaderCall(call);
        this.call = call;
    }
}
