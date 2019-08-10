package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.SOStatus;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


/**
 * Created by m_pejam on 01/24/18.
 */
@Dao
public interface SOStatusDao {

  // Get

  @Query("SELECT * FROM __SOStatus__ WHERE _id = :sOStatusId")
  SOStatus getSOStatusById(int sOStatusId);

  @Query("SELECT * FROM __SOStatus__ WHERE SOId = :soId AND FPId = :fpId")
  SOStatus getSOStatusBySOIdAndFPId(int soId, int fpId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertSOStatus(SOStatus sOStatus);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertSOStatuses(List<SOStatus> soStatuses);

  // Update

  @Update
  int updateSOStatus(SOStatus sOStatus);

  // Delete

  @Query("DELETE FROM __SOStatus__")
  int deleteAllSOStatus();

  @Query("DELETE FROM __SOStatus__ WHERE _id = :sOStatusId")
  int deleteSOStatusById(int sOStatusId);

  // Other Methods
  @Query("SELECT * FROM __SOStatus__ WHERE FPId = :fpid AND Posted = 1")
  List<SOStatus> getAllPostedSOStatus(int fpid);
}
