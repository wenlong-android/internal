package com.ebig.crosso.manager.http;

/*
 * 编写：wenlong on 2017/5/5 14:16
 * 企业QQ： 2853239883
 * 钉钉：13430330686
 */
public interface HttpCallBack {
    void onFinish(JsonBean bean);
    void onError(JsonBean bean);
}
