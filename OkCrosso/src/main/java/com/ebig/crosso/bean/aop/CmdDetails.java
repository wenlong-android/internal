package com.ebig.crosso.bean.aop;

public class CmdDetails extends AopBaseEntity{
    private boolean cmdSend;
    private String content;

    public CmdDetails(boolean cmdSend, String content) {
        this.cmdSend = cmdSend;
        this.content = content;
    }

    public boolean isCmdSend() {
        return cmdSend;
    }

    public void setCmdSend(boolean cmdSend) {
        this.cmdSend = cmdSend;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CmdDetails{" +
                "cmdSend=" + cmdSend +
                ", content='" + content + '\'' +
                '}';
    }
}
