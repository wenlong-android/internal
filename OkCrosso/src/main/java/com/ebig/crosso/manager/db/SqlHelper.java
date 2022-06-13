package com.ebig.crosso.manager.db;

import android.content.Context;

import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.ebig.crosso.manager.type.AopDbInfo;
import com.ebig.crosso.manager.type.RecordType;
import com.ebig.crosso.ui.AopDataCall;
import com.ebig.crosso.utils.CroThread;
import com.ebig.log.ELog;
import com.ebig.crosso.utils.CrossoTimeUtils;

import java.util.Arrays;
import java.util.List;

public class SqlHelper {
    private static long ONE_MINUTE_IN_MS = 60 * 1000;
    private static long ONE_HOUR_IN_MS = 60 * ONE_MINUTE_IN_MS;
    private static long ONE_DAY_IN_MS = 24 * ONE_HOUR_IN_MS;
    public static void getSql(Context context,Object[] objectsArr, AopDataCall<List<AopDbInfo>> listAopCall){
        CroThread.getIns().runSingleThread(new Runnable() {
            @Override
            public void run() {
                String sql = "select * from AopDbInfo where";
                for (int i = 0; i < objectsArr.length-2; i++) {
                    if (i==0){
                        sql=sql+" ( event = ?";
                    }else {
                        sql=sql+" or event = ?";
                    }
                }
                sql=sql+") and id > ? and id < ? order by id DESC";
                ELog.print("sql语句=="+sql);
                ELog.print("sql数组=="+ Arrays.toString(objectsArr));
                 ELog.print("sql日期=="+ CrossoTimeUtils.getDateFormat((Long)objectsArr[objectsArr.length-2])+"-"+
                         CrossoTimeUtils.getDateFormat((Long)objectsArr[objectsArr.length-1]));
                SupportSQLiteQuery sqLiteQuery = new SimpleSQLiteQuery(sql,objectsArr);
                List<AopDbInfo> list = CrossoDb.with(context).getAop().getList(sqLiteQuery);
                listAopCall.onCall(list);
                if (list!=null&&list.size()>0){
                    for(AopDbInfo info:list){
                        //ELog.print("获取到数据："+info.getEvent());
                    }
                }else {
                    ELog.print("获取到数据为空" );

                }
            }
        });
    }

    public static Object[] getArr(long start, long end) {
        Object[]arr=new Object[12];
        arr[0]=RecordType.leak;
        arr[1]=RecordType.block;
        arr[2] =RecordType.aopUserClick ;
        arr[3] =RecordType.aopHardWare;
        arr[4] =RecordType.aopServer  ;
        arr[5] =RecordType.aopCrash ;
        arr[6] =RecordType.aopConsume;
        arr[7] =RecordType.exception ;
        arr[8] =RecordType.ram ;
        arr[9] =RecordType.stack;
        arr[10] =start;
        arr[11] =end;
        return arr;
    }
}
