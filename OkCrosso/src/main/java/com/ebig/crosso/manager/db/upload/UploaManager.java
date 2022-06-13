package com.ebig.crosso.manager.db.upload;

import com.ebig.annotation.ThreadIo;
import com.ebig.crosso.manager.db.UpLoadDb;
import com.ebig.utils.AppGlobals;
import com.ebig.utils.TimeUtils;

import java.util.List;

public class UploaManager {
    public static void inser(ThDbInfo info) {
        UpLoadDb.with(AppGlobals.getApplication()).getTHDao().insert(info);
    }

    public static List<ThDbInfo> getList(){
        long time= System.currentTimeMillis()-43200000;
        return  UpLoadDb.with(AppGlobals.getApplication()).getTHDao().get12hours(time);
    }

}
