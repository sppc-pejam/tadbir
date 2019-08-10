package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.SOStatus;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/24/18.
 */

public interface SOStatusRepository {

  // ________________________________________ CRUD ________________________________________ //
  
  interface GetSOStatusCallback {
    void onSOStatusLoaded(SOStatus sOStatus);
    void onDataNotAvailable();
  }
  void getSOStatus(int sOStatusId, @NonNull GetSOStatusCallback callback);
  void getSOStatusBySOIdAndFPId(int soId, int fpId, @NonNull GetSOStatusCallback callback);

  // Insert

  interface InsertSOStatusCallback {
    void onSOStatusInserted(long sOStatusId);
    void onDataNotAvailable();
  }
  void insertSOStatus(SOStatus sOStatus, @NonNull InsertSOStatusCallback callback);

  interface InsertSOStatusesCallback {
    void onSOStatusesInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertSOStatuses(List<SOStatus> soStatuses, @NonNull InsertSOStatusesCallback callback);


  // Update

  interface UpdateSOStatusCallback {
    void onSOStatusUpdated(int i);
    void onDataNotAvailable();
  }
  void updateSOStatus(SOStatus sOStatus, @NonNull UpdateSOStatusCallback callback);


  // Delete

  interface DeleteAllSOStatusCallback {
    void onSOStatussDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllSOStatus(@NonNull DeleteAllSOStatusCallback callback);


  interface DeleteSOStatusCallback {
    void onSOStatusDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSOStatusById(int sOStatusId, @NonNull DeleteSOStatusCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  interface GetAllPostedSOStatusCallback {
    void onSOStatusLoaded(List<SOStatus> soStatusList);
    void onDataNotAvailable();
  }
  void getAllPostedSOStatus(int fpId, @NonNull GetAllPostedSOStatusCallback callback);
}
