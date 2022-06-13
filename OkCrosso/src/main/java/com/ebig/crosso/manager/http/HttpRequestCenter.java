package com.ebig.crosso.manager.http;

import com.ebig.log.ELog;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;

import okhttp3.Request;


public class HttpRequestCenter {
    public HttpRequestCenter() {
    }

    private static class Holder {
        private static volatile HttpRequestCenter helper = new HttpRequestCenter();
    }

    public static HttpRequestCenter L() {
        return Holder.helper;
    }

    /**
     * =============================================get=======================================================
     */
    public void get(final String url, final HttpCallBack2 callback) {
        runGet(url, new HttpCallBack() {
            @Override
            public void onFinish(JsonBean bean) {
                callback.onSuccess(bean.getJson());
            }

            @Override
            public void onError(JsonBean bean) {
                callback.onError();
            }
        });
    }

    /**
     *
     * get
     *
     * */
    public void runGet(final String url, final HttpCallBack callback) {
        OkHttpManager.getInstance().get(url, new BaseCallback() {
            @Override
            public void onFailure(Request request, Exception e) {
                //LogTools.debug("camera_config","okhttp get 请求失败=" + e.getMessage());
                callback.onError(errorCall(e));
            }

            @Override
            public void onResponse(int code, String json) {
                 if (code == 200) {
                    callback.onFinish(new JsonBean(code, json));
                } else {
                    callback.onError(new JsonBean(code, json));
                }
            }
        });
    }

    /**
     * =============================================post=======================================================
     */
    public void runPost(final String url, final String params, final BaseCallback callBack) {
        runPostWihtHeader(url, null, params, callBack, null);
    }

    /**
     * url ip
     * header 头部
     * params 参数
     * baseCallback old callback
     * httpcallback new callback with JsonBean
     */
    public void runPost(final String url, final String params, final HttpCallBack callBack) {
        runPostWihtHeader(url, null, params, null, callBack);
    }

    /**
     * url ip
     * header 头部
     * params 参数
     * baseCallback old callback
     * httpcallback new callback with JsonBean
     */
    public void runPost(final String url, HashMap<String, String> header, final String params, final HttpCallBack callBack) {
        runPostWihtHeader(url, header, params, null, callBack);
    }

    /**
     * url ip
     * header 头部
     * params 参数
     * baseCallback old callback
     * httpcallback new callback with JsonBean
     */
    protected void runPostWihtHeader(final String url, HashMap<String, String> header, final String params, final BaseCallback baseCallback, final HttpCallBack callBack) {
        try {
            // 获取JSON对象
            JSONObject jsonObject = new JSONObject(params);
            //LogTools.debug("login", "key=" + jsonObject.optString("authkey"));
            if (jsonObject.optString("authkey").equals("-1")) return;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        ELog.print("login", "post url="+url+", params=" + params);
        OkHttpManager.getInstance().post(url, params, header, new BaseCallback() {
            @Override
            public void onFailure(Request request, Exception e) {
                ELog.print("*******************************************************");
                ELog.print("请求链接=" + url);
                ELog.print("请求参数=" + params);
                ELog.print("请求失败=" + e.getMessage());
                ELog.print("请求失败 baseCallback=null?:" +(baseCallback==null));
                ELog.print("请求失败 callBack=null?:" +(callBack==null));
                ELog.print("*******************************************************");
                ELog.print("\n");
                if (baseCallback != null) {
                    baseCallback.onFailure(request, e);
                } else if (callBack != null) {
                    callBack.onError(errorCall(e));
                }

            }

            @Override
            public void onResponse(int code, String json) {
                ELog.print("\n");
                ELog.print("***************************-***************************");
                ELog.print("请求链接=" + url);
                ELog.print("请求参数=" + params);
                ELog.print("请求结果code=" + code + "--json=" + json);
                ELog.print("***************************-***************************");
                ELog.print("\n");
                if (baseCallback != null) {
                    baseCallback.onResponse(code, json);
                } else if (callBack != null) {
                    if (code == 200) {
                        if (json.equals("{}")) {
                            callBack.onError(new JsonBean(0, "{}"));
                        } else if (json.equals("[]")) {
                            callBack.onError(new JsonBean(0, "[]"));
                        } else {
                            callBack.onFinish(new JsonBean(code, json));
                        }
                    } else {
                        callBack.onError(new JsonBean(code, json));
                    }
                }

            }
        });
    }

    /**
     * url ip
     * header 头部
     * params 参数
     * baseCallback old callback
     * httpcallback new callback with JsonBean
     */
    public void runPost(final String url, final String params, final HttpJsonCallBack callBack) {
        runPostWihtHeader(url, null, params, new BaseCallback() {
            @Override
            public void onFailure(Request request, Exception e) {
                if (callBack != null) {
                    callBack.onError(errorCall(e));
                }
            }
            @Override
            public void onResponse(int code, String json) {
                Object object = null;
                if (callBack != null) {
                    try {
                        object = new JSONTokener(json).nextValue();
                        if (object instanceof JSONObject) {
                            JSONObject jsonObject = new JSONObject(json);
                            callBack.onFinish(jsonObject.toString(), JsIns.L());
                        } else if (object instanceof JSONArray) {
                            JSONArray jsonArray = new JSONArray(json);
                            callBack.onFinish(jsonArray.toString(), JsIns.L());
                        } else {
                            callBack.onError(new JsonBean(code, json));
                        }
                    } catch (Exception e) {
                        callBack.onError(new JsonBean(code, e.getMessage()));
                    }
                }

            }
        }, null);
    }


    private JsonBean errorCall(Exception e) {
        if (e != null && e.getMessage() != null) {
            if (e.getMessage().contains("Unable to resolve host") ||
                    e.getMessage().contains("SSL") ||
                    e.getMessage().equals("timeout") ||
                    e.getMessage().contains("timed out") ||
                    e.getMessage().contains("failed to connect to")) {
                return new JsonBean(20000, e.getMessage());
            } else {
                return new JsonBean(0, e.getMessage());
            }
        } else {
            return new JsonBean(0, "Exception is null or e.getMessage()==null");
        }
    }

}
