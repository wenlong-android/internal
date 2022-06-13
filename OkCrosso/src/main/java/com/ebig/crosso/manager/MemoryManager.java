package com.ebig.crosso.manager;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

public class MemoryManager implements ComponentCallbacks2 {
    private Application application;

    public MemoryManager(Application app) {
        this.application = app;
        application.registerComponentCallbacks(this);
    }

    @Override
    public void onTrimMemory(int level) {

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {

    }

    @Override
    public void onLowMemory() {

    }
}
