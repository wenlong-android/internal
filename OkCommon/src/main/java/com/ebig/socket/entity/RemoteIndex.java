package com.ebig.socket.entity;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

@IntDef( {
        /*硬件返回*/
        RemoteIndex.messageRead,
        /*mq rabbit推送*/
        RemoteIndex.rabbitMsg,
        RemoteIndex.deviceConnect,
        RemoteIndex.deviceDisConnect,
        RemoteIndex.writeOutTime,
        RemoteIndex.readOutTime,
        RemoteIndex.outTime,
       })
public @interface RemoteIndex {
    /*硬件返回*/
    final static int messageRead= 0;
    /*mq rabbit推送*/
    final static int rabbitMsg= 1;
    /*指定ip连接*/
    final static int deviceConnect=2;
    final static int deviceDisConnect=3;
    final static int writeOutTime=4;
    final static int readOutTime=5;
    final static int outTime=6;
}
