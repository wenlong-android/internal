package com.ebig.socket.dispatchRead;

import com.ebig.log.ELog;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.dispatchRead.handler.BaseHandler;
import com.ebig.socket.dispatchRead.handler.ChainHandler;
import com.ebig.socket.idl.SenderListenner;

import java.util.ArrayList;
import java.util.List;

public class Chain4Handler implements ChainHandler {
    private int index=0;
    private List<BaseHandler> handlerList=new ArrayList<>();

    public Chain4Handler() {
        index=0;
    }

    public Chain4Handler addHandler(BaseHandler clazz){
        handlerList.add(clazz);
        clazz.bind(Chain4Handler.this);
        return Chain4Handler.this;
    }

    public Chain4Handler bind(SenderListenner listenner){
        return Chain4Handler.this;
    }


    @Override
    public void nextIndex(CmdResultInfo info) {
        if (handlerList.isEmpty()) {
            return;
        } else if (index >= handlerList.size()-1) {
            return;
        } else {

            BaseHandler task = handlerList.get(index);
            if (isShowHandlerLog){
               ELog.print("index:"+index+"  ,name:"+task.getClass().getSimpleName());
            }

            index++;
            task.onNextHanlder(info);

        }
    }

    @Override
    public void bind(ChainHandler baseHandler) {

    }

}
