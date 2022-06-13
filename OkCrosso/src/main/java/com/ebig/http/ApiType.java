package com.ebig.http;

import androidx.annotation.StringDef;

@StringDef({ApiType.upms,/*微服务*/
        ApiType.base,/*基础资料*/
        ApiType.storage,/*存储*/
        ApiType.machine,/*机器*/
})
public @interface ApiType {
    final static String upms = "upms";
    final static String base = "base";
    final static String storage = "storage";;
    final static String machine = "machine";
}
