package com.ebig.http;

import com.ebig.utils.GsonUtils;
import com.ebig.utils.JsonUtils;

import org.json.JSONObject;

import java.util.HashMap;

public class ApiHelper {



    private static HashMap<String, String> map = new HashMap<>();


    protected String jsonMake(JSONObject data) {
        //ApiParamsForm form = new ApiParamsForm(data);
        JSONObject jsonObject=ApiParamsForm.getJson(data);
        return jsonObject.toString();
    }

    public static JSONObject makeRegisterDeviceJsons(String factoryCode, String tenantId, String machineName) {
        JSONObject jsonObject = null;
        jsonObject = JsonUtils.put(jsonObject, "factoryCode", factoryCode);
        jsonObject = JsonUtils.put(jsonObject, "tenantId", tenantId);
        jsonObject = JsonUtils.put(jsonObject, "machineName", machineName);
        return jsonObject;
    }



    public static String makeUrl(boolean b, final String host, String interfaceName, String serviceId) {
        if (b) {
            return host + serviceId + "/deviceService/" + interfaceName;
        } else {
            return host + serviceId + "/dynamic/masterDetailQuery";
        }
    }
}
