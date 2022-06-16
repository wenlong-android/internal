package com.ebig.crosso.manager.db.upload;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ThDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ThDbEntity table);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<ThDbEntity> list);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ThDbEntity entity);

    @Query("select * from ThDbEntity where  id=:id")
    ThDbEntity at(long id);
//ASC
    @Query("select * from ThDbEntity  where commitState =:event order by id DESC")
    List<ThDbEntity> onState(int event);

    @Query("select * from ThDbEntity  where id >=:time order by id DESC")
    List<ThDbEntity> get12hours(long  time);

    @Delete
   void delete(List<ThDbEntity> list);
}
