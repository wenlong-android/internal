package com.ebig.socket.dispatchRead.handler;

import com.ebig.log.ELog;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.common.PipeBus;
import com.ebig.socket.entity.CmdType;
import com.ebig.utils.HexUtils;

/*电子秤*/
public class Handler4Scale implements BaseHandler {
    private ChainHandler handler;


    @Override
    public void bind(ChainHandler baseHandler) {
        this.handler = baseHandler;
    }

    @Override
    public void onNextHanlder(CmdResultInfo info) {
        if (CmdType.isFingerDown(info.getOrder())) {
           ELog.print("Handler电子秤处理:" + info.getCmd() + ",data:" + info.getData());
            //    dispatchSevice.onHearBeat(System.currentTimeMillis());
//            if (info.getData().startsWith(TypeConstance.C_03)) {
//               ELog.print("总data:"+msg);
//               ELog.print(HexUtils.deUnicode(msg));
//            }
            String msg=info.getData();
            int len=msg.length();
            String fix=msg.substring(2,len-2);
           ELog.print("截取："+fix);
           ELog.print(HexUtils.deUnicode(fix));
            PipeBus.l().receive(info);
        } else {
            handler.nextIndex(info);
        }
    }


}
