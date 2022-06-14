package com.ebig.utils;

import com.ebig.sp.BaseSp;

public class FactoryCodeIns {
    public static final String FactoryCode="FactoryCodeIns";
    public static void save(String code){
        BaseSp.l().putString(FactoryCode,code);
    }
    public static String getCode(){
        return BaseSp.l().getString(FactoryCode);
    }
}
