package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.AccVsDetail;
import com.sppcco.core.data.sub_model.AccVectorInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;

/**
 * Created by b_nematzadeh on 11/02/18.
 */
@Dao
public interface AccVsDetailDao {

  // Get

  @Query("SELECT * FROM __AccVsDetail__")
  List<AccVsDetail> getAllAccVsDetail();


  @Query("SELECT * FROM __AccVsDetail__ WHERE FullId = :fullId")
  AccVsDetail getAccVsDetailByFullId(String fullId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertAccVsDetails(List<AccVsDetail> accVsDetails);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXAccVsDetails(List<AccVsDetail> accVsDetails);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertAccVsDetail(AccVsDetail accVsDetail);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateAccVsDetails(AccVsDetail... accVsDetails);

  @Update
  int updateAccVsDetail(AccVsDetail accVsDetail);

  // Delete

  @Delete
  int deleteAccVsDetails(AccVsDetail... accVsDetails);

  @Query("DELETE FROM __AccVsDetail__")
  int deleteAllAccVsDetail();

  // Other Methods

  @Query("SELECT COUNT(*) FROM __AccVsDetail__")
  int getCountAccVsDetail();

  @Query("SELECT  T1||' '||T2||' '||T3||' '||T4 AS code, Name AS accountName, DetId AS parentAccount FROM __AccVsDetail__ ad \n" +
    "INNER JOIN __DetailAcc__ facc ON (ad.DetId = facc._id AND ad.FPId = facc.FPId ) WHERE \n" +
    "Necessary <> 0 AND FullId = :fullId ORDER BY T1||T2||T3||T4")
  List<AccVectorInfo> getAccVsDetailRelatedDetailAcc(String fullId);
}
