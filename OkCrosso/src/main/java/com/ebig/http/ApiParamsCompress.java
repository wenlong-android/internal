package com.ebig.http;

import com.google.gson.Gson;

import java.util.List;

public class ApiParamsCompress implements ApiBaseParams {
    private Integer current;
    private Integer size;
    private String queryName;
    private List<String> ids;
    private boolean compress;

    public ApiParamsCompress(Integer current, Integer size, String queryName, List<String> ids) {
        this.current = current;
        this.size = size;
        this.queryName = queryName;
        this.ids = ids;
        this.compress = true;
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

    @Override
    public String toString() {
        return "ApiParams{" +
                "current=" + current +
                ", size=" + size +
                ", queryName='" + queryName + '\'' +
                ", ids=" + ids +
                '}';
    }
}
