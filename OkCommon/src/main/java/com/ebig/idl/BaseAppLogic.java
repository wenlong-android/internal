package com.ebig.idl;

import android.app.Application;

import androidx.annotation.NonNull;

public interface BaseAppLogic {
    void ebOnCreate(@NonNull Application mApplication);

    void ebOnLowMemory();

    void ebOnTrimMemory();

    void ebOnTerminate();
}
