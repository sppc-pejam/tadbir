package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.ApiServiceInfo;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public interface ApiServiceInfoRepository {

  // ________________________________________ CRUD ________________________________________ //


  interface GetApiServiceInfosCallback {
    void onApiServiceInfosLoaded(List<ApiServiceInfo> apiServiceInfos);
    void onDataNotAvailable();
  }

  void getApiServiceInfos(@NonNull GetApiServiceInfosCallback callback);


  interface GetApiServiceInfoCallback {
    void onApiServiceInfoLoaded(ApiServiceInfo apiServiceInfo);
    void onDataNotAvailable();
  }

  void getApiServiceInfo(int apiServiceInfoId, @NonNull GetApiServiceInfoCallback callback);


  // Insert
  interface InsertApiServiceInfosCallback {
    void onApiServiceInfosInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertApiServiceInfos(List<ApiServiceInfo> apiServiceInfos, @NonNull InsertApiServiceInfosCallback callback);

  interface InsertApiServiceInfoCallback {
    void onApiServiceInfoInserted(long apiServiceInfoId);
    void onDataNotAvailable();
  }

  void insertApiServiceInfo(ApiServiceInfo apiServiceInfo, @NonNull InsertApiServiceInfoCallback callback);


  // Update

  interface UpdateApiServiceInfosCallback {
    void onApiServiceInfosUpdated(int i);
    void onDataNotAvailable();
  }

  void updateApiServiceInfos(@NonNull UpdateApiServiceInfosCallback callback, ApiServiceInfo... apiServiceInfos);


  interface UpdateApiServiceInfoCallback {
    void onApiServiceInfoUpdated(int i);
    void onDataNotAvailable();
  }

  void updateApiServiceInfo(ApiServiceInfo apiServiceInfo, @NonNull UpdateApiServiceInfoCallback callback);

  // Delete


  interface DeleteApiServiceInfosCallback {
    void onApiServiceInfosDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteApiServiceInfos(@NonNull DeleteApiServiceInfosCallback callback, ApiServiceInfo... apiServiceInfos);


  interface DeleteAllApiServiceInfoCallback {
    void onApiServiceInfosDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllApiServiceInfo(@NonNull DeleteAllApiServiceInfoCallback callback);


  interface DeleteApiServiceInfoCallback {
    void onApiServiceInfoDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteApiServiceInfoById(int apiServiceInfoId, @NonNull DeleteApiServiceInfoCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method
  
  
}
