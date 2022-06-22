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

    public void saveDept(boolean enable) {
        BaseSp.l().putBool(dept, enable);
    }
    public boolean getDept() {
        return BaseSp.l().getBool(dept);
    }

    public void saveUser(String user, boolean enable) {
        BaseSp.l().putBool(user, enable);
    }
    public boolean getUser() {
        return BaseSp.l().getBool(user);
    }

    public void saveUserBinding(String userBinding, boolean enable) {
        BaseSp.l().putBool(userBinding, enable);
    }
    public boolean getUserBinding() {
        return BaseSp.l().getBool(userBinding);
    }

    public void saveStorehouse(String storehouse, boolean enable) {
        BaseSp.l().putBool(storehouse, enable);
    }
    public boolean getStorehouse() {
        return BaseSp.l().getBool(storehouse);
    }

    public void saveBaseLoc(String baseLoc, boolean enable) {
        BaseSp.l().putBool(baseLoc, enable);
    }
    public boolean getBaseLoc() {
        return BaseSp.l().getBool(baseLoc);
    }

    public void saveBaseGoods(String baseGoods, boolean enable) {
        BaseSp.l().putBool(baseGoods, enable);
    }
    public boolean getBaseGoods() {
        return BaseSp.l().getBool(baseGoods);
    }


    public void saveGoodsStrategy(String goodsStrategy, boolean enable) {
        BaseSp.l().putBool(goodsStrategy, enable);
    }
    public boolean getGoodsStrategy() {
        return BaseSp.l().getBool(goodsStrategy);
    }

    public void saveCompany(String company, boolean enable) {
        BaseSp.l().putBool(company, enable);
    }
    public boolean getCompany() {
        return BaseSp.l().getBool(company);
    }

    public void saveStorageLot(String storageLot, boolean enable) {
        BaseSp.l().putBool(storageLot, enable);
    }
    public boolean getStorageLot() {
        return BaseSp.l().getBool(storageLot);
    }

    public void saveBaseBatch(String baseBatch, boolean enable) {
        BaseSp.l().putBool(baseBatch, enable);
    }
    public boolean getBaseBatch() {
        return BaseSp.l().getBool(baseBatch);
    }

    public void saveUniqueCode(String uniqueCode, boolean enable) {
        BaseSp.l().putBool(uniqueCode, enable);
    }
    public boolean getUniqueCode() {
        return BaseSp.l().getBool(uniqueCode);
    }

    public void saveUserBindings(String userBindings, boolean enable) {
        BaseSp.l().putBool(userBindings, enable);
    }
    public boolean getUserBindings() {
        return BaseSp.l().getBool(userBindings);
    }

    public void saveDvSettings(String dvSettings, boolean enable) {
        BaseSp.l().putBool(dvSettings, enable);
    }
    public boolean getDvSettings() {
        return BaseSp.l().getBool(dvSettings);
    }


}
