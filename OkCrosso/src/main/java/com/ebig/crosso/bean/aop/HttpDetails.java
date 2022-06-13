package com.ebig.crosso.bean.aop;

public class HttpDetails extends AopBaseEntity {
    private String params;
    private String url;
    private int code;
    private String data;

    public HttpDetails(String params, String url, int code, String data) {
        this.params = params;
        this.url = url;
        this.code = code;
        this.data = data;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpDetails{" +
                "params='" + params + '\'' +
                ", url='" + url + '\'' +
                ", code=" + code +
                ", data='" + data + '\'' +
                '}';
    }
}
