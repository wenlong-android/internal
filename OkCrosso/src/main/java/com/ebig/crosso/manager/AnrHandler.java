package com.ebig.crosso.manager;

import android.app.Application;


import com.ebig.log.ELog;
import com.github.anrwatchdog.ANRError;
import com.github.anrwatchdog.ANRWatchDog;

public class AnrHandler {
    private Application application;
    //设置3秒无响应
    private static final int anrTimeout=5000;
    public AnrHandler(Application application) {
        this.application = application;
    }
    public void watch(){
        ANRWatchDog watchDog=new ANRWatchDog(anrTimeout);
        watchDog.setANRListener(new ANRWatchDog.ANRListener() {
            @Override
            public void onAppNotResponding(ANRError anrError) {
                ELog.print("onAppNotResponding "+ anrError.getMessage());
            }
        });
        watchDog.start();
    }
}
