package com.ebig.utils;

import com.ebig.sp.SpDevice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
    public static JSONObject makeJsonObj(JSONObject jsonObject){
        if (jsonObject==null) {
            jsonObject=new JSONObject();
        }
        return jsonObject;
    }

    public static JSONObject put(JSONObject jsonObject,String key,Object valye){
        if (jsonObject==null) {
            jsonObject=new JSONObject();
        }
        try {
            jsonObject.put(key,valye);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
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

    public static JSONObject createStting(String key, String value) {
        JSONObject father=new JSONObject();
        JSONObject son=new JSONObject();
        try {
            son.put(key,value);
            father.put("settings",son);
            father.put("factoryCode", SpDevice.getCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return father;
    }

    public static JSONObject crateSettingArr(JSONArray arrJson) {
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("settingKeys",arrJson);
            jsonObject.put("factoryCode", SpDevice.getCode());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
