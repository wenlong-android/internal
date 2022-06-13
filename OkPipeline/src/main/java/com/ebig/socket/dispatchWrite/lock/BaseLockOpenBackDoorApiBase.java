package com.ebig.socket.dispatchWrite.lock;

import android.annotation.SuppressLint;

import com.ebig.socket.dispatchWrite.base.BaseLockApiBase;

/*打开后门*/
public class BaseLockOpenBackDoorApiBase implements BaseLockApiBase {
    @SuppressLint("WrongConstant")
    @Override
    public String getCmd() {
        return  "0201";
    }
}
