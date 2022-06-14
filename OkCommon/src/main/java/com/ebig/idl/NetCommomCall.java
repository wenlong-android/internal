package com.ebig.idl;


import com.ebig.http.NetResult;

public interface NetCommomCall<T> {
    void onDataCall(T result);
    void onFail(int code,String error);


}
