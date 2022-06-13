package com.ebig.socket.dispatchWrite.finger;

public interface FingerSenderIdl {
    FingerAnSender regist(FingerParam param);
    FingerAnSender cancle(FingerParam param);
    FingerAnSender delete(FingerParam param);
    FingerAnSender deleteAll(FingerParam param);
    FingerAnSender getFingerId(FingerParam param);
    FingerAnSender getFingerInfoTemplates(FingerParam param);
    FingerAnSender downloadInfoTemplates(FingerParam param);
}
