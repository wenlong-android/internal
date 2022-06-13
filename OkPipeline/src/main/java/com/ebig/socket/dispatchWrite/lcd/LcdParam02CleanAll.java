package com.ebig.socket.dispatchWrite.lcd;

import com.ebig.socket.entity.LcdC;
import com.ebig.socket.entity.LcdMode;

public class LcdParam02CleanAll  implements LcdParam{
    private String mode;

    public LcdParam02CleanAll() {
        setMode(LcdMode.clearAll);
    }

    private String getMode() {
        return mode;
    }

    private void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String getFinalCmdString() {
        return LcdC.mode+"01"+mode;
    }
}
