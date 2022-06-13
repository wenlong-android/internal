package com.ebig.crosso.bean.aop;

public class MemoryDetails extends AopBaseEntity{
    private int level;
    private String method;
    private String ramInfo;

    public MemoryDetails(int level, String method, String ramInfo) {
        this.level = level;
        this.method = method;
        this.ramInfo = ramInfo;
    }

    public MemoryDetails() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRamInfo() {
        return ramInfo;
    }

    public void setRamInfo(String ramInfo) {
        this.ramInfo = ramInfo;
    }
}
