package com.ebig.socket.idl;

public interface CardReaderListenner extends SenderListenner {
    void readResult(String result);
}
