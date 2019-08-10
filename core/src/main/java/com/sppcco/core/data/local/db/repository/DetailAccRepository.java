package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.DetailAcc;
import com.sppcco.core.data.sub_model.DetailAccVectorInfo;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public interface DetailAccRepository {

  // ________________________________________ CRUD ________________________________________ //

  interface GetDetailAccsCallback {
    void onDetailAccsLoaded(List<DetailAcc> detailAccs);
    void onDataNotAvailable();
  }
  void getDetailAccs(@NonNull GetDetailAccsCallback callback);


  interface GetDetailAccCallback {
    void onDetailAccLoaded(DetailAcc detailAcc);
    void onDataNotAvailable();
  }
  void getDetailAcc(int detailAccId, @NonNull GetDetailAccCallback callback);


  // Insert
  interface InsertDetailAccsCallback {
    void onDetailAccsInserted(Long[] longs);
    void onDataNotAvailable();
  }
  void insertDetailAccs(List<DetailAcc> detailAccs, @NonNull InsertDetailAccsCallback callback);

  interface InsertDetailAccCallback {
    void onDetailAccInserted(long detailAccId);
    void onDataNotAvailable();
  }
  void insertDetailAcc(DetailAcc detailAcc, @NonNull InsertDetailAccCallback callback);


  // Update

  interface UpdateDetailAccsCallback {
    void onDetailAccsUpdated(int i);
    void onDataNotAvailable();
  }
  void updateDetailAccs(@NonNull UpdateDetailAccsCallback callback, DetailAcc... detailAccs);


  interface UpdateDetailAccCallback {
    void onDetailAccUpdated(int i);
    void onDataNotAvailable();
  }
  void updateDetailAcc(DetailAcc detailAcc, @NonNull UpdateDetailAccCallback callback);

  // Delete


  interface DeleteDetailAccsCallback {
    void onDetailAccsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteDetailAccs(@NonNull DeleteDetailAccsCallback callback, DetailAcc... detailAccs);


  interface DeleteAllDetailAccCallback {
    void onDetailAccsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllDetailAcc(@NonNull DeleteAllDetailAccCallback callback);


  interface DeleteDetailAccCallback {
    void onDetailAccDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteDetailAccById(int detailAccId, @NonNull DeleteDetailAccCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetDetailAccNameCallback {
    void onDetailAccNameLoaded(String detailAccName);
    void onDataNotAvailable();
  }
  void getDetailAccNameFromDetailAccId(int detailAccId, @NonNull GetDetailAccNameCallback callback);

  // Count(*)
  interface GetCountDetailAccCallback {
    void onDetailAccCounted(int count);
    void onDataNotAvailable();
  }
  void getCountDetailAcc(@NonNull GetCountDetailAccCallback callback);

  interface GetCountDetailAccByFullIdCallback {
    void onDetailAccCounted(int count);
    void onDataNotAvailable();
  }
  void getCountDetailAccByFullId(String fullId, @NonNull GetCountDetailAccByFullIdCallback callback);

  interface GetDetailAccVectorInfoByIdCallback {
    void onVectorInfo(DetailAccVectorInfo vectorInfo);
    void onDataNotAvailable();
  }
  void getDetailAccVectorInfoById(int id, @NonNull GetDetailAccVectorInfoByIdCallback callback);


  interface GetDetailCodeByIdCallback {
    void onDetailCodeLoaded(String detailCode);
    void onDataNotAvailable();
  }
  void getDetailCodeById(int detId, @NonNull GetDetailCodeByIdCallback callback);


  interface GetIsFAccInLeafLevelCallback {
    void onCountFAccInLeafLevel(int inLeafLevel);
    void onDataNotAvailable();
  }
  void isFAccInLeafLevel(String faccFullCode, @NonNull GetIsFAccInLeafLevelCallback callback);

  interface GetFAccCodeCallback {
    void onFAccCode(String faccCode);
    void onDataNotAvailable();
  }
  void getFAccCode(int faccFullCode, @NonNull GetFAccCodeCallback callback);
}
