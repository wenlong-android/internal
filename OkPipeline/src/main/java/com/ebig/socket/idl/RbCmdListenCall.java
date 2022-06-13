package com.ebig.socket.idl;

public interface RbCmdListenCall<T> {
    void onCall(T t);
    void socketStatus(boolean isConnect);
}
