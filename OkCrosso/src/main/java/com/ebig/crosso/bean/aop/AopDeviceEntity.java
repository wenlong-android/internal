package com.ebig.crosso.bean.aop;
public class AopDeviceEntity {
    private String version;
    private String manufacturer;
    private String model;
    private String $screen;

    public AopDeviceEntity() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String get$screen() {
        return $screen;
    }

    public void set$screen(String $screen) {
        this.$screen = $screen;
    }

    @Override
    public String toString() {
        return "AopDeviceEntity{" +
                "version='" + version + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", $screen='" + $screen + '\'' +
                '}';
    }
}
