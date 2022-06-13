package com.ebig.socket.dispatchWrite.finger;

import com.ebig.socket.dispatchWrite.base.BaseFingerApiBase;

public class FingerInfoBaseCmd implements BaseFingerApiBase {
    private FingerParam param;

    public FingerInfoBaseCmd(FingerParam param) {
        this.param = param;
    }

    @Override
    public String getCmd() {
        return param.getCmd();
    }
}
