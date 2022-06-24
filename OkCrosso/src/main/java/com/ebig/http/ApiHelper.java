package com.ebig.http;

import com.ebig.utils.GsonUtils;
import com.ebig.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ApiHelper {



    private static HashMap<String, String> map = new HashMap<>();


    protected JSONObject simpleJson(String key,Object value){
        JSONObject object=new JSONObject();
        try {
            object.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    protected void append(JSONObject object,String key,Object value){
        try {
            object.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    protected void addFactoryCode(JSONObject object,Object value){
        try {
            object.put("factoryCode",value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected String jsonMake(String factoryCode,JSONObject data) {
         JSONObject jsonObject=ApiParamsForm.getJson(factoryCode,data);
        return jsonObject.toString();
    }

    public static JSONObject makeRegisterDeviceJsons(boolean main,String factoryCode, String tenantId, String machineName) {
        JSONObject jsonObject = null;
        jsonObject = JsonUtils.put(jsonObject, "factoryCode", factoryCode);
        jsonObject = JsonUtils.put(jsonObject, "tenantId", tenantId);
        jsonObject = JsonUtils.put(jsonObject, "machineName", machineName);
        jsonObject = JsonUtils.put(jsonObject, "main", main);

        return jsonObject;
    }



    public static String makeUrl(boolean b, final String host, String interfaceName, String serviceId) {
        if (b) {
            return host + serviceId + "/deviceService/" + interfaceName;
        } else {
            return host + serviceId + "/dynamic/masterDetailQuery";
        }
    }

    public static String createSome() {
        JSONObject object=new JSONObject();
        try {
            object.put("key","value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public static String appan(String toJson, String data) {
        try {
            JSONObject jsonObject=new JSONObject(toJson);
            jsonObject.put("data",data);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static JSONObject createStting(String factoryCode,String key, String value) {
        JSONObject father=new JSONObject();
        JSONObject son=new JSONObject();
        try {
            son.put(key,value);
            father.put("settings",son);
            father.put("factoryCode", factoryCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return father;
    }

    public static JSONObject crateSettingArr(String factoryCode, JSONArray arrJson) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("settingKeys",arrJson);
            jsonObject.put("factoryCode", factoryCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
