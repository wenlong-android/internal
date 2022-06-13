package com.ebig.crosso.bean.aop;

public class ExceptionDetails extends AopBaseEntity{
    private String threadName;
 private String exception;
 private String stack;

    public ExceptionDetails(String threadName, String exception, String stack) {
        this.threadName = threadName;
        this.exception = exception;
        this.stack = stack;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }
}
