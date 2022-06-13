package com.ebig.crosso.manager.type;

import androidx.annotation.StringDef;

@StringDef({
        AopField.tableName,/*表名*/
        AopField.currentTime,/*时间戳*/
        AopField.version,/*版本号*/
        AopField.timeStamp,/*时间*/
        AopField.event,/*类型*/
        AopField.detail,/*详情*/
        AopField.stack,/*堆栈*/
        AopField.thread,/*线程*/
        AopField.amend/*修改标志*/,
        AopField.memory,/*内存*/
        AopField.appResponsible,/*app端编辑人*/
        AopField.finalResponsible/*最终责任人*/


})
public @interface AopField {
    final static String tableName = "AopDbInfo";
    final static String currentTime = "id";
    final static String version = "version";
    final static String timeStamp = "timeStamp";
    final static String event = "event";
    final static String detail ="detail";
    final static String thread="thread";
    final static String stack="stack";
    final static String appResponsible= "appResponsible";
    final static String finalResponsible ="finalResponsible";
    final static String amend = "amend";final static String memory="memory";

}
