package com.ebig.sp;

public class SpDevice {

    private static final String TENANT_ID = "TENANT_ID";/*租户id*/
    public static final String FactoryCode = "FactoryCodeIns";/*设备序列码*/


    public static void save(String code) {
        BaseSp.l().putString(FactoryCode, code);
    }

    public static String getCode() {
        return BaseSp.l().getString(FactoryCode);
    }

    /*设置租户id*/
    public static void saveTenantId(String type) {
        BaseSp.l().putString(TENANT_ID, type);
    }

    /*获取租户id*/
    public static String getTenantId() {
        return BaseSp.l().getString(TENANT_ID);
    }

}
