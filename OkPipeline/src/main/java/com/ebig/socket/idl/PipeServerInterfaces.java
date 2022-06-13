package com.ebig.socket.idl;

import com.ebig.socket.common.SocketInterfaces;
import com.ebig.socket.netty.PipeSocketServer;

public interface PipeServerInterfaces {
    PipeSocketServer withPort(int port);
    PipeSocketServer addListenner(SocketInterfaces listenner);
    PipeSocketServer addStartResult(PipeServerCall listenner);
    void start();
    void shutDown();
}
