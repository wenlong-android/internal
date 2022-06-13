package com.ebig.socket.dispatchRead.handler;

import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.common.PipeBus;
import com.ebig.socket.entity.CmdType;

/*心跳*/
public class Handler4Idle implements BaseHandler {
    private ChainHandler handler;
    @Override
    public void bind(ChainHandler baseHandler) {
        this.handler = baseHandler;
    }
    @Override
    public void onNextHanlder(CmdResultInfo info ) {
        if (CmdType.isIdel(info.getOrder())) {
            info.setType(CmdType.TYPE_IDLE_STR);
            long internal = System.currentTimeMillis() - TimeInternal.idelStart;
            PipeBus.l().idel(internal);
            TimeInternal.idelStart = System.currentTimeMillis();
        } else {
            handler.nextIndex(info);
        }
    }

}
