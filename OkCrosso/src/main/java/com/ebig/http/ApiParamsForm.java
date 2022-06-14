package com.ebig.http;

import com.ebig.utils.FactoryCodeIns;
import com.ebig.utils.TimeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import static com.ebig.utils.FactoryCodeIns.FactoryCode;

public class ApiParamsForm {

    private String requestId;
    private String operationTime;
    private String factoryCode;
    private JSONObject data;

    public ApiParamsForm(JSONObject data) {
        this.requestId = System.currentTimeMillis()+"";
        this.operationTime = TimeUtils.getCurrentDate();
        this.factoryCode = FactoryCodeIns.getCode();
        this.data = data;
    }
    public ApiParamsForm() {
        this.requestId = System.currentTimeMillis()+"";
        this.operationTime = TimeUtils.getCurrentDate();
        this.factoryCode = FactoryCodeIns.getCode();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiParamsForm{" +
                "requestId='" + requestId + '\'' +
                ", operationTime='" + operationTime + '\'' +
                ", factoryCode='" + factoryCode + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public static JSONObject getJson(JSONObject data){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("requestId",System.currentTimeMillis()+"");
            jsonObject.put("operationTime", TimeUtils.getCurrentDate());
            jsonObject.put("factoryCode",FactoryCodeIns.getCode());
            jsonObject.put("data",data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
