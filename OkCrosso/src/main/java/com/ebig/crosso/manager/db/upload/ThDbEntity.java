package com.ebig.crosso.manager.db.upload;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ebig.crosso.manager.type.AopField;


@Entity(tableName = "ThDbEntity")
public class ThDbEntity {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name="uuid")
    private String uuid;
    @ColumnInfo(name="host")
    private String host;
    @ColumnInfo(name="temperature")
    double temperature;
    @ColumnInfo(name="humidity")
    double humidity;
    @ColumnInfo(name="json")
    String json;
    @ColumnInfo(name="commitState")
    private int commit;

    public ThDbEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getCommit() {
        return commit;
    }

    public void setCommit(int commit) {
        this.commit = commit;
    }

    @Override
    public String toString() {
        return "ThDbEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", host='" + host + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", json='" + json + '\'' +
                ", commit=" + commit +
                '}';
    }
}
