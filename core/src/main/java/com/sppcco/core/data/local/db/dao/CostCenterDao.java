package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.sub_model.AccVectorInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;
import io.reactivex.Single;

/**
 * Created by m_pejam on 01/15/18.
 */

@Dao
public interface CostCenterDao {

  // Get

  @Query("SELECT * FROM __CostCenter__")
  List<CostCenter> getAllCostCenter();

  @Query("SELECT * FROM __CostCenter__ WHERE _id = :costCenterId")
  CostCenter getCostCenterById(int costCenterId);

  @Query("SELECT * FROM __CostCenter__ WHERE CCCode = :code")
  CostCenter getCostCenterByCode(int code);

  //@Query("SELECT * FROM __CostCenter__ WHERE CCCode = :code")
  @Query("SELECT * FROM __CostCenter__ WHERE CCCode = :code UNION ALL " +
    "SELECT 0 AS Id, 0 AS CCCode, '' AS Name, '' AS CCDesc, 0 AS Debit, " +
    "0 AS Credit, 0 AS Budget, 0 AS FPId, 0 AS CurrencyBudget, 0 AS CurrencyVal " +
    ",0 AS CurrencyId, 0 AS CTextCode WHERE :code = 0")
  Single<CostCenter> getRXCostCenterByCode(int code);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertCostCenters(List<CostCenter> costCenters);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXCostCenters(List<CostCenter> costCenters);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertCostCenter(CostCenter costCenter);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateCostCenters(CostCenter... costCenters);

  @Update
  int updateCostCenter(CostCenter costCenter);

  // Delete

  @Delete
  int deleteCostCenters(CostCenter... costCenters);

  @Query("DELETE FROM __CostCenter__")
  int deleteAllCostCenter();

  @Query("DELETE FROM __CostCenter__ WHERE _id = :costCenterId")
  int deleteCostCenterById(int costCenterId);

  // Other Method

  @Query("SELECT Name FROM __CostCenter__ WHERE _id = :costCenterId")
  String GetCostCenterNameFromCostCenterId(int costCenterId);

  @Query("SELECT COUNT(*) FROM __CostCenter__")
  int getCountCostCenter();

  @Query("SELECT COUNT(*) FROM (SELECT CCCode AS code, Name AS accountName, _id AS parentAccount FROM __CostCenter__ " +
    "INNER JOIN __AccVsCC__ ON ( [__CostCenter__]._id = [__AccVsCC__].CCId AND " +
    "[__CostCenter__].FPId = [__AccVsCC__].FPId ) WHERE FullId = :fullId)")
  int getCountCostCenterByFullId(String fullId);

  @Query("SELECT CCCode AS code, Name AS accountName, _id AS parentAccount FROM __CostCenter__ " +
    "INNER JOIN __AccVsCC__ ON ( [__CostCenter__]._id = [__AccVsCC__].CCId AND " +
    "[__CostCenter__].FPId = [__AccVsCC__].FPId ) WHERE CCCode = :code")
  AccVectorInfo getCostCenterVectorInfoByCode(int code);
}
