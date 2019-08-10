package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.SPTax;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SPTaxDao {

  // Get

  @Query("SELECT * FROM __SPTax__")
  List<SPTax> getAllSPTax();

  @Query("SELECT * FROM __SPTax__ WHERE _id = :id")
  SPTax getSPTaxById(int id);

  @Query("SELECT * FROM __SPTax__ WHERE SPId = :spId ORDER BY _id")
  List<SPTax> getSPTaxBySPId(int spId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertSPTaxs(List<SPTax> SPTaxs);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertSPTax(SPTax SPTax);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateSPTaxs(SPTax... SPTaxs);

  @Update
  int updateSPTax(SPTax SPTax);

  // Delete

  @Delete
  int deleteSPTaxs(SPTax... SPTaxs);

  @Query("DELETE FROM __SPTax__")
  int deleteAllSPTax();

  @Query("DELETE FROM __SPTax__ WHERE SPId = :spId")
  int deleteSPTaxBySPId(int spId);

  // Other Method


}
