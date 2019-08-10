package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.TablesStatus;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by m_pejam on 01/24/18.
 */
@Dao
public interface TablesStatusDao {

  // Get

  @Query("SELECT * FROM __TablesStatus__")
  List<TablesStatus> getAllTablesStatus();

  @Query("SELECT * FROM __TablesStatus__ WHERE _id = :tablesStatusId")
  TablesStatus getTablesStatusById(int tablesStatusId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertTablesStatuss(List<TablesStatus> tablesStatuss);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertTablesStatus(TablesStatus tablesStatus);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateTablesStatuss(TablesStatus... tablesStatuss);

  @Update
  int updateTablesStatus(TablesStatus tablesStatus);

  // Delete

  @Delete
  int deleteTablesStatuss(TablesStatus... tablesStatuss);

  @Query("DELETE FROM __TablesStatus__")
  int deleteAllTablesStatus();

  @Query("DELETE FROM __TablesStatus__ WHERE _id = :tablesStatusId")
  int deleteTablesStatusById(int tablesStatusId);

  // Other Methods
  @Query("UPDATE __TablesStatus__ SET LastUpdateDate = :lastUpdate, FPId = :FPId,LastUpdateRowCount = :lastUpdateRowCount WHERE TableName = :tableName")
  int updateLastSyncTableStatus(String tableName, String lastUpdate, int FPId, int lastUpdateRowCount);

  @Query("SELECT CASE WHEN " +
    "( COALESCE" +
    "(((SELECT COUNT(*) FROM __SOStatus__ WHERE Approved = 1 AND Posted = 0) > 0) || ((SELECT COUNT(*) FROM __SPStatus__ WHERE Approved = 1 AND Posted = 0) > 0),0)" +
    " )" +
    "THEN 1 ELSE 0 END")
  int getCountUnsendData();
}

