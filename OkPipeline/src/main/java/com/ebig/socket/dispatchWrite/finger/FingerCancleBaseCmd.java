package com.ebig.socket.dispatchWrite.finger;

import com.ebig.socket.dispatchWrite.base.BaseFingerApiBase;

public class FingerCancleBaseCmd implements BaseFingerApiBase {
    private FingerParam param;
    public FingerCancleBaseCmd(FingerParam param) {
        this.param = param;
    }

    @Override
    public String getCmd() {
        return param.getCmd();
    }
}
