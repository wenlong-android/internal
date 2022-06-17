package com.ebig.http;

import com.ebig.utils.GsonUtils;
import com.ebig.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class Api extends ApiHelper implements IApi {
    private static String host = "http://192.168.1.71:9999/";


    private static class Host {
        private static Api api = new Api();
    }

    public static Api deFault(){
        return Host.api;
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
     * 对应db:
     */
    @Override
    public ApiRequest registerDevice(String factoryCode, String tenantId, String machineName) {
        String url = ApiHelper.makeUrl(true, host, "registerDevice", ApiType.machine);
        JSONObject param =  makeRegisterDeviceJsons(factoryCode, tenantId, machineName);
        ApiRequest request=new ApiRequest(url,jsonMake(param));
        return request;
    }
    /**
     * 保存设备的设置
     * @return
     * 对应db:
     */
    @Override
    public ApiRequest saveDeviceSettings(String key,String value) {
        String url = ApiHelper.makeUrl(true, host, "saveDeviceSettings", ApiType.machine);
        JSONObject params=JsonUtils.createStting(key,value);
        ApiRequest request=new ApiRequest(url,jsonMake(params));
        return request;
    }
    /**
     * 获取设备的设置
     * @return
     * 对应db:
     */
    @Override
    public ApiRequest getDeviceSettings(JSONArray arrJson) {
        String url = ApiHelper.makeUrl(true, host, "getDeviceSettings", ApiType.machine);
        JSONObject params=JsonUtils.crateSettingArr(arrJson);
        ApiRequest request=new ApiRequest(url,jsonMake(params));
        return request;
    }
    /**
     * 删除设备的设置
     * @return
     * 对应db:
     */
    @Override
    public ApiRequest deleteDeviceSettings(JSONArray arrJson) {
        String url = ApiHelper.makeUrl(true, host, "deleteDeviceSettings", ApiType.machine);
        JSONObject params=JsonUtils.crateSettingArr(arrJson);
        ApiRequest request=new ApiRequest(url,jsonMake(params));
        return request;
    }

    /**
     * 获取租户列表
     * @return
     * 对应db:
     */
    @Override
    public ApiRequest getTenantList() {
        String url = ApiHelper.makeUrl(true, host, "getTenantList", ApiType.upms);
        ApiRequest request=new ApiRequest(url, JsonUtils.createSome());
        return request;
    }

    /**
     * 新增/修改登录绑定信息
     * @param json
     * @return
     * 对应db:
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
     * 对应db:
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
     * 对应db:
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
     * 对应db:
     */
    @Override
    public ApiRequest takeOut(String json) {
        String url = ApiHelper.makeUrl(true, host, "takeOut", ApiType.storage);
        ApiRequest request=new ApiRequest(url,json);
        return request;
    }

    /**
     * 获取部门信息
     * @param params
     * @return
     * 对应db:sys_dept
     */
    @Override
    public ApiRequest deviceBaseDept(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseDept", ApiType.upms);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 获取用户信息
     * @param params
     * @return
     * 对应db:
     */
    @Override
    public ApiRequest deviceBaseUser(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseUser", ApiType.upms);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 获取用户登录绑定信息
     * @param params
     * @return
     * 对应db:sys_user
     */
    @Override
    public ApiRequest deviceBaseUserBinding(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseUserBinding", ApiType.upms);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }

    /**
     * 获取库房信息
     * @param params
     * @return
     * 对应db:base_storehouse
     */
    @Override
    public ApiRequest deviceBaseStorehouse(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseStorehouse", ApiType.base);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }

    /**
     * 获取货位信息
     * @param params
     * @return
     * 对应db:base_loc
     */
    @Override
    public ApiRequest deviceBaseLoc(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseLoc", ApiType.base);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 获取商品信息
     * @param params
     * @return
     * 对应db:base_goods
     */
    @Override
    public ApiRequest deviceBaseGoods(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseGoods", ApiType.base);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 获取商品策略
     * @param params
     * @return
     * 对应db:base_goods_strategy
     */
    @Override
    public ApiRequest deviceBaseGoodsStrategy(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseGoodsStrategy", ApiType.base);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 获取组织单位
     * @param params
     * @return
     * 对应db:base_company
     */
    @Override
    public ApiRequest deviceBaseCompany(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseCompany", ApiType.base);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 获取批号
     * @param params
     * @return
     * 对应db:st_lot
     */
    @Override
    public ApiRequest deviceBaseLot(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseLot", ApiType.storage);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 获取批次
     * @param params
     * @return
     * 对应db:st_batch
     */
    @Override
    public ApiRequest deviceBaseBatch(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseBatch", ApiType.storage);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }
    /**
     * 获取单品码
     * @param params
     * @return
     * 对应db:st_unique_code
     */
    @Override
    public ApiRequest deviceBaseUniqueCode(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBaseUniqueCode", ApiType.storage);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }

    /**
     * 获取入库单
     * @param params
     * @return
     * 对应db:st_store_command
     */
    @Override
    public ApiRequest deviceBizInBoundOrder(ApiBaseParams params) {
        String url = ApiHelper.makeUrl(false, host, "deviceBizInBoundOrder", ApiType.storage);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }

    /**
     * 获取出库单
     * @param params
     * @return
     * 对应db:st_out_command
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
     * 对应db:
     */
    @Override
    public ApiRequest push(ApiBaseParams params,String name, String deviceId) {
        String url = ApiHelper.makeUrl(false, host, name,deviceId);
        ApiRequest request=new ApiRequest(url, GsonUtils.toJson(params));
        return request;
    }

    /***
     * 上传文件
     * @param path
     * @return
     */
    @Override
    public ApiPostFile postFile(String path) {
        String url=host+"upms/file/upload";
        ApiPostFile request=new ApiPostFile(url,path);
        return request;
    }
}
