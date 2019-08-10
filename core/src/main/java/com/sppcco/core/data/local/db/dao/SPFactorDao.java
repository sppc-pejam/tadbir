package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.SPFactor;
import com.sppcco.core.data.sub_model.ApprovedPrefactorInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SPFactorDao {

  // Get

  @Query("SELECT * FROM __SPFactor__")
  List<SPFactor> getAllSPFactor();

  @Query("SELECT * FROM __SPFactor__ WHERE _id = :spFactorId")
  SPFactor getSPFactorById(int spFactorId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertSPFactors(List<SPFactor> spFactors);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertSPFactor(SPFactor spFactor);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateSPFactors(SPFactor... spFactors);

  @Update
  int updateSPFactor(SPFactor spFactor);

  // Delete

  @Delete
  int deleteSPFactors(SPFactor... spFactors);

  @Query("DELETE FROM __SPFactor__")
  int deleteAllSPFactor();

  @Query("DELETE FROM __SPFactor__ WHERE _id = :spFactorId")
  int deleteSPFactorById(int spFactorId);

  // Other Method

  @Query("SELECT MAX(FactorNo) FROM __SPFactor__ WHERE FPId = :fpId AND UserId = :userId")
  int getNextFactorNo(int fpId, int userId);

  @Query("SELECT spf._id as SPId, spf.FactorNo as FactorNo, spf.EDate as EDate, " +
    "spf.CustomerId as CustomerId, spf.CustomerName as CustomerName, " +
    "sps.Approved as Approved, sps.ApprovalDate as ApprovalDate, sps.Edited as Edited, sps.EditedDate as EditedDate, " +
    "sps.Retrieved as Retrieved, sps.RetrievalDate as RetrievalDate, sps.Faulted as Faulted, sps.FaultalDate as FaultalDate "+
    "FROM __SPFactor__ spf INNER JOIN __SPStatus__ sps ON " +
    "spf._id = sps.SPId WHERE sps.Approved = 1 AND sps.Posted = 0 ORDER BY spf._id DESC ")
  List<ApprovedPrefactorInfo> getApprovedSPFactorDetails();

  @Query("UPDATE __SPFactor__ SET SPRefNo = :spreference WHERE _id = :spid AND FPId = :fpid")
  int updateSPFactorReference(int spid, int spreference, int fpid);

  @Query("SELECT * from __SPFactor__ spf WHERE spf.FPId = :fpId AND spf.UserId = :userId AND  " +
    "spf.[_id] NOT IN(SELECT SPId FROM [__SPStatus__]) ")
  SPFactor getNotApprovedSPFactor(int fpId, int userId);

}
