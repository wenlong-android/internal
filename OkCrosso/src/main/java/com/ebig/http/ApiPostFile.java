package com.ebig.http;

import com.ebig.athena.okhttp.RequestCenter;
import com.ebig.athena.okhttp.exception.OkHttpException;
import com.ebig.athena.okhttp.listener.DisposeDataListener;
import com.ebig.athena.okhttp.request.RequestParams;
import com.ebig.crosso.bean.aop.HttpDetails;
import com.ebig.crosso.manager.CrossoDataAPI;
import com.ebig.log.ELog;
import com.ebig.utils.GsonUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;

public class ApiPostFile implements IApiRequest {
    private String url;
    private String file;

    public ApiPostFile(String url, String file) {
        this.url = url;
        this.file = file;
    }

    @Override
    public void request(ApiCall<NetResult> callBack) {
        ELog.print("ApiRequest url:"+url);
        ELog.print("ApiRequest file:"+file);
        RequestParams params=new RequestParams();
        try {
            params.put("file",new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        RequestCenter.getRequestUpLoad(url, params, new DisposeDataListener() {
            @Override
            public void onSuccess(NetResult result) {
                ELog.print("ApiRequest onSuccess:"+ GsonUtils.toJson(result));
                if (result != null && result.getCode() == 200) {
                    callBack.onResult(result);
                    CrossoDataAPI.getInstance().http(Thread.currentThread().getName(),
                            new HttpDetails(file, url, result.getCode(), new Gson().toJson(result)));
                } else {
                    CrossoDataAPI.getInstance().http(Thread.currentThread().getName(),
                            new HttpDetails(file, url, 0, result.toString()));
                }
            }

            @Override
            public void onFailure(OkHttpException exception) {
                ELog.print("ApiRequest onFailure:"+ exception.getMessage());
                callBack.onFail(exception.getEcode(), exception.getMessage());
                CrossoDataAPI.getInstance().http(Thread.currentThread().getName(), new HttpDetails(file, url, exception.getEcode(), exception.getMessage()));
            }
        });
    }
}
