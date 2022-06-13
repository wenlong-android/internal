package com.ebig.socket.bean.cmd;

public class BaseCmd {
    protected String wholeCmd;
    protected String hostIp;
    protected String type;
    protected String uuid;
    protected String userData;

    public String getWholeCmd() {
        return wholeCmd;
    }

    public void setWholeCmd(String wholeCmd) {
        this.wholeCmd = wholeCmd;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }
}
