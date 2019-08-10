package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.AccVsCC;
import com.sppcco.core.data.sub_model.AccVectorInfo;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 11/02/18.
 */

public interface AccVsCCRepository {

  // ________________________________________ CRUD ________________________________________ //


  interface GetAccVsCCsCallback {
    void onAccVsCCsLoaded(List<AccVsCC> accVsCCs);
    void onDataNotAvailable();
  }

  void getAccVsCCs(@NonNull GetAccVsCCsCallback callback);


  interface GetAccVsCCCallback {
    void onAccVsCCLoaded(AccVsCC accVsCC);
    void onDataNotAvailable();
  }

  void getAccVsCCByFullId(String fullId, @NonNull GetAccVsCCCallback callback);



  // Insert
  interface InsertAccVsCCsCallback {
    void onAccVsCCsInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertAccVsCCs(List<AccVsCC> accVsCCs, @NonNull InsertAccVsCCsCallback callback);

  interface InsertAccVsCCCallback {
    void onAccVsCCInserted(long accVsCCId);
    void onDataNotAvailable();
  }

  void insertAccVsCC(AccVsCC accVsCC, @NonNull InsertAccVsCCCallback callback);


  // Update

  interface UpdateAccVsCCsCallback {
    void onAccVsCCsUpdated(int i);
    void onDataNotAvailable();
  }

  void updateAccVsCCs(@NonNull UpdateAccVsCCsCallback callback, AccVsCC... accVsCCs);


  interface UpdateAccVsCCCallback {
    void onAccVsCCUpdated(int i);
    void onDataNotAvailable();
  }

  void updateAccVsCC(AccVsCC accVsCC, @NonNull UpdateAccVsCCCallback callback);

  // Delete
  interface DeleteAccVsCCsCallback {
    void onAccVsCCsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAccVsCCs(@NonNull DeleteAccVsCCsCallback callback, AccVsCC... accVsCCs);


  interface DeleteAllAccVsCCCallback {
    void onAccVsCCsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllAccVsCC(@NonNull DeleteAllAccVsCCCallback callback);
  
  // ________________________________________ CRUD ________________________________________ //

  // Other Method
  
  // Count(*)
  interface GetCountAccVsCCCallback {
    void onAccVsCCCounted(int count);
    void onDataNotAvailable();
  }
  void getCountAccVsCC(@NonNull GetCountAccVsCCCallback callback);

  interface GetAccVsCCRelatedCostCenterCallback {
    void onAccVsCCRelated(List<AccVectorInfo> accountInfos);
    void onDataNotAvailable();
  }
  void getAccVsCCRelatedCostCenter(String fullId, @NonNull GetAccVsCCRelatedCostCenterCallback callback);
}
