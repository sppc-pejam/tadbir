package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.AccVsPrj;
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
public interface AccVsPrjDao {

  // Get

  @Query("SELECT * FROM __AccVsPrj__")
  List<AccVsPrj> getAllAccVsPrj();


  @Query("SELECT * FROM __AccVsPrj__ WHERE FullId = :fullId")
  AccVsPrj getAccVsPrjByFullId(String fullId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertAccVsPrjs(List<AccVsPrj> accVsPrjs);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXAccVsPrjs(List<AccVsPrj> accVsPrjs);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertAccVsPrj(AccVsPrj accVsPrj);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateAccVsPrjs(AccVsPrj... accVsPrjs);

  @Update
  int updateAccVsPrj(AccVsPrj accVsPrj);

  // Delete

  @Delete
  int deleteAccVsPrjs(AccVsPrj... accVsPrjs);

  @Query("DELETE FROM __AccVsPrj__")
  int deleteAllAccVsPrj();

  // Other Methods

  @Query("SELECT COUNT(*) FROM __AccVsPrj__")
  int getCountAccVsPrj();

  @Query("SELECT PCode AS code, Name AS accountName, _id AS parentAccount FROM __Project__ INNER JOIN __AccVsPrj__ " +
    "ON ( [__Project__]._id = [__AccVsPrj__].PrjId AND [__Project__].FPId = [__AccVsPrj__].FPId ) " +
    "WHERE FullId = :fullId ORDER BY PCode")
  List<AccVectorInfo> getAccVsPrjRelatedProject(String fullId);
}
