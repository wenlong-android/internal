package com.ebig.socket.dispatchRead.handler;

import com.ebig.log.ELog;
import com.ebig.socket.common.AndPipe;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.entity.CmdType;

/*三色灯*/
public class Handler4ColorL implements BaseHandler {
    private ChainHandler handler;

    @Override
    public void bind(ChainHandler baseHandler) {
        this.handler=baseHandler;
    }

    @Override
    public void onNextHanlder(CmdResultInfo info ) {
        if (CmdType.isColorLUp(info.getOrder())){
           ELog.print("Handler三色灯处理:"+info.getCmd()+",data:"+info.getData());
            //dispatchSevice.onHearBeat(System.currentTimeMillis());
            AndPipe.l().receive(info);
        }else {
            handler.nextIndex(info);
        }
    }


}
