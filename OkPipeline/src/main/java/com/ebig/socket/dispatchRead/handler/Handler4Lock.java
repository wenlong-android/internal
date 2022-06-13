package com.ebig.socket.dispatchRead.handler;

import com.ebig.log.ELog;
import com.ebig.socket.common.PipeBus;
import com.ebig.utils.StrUtils;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.entity.CmdType;

import java.util.List;

public class Handler4Lock implements BaseHandler {
    private ChainHandler handler;


    @Override
    public void bind(ChainHandler baseHandler) {
        this.handler=baseHandler;
    }

    @Override
    public void onNextHanlder(CmdResultInfo info ) {
        if (CmdType.isDoorUp(info.getOrder())){
           ELog.print("Handler门锁处理:"+info.getCmd()+",data:"+info.getData());
            List<String> dataArr= StrUtils.spiltHex(info.getData());
//            SenderLockListenner lockListenner=(SenderLockListenner)answer.getListenner();
//            /*数据区为6位，3位地址码+锁id+锁状态+传感器状态*/
//            if (StrUtils.objNotNull(dataArr)&&dataArr.size()==6) {
//                if (dataArr.get(4).equals(RtLock.LockOpen)) {
//                   ELog.print("Handler门锁手动打开");
//                    if (lockListenner!=null){
//                        lockListenner.doorOpen();
//                    }
//                }else  if (dataArr.get(4).equals(RtLock.LockClose)) {
//                   ELog.print("Handler门锁手动关闭");
//                    if (lockListenner!=null){
//                        lockListenner.doorClose();
//                    }
//                }
//            }
           // dispatchSevice.onHearBeat(System.currentTimeMillis());
            PipeBus.l().receive(info);
        }else {
            handler.nextIndex(info);
        }
    }



}
