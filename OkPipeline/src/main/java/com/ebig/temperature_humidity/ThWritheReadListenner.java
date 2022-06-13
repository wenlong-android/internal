package com.ebig.temperature_humidity;

public interface ThWritheReadListenner {
    void onWrite(String json);
    void onRead(String json);
}
