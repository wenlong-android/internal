package com.ebig.http;

import java.util.HashMap;

public class ApiServiceId {
    public static HashMap<String,String> apiService=new HashMap<>();
    //注册设备信息
    protected final static String registerDevice = "registerDevice";
    //保存设备的设置
    protected final static String saveDeviceSettings = "saveDeviceSettings";
    //删除设备的设置
    protected final static String deleteDeviceSettings = "deleteDeviceSettings";
    //获取设备的设置
    protected final static String getDeviceSettings = "getDeviceSettings";
    //获取租户列表
    protected final static String getTenantList = "getTenantList";
    //新增/修改登录绑定信息
    protected final static String saveUserBindings = "saveUserBindings";
    //删除登录绑定信息
    protected final static String deleteUserBindings = "deleteUserBindings";
    //放入接口
    protected final static String putIn = "putIn";
    //取出接口
    protected final static String takeOut = "takeOut";

    /**************************基础信息接口**************************/

    //部门信息
    protected final static String deviceBaseDept = "deviceBaseDept";
    //用户信息
    protected final static String deviceBaseUser = "deviceBaseUser";
    //用户登录绑定信息
    protected final static String deviceBaseUserBinding = "deviceBaseUserBinding";
    //库房信息
    protected final static String deviceBaseStorehouse = "deviceBaseStorehouse";
    //货位信息
    protected final static String deviceBaseLoc = "deviceBaseLoc";
    //商品信息
    protected final static String deviceBaseGoods = "deviceBaseGoods";
    //商品策略
    protected final static String deviceBaseGoodsStrategy = "deviceBaseGoodsStrategy";
    //组织单位
    protected final static String deviceBaseCompany = "deviceBaseCompany";
    //批号
    protected final static String deviceBaseLot = "deviceBaseLot";
    //批次
    protected final static String deviceBaseBatch = "deviceBaseBatch";
    //单品码
    protected final static String deviceBaseUniqueCode = "deviceBaseUniqueCode";
    //业务信息接口
    protected final static String deviceBizInBoundOrder = "deviceBizInBoundOrder";
    //出库单
    protected final static String deviceBizOutBoundOrder = "deviceBizOutBoundOrder";
    //xxxxx
    //protected final static String xxxxx = "xxxxx";

    public static void init(){
        apiService.put(registerDevice, ApiType.machine);
        apiService.put(saveDeviceSettings, ApiType.machine);
        apiService.put(getDeviceSettings, ApiType.machine);
        apiService.put(deleteDeviceSettings, ApiType.machine);
        apiService.put(getTenantList, ApiType.upms);
        apiService.put(saveUserBindings, ApiType.upms);
        apiService.put(deleteUserBindings, ApiType.upms);
        apiService.put(putIn, ApiType.storage);
        apiService.put(takeOut, ApiType.storage);
        /**************************基础信息接口**************************/
        apiService.put(deviceBaseDept, ApiType.upms);
        apiService.put(deviceBaseUser, ApiType.upms);
        apiService.put(deviceBaseUserBinding, ApiType.upms);
        apiService.put(deviceBaseStorehouse, ApiType.base);
        apiService.put(deviceBaseLoc, ApiType.base);
        apiService.put(deviceBaseGoods, ApiType.base);
        apiService.put(deviceBaseGoodsStrategy, ApiType.base);
        apiService.put(deviceBaseCompany, ApiType.base);
        apiService.put(deviceBaseLot, ApiType.storage);
        apiService.put(deviceBaseBatch, ApiType.storage);
        apiService.put(deviceBaseUniqueCode, ApiType.storage);
        apiService.put(deviceBizInBoundOrder, ApiType.storage);
        apiService.put(deviceBizOutBoundOrder, ApiType.storage);

    }
    public static String getService(String key){
        if (apiService.containsKey(key)){
            return apiService.get(key);
        }
        return ApiType.base;
    }
}
