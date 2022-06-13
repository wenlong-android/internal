package com.ebig.socket.entity;


import com.ebig.socket.idl.SenderListenner;

public class CmdRequestInfo {
    private String order;
    private String cmd;
    private String data;
    private SenderListenner listenner;
    private int cargo;
    private int layer;
    private int site;
    public CmdRequestInfo(@CTypeWrite String order, String cmd) {
        this.order = order;
        this.cmd = cmd;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String order() {
        return order;
    }

    public void setOrder( String order) {
        this.order = order;
    }

    public String cmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public SenderListenner getListenner() {
        return listenner;
    }

    public void addListnner(SenderListenner listenner) {
        this.listenner = listenner;
    }

    public void config(int cargo, int layer, int site){
        this.cargo=cargo;
        this.layer=layer;
        this.site=site;
    }

    public void setListenner(SenderListenner listenner) {
        this.listenner = listenner;
    }

    public int cargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public int layer() {
        return layer;
    }

    public void Layer(int layer) {
        this.layer = layer;
    }

    public int site() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public String getRquestType(){
        if (order.equals(CTypeWrite.TypeFinger)){
            return getData().substring(4,6);
        }
        return "";
    }

    public String getCmd() {
        return cmd;
    }

    @Override
    public String toString() {
        return "CmdRequestInfo{" +
                "order='" + order + '\'' +
                ", cmd='" + cmd + '\'' +
                ", data='" + data + '\'' +
                ", listenner=" + listenner +
                ", cargo=" + cargo +
                ", layer=" + layer +
                ", site=" + site +
                '}';
    }
}
