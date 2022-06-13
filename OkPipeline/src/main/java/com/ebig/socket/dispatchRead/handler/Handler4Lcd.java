package com.ebig.socket.dispatchRead.handler;

import com.ebig.log.ELog;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.common.PipeBus;
import com.ebig.socket.entity.CmdType;

public class Handler4Lcd implements BaseHandler {
    private ChainHandler handler;

    @Override
    public void bind(ChainHandler baseHandler) {
        this.handler=baseHandler;
    }

    @Override
    public void onNextHanlder(CmdResultInfo info ) {
        if (CmdType.isLcdUp(info.getOrder())){
           ELog.print("Handler LCD处理:"+info.getCmd()+",data:"+info.getData());
           // dispatchSevice.onHearBeat(System.currentTimeMillis());
            PipeBus.l().receive(info);
        }else {
            handler.nextIndex(info );
        }
    }


}
