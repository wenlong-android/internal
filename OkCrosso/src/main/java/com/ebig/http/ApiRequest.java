package com.ebig.http;

import com.ebig.athena.okhttp.RequestCenter;
import com.ebig.athena.okhttp.exception.OkHttpException;
import com.ebig.athena.okhttp.listener.DisposeDataListener;
import com.ebig.crosso.bean.aop.HttpDetails;
import com.ebig.crosso.manager.CrossoDataAPI;
import com.ebig.log.ELog;
import com.ebig.utils.GsonUtils;
import com.google.gson.Gson;

public class ApiRequest implements IApiRequest {
    private String url;
    private String params;

    public ApiRequest(String url, String params) {
        this.url = url;
        this.params = params;
    }

    @Override
    public void request(ApiCall<NetResult> callBack) {
        ELog.print("ApiRequest url:"+url);
        ELog.print("ApiRequest params:"+params);
        RequestCenter.getRequestPost(url, params, new DisposeDataListener() {
            @Override
            public void onSuccess(NetResult result) {
                ELog.print("ApiRequest onSuccess:"+ GsonUtils.toJson(result));
                if (result != null && result.getCode() == 200) {
                    callBack.onResult(result);
                    CrossoDataAPI.getInstance().http(Thread.currentThread().getName(),
                            new HttpDetails(params, url, result.getCode(), new Gson().toJson(result)));
                } else {
                    CrossoDataAPI.getInstance().http(Thread.currentThread().getName(),
                            new HttpDetails(params, url, 0, result.toString()));
                }
            }

            @Override
            public void onFailure(OkHttpException exception) {
                ELog.print("ApiRequest onFailure:"+ exception.getMessage());
                callBack.onFail(exception.getEcode(), exception.getMessage());
                CrossoDataAPI.getInstance().http(Thread.currentThread().getName(), new HttpDetails(params, url, exception.getEcode(), exception.getMessage()));
            }
        });
    }
}
