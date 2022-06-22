package com.ebig.sp;

public class SpApi {
    private static final String Api = "API_";
    private static final String dept = Api + "deviceBaseDept";//部门信息
    private static final String  user = Api + "deviceBaseUser";//用户信息
    private static final String userBinding = Api + "deviceBaseUserBinding";//用户登录绑定信息
    private static final String storehouse = Api + "deviceBaseStorehouse";//库房信息
    private static final String  baseLoc = Api + "deviceBaseLoc";//货位信息
    private static final String  baseGoods = Api + "deviceBaseGoods";//商品信息
    private static final String  goodsStrategy = Api + "deviceBaseGoodsStrategy";//商品策略
    private static final String company = Api + "deviceBaseCompany";//组织单位
    private static final String storageLot = Api + "storageLot";//批号
    private static final String baseBatch = Api + "deviceBaseBatch";//批次
    private static final String uniqueCode = Api + "deviceBaseUniqueCode";//单品码
    private static final String userBindings = Api + "saveUserBindings";//新增/修改登录绑定信息
    private static final String dvSettings = Api + "getDeviceSettings";//获取设备的设置

    public static void saveDept(boolean enable) {
        BaseSp.l().putBool(dept, enable);
    }
    public static boolean getDept() {
        return BaseSp.l().getBool(dept);
    }

    public static void saveUser(boolean enable) {
        BaseSp.l().putBool(user, enable);
    }
    public static boolean getUser() {
        return BaseSp.l().getBool(user);
    }

    public static void saveUserBinding(boolean enable) {
        BaseSp.l().putBool(userBinding, enable);
    }
    public static boolean getUserBinding() {
        return BaseSp.l().getBool(userBinding);
    }

    public static void saveStorehouse(boolean enable) {
        BaseSp.l().putBool(storehouse, enable);
    }
    public static boolean getStorehouse() {
        return BaseSp.l().getBool(storehouse);
    }

    public static void saveBaseLoc(boolean enable) {
        BaseSp.l().putBool(baseLoc, enable);
    }
    public static boolean getBaseLoc() {
        return BaseSp.l().getBool(baseLoc);
    }

    public static void saveBaseGoods(boolean enable) {
        BaseSp.l().putBool(baseGoods, enable);
    }
    public static boolean getBaseGoods() {
        return BaseSp.l().getBool(baseGoods);
    }


    public static void saveGoodsStrategy(boolean enable) {
        BaseSp.l().putBool(goodsStrategy, enable);
    }
    public static boolean getGoodsStrategy() {
        return BaseSp.l().getBool(goodsStrategy);
    }

    public static void saveCompany(boolean enable) {
        BaseSp.l().putBool(company, enable);
    }
    public static boolean getCompany() {
        return BaseSp.l().getBool(company);
    }

    public static void saveStorageLot(boolean enable) {
        BaseSp.l().putBool(storageLot, enable);
    }
    public static boolean getStorageLot() {
        return BaseSp.l().getBool(storageLot);
    }

    public static void saveBaseBatch(boolean enable) {
        BaseSp.l().putBool(baseBatch, enable);
    }
    public static boolean getBaseBatch() {
        return BaseSp.l().getBool(baseBatch);
    }

    public static void saveUniqueCode(boolean enable) {
        BaseSp.l().putBool(uniqueCode, enable);
    }
    public static boolean getUniqueCode() {
        return BaseSp.l().getBool(uniqueCode);
    }

    public static void saveUserBindings(boolean enable) {
        BaseSp.l().putBool(userBindings, enable);
    }
    public static boolean getUserBindings() {
        return BaseSp.l().getBool(userBindings);
    }

    public static void saveDvSettings(boolean enable) {
        BaseSp.l().putBool(dvSettings, enable);
    }
    public static boolean getDvSettings() {
        return BaseSp.l().getBool(dvSettings);
    }


}
