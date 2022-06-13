package com.ebig.crosso.bean.aop;

public class AopClickEntity extends AopBaseEntity{
    private String model;
    private String screen;
    private String manufacturer;
    private String deviceId;
    private String viewType;
    private String viewId;
    private String viewContent;
    private String activity;

    public AopClickEntity() {
    }

    public AopClickEntity(String model,
                          String screen,
                          String manufacturer,
                          String deviceId,
                          String viewType,
                          String viewId,
                          String viewContent,
                          String activity) {
        this.model = model;
        this.screen = screen;
        this.manufacturer = manufacturer;
        this.deviceId = deviceId;
        this.viewType = viewType;
        this.viewId = viewId;
        this.viewContent = viewContent;
        this.activity = activity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getViewContent() {
        return viewContent;
    }

    public void setViewContent(String viewContent) {
        this.viewContent = viewContent;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "用户操作信息{" +
                "设备=" + model + '\n' +
                "屏幕='" + screen + '\n' +
                "品牌" + manufacturer + '\n' +
                "设备ID='" + deviceId+ '\n' +
                "控件类型='" + viewType + '\n' +
                "控件ID='" + viewId + '\n' +
                "控件内容='" + viewContent + '\n' +
                "页面=" + activity ;
    }
}
