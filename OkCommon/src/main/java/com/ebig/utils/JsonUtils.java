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
}
