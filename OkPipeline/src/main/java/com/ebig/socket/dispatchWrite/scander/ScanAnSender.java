package com.ebig.socket.dispatchWrite.scander;
import com.ebig.idl.CommonCall;
import com.ebig.socket.dispatchWrite.base.AnSender;
import com.ebig.socket.entity.TypeConstance;
import com.ebig.socket.listenner.Listenner4Scanner;

public class ScanAnSender extends AnSender implements ScanSenderIdl {
    @Override
    public String getOrder() {
        return TypeConstance.T_SCANE_SEND;
    }

    @Override
    public ScanAnSender scan(CommonCall<String> scanCall) {
        Listenner4Scanner.setReaderCall(scanCall);
        return  (ScanAnSender) this.with(new BaseScanStratApiBase());
    }


}
