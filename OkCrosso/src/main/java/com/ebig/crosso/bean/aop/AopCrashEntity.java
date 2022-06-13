package com.ebig.crosso.bean.aop;

public class AopCrashEntity extends AopBaseEntity {
    private String crash;

    public AopCrashEntity(String crash) {
        this.crash = crash;
    }

    public String getCrash() {
        return crash;
    }

    public void setCrash(String crash) {
        this.crash = crash;
    }
}
