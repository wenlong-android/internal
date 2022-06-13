package com.ebig.crosso.manager.http;

/*
 * 编写：wenlong on 2017/5/5 14:16
 * 企业QQ： 2853239883
 * 钉钉：13430330686
 */
public interface HttpJsonCallBack {
    void onFinish(String json,JsIns jsIns) throws Exception;
    void onError(JsonBean bean);
}
