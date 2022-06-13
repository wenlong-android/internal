package com.ebig.crosso.manager.type;

import androidx.annotation.StringDef;

@StringDef({
        RecordType.aopUserClick,/*行为日志*/
        RecordType.aopHardWare,/*硬件函数*/
        RecordType.aopServer,/*服务器函数*/
        RecordType.aopCrash,/*奔溃日志*/
        RecordType.aopConsume,/*耗时日志*/
        RecordType.exception,/*exception日志*/
        RecordType.ram,/*内存日志*/
        RecordType.stack,/*堆栈信息*/
        RecordType.leak,/*内存泄漏*/
        RecordType.block,/*卡顿监控*/
})/*耗时统计*/
public @interface RecordType {
    //final static String all = "-1";
    final static String aopUserClick = "0";
    final static String aopHardWare = "1";
    final static String aopServer = "2"; ;
    final static String aopCrash = "3";
    final static String aopConsume = "4";
    final static String exception = "5";
    final static String ram = "6";
    final static String stack="7";
    final static String leak="8";
    final static String block="9";
}
