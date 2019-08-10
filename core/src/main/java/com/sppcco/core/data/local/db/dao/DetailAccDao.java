package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.DetailAcc;
import com.sppcco.core.data.sub_model.DetailAccVectorInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;

/**
 * Created by m_pejam on 01/16/18.
 */
@Dao
public interface DetailAccDao {

  @Query("SELECT T1||' '||T2||' '||T3 FROM __DetailAcc__ WHERE _id = :detId")
  String getDetailAccCodeById(int detId);

  // Get

  @Query("SELECT * FROM __DetailAcc__")
  List<DetailAcc> getAllDetailAcc();

  @Query("SELECT * FROM __DetailAcc__ WHERE _id = :detailAccId")
  DetailAcc getDetailAccById(int detailAccId);

  @Query("SELECT * FROM __DetailAcc__ WHERE _id = :detailAccId")
  Flowable<DetailAcc> getRXDetailAccById(int detailAccId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertDetailAccs(List<DetailAcc> detailAccs);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXDetailAccs(List<DetailAcc> detailAccs);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertDetailAcc(DetailAcc detailAcc);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateDetailAccs(DetailAcc... detailAccs);

  @Update
  int updateDetailAcc(DetailAcc detailAcc);

  // Delete

  @Delete
  int deleteDetailAccs(DetailAcc... detailAccs);

  @Query("DELETE FROM __DetailAcc__")
  int deleteAllDetailAcc();

  @Query("DELETE FROM __DetailAcc__ WHERE _id = :detailAccId")
  int deleteDetailAccById(int detailAccId);

  // Other Method

  @Query("SELECT Name FROM __DetailAcc__ WHERE _id = :detailAccId ")
  String getDetailAccNameFromDetailAccId(int detailAccId);

  @Query("SELECT COUNT(*) FROM __DetailAcc__")
  int getCountDetailAcc();

  @Query("SELECT COUNT(*) FROM (SELECT T1||' '||T2||' '||T3||' '||T4 AS code, Name AS accountName, DetId AS parentAccount " +
    "FROM __AccVsDetail__ ad  INNER JOIN __DetailAcc__ facc ON (ad.DetId = facc._id AND ad.FPId = facc.FPId ) WHERE " +
    "Necessary <> 0 AND FullId = :fullId)")
  int getCountDetailAccByFullId(String fullId);

  @Query("SELECT T1||' '||T2||' '||T3||' '||T4 AS code, Name AS accountName, DetId AS parentAccount, AccLevel AS accLevel " +
    "FROM __AccVsDetail__ ad  INNER JOIN __DetailAcc__ facc ON (ad.DetId = facc._id AND ad.FPId = facc.FPId ) WHERE " +
    "Necessary <> 0 AND _id = :id")
  DetailAccVectorInfo getDetailAccVectorInfoById(int id);

  @Query("SELECT COUNT(_id) FROM __DetailAcc__ WHERE T1||T2||T3||T4 LIKE '' || :fullId || '%'")
  int isFAccInLeafLevel(String fullId);

  @Query("SELECT CASE WHEN :accLevel < 4 THEN 0 " +
    "WHEN :accLevel = 4 THEN (SELECT T1 FROM __DetailAcc__)" +
    "WHEN :accLevel = 5 THEN (SELECT T1||' '||T2 FROM __DetailAcc__)" +
    "WHEN :accLevel = 6 THEN (SELECT T1||' '||T2||' '||T3 FROM __DetailAcc__)" +
    "WHEN :accLevel = 7 THEN (SELECT T1||' '||T2||' '||T3||' '||T4 FROM __DetailAcc__)END")
  String getFAccCode(int accLevel);

}
