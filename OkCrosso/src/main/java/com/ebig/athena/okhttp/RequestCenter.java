package com.ebig.athena.okhttp;
import com.ebig.athena.okhttp.listener.DisposeDataHandle;
import com.ebig.athena.okhttp.listener.DisposeDataListener;
import com.ebig.athena.okhttp.request.CommonRequest;
import com.ebig.athena.okhttp.request.RequestParams;

/**
 * 请求中心
 */
public class RequestCenter {
    //根据参数发送所有post请求
    public static void getRequestGet(String url, RequestParams params,RequestParams header, DisposeDataListener listener) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params,header), new DisposeDataHandle(listener));
    }
    public static void getRequestPost(String url, String params, DisposeDataListener listener) {
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url, params), new DisposeDataHandle(listener));
    }

    public static void getRequestHeaderPost(String url,  RequestParams header,String params, DisposeDataListener listener) {
        CommonOkHttpClient.post(CommonRequest.createPostRequest(url, params, header), new DisposeDataHandle(listener));
    }


    public static void getRequestDownLoad(String url, String path, DisposeDataListener listener) {
        CommonOkHttpClient.downloadFile(CommonRequest.createDownloadRequest(url), new DisposeDataHandle(listener, path));
    }

    public static void getRequestUpLoad(String url, RequestParams params,  DisposeDataListener listener) {
        CommonOkHttpClient.uploadFile(CommonRequest.createMultiPostRequest(url,params),new DisposeDataHandle(listener));
    }
}
