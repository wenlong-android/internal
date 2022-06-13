package com.ebig.socket.dispatchWrite.base;

import com.ebig.socket.entity.CmdRequestInfo;
import com.ebig.socket.common.SocketIoManager;
import com.ebig.socket.dispatchRead.SoketIoFactory;
import com.ebig.socket.idl.SenderListenner;

public abstract class AnSender {
    protected BaseCmdApi baseCmdApi;
    private String order = null;
    private CmdRequestInfo taskInfo;

    public AnSender with(BaseCmdApi api) {
        this.baseCmdApi = api;
        order = getOrder();
        taskInfo = new CmdRequestInfo(order, baseCmdApi.getCmd());
        return this;
    }

    public AnSender addListenner(SenderListenner listenner) {
        taskInfo.addListnner(listenner);
        return this;
    }

    /**
     * 发送指令
     * @param cargo 柜子地址
     * @param layer 柜子层级地址
     * @param site  柜子具体层级的位置号
     */
    public void sendTo(int cargo, int layer, int site) {
        taskInfo.config(cargo, layer, site);
        taskInfo = SoketIoFactory.makeStandardCmd(taskInfo);
        SocketIoManager.load().addFormatCmd2TaskPipeline(taskInfo);
    }

    public abstract String getOrder();

}
