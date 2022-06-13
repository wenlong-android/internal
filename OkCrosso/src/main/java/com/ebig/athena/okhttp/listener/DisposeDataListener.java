package com.ebig.athena.okhttp.listener;

import com.ebig.http.NetResult;
import com.ebig.athena.okhttp.exception.OkHttpException;

/*
 * 业务逻辑层真正处理的地方，包括java层异常和业务层异常
 */
public interface DisposeDataListener {

    /**
     * 请求成功回调事件处理
     */
    void onSuccess(NetResult responseObj);

    /**
     * 请求失败回调事件处理
     */
    void onFailure(OkHttpException exception);

}
