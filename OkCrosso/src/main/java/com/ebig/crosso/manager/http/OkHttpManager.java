package com.ebig.crosso.manager.http;
import com.ebig.log.ELog;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class OkHttpManager {
    public static final String TAG = "HttpRequestCenter";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType TEXT_FILE = MediaType.parse("text/plain; charset=utf-8");
    private static OkHttpManager mInstance;
    private static OkHttpClient mHttpClient;


    static {
        mHttpClient = getUnsafeOkHttpClient();
        mInstance = new OkHttpManager();
    }

    private OkHttpManager() {}

    protected static OkHttpManager getInstance() {
        return mInstance;
    }

    /*base get and post*/
    protected void get(String url, BaseCallback callback) {
        get(url, null, callback);
    }

    protected void get(String url, Map<String, Object> param, BaseCallback callback) {
        ELog.print("request wwl", "get请求连接=" +  url  );
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        getRequest(request, callback);
    }

    private void getRequest(final Request request, final BaseCallback callback) {
        //LogTools.debug("camera_config","---------------getRequest----------------"+Thread.currentThread().getName());
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callbackFailure(callback, request, e);
                ELog.print("request wwl", "get请求失败=" +  e.getMessage()  );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String  json = response.body().string();
                callbackResponse(callback, response.code(), json);
                ELog.print("request wwl", "get请求成功=code:"+response.code() + "__json:"+json );
            }
        });
    }
    protected void post(String url, String json, BaseCallback callback) {
        post(url, json, null, callback);
    }

    protected void post(String url, String json, HashMap<String, String> header, BaseCallback callback) {
        try {
            // 获取JSON对象
            JSONObject jsonObject = new JSONObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ELog.print("request wwl", "请求参数=" + json);

        Request request = null;
        if (header != null) {
            request = buildRequestWithHeader(url, HttpMethodType.POST, json, header);
        } else {
            request = buildPostRequest(url, json);
        }
        request(request, callback);
    }


    /*get Request*/
    private Request buildGetRequest(String url, Map<String, Object> param) {
        return buildRequest(url, HttpMethodType.GET, param);
    }

    private Request buildPostRequest(String url, String json) {
        return buildRequest(url, HttpMethodType.POST, json);
    }

    private Request buildRequest(String url, HttpMethodType methodType, Map<String, Object> params) {
        return buildRequest(url, HttpMethodType.POST, new Gson().toJson(params));
    }

    private Request buildRequest(String url, HttpMethodType methodType, String json) {
        return buildRequestWithHeader(url, methodType, json, null);
    }


    private Request buildRequestWithHeader(String url, HttpMethodType methodType, String json, HashMap<String, String> headerMap) {
        Request.Builder build = new Request.Builder().url(url);
        for (String key:headerMap.keySet()){
            build.addHeader(key,headerMap.get(key));
        }
        if (headerMap != null) {
            Iterator iter = headerMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                ELog.print((String) entry.getKey() + "========" + (String) entry.getValue());
                build.addHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (methodType == HttpMethodType.GET) {
        } else if (methodType == HttpMethodType.POST) {
            RequestBody requestBody = RequestBody.create(JSON, json);
            build.post(requestBody);
        }
        return build.build();
    }


    private void request(final Request request, final BaseCallback callback) {
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callbackFailure(callback, request, e);
                ELog.print("request wwl", "请求连接=" + request.url() + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) {
                String json = null;
                try {
                    if (response.code() == 301) {
                        json = response.header("rewrite-host");
                    } else
                        json = response.body().string();
                    ELog.print("request", "请求连接=" + request.url() + "  请求结果:" + json);
                    callbackResponse(callback, response.code(), json);
                } catch (IOException e) {
                    callbackFailure(callback, null, e);
                }
            }
        });
    }


    private void callbackFailure(final BaseCallback callback, final Request request, final IOException e) {
                if (callback != null)
                    callback.onFailure(request, e);
    }

    private void callbackResponse(final BaseCallback callback, final int code, final String json) {
                if (callback != null)
                    callback.onResponse(code, json);
    }


    enum HttpMethodType {
        GET,
        POST,
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            builder.connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true).build();

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 }
