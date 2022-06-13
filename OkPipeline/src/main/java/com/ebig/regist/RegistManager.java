package com.ebig.regist;

import android.app.Application;

import com.jeremyliao.liveeventbus.LiveEventBus;

public class RegistManager {
    public static void regist(Application application){
        LiveEventBus.config().autoClear(false).enableLogger(true).lifecycleObserverAlwaysActive(true);
    }
}
