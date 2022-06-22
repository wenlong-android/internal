package com.ebig.http;

public class RabbitPushBean {
    private String factoryCode;
    private ApushEntity apushEntity;

    public RabbitPushBean(String factoryCode, ApushEntity apushEntity) {
        this.factoryCode = factoryCode;
        this.apushEntity = apushEntity;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public ApushEntity getApushEntity() {
        return apushEntity;
    }

    public void setApushEntity(ApushEntity apushEntity) {
        this.apushEntity = apushEntity;
    }

    @Override
    public String toString() {
        return "RabbitPushBean{" +
                "factoryCode='" + factoryCode + '\'' +
                ", apushEntity=" + apushEntity +
                '}';
    }
}
