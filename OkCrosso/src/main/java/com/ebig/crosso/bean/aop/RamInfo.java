package com.ebig.crosso.bean.aop;

public class RamInfo {
    /*最大分配内存*/
    private String max;
    /*当前分配的总内存*/
    private String total;
    /*剩余内存*/
    private String free;
    /*使用内存*/
    private String use;
    /*系统剩余内存*/
    private String systemFree;
    /*系统低内存阈值*/
    private String systemThreshold;
    /*低内存运行*/
    private String lowRunning;
    /*系统已经分配的native内存*/
    private String nativeExsit;
    /*系统还剩余的native内存*/
    private String nativeFree;
    /*系统的所有native内存大小*/
    private String nativeAll;

    public RamInfo(String max, String total, String free, String use, String systemFree, String systemThreshold, String lowRunning, String nativeExsit, String nativeFree, String nativeAll) {
        this.max = max;
        this.total = total;
        this.free = free;
        this.use = use;
        this.systemFree = systemFree;
        this.systemThreshold = systemThreshold;
        this.lowRunning = lowRunning;
        this.nativeExsit = nativeExsit;
        this.nativeFree = nativeFree;
        this.nativeAll = nativeAll;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getSystemFree() {
        return systemFree;
    }

    public void setSystemFree(String systemFree) {
        this.systemFree = systemFree;
    }

    public String getSystemThreshold() {
        return systemThreshold;
    }

    public void setSystemThreshold(String systemThreshold) {
        this.systemThreshold = systemThreshold;
    }

    public String getLowRunning() {
        return lowRunning;
    }

    public void setLowRunning(String lowRunning) {
        this.lowRunning = lowRunning;
    }

    public String getNativeExsit() {
        return nativeExsit;
    }

    public void setNativeExsit(String nativeExsit) {
        this.nativeExsit = nativeExsit;
    }

    public String getNativeFree() {
        return nativeFree;
    }

    public void setNativeFree(String nativeFree) {
        this.nativeFree = nativeFree;
    }

    public String getNativeAll() {
        return nativeAll;
    }

    public void setNativeAll(String nativeAll) {
        this.nativeAll = nativeAll;
    }

    @Override
    public String toString() {
        return "内容信息\n" +
                "最大分配内存='" + max + '\n' +
                ", 当前分配的总内存='" + total + '\n' +
                ", 剩余内存='" + free + '\n' +
                ", 使用内存='" + use + + '\n' +
                ", 系统剩余内存='" + systemFree + '\n' +
                ", 系统低内存阈值='" + systemThreshold + '\n' +
                ", 低内存运行='" + lowRunning + '\n' +
                ", 系统已经分配的native内存='" + nativeExsit + '\n' +
                ", 系统还剩余的native内存='" + nativeFree +  '\n' +
                ", 系统的所有native内存大小='" + nativeAll+ '\n';
    }

}
