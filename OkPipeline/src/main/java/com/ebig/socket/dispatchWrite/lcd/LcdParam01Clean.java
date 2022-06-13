package com.ebig.socket.dispatchWrite.lcd;

import com.ebig.socket.entity.LcdC;
import com.ebig.socket.entity.LcdMode;
import com.ebig.socket.utils.CrossoStr;

public class LcdParam01Clean  implements LcdParam{
    private String mode;

    public LcdParam01Clean() {
        setMode(LcdMode.clear);
    }

    private String getMode() {
        return mode;
    }

    private void setMode(String mode) {
        this.mode = mode;
    }

    public String getFinalCmdString() {
        return CrossoStr.getLcdCmd(LcdC.mode,mode);
    }
}
