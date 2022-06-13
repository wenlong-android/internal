package com.ebig.socket.entity;

import androidx.annotation.StringDef;

@StringDef({
        LcdMode.none,/*无*/
        LcdMode.clear,/*清除匹配内容(需要与待清除内容或者<识别码0xFC>一起下发)*/
        LcdMode.clearAll,/*清除全部显示数据*/
        LcdMode.syncTime,/*清除全部显示数据*/
        LcdMode.powerOff,/*层板断电(可单独或者与数据合并发送此字段)*/
        LcdMode.powerOn,/*层板上电(可单独或者与数据合并发送此字段)*/
        LcdMode.modeOnSale,/*显示模式1-上架(使用模板时无效,用模板编号代替)*/
        LcdMode.modeBlack,/*显示模式2-黑色(使用模板时无效,用模板编号代替)*/
        LcdMode.modeSelect/*显示模式3-拣选(使用模板时无效,用模板编号代替)*/
})
public @interface LcdMode {
    final static String none = TypeConstance.C_00;/*无*/
    final static String clear = TypeConstance.C_01;/*清除匹配内容(需要与待清除内容或者<识别码0xFC>一起下发)*/
    final static String clearAll = TypeConstance.C_02;/*清除全部显示数据*/
    final static String syncTime = TypeConstance.C_03;/*清除全部显示数据*/
    final static String powerOff = TypeConstance.C_04;/*层板断电(可单独或者与数据合并发送此字段)*/
    final static String powerOn = TypeConstance.C_05;/*层板上电(可单独或者与数据合并发送此字段)*/
    final static String modeOnSale = TypeConstance.C_06;/*显示模式1-上架(使用模板时无效,用模板编号代替)*/
    final static String modeBlack = TypeConstance.C_07;/*显示模式2-黑色(使用模板时无效,用模板编号代替)*/
    final static String modeSelect = TypeConstance.C_08;/*显示模式3-拣选(使用模板时无效,用模板编号代替)*/
}
