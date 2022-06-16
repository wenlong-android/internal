package com.ebig.socket.dispatchRead.handler;

import com.ebig.log.ELog;
import com.ebig.socket.listenner.Listenner4CardReader;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.entity.CmdType;

public class Handler4CardReader implements BaseHandler {
    private ChainHandler handler;

    @Override
    public void bind(ChainHandler baseHandler) {
        this.handler=baseHandler;
    }

    @Override
    public void onNextHanlder(CmdResultInfo info) {
        if (CmdType.isCardReaderUp(info.getOrder())){
           ELog.print("Handler读卡器处理:"+info.getCmd()+",data:"+info.getData());
         //   dispatchSevice.onHearBeat(System.currentTimeMillis());
            Listenner4CardReader.onReceive(info.getData());
            //AndPipe.l().receive(info);
        }else {
            handler.nextIndex(info );
        }
    }

}
