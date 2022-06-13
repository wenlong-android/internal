package com.ebig.socket.dispatchRead.handler;

import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.entity.CmdType;

public class Handler4Answer implements BaseHandler {
    private ChainHandler handler;


    @Override
    public void bind(ChainHandler baseHandler) {
        this.handler=baseHandler;
    }

    @Override
    public void onNextHanlder(CmdResultInfo info) {

        if (CmdType.isAnswerUp(info.getOrder())){
//            SenderListenner listenner=answer.getListenner();
//            switch (answer.order()){
//                case CTypeWrite.TypeLock:
//                    ((SenderLockListenner)listenner).lockCall();
//                    break;
//                case CTypeWrite.TypeBackLight:
//                    ((SenderBacklightListenner)listenner).ok();
//                    break;
//
//                case CTypeWrite.TypeColorLight:
//                    ((SenderColorLightListenner)listenner).ok();
//                    break;
//
//                case CTypeWrite.TypeLcd:
//                    // ((SenderBacklightListenner)listenner).ok();
//                    break;
//
//                case CTypeWrite.TypeFinger:
//                    // ((SenderBacklightListenner)listenner).ok();
//                    break;
//
//                case CTypeWrite.TypeScale:
//                    // ((SenderBacklightListenner)listenner).ok();
//                    break;
//
//                case CTypeWrite.TypeScan:
//                    // ((SenderBacklightListenner)listenner).ok();
//                    break;
//            }
//           ELog.print("Handler消息应答处理:"+info.getCmd());
           // dispatchSevice.onHearBeat(System.currentTimeMillis());
        }else {
            handler.nextIndex(info);
        }
    }
}
