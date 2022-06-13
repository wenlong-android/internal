package com.ebig.socket.dispatchWrite.lock;

import android.annotation.SuppressLint;

import com.ebig.socket.dispatchWrite.base.BaseLockApiBase;

/*打开前后门*/
public class BaseLockOpenBothDoorApiBase implements BaseLockApiBase {
    @SuppressLint("WrongConstant")
    @Override
    public String getCmd() {
        return "01010201";
    }
}
