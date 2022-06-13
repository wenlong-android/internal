package com.ebig.crosso.manager.db.upload;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ebig.crosso.manager.type.AopField;


@Entity(tableName = "ThDbInfo")
public class ThDbInfo {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name="uuid")
    private String uuid;
    @ColumnInfo(name="host")
    private String host;
    @ColumnInfo(name="temperature")
    double temperatureInt;
    @ColumnInfo(name="humidity")
    double humidityInt;
    @ColumnInfo(name="json")
    String json;
    @ColumnInfo(name="commitState")
    private int commit;

    public ThDbInfo() {
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

    public double getTemperatureInt() {
        return temperatureInt;
    }

    public void setTemperatureInt(double temperatureInt) {
        this.temperatureInt = temperatureInt;
    }

    public double getHumidityInt() {
        return humidityInt;
    }

    public void setHumidityInt(double humidityInt) {
        this.humidityInt = humidityInt;
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
        return "ThDbInfo{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", host='" + host + '\'' +
                ", temperatureInt=" + temperatureInt +
                ", humidityInt=" + humidityInt +
                ", json='" + json + '\'' +
                ", commit=" + commit +
                '}';
    }
}
