package com.ebig.idl;


import com.ebig.http.NetResult;

public interface NetCallBack {
    void onDataCall(NetResult result);
    void onFail(int code,String error);


}
