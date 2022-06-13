package com.ebig.common;

import androidx.annotation.StringDef;

@StringDef({
        ModuleName.launcher,/**/
        ModuleName.base,/**/
        ModuleName.ui,/**/
        ModuleName.db,/**/
        ModuleName.highFrequency,/**/
        ModuleName.lowerCabinet,/**/
        ModuleName.highCabinet,/**/
        ModuleName.smartCabinet,/**/
        ModuleName.weightCabinet,/**/
})

public @interface ModuleName {
    final static String launcher = "launcher";
    final static String base = "base";
    final static String ui = "ui";
    final static String db = "db";
    final static String highFrequency = "highFrequency";
    final static String lowerCabinet = "lowerCabinet";
    final static String highCabinet = "highCabinet";
    final static String smartCabinet = "smartCabinet";
    final static String weightCabinet = "weightCabinet";
}
