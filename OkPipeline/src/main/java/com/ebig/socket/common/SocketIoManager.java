package com.ebig.socket.common;

import com.ebig.service.CmdServiceManager;
import com.ebig.socket.entity.CmdRequestInfo;
import com.ebig.log.ELog;
import com.ebig.utils.StrUtils;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.dispatchRead.Chain4Handler;
import com.ebig.socket.dispatchRead.SoketIoFactory;
import com.google.gson.Gson;

public class SocketIoManager extends SocketIoBase {
    private static class L {
        private static SocketIoManager manager = new SocketIoManager();
    }

    public static SocketIoManager load() {
        return L.manager;
    }

    /**
     * 发送指令位置
     * @param task 格式化的请求指令
     */
    @Override
    public void addFormatCmd2TaskPipeline(CmdRequestInfo task) {
        PipeBus.l().send(task);
        CmdServiceManager.l().sendCmd(new Gson().toJson(task));
    }

    /**
     * 频率性获取队列任务，获取频率在定时器设置
     */
    @Override
    protected void onSchedule() {
//        if (!cmdQueue.isEmpt()) {
//            //ELog.print("队列不为空,队列数量：:"+cmdQueue.getSize());
//            if (PipeWriter.l().getTask() == null) {
//                ELog.print("执行下一个任务");
//                CmdRequestInfo task = cmdQueue.get();
//                /**缓存待响应的task*/
//                cacheAnswerTask(task);
//                PipeWriter.l().send(task);
//            } else {
//                // ELog.print("正在执行任务，请稍后...");
//            }
//
//        }

    }

    /**
     * 接收中控板指令
     * @param uuid 设备uid
     * @param host 设备ip
     * @param cmd  原始cmd字符串
     */
    @Override
    public void accept(String uuid, String host, String cmd) {
        String orginHost=PipeBus.l().getHost();
        if (!host.equals(orginHost)){
            ELog.print(host+ " getHost:" + orginHost);
            return ;
        } else {
            CmdResultInfo rawInfo = SoketIoFactory.dispatchMatch(uuid, host, cmd);
            createCmdTaskChainAndHandleIt(rawInfo);
        }

    }
    /**
     * 创建不同指令的处理handle的责任链并进行处理
     * @param rawInfo 格式化的cmd回调指令
     */
    @Override
    protected void createCmdTaskChainAndHandleIt(CmdResultInfo rawInfo) {
        String order = rawInfo.getOrder();
        PipeWriter.make().cancleIfMatch(order);
        if (StrUtils.notEmpty(order)) {
            /**创建对应的chain4Handler*/
            Chain4Handler chain4Handler = makeDispatchHandleList();
            chain4Handler.nextIndex(rawInfo);
        } else {
            ELog.print("返回异常 cmd：" + rawInfo.toString());
        }
    }
}
