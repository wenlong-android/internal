package com.ebig.http;

public interface IApi {
    Api resetHost(String host);
    /**
     * 服务接口
     */
    ApiRequest registerDevice(String factoryCode,String tenantId,String machineName);
    ApiRequest saveDeviceSettings(String json);
    ApiRequest getDeviceSettings(String arrJson);
    ApiRequest deleteDeviceSettings(String arrJson);
    ApiRequest getTenantList();
    ApiRequest saveUserBindings(String json);
    ApiRequest deleteUserBindings(String json);
    ApiRequest putIn(String json);
    ApiRequest takeOut(String json);
    /**
     * 基础资料、业务单据查询接口
     */
     //部门信息
    ApiRequest deviceBaseDept(ApiBaseParams params);
    //用户信息
    ApiRequest deviceBaseUser(ApiBaseParams params);
    //用户登录绑定信息
    ApiRequest deviceBaseUserBinding(ApiBaseParams params);
    //库房信息
    ApiRequest deviceBaseStorehouse(ApiBaseParams params);

    //货位信息
    ApiRequest deviceBaseLoc(ApiBaseParams params);

    //商品信息
    ApiRequest deviceBaseGoods(ApiBaseParams params);

    //商品策略
    ApiRequest deviceBaseGoodsStrategy(ApiBaseParams params);

    //组织单位
    ApiRequest deviceBaseCompany(ApiBaseParams params);

    //批号
    ApiRequest deviceBaseLot(ApiBaseParams params);
    //批次
    ApiRequest deviceBaseBatch(ApiBaseParams params);

    //单品码
    ApiRequest deviceBaseUniqueCode(ApiBaseParams params);

    //入库单
    ApiRequest deviceBizInBoundOrder(ApiBaseParams params);

    //出库单
    ApiRequest deviceBizOutBoundOrder(ApiBaseParams params);


    //推送
    ApiRequest push(ApiBaseParams params,String name,String deviceId);
}
