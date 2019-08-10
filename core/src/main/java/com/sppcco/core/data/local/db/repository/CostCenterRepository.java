package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.sub_model.AccVectorInfo;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public interface CostCenterRepository {

  // ________________________________________ CRUD ________________________________________ //

  interface GetCostCentersCallback {
    void onCostCentersLoaded(List<CostCenter> costCenters);
    void onDataNotAvailable();
  }
  void getCostCenters(@NonNull GetCostCentersCallback callback);


  interface GetCostCenterCallback {
    void onCostCenterLoaded(CostCenter costCenter);
    void onDataNotAvailable();
  }
  void getCostCenter(int costCenterId, @NonNull GetCostCenterCallback callback);

  interface GetCostCenterByIdCallback {
    void onCostCenterLoaded(CostCenter costCenter);
    void onDataNotAvailable();
  }
  void getCostCenterById(int id, @NonNull GetCostCenterByIdCallback callback);

  interface GetCostCenterByCodeCallback {
    void onCostCenterLoaded(CostCenter costCenter);
    void onDataNotAvailable();
  }
  void getCostCenterByCode(int code, @NonNull GetCostCenterByCodeCallback callback);


  // Insert
  interface InsertCostCentersCallback {
    void onCostCentersInserted(Long[] longs);
    void onDataNotAvailable();
  }
  void insertCostCenters(List<CostCenter> costCenters, @NonNull InsertCostCentersCallback callback);


  interface InsertCostCenterCallback {
    void onCostCenterInserted(long costCenterId);
    void onDataNotAvailable();
  }
  void insertCostCenter(CostCenter costCenter, @NonNull InsertCostCenterCallback callback);


  // Update

  interface UpdateCostCentersCallback {
    void onCostCentersUpdated(int i);
    void onDataNotAvailable();
  }
  void updateCostCenters(@NonNull UpdateCostCentersCallback callback, CostCenter... costCenters);


  interface UpdateCostCenterCallback {
    void onCostCenterUpdated(int i);
    void onDataNotAvailable();
  }
  void updateCostCenter(CostCenter costCenter, @NonNull UpdateCostCenterCallback callback);

  // Delete


  interface DeleteCostCentersCallback {
    void onCostCentersDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteCostCenters(@NonNull DeleteCostCentersCallback callback, CostCenter... costCenters);


  interface DeleteAllCostCenterCallback {
    void onCostCentersDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllCostCenter(@NonNull DeleteAllCostCenterCallback callback);


  interface DeleteCostCenterCallback {
    void onCostCenterDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteCostCenterById(int costCenterId, @NonNull DeleteCostCenterCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetCostCenterNameCallback {
    void onCostCenterNameLoaded(String s);
    void onDataNotAvailable();
  }
  void GetCostCenterNameFromCostCenterId(int costCenterId, @NonNull GetCostCenterNameCallback callback);

  // Count(*)
  interface GetCountCostCenterCallback {
    void onAccountsCounted(int count);
    void onDataNotAvailable();
  }
  void getCountCostCenters(@NonNull GetCountCostCenterCallback callback);

  interface GetCountCostCenterByFullIdCallback {
    void onAccountsCounted(int count);
    void onDataNotAvailable();
  }
  void getCountCostCenterByFullId(String fullId, @NonNull GetCountCostCenterByFullIdCallback callback);

  interface GetCostCenterVectorInfoByCodeCallback {
    void onVectorInfo(AccVectorInfo vectorInfo);
    void onDataNotAvailable();
  }
  void getCostCenterVectorInfoByCode(int id, @NonNull GetCostCenterVectorInfoByCodeCallback callback);

}
