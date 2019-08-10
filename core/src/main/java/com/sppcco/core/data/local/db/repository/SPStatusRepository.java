package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.SPStatus;

import java.util.List;

import androidx.annotation.NonNull;


public interface SPStatusRepository {

  // ________________________________________ CRUD ________________________________________ //
  
  interface GetSPStatusCallback {
    void onSPStatusLoaded(SPStatus sPStatus);
    void onDataNotAvailable();
  }
  void getSPStatus(int sPStatusId, @NonNull GetSPStatusCallback callback);
  void getSPStatusBySPIdAndFPId(int spId, int factorType, int fpId, @NonNull GetSPStatusCallback callback);

  // Insert

  interface InsertSPStatusCallback {
    void onSPStatusInserted(long sPStatusId);
    void onDataNotAvailable();
  }
  void insertSPStatus(SPStatus sPStatus, @NonNull InsertSPStatusCallback callback);

  interface InsertSPStatusesCallback {
    void onSPStatusesInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertSPStatuses(List<SPStatus> spStatuses, @NonNull InsertSPStatusesCallback callback);


  // Update

  interface UpdateSPStatusCallback {
    void onSPStatusUpdated(int i);
    void onDataNotAvailable();
  }
  void updateSPStatus(SPStatus sPStatus, @NonNull UpdateSPStatusCallback callback);


  // Delete

  interface DeleteAllSPStatusCallback {
    void onSPStatussDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllSPStatus(@NonNull DeleteAllSPStatusCallback callback);


  interface DeleteSPStatusCallback {
    void onSPStatusDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSPStatusById(int sPStatusId, @NonNull DeleteSPStatusCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  interface GetAllPostedSPStatusCallback {
    void onSPStatusLoaded(List<SPStatus> spStatusList);
    void onDataNotAvailable();
  }
  void getAllPostedSPStatus(int factoryType, int fpId, @NonNull GetAllPostedSPStatusCallback callback);
}
