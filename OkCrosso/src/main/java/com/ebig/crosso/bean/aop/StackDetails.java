package com.ebig.crosso.bean.aop;

public class StackDetails extends AopBaseEntity{
    private String stack;

    public StackDetails(String stack) {
        this.stack = stack;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }
}
