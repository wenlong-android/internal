package com.ebig.socket.dispatchRead.handler;

import com.ebig.socket.entity.CmdResultInfo;

public interface ChainHandler {
    public boolean isShowHandlerLog=false;
    void nextIndex(CmdResultInfo info);
    void bind(ChainHandler baseHandler);
}
