package com.ebig.crosso.manager.db.upload;

import com.ebig.crosso.manager.db.UpLoadDb;
import com.ebig.utils.AppGlobals;

import java.util.List;

public class ThDataImpl {
    public static void inser(ThDbEntity info) {
        UpLoadDb.with(AppGlobals.getApplication()).getTHDao().insert(info);
    }
    /*获取温湿度最近12小时数据*/
    public static List<ThDbEntity> getWith12hourData(){
        long time= System.currentTimeMillis()-43200000;
        return  UpLoadDb.with(AppGlobals.getApplication()).getTHDao().get12hours(time);
    }

}
