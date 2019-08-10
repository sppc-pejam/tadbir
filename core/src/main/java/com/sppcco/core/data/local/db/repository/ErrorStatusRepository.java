package com.sppcco.core.data.local.db.repository;


import com.sppcco.core.data.model.ErrorStatus;

import java.util.List;

import androidx.annotation.NonNull;

public interface ErrorStatusRepository {


  // ________________________________________ CRUD ________________________________________ //

  interface GetErrorStatusesCallback {
    void onErrorStatuesLoaded(List<ErrorStatus> errorStatuses);

    void onDataNotAvailable();
  }

  void getErrorStatuses(@NonNull GetErrorStatusesCallback callback);

  void getErrorStatusByIdAndArticleId(int id, int articleId, int docType, @NonNull GetErrorStatusesCallback callback);

  void getErrorStatusById(int id, int docType, @NonNull GetErrorStatusesCallback callback);

  void getErrorStatusDocId(int id, int docType, @NonNull GetErrorStatusesCallback callback);

  void getErrorStatusDocArticleId(int id, int docType, @NonNull GetErrorStatusesCallback callback);

  // Insert

  interface InsertErrorStatusesCallback {
    void onErrorStatusesInserted(Long[] longs);

    void onDataNotAvailable();
  }

  void insertErrorStatuses(List<ErrorStatus> errorStatuses, @NonNull InsertErrorStatusesCallback callback);

  interface InsertErrorStatusCallback {
    void onErrorStatusInserted(long unitId);

    void onDataNotAvailable();
  }

  void insertErrorStatus(ErrorStatus errorStatus, @NonNull InsertErrorStatusCallback callback);

  // Update

  interface UpdateErrorStatusesCallback {
    void onErrorStatusesUpdated(int i);

    void onDataNotAvailable();
  }

  void updateErrorStatuses(@NonNull UpdateErrorStatusesCallback callback, ErrorStatus... errorStatuses);


  interface UpdateErrorStatusCallback {
    void onErrorStatusUpdated(int i);

    void onDataNotAvailable();
  }

  void updateErrorStatus(ErrorStatus errorStatus, @NonNull UpdateErrorStatusCallback callback);

  // Delete

  interface DeleteErrorStatusesCallback {
    void onErrorStatusesDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteErrorStatuses(@NonNull DeleteErrorStatusesCallback callback, ErrorStatus... errorStatuses);

  void deleteAllErrorStatus(@NonNull DeleteErrorStatusesCallback callback);

  void deleteErrorStatusByIdAndArticleId(int id, int articleId, int docType, @NonNull DeleteErrorStatusesCallback callback);

  void deleteErrorStatusById(int id, int docType, @NonNull DeleteErrorStatusesCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  // Count(*)
  interface GetErrorStatusCountCallback {
    void onErrorStatusCounted(int count);

    void onDataNotAvailable();
  }

  void getErrorStatusCount(@NonNull GetErrorStatusCountCallback callback);

  void getErrorStatusCountByIdAndArticleId(int id, int articleId, int docType, @NonNull GetErrorStatusCountCallback callback);

  void getErrorStatusCountById(int id, int docType, @NonNull GetErrorStatusCountCallback callback);

  void getErrorStatusCountByDocId(int id, int docType, @NonNull GetErrorStatusCountCallback callback);

  void getErrorStatusCountByDocArticleId(int id, int docType, @NonNull GetErrorStatusCountCallback callback);
}
