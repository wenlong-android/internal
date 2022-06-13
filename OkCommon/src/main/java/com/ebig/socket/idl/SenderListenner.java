package com.ebig.socket.idl;


import com.ebig.socket.entity.RtError;

public interface SenderListenner {
    void onFail(@RtError String msg);
}
