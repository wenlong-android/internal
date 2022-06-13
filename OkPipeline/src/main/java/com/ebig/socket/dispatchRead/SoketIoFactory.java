package com.ebig.socket.dispatchRead;

import com.ebig.log.ELog;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.socket.device.CmdCompiler;
import com.ebig.socket.entity.CmdRequestInfo;

public class SoketIoFactory {
    /**
     * 生成对应的指令实体类
     * @param uuid
     * @param host
     * @param cmd
     * @return
     */
    public static CmdResultInfo dispatchMatch(String uuid, String host, String cmd) {
        return CmdResultInfo.load(uuid, host, cmd);
    }

    private static boolean match(String type, String mark) {
        return type.equals(mark);
    }

    /**
     * 形成格式化的请求任务实体
     * @param taskInfo
     * @return
     */
    public static CmdRequestInfo makeStandardCmd(CmdRequestInfo taskInfo) {
        ELog.print("makeStandardCmd:" + taskInfo);
        CmdCompiler cmdCompiler=CmdCompiler.l().with(taskInfo);
        String finalCmd = cmdCompiler.makeFinal();
        taskInfo.setCmd(finalCmd);
        ELog.print("最终的cmd:" + taskInfo);
        return taskInfo;
    }
}
