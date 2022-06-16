package com.ebig.socket.listenner;

public interface IFingerRegistListenner  {
    void startAndPutFinger2Regiest();
    void firstRegistSuccess();
    void secondTimePutFinger2Regiest();
    void secondRegistSuccess();
    void thirdAndPutFinger2Regiest();
    void thirdRegistSuccess();
    void tooMuchDifferenceRegisterAgain();
    void registFinallySuccess();
}
