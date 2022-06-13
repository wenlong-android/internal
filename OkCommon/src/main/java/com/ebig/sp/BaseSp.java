package com.ebig.sp;

import android.content.Context;

import com.tencent.mmkv.MMKV;

public class BaseSp {
    private static final String SP_ID = "Ebig_sp_id";
    private static class Lazy {
        private static BaseSp sharedPreferences = new BaseSp();
    }
    public static BaseSp l() {
        return Lazy.sharedPreferences;
    }

    public void init(Context context) {
        // 设置初始化的根目录
        String dir = context.getFilesDir().getAbsolutePath() + "/Ebig_SP";
        String rootDir = MMKV.initialize(dir);
    }

    private MMKV getKv() {
        // 获取默认的全局实例
        MMKV kv = MMKV.defaultMMKV();
        return kv;
    }

    public void putString(String key, String value) {
        getKv().encode(key, value);
    }

    public String getString(String key) {
        return getKv().decodeString(key);
    }

    public void putInt(String key, int value) {
        getKv().encode(key, value);
    }

    public int getInt(String key) {
        return getKv().decodeInt(key);
    }

    public void putLong(String key, long value) {
        getKv().encode(key, value);
    }

    public long getLong(String key) {
        return getKv().decodeLong(key);
    }


    public void putBool(String key, boolean value) {
        getKv().encode(key, value);
    }

    public boolean getBool(String key) {
        return getKv().decodeBool(key);
    }

    /**
     * 默认返回ture
     * @param key
     * @return
     */
    public boolean getBoolDefaultTure(String key) {
        return getKv().decodeBool(key, true);
    }

    public boolean getBoolDefaultFalse(String key) {
        return getKv().decodeBool(key, false);
    }


    public void clear() {
        getKv().clear();
    }
}
