package com.ebig.socket.dispatchWrite.scale;

public interface ScaleSenderIdl {
    ScaleAnSender clear();//清零
    ScaleAnSender write();//标定
    ScaleAnSender read();//读取数量
    ScaleAnSender upload(boolean uploadAll);//上传
}
