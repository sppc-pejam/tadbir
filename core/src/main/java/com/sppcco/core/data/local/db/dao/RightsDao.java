package com.sppcco.core.data.local.db.dao;


import com.sppcco.core.data.model.Rights;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;

@Dao
public interface RightsDao {

  @Query("SELECT * FROM __Rights__")
  List<Rights> getAllRights();

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertRights(List<Rights> Rights);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXRights(List<Rights> rights);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateRights(Rights... Rights);

  // Delete

  @Query("DELETE FROM __Rights__")
  int deleteAllRights();

  // Other Methods

  @Query("SELECT accessRight FROM __Rights__ WHERE SubSystem = :SubSystem AND FormId = :FormId")
  String getAccessRight(int SubSystem, int FormId);

  @Query("SELECT COUNT(*) FROM __Rights__")
  int getCountRights();
}
