package com.ebig.app;

import android.app.Application;

import androidx.annotation.NonNull;

import com.ebig.crosso.manager.Crosso;
import com.ebig.http.ApiServiceId;
import com.ebig.idl.BaseAppLogic;
import com.jeremyliao.liveeventbus.LiveEventBus;

public class CrossoApplication implements BaseAppLogic {
    @Override
    public void ebOnCreate(@NonNull Application application) {
        Crosso.with(application).defaultConfig().debug(true).cleanFrequency(30).start();
        ApiServiceId.init();
       // LiveEventBus.config().lifecycleObserverAlwaysActive(true);
    }

    @Override
    public void ebOnLowMemory() {

    }

    @Override
    public void ebOnTrimMemory() {

    }

    @Override
    public void ebOnTerminate() {

    }
}
