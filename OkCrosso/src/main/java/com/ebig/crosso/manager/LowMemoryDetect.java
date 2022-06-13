package com.ebig.crosso.manager;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.ebig.crosso.bean.aop.MemoryDetails;

public class LowMemoryDetect {
    private Application application;

    public LowMemoryDetect(Application application) {
        this.application = application;
    }
    public void start(){
        application.registerComponentCallbacks(new ComponentCallbacks2() {
            @Override
            public void onTrimMemory(int level) {
                MemoryDetails details=new MemoryDetails();
                details.setLevel(level);
                details.setMethod("onTrimMemory");
                CrossoDataAPI.getInstance().recordMemory(Thread.currentThread().getName(),details);
            }

            @Override
            public void onConfigurationChanged(@NonNull Configuration newConfig) {

            }

            @Override
            public void onLowMemory() {
                MemoryDetails details=new MemoryDetails();
                details.setLevel(-1);
                details.setMethod("onLowMemory");
                CrossoDataAPI.getInstance().recordMemory(Thread.currentThread().getName(),details);
            }
        });
    }
}
