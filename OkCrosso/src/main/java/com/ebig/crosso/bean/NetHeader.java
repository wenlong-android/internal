package com.ebig.crosso.bean;

import java.util.HashMap;

public class NetHeader {
    HashMap<String,String> hashMap;
    public static NetHeader init() {
        return new NetHeader();
    }
    public NetHeader add(String key, String value) {
        if (hashMap==null){
            hashMap=new HashMap<>();
        }
        hashMap.put(key, value);
        return this;
    }
    public HashMap<String,String> ok(){
        return hashMap;
    }
}
