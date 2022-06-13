package com.ebig.idl;

public interface CommonCall3<Result,Opposite,TimeOut> {
    void onCommonCall(Result result,Opposite opposite,TimeOut timeOut);
}
