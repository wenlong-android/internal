package com.ebig.http;

import com.ebig.utils.GsonUtils;
import com.ebig.utils.JsonUtils;

import org.json.JSONObject;

import java.util.HashMap;

public class ApiHelper {
    //注册设备信息
    protected final String registerDevice = "registerDevice";
    //保存设备的设置
    protected final String saveDeviceSettings = "saveDeviceSettings";
    //删除设备的设置
    protected final String deleteDeviceSettings = "deleteDeviceSettings";
    //获取设备的设置
    protected final String getDeviceSettings = "getDeviceSettings";
    //获取租户列表
    protected final String getTenantList = "getTenantList";
    //新增/修改登录绑定信息
    protected final String saveUserBindings = "saveUserBindings";
    //删除登录绑定信息
    protected final String deleteUserBindings = "deleteUserBindings";
    //放入接口
    protected final String putIn = "putIn";
    //取出接口
    protected final String takeOut = "takeOut";


    private static HashMap<String, String> map = new HashMap<>();

    public static String makeRegisterDeviceJsons(String factoryCode, String tenantId, String machineName) {
        JSONObject jsonObject = null;
        jsonObject = JsonUtils.put(jsonObject, "factoryCode", factoryCode);
        jsonObject = JsonUtils.put(jsonObject, "tenantId", tenantId);
        jsonObject = JsonUtils.put(jsonObject, "machineName", machineName);
        return jsonObject.toString();
    }


    public void check() {
        if (map.size() == 0) {
            initMap();
        }
    }

    private void initMap() {
        map.put(registerDevice, ApiType.machine);
        map.put(saveDeviceSettings, ApiType.machine);
        map.put(getDeviceSettings, ApiType.machine);
        map.put(deleteDeviceSettings, ApiType.machine);
        map.put(getTenantList, ApiType.upms);
        map.put(saveUserBindings, ApiType.upms);
        map.put(deleteUserBindings, ApiType.upms);
        map.put(putIn, ApiType.storage);
        map.put(takeOut, ApiType.storage);

    }


    public static String makeUrl(boolean b, final String host, String interfaceName, String serviceId) {
        if (b) {
            return host + serviceId + "/device/upload/" + interfaceName;
        } else {
            return host + serviceId + "/dynamic/masterDetailQuery";
        }
    }
}
