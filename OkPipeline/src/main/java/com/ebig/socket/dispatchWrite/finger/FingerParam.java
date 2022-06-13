package com.ebig.socket.dispatchWrite.finger;

import com.ebig.log.ELog;
import com.ebig.socket.entity.FingerResult;
import com.ebig.socket.entity.TypeConstance;
import com.ebig.utils.HexUtils;

public class FingerParam {
    /**
     * startCode 默认开头 0x40
     * deviceId 0xff为广播地址
     * pid 自定义手指id
     * gid 分组id
     * enCode 默认结尾
     * */
    private String startCode= TypeConstance.C_40;
    private @FingerResult
    String action;
    private String deviceId= TypeConstance.C_ff;
    private String pid;
    private String gid;
    private String enCode= TypeConstance.C_0d;
    private String userCode= TypeConstance.C_00;
    public FingerParam(@FingerResult String cmd, int pid, int gid) {
        this.action = cmd;
        this.pid =HexUtils.long2HexReverse(pid) ;
        this.gid = HexUtils.int2Hex(gid);
        if (cmd.equals(FingerResult.deleteAll)){
            this.pid="00";
            this.gid ="00";
        }
    }

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
    }

    public String getAction() {
        return action;
    }

    public void setCmd(String action) {
        this.action = action;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getEnCode() {
        return enCode;
    }

    public void setEnCode(String enCode) {
        this.enCode = enCode;
    }
    public String getCmd(){
        /*帧头+指令码+设备号+参数1+参数2+参数3*/
        String incompleteCmd=startCode+getAction()+getDeviceId()
                +getPid()+getGid();
        /*incompleteCmd+检验和+帧尾*/
        String crc=HexUtils.getXORCheck(incompleteCmd);
       ELog.print("厂家协议 >> >> >> >>指静脉[用户码:" + userCode + "]"
                + "[开头:" + startCode + "]" +
                 "[指令码:" + action + "]" +
                "[设备号:" + deviceId + "]" +
                "[用户id:" + getPid()+"]"
                + "[组id:" + getGid() + "]"
                + "[校验码:" + crc + "]" +
                "[结束" + enCode+ "]");
        return userCode+incompleteCmd+crc+enCode  ;
    }
}
