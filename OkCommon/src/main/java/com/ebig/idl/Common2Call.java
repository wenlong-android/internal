package com.ebig.idl;

public interface Common2Call<Result,Opposite> {
    void onCommonCall(Result result);
    void onOpposite(Opposite error);
}
