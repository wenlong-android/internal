package com.ebig.crosso.manager.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ebig.crosso.manager.db.upload.ThDao;
import com.ebig.crosso.manager.db.upload.ThDbEntity;


//    /**
//     * 注意，冒号后面必须紧跟参数名，中间不能有空格。大于小于号和冒号中间是有空格的。
//     * select *from cache where【表中列名】 =:【参数名】------>等于
//     * where 【表中列名】 < :【参数名】 小于
//     * where 【表中列名】 between :【参数名1】 and :【参数2】------->这个区间
//     * where 【表中列名】like :参数名----->模糊查询
//     * where 【表中列名】 in (:【参数名集合】)---->查询符合集合内指定字段值的记录
//     * @param key
//     * @return
//     */

@Database(entities = {ThDbEntity.class}, version = 1, exportSchema = false)
public abstract class UpLoadDb extends RoomDatabase {
    private static final String dbName="UpLoadData";
    private static UpLoadDb database;
    public static synchronized UpLoadDb with(Context context) {
        if (database==null){
            database = Room.databaseBuilder(context,
                    UpLoadDb.class, dbName).build();
        }
      return database;
    }

    public abstract ThDao getTHDao();
}
