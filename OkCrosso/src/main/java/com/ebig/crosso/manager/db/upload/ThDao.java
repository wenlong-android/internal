package com.ebig.crosso.manager.db.upload;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.ebig.crosso.manager.type.AopDbInfo;
import com.ebig.crosso.manager.type.RecordType;

import java.util.List;

@Dao
public interface ThDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ThDbInfo table);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<ThDbInfo> list);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ThDbInfo entity);

    @Query("select * from ThDbInfo where  id=:id")
    ThDbInfo at(long id);
//ASC
    @Query("select * from ThDbInfo  where commitState =:event order by id DESC")
    List<ThDbInfo> onState(int event);

    @Query("select * from ThDbInfo  where id >=:time order by id DESC")
    List<ThDbInfo> get12hours(long  time);

    @Delete
   void delete(List<ThDbInfo> list);
}
