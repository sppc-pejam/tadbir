package com.sppcco.core.data.local.db.repository;



import com.sppcco.core.data.model.Rights;

import java.util.List;

import androidx.annotation.NonNull;

public interface RightsRepository {

  // ________________________________________ CRUD ________________________________________ //

  // select
  interface GetAllRightsCallback {
    void onAllRightsLoaded(List<Rights> Rights);
    void onDataNotAvailable();
  }
  void getAllRights(@NonNull GetAllRightsCallback callback);


  // Insert
  interface InsertRightsCallback {
    void onRightsInserted(Long[] longs);
    void onDataNotAvailable();
  }
  void insertRights(List<Rights> Rights, @NonNull InsertRightsCallback callback);

  // Update

  interface UpdateRightsCallback {
    void onRightsUpdated(int i);
    void onDataNotAvailable();
  }
  void updateRights(@NonNull RightsRepository.UpdateRightsCallback callback, Rights... Rights);


  // Delete
  interface DeleteAllRightsCallback {
    void onRightsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllRight(@NonNull RightsRepository.DeleteAllRightsCallback callback);

  // ________________________________________ CRUD ________________________________________ //


  // Other Method

  interface GetAccessRightCallback{
    void onAccessRightLoaded(String accessRight);
    void onDataNotAvailable();
  }
  void getAccessRight(int SubSystem, int FormId, @NonNull GetAccessRightCallback callback);

  // Count(*)
  interface GetCountRightCallback {
    void onRightsCounted(int count);
    void onDataNotAvailable();
  }
  void getCountRights(@NonNull GetCountRightCallback callback);
}
