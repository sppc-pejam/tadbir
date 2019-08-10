package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.AccVsCC;
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
public interface AccVsCCDao {

  // Get

  @Query("SELECT * FROM __AccVsCC__")
  List<AccVsCC> getAllAccVsCC();


  @Query("SELECT * FROM __AccVsCC__ WHERE FullId = :fullId")
  AccVsCC getAccVsCCByFullId(String fullId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertAccVsCCs(List<AccVsCC> accVsCCS);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXAccVsCCs(List<AccVsCC> accVsCCS);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertAccVsCC(AccVsCC accVsCC);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateAccVsCCs(AccVsCC... accVsCCS);

  @Update
  int updateAccVsCC(AccVsCC accVsCC);

  // Delete

  @Delete
  int deleteAccVsCCs(AccVsCC... accVsCCS);

  @Query("DELETE FROM __AccVsCC__")
  int deleteAllAccVsCC();

  // Other Methods

  @Query("SELECT COUNT(*) FROM __AccVsCC__")
  int getCountAccVsCC();

  @Query("SELECT CCCode AS code, Name AS accountName, _id AS parentAccount FROM __CostCenter__ " +
    "INNER JOIN __AccVsCC__ ON ( [__CostCenter__]._id = [__AccVsCC__].CCId AND \n" +
    "[__CostCenter__].FPId = [__AccVsCC__].FPId ) WHERE FullId = :fullId ORDER BY CCCode")
  List<AccVectorInfo> getAccVsCCRelatedCostCenter(String fullId);
}
