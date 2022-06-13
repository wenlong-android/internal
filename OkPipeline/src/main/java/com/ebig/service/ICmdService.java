package com.ebig.service;

import android.content.Context;
import android.content.ServiceConnection;

public interface ICmdService extends ServiceConnection {
    void begin(Context context);
}
