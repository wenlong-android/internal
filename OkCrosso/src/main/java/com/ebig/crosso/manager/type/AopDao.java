package com.ebig.crosso.manager.type;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;


import java.util.List;

@Dao
public interface AopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AopDbInfo table);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<AopDbInfo> list);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(AopDbInfo entity);

    @Query("select * from AopDbInfo where  id=:id")
    AopDbInfo at(long id);
//ASC
    @Query("select * from AopDbInfo  where event=:event order by id DESC")
    List<AopDbInfo> atEvent(@RecordType String event);

    @Query("select * from AopDbInfo  where timeStamp like '%' || :date || '%' order by id DESC")
    List<AopDbInfo> atDate(String date);

    @Query("select * from AopDbInfo  where timeStamp like '%' || :date || '%' and event =:event order by id DESC")
    List<AopDbInfo> select(String date, @RecordType String event);

    @Query("select * from AopDbInfo where timeStamp like '%' || :date || '%' order by id DESC")
    List<AopDbInfo> atAll(String date);


    @RawQuery(observedEntities = AopDbInfo.class)
    List<AopDbInfo> getList(SupportSQLiteQuery query);



    @Query("select * from AopDbInfo where id <:date and event != 3 and event != 5 and event != 8 order by id DESC")
    List<AopDbInfo> getCleanList(long date);

    @Delete
   void delete(List<AopDbInfo> list);
}
