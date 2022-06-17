package com.ebig.athena.okhttp.request;

import android.util.Log;

import com.ebig.athena.okhttp.LogUtils;
import com.ebig.log.ELog;

import java.io.File;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CommonRequest {
    /**
     * create the key-value Request
     *
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url, String params) {
        return createPostRequest(url, params, null);
    }

    /**
     * 可以带请求头的Post请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static Request createPostRequest(String url, String params, RequestParams headers) {
        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
                LogUtils.print("头部 " + entry.getKey() + ":" + entry.getValue());
            }
        }
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, params);
        Headers mHeader = mHeaderBuild.build();
        Request request = new Request.Builder().url(url).post(body).
                headers(mHeader).build();
        return request;
    }

    /**
     * ressemble the params to the url
     *
     * @param url
     * @param params
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params) {

        return createGetRequest(url, params, null);
    }

    /**
     * 可以带请求头的Get请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params, RequestParams headers) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }
        Headers mHeader = mHeaderBuild.build();
        String finalUrl = urlBuilder.substring(0, urlBuilder.length() - 1);
        Log.i("url", finalUrl);
        return new Request.Builder().
                url(finalUrl)
                .get()
                .headers(mHeader)
                .build();
    }

    /**
     * 文件上传请求
     *
     * @return
     */
   // private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");
    private static final MediaType FILE_TYPE = MediaType.parse("text/plain");

    public static Request createMultiPostRequest(String url, RequestParams params) {
        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        requestBody.setType(MultipartBody.FORM);
        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        mHeaderBuild.add("TENANT-ID", 1+"");

        if (params != null) {
            for (Map.Entry<String, Object> entry : params.fileParams.entrySet()) {
                if (entry.getValue() instanceof File) {
//                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
//                            RequestBody.create(FILE_TYPE, (File) entry.getValue()));
                    requestBody.addFormDataPart(
                            entry.getKey(),
                            ((File) entry.getValue()).getAbsolutePath(),
                            RequestBody.create(MediaType.parse("application/octet-stream"),(File) ((File) entry.getValue()).getAbsoluteFile())
                    );
                    ELog.print("ApiRequest name:"+entry.getKey()+ "   ,path:"+( (File) entry.getValue()).getAbsolutePath());

                } else if (entry.getValue() instanceof String) {
                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(null, (String) entry.getValue()));
                    ELog.print("ApiRequest path:"+entry.getValue());
                }
            }
        }

        Request request = null;
        try {
            request = new Request.Builder().url(url).post(requestBody.build()).headers(mHeaderBuild.build()).build();
        } catch (Exception e) {

        }

        return request;

    }

    public static Request createDownloadRequest(String url) {
        return new Request.Builder().url(url).build();
    }

}