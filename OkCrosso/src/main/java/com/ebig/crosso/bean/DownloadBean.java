package com.ebig.crosso.bean;

/**
 * Created by Administrator on 2017/3/15.
 */

public class DownloadBean {
    private String key;
    private String value;

    public DownloadBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
