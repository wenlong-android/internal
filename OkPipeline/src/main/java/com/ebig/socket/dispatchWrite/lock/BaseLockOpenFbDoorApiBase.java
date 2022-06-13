package com.ebig.socket.dispatchWrite.lock;

import com.ebig.socket.dispatchWrite.base.BaseLockApiBase;

/*打开前后门*/
public class BaseLockOpenFbDoorApiBase implements BaseLockApiBase {


    @Override
    public String getCmd() {
        return "7e0400be01000074777f";
    }
}
