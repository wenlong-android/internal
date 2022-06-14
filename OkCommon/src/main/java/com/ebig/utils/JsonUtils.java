package com.ebig.utils;

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
}
