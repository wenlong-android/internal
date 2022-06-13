package com.ebig.http;

import com.ebig.utils.GsonUtils;

public class Api implements IApi {
    private static String host = "http://192.168.1.71:9999/";


    private static class Host {
        private static Api api = new Api();
    }

    @Override
    public Api resetHost(String h) {
        host=h;
        return Host.api;
    }

    /**
     * 注册设备信息
     * @param factoryCode
     * @param tenantId
     * @param machineName
     * @return
     */
    @Override
    public ApiRequest registerDevice(String factoryCode, String tenantId, String machineName) {
        String url = ApiHelper.makeUrl(true, host, "registerDevice", ApiType.machine);
        String param = ApiHelper.makeRegisterDeviceJsons(factoryCode, tenantId, machineName);
        ApiRequest request=new ApiRequest(url,param);
        return request;
    }
    /**
     * 保存设备的设置
     * @return
     */
    @Override
    public ApiRequest saveDeviceSettings(String json) {
        String url = ApiHelper.makeUrl(true, host, "saveDeviceSettings", ApiType.machine);
        ApiRequest request=new ApiRequest(url,json);
        return request;
    }
    /**
     * 获取设备的设置
     * @return
     */
    @Override
    public ApiRequest getDeviceSettings(String arrJson) {
        String url = ApiHelper.makeUrl(true, host, "getDeviceSettings", ApiType.machine);
        ApiRequest request=new ApiRequest(url,arrJson);
        return request;
    }
    /**
     * 删除设备的设置
     * @return
     */
    @Override
    public ApiRequest deleteDeviceSettings(String arrJson) {
        String url = ApiHelper.makeUrl(true, host, "deleteDeviceSettings", ApiType.machine);
        ApiRequest request=new ApiRequest(url,arrJson);
        return request;
    }

    /**
     * 获取租户列表
     * @return
     */
    @Override
    public ApiRequest getTenantList() {
        String url = ApiHelper.makeUrl(true, host, "getTenantList", ApiType.upms);
        ApiRequest request=new ApiRequest(url,"");
        return request;
    }

    /**
     * 新增/修改登录绑定信息
     * @param json
     * @return
     */
    @Override
    public ApiRequest saveUserBindings(String json) {
        String url = ApiHelper.makeUrl(true, host, "saveUserBindings", ApiType.upms);
        ApiRequest request=new ApiRequest(url,json);
        return request;
    }
    /**
     * 删除登录绑定信息
     * @param json
     * @return
     */
    @Override
    public ApiRequest deleteUserBindings(String json) {
        String url = ApiHelper.makeUrl(true, host, "deleteUserBindings", ApiType.upms);
        ApiRequest request=new ApiRequest(url,json);
        return request;
    }
    /**
     * 放入接口
     * @param json
     * @return
     */
    @Override
    public ApiRequest putIn(String json) {
        String url = ApiHelper.makeUrl(true, host, "putIn", ApiType.storage);
        ApiRequest request=new ApiRequest(url,json);
        return request;
    }
    /**
     * 取出接口
     * @param json
     * @return
     */
    @Override
    public ApiRequest takeOut(String json) {
        String url = ApiHelper.makeUrl(true, host, "takeOut", ApiType.storage);
        ApiRequest request=new ApiRequest(url,json);
        return request;
    }

    /**
     * 部门信息
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBaseDept(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseDept", ApiType.upms);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 用户信息
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBaseUser(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseUser", ApiType.upms);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 用户登录绑定信息
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBaseUserBinding(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseUserBinding", ApiType.upms);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }

    /**
     * 库房信息
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBaseStorehouse(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseStorehouse", ApiType.base);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }

    /**
     * 货位信息
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBaseLoc(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseLoc", ApiType.base);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 商品信息
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBaseGoods(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseGoods", ApiType.base);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 商品策略
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBaseGoodsStrategy(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseGoodsStrategy", ApiType.base);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 组织单位
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBaseCompany(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseCompany", ApiType.base);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 批号
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBaseLot(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseLot", ApiType.storage);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 批次
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBaseBatch(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseBatch", ApiType.storage);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 单品码
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBaseUniqueCode(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseUniqueCode", ApiType.storage);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }

    /**
     * 入库单
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBizInBoundOrder(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBizInBoundOrder", ApiType.storage);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }

    /**
     * 出库单
     * @param params
     * @return
     */
    @Override
    public ApiRequest deviceBizOutBoundOrder(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBizOutBoundOrder", ApiType.storage);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 推送自动调用，在入库时需做判断接口类型
     * @param params
     * @return
     */
    @Override
    public ApiRequest push(ApiBaseParams params,String name, String deviceId) {
        String url = ApiHelper.makeUrl(false, host, name,deviceId);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
}
