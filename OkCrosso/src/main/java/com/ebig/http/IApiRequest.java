package com.ebig.http;

public interface IApiRequest {
    void request(ApiCall<NetResult> callBack);
}
