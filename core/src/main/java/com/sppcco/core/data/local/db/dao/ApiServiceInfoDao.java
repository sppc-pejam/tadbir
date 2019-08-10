package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.ApiServiceInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by m_pejam on 01/16/18.
 */
@Dao
public interface ApiServiceInfoDao {

  // Get

  @Query("SELECT * FROM __ApiServiceInfo__")
  List<ApiServiceInfo> getAllApiServiceInfo();

  @Query("SELECT * FROM __ApiServiceInfo__ WHERE _id = :apiServiceInfoId")
  ApiServiceInfo getApiServiceInfoById(int apiServiceInfoId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertApiServiceInfos(List<ApiServiceInfo> apiServiceInfos);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertApiServiceInfo(ApiServiceInfo apiServiceInfo);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateApiServiceInfos(ApiServiceInfo... apiServiceInfos);

  @Update
  int updateApiServiceInfo(ApiServiceInfo apiServiceInfo);

  // Delete

  @Delete
  int deleteApiServiceInfos(ApiServiceInfo... apiServiceInfos);

  @Query("DELETE FROM __ApiServiceInfo__")
  int deleteAllApiServiceInfo();

  @Query("DELETE FROM __ApiServiceInfo__ WHERE _id = :apiServiceInfoId")
  int deleteApiServiceInfoById(int apiServiceInfoId);
}
