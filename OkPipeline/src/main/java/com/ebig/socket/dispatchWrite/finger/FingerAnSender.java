package com.ebig.socket.dispatchWrite.finger;

import com.ebig.socket.dispatchWrite.base.AnSender;
import com.ebig.socket.entity.TypeConstance;

public class FingerAnSender extends AnSender implements FingerSenderIdl {
    @Override
    public String getOrder() {
        return TypeConstance.T_FINGER_SEND;
    }
    /*注册手指静脉*/
    @Override
    public FingerAnSender regist(FingerParam param) {
        return   (FingerAnSender)this.with(new FingerRegistBaseCmd(param));
    }
    /*结束/取消注册*/
    @Override
    public FingerAnSender cancle(FingerParam param) {
        return   (FingerAnSender)this.with(new FingerCancleBaseCmd(param));
    }
    /*删除手指*/
    @Override
    public FingerAnSender delete(FingerParam param) {
        return   (FingerAnSender)this.with(new FingerDeleteBaseCmd(param));
    }
    /*删除所有*/
    @Override
    public FingerAnSender deleteAll(FingerParam param) {
        return   (FingerAnSender)this.with(new FingerDeleteAllBaseCmd(param));
    }
    /*获取所有手指的ID信息*/
    @Override
    public FingerAnSender getFingerId(FingerParam param) {
        return   (FingerAnSender)this.with(new FingerInfoBaseCmd(param));
    }
    /*获取指定手指的信息和模板*/
    @Override
    public FingerAnSender getFingerInfoTemplates(FingerParam param) {
        return   (FingerAnSender)this.with(new FingerGetAllBaseCmd(param));    }
    /*下发手指信息和模板*/
    @Override
    public FingerAnSender downloadInfoTemplates(FingerParam param) {
        return   (FingerAnSender)this.with(new FingerDownInfoBaseCmd(param));
    }
}
