package com.ebig.medical.demo;

import android.app.Application;
import androidx.multidex.MultiDex;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ebig.crosso.manager.Crosso;
import com.ebig.log.ELog;
import com.ebig.regist.RegistManager;
import com.ebig.socket.common.AndPipe;
import com.ebig.sp.BaseSp;

public class JesseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 主要是添加下面这句代码
        MultiDex.install(this);
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
        ELog.setDebug(true);
        BaseSp.l().init(this);

        Crosso.with(this).defaultConfig().debug(true).start();

        AndPipe.l().make().start();

        RegistManager.regist(this);
    }


}
