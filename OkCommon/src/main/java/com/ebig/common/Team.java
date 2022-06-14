package com.ebig.common;

import androidx.annotation.StringDef;

@StringDef({
        Team.wwl,/*王文龙*/
        Team.yzs,/**/
        Team.lyr,/**/
        Team.gyw,/**/

        Team.launcher,/**/
        Team.login,/**/
        Team.setting,/**/
        Team.arouter,/**/
        Team.data,/**/
        Team.base,/**/
        Team.ui,/**/
        Team.utils,/**/
        Team.highFrequency,/**/
        Team.lowerCabinet,/**/
        Team.highCabinet,/**/
        Team.smartCabinet,/**/
        Team.weightCabinet,/**/
})

public @interface Team {
    final static String wwl = "wwl";
    final static String yzs = "yzs";
    final static String lyr = "lyr";
    final static String gyw = "gyw";

    final static String launcher = "appLauncher";
    final static String login = "ftLogin";
    final static String setting = "ftSetting";
    final static String arouter = "libArouter";
    final static String data = "libData";
    final static String base = "base";
    final static String ui = "libUi";
    final static String utils = "libUtils";
    final static String highFrequency = "highFrequency";
    final static String lowerCabinet = "lowerCabinet";
    final static String highCabinet = "highCabinet";
    final static String smartCabinet = "smartCabinet";
    final static String weightCabinet = "weightCabinet";
}
