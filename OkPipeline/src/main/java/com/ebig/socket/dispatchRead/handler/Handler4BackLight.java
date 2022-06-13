package com.ebig.socket.dispatchRead.handler;

import com.ebig.log.ELog;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.common.PipeBus;
import com.ebig.socket.entity.CmdType;

/*指静脉*/
public class Handler4BackLight implements BaseHandler {
    private ChainHandler handler;


    @Override
    public void bind(ChainHandler baseHandler) {
        this.handler=baseHandler;
    }

    @Override
    public void onNextHanlder(CmdResultInfo info ) {
        if (CmdType.isBackLUp(info.getOrder())){
           ELog.print("Handler背光灯处理:"+info.getCmd()+",data:"+info.getData());
           // dispatchSevice.onHearBeat(System.currentTimeMillis());
            PipeBus.l().receive(info);
        }else {
            handler.nextIndex(info);
        }
    }



}
