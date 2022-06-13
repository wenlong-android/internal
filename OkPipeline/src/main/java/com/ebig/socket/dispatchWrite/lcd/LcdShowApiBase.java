package com.ebig.socket.dispatchWrite.lcd;
public class LcdShowApiBase implements LcdApiBase {
    private LcdParam param;

    public LcdShowApiBase(LcdParam param) {
        this.param = param;
    }

    @Override
    public String getCmd() {
        /*编码+长度+数据*/
        return  param.getFinalCmdString();
    }
}
