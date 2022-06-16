package com.ebig.socket.dispatch;

import com.ebig.socket.common.SocketIoBase;
import com.ebig.socket.entity.CmdRequestInfo;
import com.ebig.log.ELog;
import com.ebig.utils.StrUtils;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.common.PipeWriter;

public class DispatchManagerCopy extends SocketIoBase {
    private static class L {
        private static DispatchManagerCopy manager = new DispatchManagerCopy();
    }
    public static DispatchManagerCopy l() {
        if (StrUtils.objNull(cmdQueue)) {
            cmdQueue = new CmdQueue();
        }
        return L.manager;
    }

    public void addTask(CmdRequestInfo task) {
        createTimer();
        cmdQueue.put(task);
    }

    private long last = 0;

    @Override
    protected void onSchedule() {
        if (!cmdQueue.isEmpt()) {
            //ELog.print("队列不为空,队列数量：:"+cmdQueue.getSize());
            if (PipeWriter.make().getTask() == null) {
                ELog.print("执行下一个任务");
                CmdRequestInfo task = cmdQueue.get();
                /**缓存待响应的task*/
                cacheAnswerTask(task);
                PipeWriter.make().send(task);
            } else {
                // ELog.print("正在执行任务，请稍后...");
            }

        }

    }


    @Override
    public void accept(String uuid, String host, String cmd) {
//        if (!host.equals(AndPipe.l().getHost())) return;
//        //ELog.print(host+ " 接收到的原始数据:" + cmd);
//        CmdResultInfo rawInfo = SoketIoFactory.dispatchMatch(uuid, host, cmd);
//        String order = rawInfo.getOrder();
//        PipeWriter.make().cancleIfMatch(order);
//        if (StrUtils.notEmpty(order)) {
//            /**创建对应的chain4Handler*/
//            Chain4Handler chain4Handler = makeDispatchHandleList();
//            chain4Handler.nextIndex(rawInfo);
//        } else {
//            ELog.print("返回异常 cmd：" + rawInfo.toString());
//        }
    }

    @Override
    protected void addFormatCmd2TaskPipeline(CmdRequestInfo task) {

    }

    @Override
    protected void createCmdTaskChainAndHandleIt(CmdResultInfo rawInfo) {

    }
}
