package com.ebig.http;

public interface ApiCall<T> {
     void onResult(T t);
     void onFail(int code,String error);
}
