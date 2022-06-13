package com.ebig.socket.dispatchRead.handler;

import com.ebig.socket.entity.CmdResultInfo;

public interface BaseHandler {
    void onNextHanlder(CmdResultInfo info);
    void bind(ChainHandler chainHandler);
}
