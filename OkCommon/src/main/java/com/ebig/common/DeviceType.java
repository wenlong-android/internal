package com.ebig.common;

import androidx.annotation.StringDef;

import com.ebig.socket.entity.TypeConstance;

@StringDef({
                DeviceType.smartCargo,/*智能柜*/
                DeviceType.smartFridge,/*智能冰*/
                DeviceType.normalCargo,/*普通存储柜*/
                DeviceType.normalFridge,/*普通冰箱*/
})
public @interface DeviceType {
    final static String smartCargo ="0";
    final static String smartFridge = "1";
    final static String normalCargo ="2";
    final static String normalFridge = "3";
}
