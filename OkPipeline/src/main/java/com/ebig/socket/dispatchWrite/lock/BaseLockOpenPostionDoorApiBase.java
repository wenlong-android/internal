package com.ebig.socket.dispatchWrite.lock;

import com.ebig.socket.dispatchWrite.base.BaseLockApiBase;

/*打开前后门*/
public class BaseLockOpenPostionDoorApiBase implements BaseLockApiBase {

    private int index;
    private String action;

    public BaseLockOpenPostionDoorApiBase(int index, String action) {
        this.index = index;
        this.action = action;
    }

    @Override
    public String getCmd() {
        return "动作："+action+",门号："+index;
    }
}
