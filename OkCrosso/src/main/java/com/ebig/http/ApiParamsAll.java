package com.ebig.http;

import com.ebig.utils.TimeUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiParamsAll implements ApiBaseParams{

    private Integer current;
    private Integer size;
    private String queryName;

    private List<ConditionsBean> conditions;

    public ApiParamsAll(Integer current, Integer size, String queryName, long start, long end) {
        this.current = current;
        this.size = size;
        this.queryName = queryName;
        ConditionsBean s=new ConditionsBean("startTime", TimeUtils.getDate(start));
        ConditionsBean e=new ConditionsBean("endTime", TimeUtils.getDate(end));
        conditions=new ArrayList<>();
        conditions.add(s);
        conditions.add(e);
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


    public String getJson(){
        return new Gson().toJson(this);
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
