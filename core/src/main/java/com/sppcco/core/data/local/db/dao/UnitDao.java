package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.Unit;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;

/**
 * Created by m_pejam on 01/04/18.
 */

@Dao
public interface UnitDao {

  // Get

  @Query("SELECT * FROM __Unit__")
  List<Unit> getAllUnit();

  @Query("SELECT * FROM __Unit__ WHERE _id = :unitId")
  Unit getUnitById(int unitId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertUnits(List<Unit> units);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXUnits(List<Unit> units);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertUnit(Unit unit);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateUnits(Unit... units);

  @Update
  int updateUnit(Unit unit);

  // Delete

  @Delete
  int deleteUnits(Unit... units);

  @Query("DELETE FROM __Unit__")
  int deleteAllUnit();

  @Query("DELETE FROM __Unit__ WHERE _id = :unitId")
  int deleteUnitById(int unitId);

  // Other Method

  @Query("SELECT _id FROM __Unit__")
  int[] getAllUnitId();

  @Query("SELECT Name FROM __Unit__")
  String[] getAllUnitName();

  @Query("SELECT _id FROM __Unit__ WHERE Name = :unitName")
  int getUnitIdFromUnitName(String unitName);

  @Query("SELECT Name FROM __Unit__ WHERE _id = :unitId")
  String getUnitNameFromUnitId(int unitId);

  @Query("SELECT COUNT(*) FROM __Unit__")
  int getCountUnit();
}
