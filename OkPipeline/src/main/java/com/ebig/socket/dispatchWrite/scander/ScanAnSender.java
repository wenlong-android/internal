package com.ebig.socket.dispatchWrite.scander;
import com.ebig.socket.dispatchWrite.base.AnSender;
import com.ebig.socket.entity.TypeConstance;

public class ScanAnSender extends AnSender implements ScanSenderIdl {
    @Override
    public String getOrder() {
        return TypeConstance.T_SCANE_SEND;
    }

    @Override
    public ScanAnSender scan() {
        return  (ScanAnSender) this.with(new BaseScanStratApiBase());
    }


}
