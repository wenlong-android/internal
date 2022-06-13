package com.ebig.socket.bean;

import java.util.List;

public class ThEntity extends BaseCommonEntity {


    private Integer type;
    private String factoryCode;
    private List<MachineRuntimeStateListBean> machineRuntimeStateList;

    public ThEntity(Integer type, String factoryCode, List<MachineRuntimeStateListBean> machineRuntimeStateList) {
        this.type = type;
        this.factoryCode = factoryCode;
        this.machineRuntimeStateList = machineRuntimeStateList;
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

    public List<MachineRuntimeStateListBean> getMachineRuntimeStateList() {
        return machineRuntimeStateList;
    }

    public void setMachineRuntimeStateList(List<MachineRuntimeStateListBean> machineRuntimeStateList) {
        this.machineRuntimeStateList = machineRuntimeStateList;
    }

    public static class MachineRuntimeStateListBean {
        private String stateName;
        private double value;

        public MachineRuntimeStateListBean(String stateName, double value) {
            this.stateName = stateName;
            this.value = value;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
}
