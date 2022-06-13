package com.ebig.http;

import com.ebig.utils.TimeUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiParams implements ApiBaseParams{

    private Integer current;
    private Integer size;
    private String queryName;
    private List<String> ids;
    private List<ConditionsBean> conditions;

    public ApiParams(Integer current, Integer size, String queryName, List<String> ids) {
        this.current = current;
        this.size = size;
        this.queryName = queryName;
        this.ids = ids;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
    public String getJson(){
        return new Gson().toJson(this);
    }

    public String getJson(long start,long end){
        String json=new Gson().toJson(this);
        try {
            JSONObject jsonObject=new JSONObject(json);
            jsonObject.put("startTime", TimeUtils.getDate(start));
            jsonObject.put("endTime", TimeUtils.getDate(end));
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public List<ConditionsBean> getConditions() {
        return conditions;
    }

    public static class ConditionsBean {
        private String field;
        private String value;

        public ConditionsBean(String field, String value) {
            this.field = field;
            this.value = value;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
