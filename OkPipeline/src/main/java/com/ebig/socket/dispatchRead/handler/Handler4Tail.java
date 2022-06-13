package com.ebig.socket.dispatchRead.handler;

import com.ebig.log.ELog;
import com.ebig.socket.entity.CmdResultInfo;

/*温湿度*/
public class Handler4Tail implements BaseHandler {
    private ChainHandler handler;


    @Override
    public void bind(ChainHandler baseHandler) {
        this.handler = baseHandler;
    }

    @Override
    public void onNextHanlder(CmdResultInfo info) {
       ELog.print("Handler尾部处理:" + info.getCmd()+",没有对应的处理类");
    }

}
