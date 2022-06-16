package com.ebig.socket.dispatchWrite.finger;

import com.ebig.idl.CommonCall;
import com.ebig.socket.listenner.IFingerRegistListenner;

import java.util.List;

public interface FingerSenderIdl {
    FingerAnSender regist(FingerParam param, IFingerRegistListenner registListenner);
    FingerAnSender cancle(FingerParam param,CommonCall<Boolean> call);
    FingerAnSender delete(FingerParam param,CommonCall<Boolean> call);
    FingerAnSender deleteAll(FingerParam param,CommonCall<Boolean> call);
    FingerAnSender getFingerId(FingerParam param, CommonCall<List<String>> commonCall);
    FingerAnSender getFingerInfoTemplates(FingerParam param);
    FingerAnSender downloadInfoTemplates(FingerParam param);
}
