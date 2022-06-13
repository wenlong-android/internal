package com.ebig.http;

public class ApushEntity {
    private String serverHost;
    private ApiParams params;

    public ApushEntity(String serverHost, ApiParams params) {
        this.serverHost = serverHost;
        this.params = params;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public ApiParams getParams() {
        return params;
    }

    public void setParams(ApiParams params) {
        this.params = params;
    }
}
