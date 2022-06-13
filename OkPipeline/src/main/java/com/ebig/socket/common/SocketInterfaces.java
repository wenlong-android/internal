package com.ebig.socket.common;

public interface SocketInterfaces {
    void deviceConnect(String uuid,String ipHost);
    void deviceDisConnect(String uuid,String ipHost);
    void messageRead(String uuid,String ipHost,String cmd);
    void writeOutTime(String uuid,String ipHost);
    void readOutTime(String uuid,String ipHost);
    void outTime(String uuid,String ipHost);
}
