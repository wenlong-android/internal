package com.ebig.socket.bean;

public class TeHuEntity {
    private long timeStamp;
    private DataBean machineRuntimeMap;
    private int type;
    private String factoryCode;

    public TeHuEntity(long time, Integer type, String factoryCode,DataBean machineRuntimeMap) {
        this.machineRuntimeMap = machineRuntimeMap;
        this.type = type;
        this.factoryCode = factoryCode;
        timeStamp=time;
    }

    public DataBean getData() {
        return machineRuntimeMap;
    }

    public void setData(DataBean data) {
        this.machineRuntimeMap = data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public static class DataBean {
        private Double temperature;
        private Double humidity;

        public DataBean(Double temperature, Double humidity) {
            this.temperature = temperature;
            this.humidity = humidity;
        }

        public Double getTemperature() {
            return temperature;
        }

        public void setTemperature(Double temperature) {
            this.temperature = temperature;
        }

        public Double getHumidity() {
            return humidity;
        }

        public void setHumidity(Double humidity) {
            this.humidity = humidity;
        }
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "TeHuEntity{" +
                "timeStamp=" + timeStamp +
                ", machineRuntimeMap=" + machineRuntimeMap +
                ", type=" + type +
                ", factoryCode='" + factoryCode + '\'' +
                '}';
    }
}
