package com.ebig.common;

import androidx.annotation.StringDef;

@StringDef({
        Developers.wwl,/*王文龙*/
        Developers.yzs,/**/
        Developers.lyr,/**/
        Developers.pym,/**/
})

public @interface Developers {
    final static String wwl = "wangWenLong";
    final static String yzs = "yanZhiShang";
    final static String lyr = "luoYuRong";
    final static String pym = "pengYanMei";
}
