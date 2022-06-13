package com.ebig.socket.entity;

public class RomoteCmd {
    private String uuid;
    private String host;
    private String cmd;

    public RomoteCmd(String uuid, String host, String cmd) {
        this.uuid = uuid;
        this.host = host;
        this.cmd = cmd;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}
