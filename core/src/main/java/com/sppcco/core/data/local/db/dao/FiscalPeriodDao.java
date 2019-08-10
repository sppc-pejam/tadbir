package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.FiscalPeriod;

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
public interface FiscalPeriodDao {

  // Get

  @Query("SELECT * FROM __FiscalPeriod__")
  List<FiscalPeriod> getAllFiscalPeriod();

  @Query("SELECT * FROM __FiscalPeriod__ WHERE _id = :fiscalPeriodId")
  FiscalPeriod getFiscalPeriodById(int fiscalPeriodId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertFiscalPeriods(List<FiscalPeriod> fiscalPeriods);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertFiscalPeriod(FiscalPeriod fiscalPeriod);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateFiscalPeriods(FiscalPeriod... fiscalPeriods);

  @Update
  int updateFiscalPeriod(FiscalPeriod fiscalPeriod);

  // Delete

  @Delete
  int deleteFiscalPeriods(FiscalPeriod... fiscalPeriods);

  @Query("DELETE FROM __FiscalPeriod__")
  int deleteAllFiscalPeriod();

  @Query("DELETE FROM __FiscalPeriod__ WHERE _id = :fiscalPeriodId")
  int deleteFiscalPeriodById(int fiscalPeriodId);

  
}
