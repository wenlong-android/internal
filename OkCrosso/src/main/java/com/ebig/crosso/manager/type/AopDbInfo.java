package com.ebig.crosso.manager.type;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = AopField.tableName)
public class AopDbInfo {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = AopField.currentTime)
    private long id;
    @ColumnInfo(name=AopField.version)
    private String version;
    @ColumnInfo(name=AopField.timeStamp)
    private String timeStamp;
    @ColumnInfo(name=AopField.event)
    private String event;
    @ColumnInfo(name = AopField.detail)
    private String detail;
    @ColumnInfo(name = AopField.memory)
    private String memory;
    @ColumnInfo(name = AopField.thread)
    private String thread;
    @ColumnInfo(name = AopField.stack)
    private String stack;
    @ColumnInfo(name = AopField.amend)
    private String amend;

    public AopDbInfo(long id,
                     String version,
                     String timeStamp,
                     String event,
                     String detail,
                     String thread,
                     String stack,
                     String memory,
                     String amend) {
        this.id = id;
        this.version = version;
        this.timeStamp = timeStamp;
        this.event = event;
        this.detail = detail;
        this.memory = memory;
        this.thread = thread;
        this.stack = stack;
        this.amend = amend;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getAmend() {
        return amend;
    }

    public void setAmend(String amend) {
        this.amend = amend;
    }

    @Override
    public String toString() {
        return "AopDbInfo{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", event='" + event + '\'' +
                ", detail='" + detail + '\'' +
                ", memory='" + memory + '\'' +
                ", thread='" + thread + '\'' +
                ", stack='" + stack + '\'' +
                ", amend='" + amend + '\'' +
                '}';
    }
}
