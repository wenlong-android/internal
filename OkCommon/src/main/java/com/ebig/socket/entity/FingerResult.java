package com.ebig.socket.entity;

import androidx.annotation.StringDef;

@StringDef( {
        /*开始注册*/
        FingerResult.startRegist,
        /*操作成功*/
        FingerResult.success,
        /*操作失败*/
        FingerResult.fail,
        /*注册手指静脉*/
        FingerResult.regist,
        /*结束取消注册*/
        FingerResult.cancle,
        /*删除手指*/
        FingerResult.delete,
        /*删除所有*/
        FingerResult.deleteAll,
        /*获取所有手指的ID信息*/
        FingerResult.getAllId,
        /*获取指定手指的信息和模板*/
        FingerResult.getTemplates,
        /*下发手指信息和模板*/
        FingerResult.downloadTemplates})
public @interface FingerResult {
    /*操作成功*/
    final static String success= TypeConstance.C_00;
    /*操作失败*/
    final static String fail= TypeConstance.C_01;
    /*注册手指静脉*/
    final static String regist= TypeConstance.C_03;
    /*结束取消注册*/
    final static String cancle= TypeConstance.C_04;
    /*删除手指*/
    final static String delete= TypeConstance.C_05;
    /*删除所有*/
    final static String deleteAll= TypeConstance.C_06;
    /*获取所有手指的ID信息*/
    final static String getAllId= TypeConstance.C_07;
    /*获取指定手指的信息和模板*/
    final static String getTemplates= TypeConstance.C_0a;
    /*下发手指信息和模板*/
    final static String downloadTemplates= TypeConstance.C_0c;
    /*下发手指信息和模板*/
    final static String startRegist= TypeConstance.C_0f;

}
