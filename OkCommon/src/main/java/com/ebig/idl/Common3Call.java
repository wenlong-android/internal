package com.ebig.idl;

public interface Common3Call<Result,Opposite,TimeOut> {
    void onCommonCall(Result result);
    void onOpposite(Opposite error);
    void onOther(TimeOut timeOut);
}
