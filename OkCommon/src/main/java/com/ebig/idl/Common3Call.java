package com.ebig.idl;

public interface Common3Call<Result,Opposite,TimeOut> {
    void onCommonCall(double t, Result result, long interval);
    void onOpposite(Opposite error);
    void onOther(TimeOut timeOut);
}
