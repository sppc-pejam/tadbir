package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.SPStatus;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface SPStatusDao {

  // Get

  @Query("SELECT * FROM __SPStatus__ WHERE _id = :sPStatusId")
  SPStatus getSPStatusById(int sPStatusId);

  @Query("SELECT * FROM __SPStatus__ WHERE SPId = :spId AND FactorType = :factorType AND FPId = :fpId")
  SPStatus getSPStatusBySPIdAndFPId(int spId, int factorType, int fpId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertSPStatus(SPStatus sPStatus);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertSPStatuses(List<SPStatus> spStatuses);

  // Update

  @Update
  int updateSPStatus(SPStatus sPStatus);

  // Delete

  @Query("DELETE FROM __SPStatus__")
  int deleteAllSPStatus();

  @Query("DELETE FROM __SPStatus__ WHERE _id = :sPStatusId")
  int deleteSPStatusById(int sPStatusId);

  // Other Methods
  @Query("SELECT * FROM __SPStatus__ WHERE FactorType = :factorType AND FPId = :fpid AND Posted = 1")
  List<SPStatus> getAllPostedSPStatus(int factorType, int fpid);
}
