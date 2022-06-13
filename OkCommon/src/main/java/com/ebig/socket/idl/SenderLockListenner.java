package com.ebig.socket.idl;



public interface SenderLockListenner extends SenderListenner {
  void lockCall();
  void doorOpen();
  void doorClose();
}
