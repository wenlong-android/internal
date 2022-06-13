package com.ebig.socket.idl;
/*温湿度*/
public interface PipeThCall extends PipeBase{
    void onThCall(double temperature,double humidity,long internal);
}
