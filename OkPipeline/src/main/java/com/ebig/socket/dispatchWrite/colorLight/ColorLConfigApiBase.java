package com.ebig.socket.dispatchWrite.colorLight;

public class ColorLConfigApiBase implements ColorLightApiBase {
    private CLightParam param;

    public ColorLConfigApiBase(CLightParam param) {
        this.param = param;
    }

    @Override
    public String getCmd() {
        return param.makeCmd();
    }
}
