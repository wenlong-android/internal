package com.ebig.socket.entity;

import androidx.annotation.StringDef;

@StringDef( {RtLock.LockOpen, RtLock.LockClose, RtLock.LockOpenFail,
        RtLock.LockCloseFail, RtLock.LockStartOpen, RtLock.LockHalfClose})
public @interface RtLock {
    /*锁开状态*/
    final static String LockOpen= TypeConstance.C_00;
    /*锁关状态*/
    final static String LockClose= TypeConstance.C_01;
    /*开锁失败*/
    final static String LockOpenFail= TypeConstance.C_02;
    /*上锁失败*/
    final static String LockCloseFail= TypeConstance.C_03;
    /*开始开门*/
    final static String LockStartOpen= TypeConstance.C_04;
    /*半锁状态*/
    final static String LockHalfClose= TypeConstance.C_05;

}
