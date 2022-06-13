package com.ebig.crosso.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/28.
 */

public class MsgCenterDeatilsBean implements Serializable{
    private String uid;
    private String date;
    private String type;
    private String cnum;
    private String dz;
    private String fileid;
    private String tfcard;

    public MsgCenterDeatilsBean(String uid, String date, String type, String cnum, String dz, String fileid, String tfcard) {
        this.uid = uid;
        this.date = date;
        this.type = type;
        this.cnum = cnum;
        this.dz = dz;
        this.fileid = fileid;
        this.tfcard = tfcard;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getTfcard() {
        return tfcard;
    }

    public void setTfcard(String tfcard) {
        this.tfcard = tfcard;
    }

    @Override
    public String toString() {
        return "MsgCenterDeatilsBean{" +
                "uid='" + uid + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", cnum='" + cnum + '\'' +
                ", dz='" + dz + '\'' +
                ", fileid='" + fileid + '\'' +
                ", tfcard='" + tfcard + '\'' +
                '}';
    }
}
