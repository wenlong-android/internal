package com.ebig.crosso.manager;

import com.ebig.http.NetResult;
import com.ebig.athena.okhttp.RequestCenter;
import com.ebig.athena.okhttp.exception.OkHttpException;
import com.ebig.athena.okhttp.listener.DisposeDataListener;
import com.ebig.athena.okhttp.request.RequestParams;
import com.ebig.crosso.bean.aop.HttpDetails;
import com.ebig.idl.NetCallBack;
import com.google.gson.Gson;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.HashMap;

@Aspect
public class HttpAspectj {
    @Around("execution(* com.ebig.net.NetPostParamRequest.request(String , String , java.util.HashMap , com.ebig.idl.NetCallBack))")
    public Object httpPost(final ProceedingJoinPoint joinPoint) throws Throwable {
        String url = (String) joinPoint.getArgs()[0];
        String param = (String) joinPoint.getArgs()[1];
        HashMap<String, String> header = (HashMap) joinPoint.getArgs()[2];
        NetCallBack callBack = (NetCallBack) joinPoint.getArgs()[3];
        if (header == null) {
            postNormal(url, param, callBack);
        } else {
            RequestParams requestHeader = new RequestParams();
            for (String key : header.keySet()) {
                requestHeader.put(key, header.get(key));
            }
            postWithHeader(url, requestHeader, param, callBack);
        }
        return joinPoint.proceed();
    }

    private void postWithHeader(String url, RequestParams requestHeader, String requestParams, final NetCallBack callBack) {
        RequestCenter.getRequestHeaderPost(url, requestHeader, requestParams, new DisposeDataListener() {
            @Override
            public void onSuccess(NetResult result) {
                if (result != null && result.getCode() == 200 && result.getData() != null) {
                    callBack.onDataCall(result);
                    CrossoDataAPI.getInstance().http(Thread.currentThread().getName(),
                            new HttpDetails(requestParams, url, result.getCode(),  new Gson().toJson(result)));
                } else {
                    CrossoDataAPI.getInstance().http(Thread.currentThread().getName(), new HttpDetails(requestParams, url, 0, result.toString()));
                }
            }

            @Override
            public void onFailure(OkHttpException exception) {
                callBack.onFail(exception.getEcode(), exception.getMessage());
                CrossoDataAPI.getInstance().http(Thread.currentThread().getName(),
                        new HttpDetails(requestParams, url, exception.getEcode(), exception.getMessage()));
            }
        });
    }

    private void postNormal(String url, String requestParams, NetCallBack callBack) {
        RequestCenter.getRequestPost(url, requestParams,  new DisposeDataListener() {
            @Override
            public void onSuccess(NetResult result) {
                if (result != null && result.getCode() == 200 && result.getData() != null) {
                    callBack.onDataCall(result);
                    CrossoDataAPI.getInstance().http(Thread.currentThread().getName(),
                            new HttpDetails(requestParams, url, result.getCode(), new Gson().toJson(result)));
                } else {
                    CrossoDataAPI.getInstance().http(Thread.currentThread().getName(),
                            new HttpDetails(requestParams, url, 0, result.toString()));
                }
            }

            @Override
            public void onFailure(OkHttpException exception) {
                callBack.onFail(exception.getEcode(), exception.getMessage());
                CrossoDataAPI.getInstance().http(Thread.currentThread().getName(), new HttpDetails(requestParams, url, exception.getEcode(), exception.getMessage()));
            }
        });
    }


    @Around("execution(* com.ebig.net.NetGetParamRequest.request(String , java.util.HashMap , java.util.HashMap , com.ebig.idl.NetCallBack))")
    public Object httpGet(final ProceedingJoinPoint joinPoint) throws Throwable {
        String url = (String) joinPoint.getArgs()[0];
        HashMap<String, Object> param = (HashMap<String, Object>) joinPoint.getArgs()[1];
        HashMap<String, String> header = (HashMap) joinPoint.getArgs()[2];
        NetCallBack callBack = (NetCallBack) joinPoint.getArgs()[3];
        RequestParams requestParams = null;
        RequestParams requestHeader = null;
        if (param != null) {
            requestParams = new RequestParams();
            for (String key : param.keySet()) {
                requestParams.put(key, param.get(key));
            }
            if (header != null) {
                requestHeader = new RequestParams();
                for (String key : header.keySet()) {
                    requestParams.put(key, header.get(key));
                }
            }
        }
        getWithParamHeader(url, requestParams, requestHeader, callBack);
        return joinPoint.proceed();
    }


    @Around("execution(* com.ebig.net.NetUpLoadRequest.request(String , java.util.HashMap , com.ebig.idl.NetCallBack))")
    public Object httpUpload(final ProceedingJoinPoint joinPoint) throws Throwable {

        String url = (String) joinPoint.getArgs()[0];
        HashMap<String, Object> param = (HashMap<String, Object>) joinPoint.getArgs()[1];
        NetCallBack callBack = (NetCallBack) joinPoint.getArgs()[2];
        RequestParams requestParams = null;

        if (param != null) {
            requestParams = new RequestParams();
            for (String key : param.keySet()) {
                requestParams.put(key, param.get(key));
            }
        }
        upLoadWithParamHeader(url, requestParams, callBack);
        return joinPoint.proceed();
    }

    private void upLoadWithParamHeader(String url, RequestParams requestParams, NetCallBack callBack) {
        RequestCenter.getRequestUpLoad(url, requestParams, new DisposeDataListener() {
            @Override
            public void onSuccess(NetResult result) {

            }

            @Override
            public void onFailure(OkHttpException e) {

            }


        });
    }

    private void getWithParamHeader(String url, RequestParams requestParams, RequestParams requestHeader, NetCallBack callBack) {
        RequestCenter.getRequestGet(url, requestParams, requestHeader, new DisposeDataListener() {
            @Override
            public void onSuccess(NetResult responseObj) {

            }

            @Override
            public void onFailure(OkHttpException exception) {

            }
        });
    }
}
