package com.ebig.socket.bean;

import com.jeremyliao.liveeventbus.core.LiveEvent;

import java.util.List;

public class CommonEntity  {
    private int type;
    private ThEntity data;

    public CommonEntity(int type, ThEntity data) {
        this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ThEntity getData() {
        return data;
    }

    public void setData(ThEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonEntity{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }
}
