package com.ebig.crosso.manager;

import android.content.Context;

import com.ebig.crosso.manager.db.CrossoDb;
import com.ebig.crosso.manager.type.AopDbInfo;
import com.ebig.crosso.utils.CroThread;
import com.ebig.log.ELog;

import java.util.List;

public class CleanTask {
    private static long ONE_MINUTE_IN_MS = 60 * 1000;
    private static long ONE_HOUR_IN_MS = 60 * ONE_MINUTE_IN_MS;
    private static long ONE_DAY_IN_MS = 24 * ONE_HOUR_IN_MS;
    private int day;

    public CleanTask(int day) {
        this.day = day;
    }
    public void clean(Context context){
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {
                long threshold=System.currentTimeMillis()-day*ONE_DAY_IN_MS;
             List<AopDbInfo> list= CrossoDb.with(context).getAop().getCleanList(threshold);
             if (list!=null){
                 CrossoDb.with(context).getAop().delete(list);
             }
            }
        });
    }
}
