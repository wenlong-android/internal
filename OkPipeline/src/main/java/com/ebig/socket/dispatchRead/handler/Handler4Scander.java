package com.ebig.socket.dispatchRead.handler;

import com.ebig.log.ELog;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.common.PipeBus;
import com.ebig.socket.entity.CmdType;
import com.ebig.socket.listenner.Listenner4Scanner;
import com.ebig.utils.HexUtils;

/*扫描头*/
public class Handler4Scander implements BaseHandler {
    private ChainHandler handler;


    @Override
    public void bind(ChainHandler baseHandler) {
        this.handler=baseHandler;
    }

    @Override
    public void onNextHanlder(CmdResultInfo info) {
        if (CmdType.isScanUp(info.getOrder())) {
           ELog.print("扫描头处理:" + info.getCmd() + ",data:" + info.getData());
            String msg=info.getData();
            int len=msg.length();
            String fix=msg.substring(2,len-2);
          // ELog.print("截取："+fix);
            String data=HexUtils.deUnicode2(fix);
           ELog.print("扫描头扫描数据为："+data);
            Listenner4Scanner.onScanResult(data);
            PipeBus.l().receive(info);
//            if (answer.getListenner()!=null){
//                ((ScannerListenner)answer.getListenner()).onScanResult(HexUtils.deUnicode2(fix));
//            }
        }else {
            handler.nextIndex(info);
        }

    }

}
