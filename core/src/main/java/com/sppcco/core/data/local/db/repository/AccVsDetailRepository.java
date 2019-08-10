package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.AccVsDetail;
import com.sppcco.core.data.sub_model.AccVectorInfo;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 11/02/18.
 */

public interface AccVsDetailRepository {

  // ________________________________________ CRUD ________________________________________ //


  interface GetAccVsDetailsCallback {
    void onAccVsDetailsLoaded(List<AccVsDetail> accVsDetails);
    void onDataNotAvailable();
  }

  void getAccVsDetails(@NonNull GetAccVsDetailsCallback callback);


  interface GetAccVsDetailCallback {
    void onAccVsDetailLoaded(AccVsDetail accVsDetail);
    void onDataNotAvailable();
  }

  void getAccVsDetailByFullId(String fullId, @NonNull GetAccVsDetailCallback callback);



  // Insert
  interface InsertAccVsDetailsCallback {
    void onAccVsDetailsInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertAccVsDetails(List<AccVsDetail> accVsDetails, @NonNull InsertAccVsDetailsCallback callback);

  interface InsertAccVsDetailCallback {
    void onAccVsDetailInserted(long accVsDetailId);
    void onDataNotAvailable();
  }

  void insertAccVsDetail(AccVsDetail accVsDetail, @NonNull InsertAccVsDetailCallback callback);


  // Update

  interface UpdateAccVsDetailsCallback {
    void onAccVsDetailsUpdated(int i);
    void onDataNotAvailable();
  }

  void updateAccVsDetails(@NonNull UpdateAccVsDetailsCallback callback, AccVsDetail... accVsDetails);


  interface UpdateAccVsDetailCallback {
    void onAccVsDetailUpdated(int i);
    void onDataNotAvailable();
  }

  void updateAccVsDetail(AccVsDetail accVsDetail, @NonNull UpdateAccVsDetailCallback callback);

  // Delete
  interface DeleteAccVsDetailsCallback {
    void onAccVsDetailsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAccVsDetails(@NonNull DeleteAccVsDetailsCallback callback, AccVsDetail... accVsDetails);


  interface DeleteAllAccVsDetailCallback {
    void onAccVsDetailsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllAccVsDetail(@NonNull DeleteAllAccVsDetailCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  // Count(*)
  interface GetCountAccVsDetailCallback {
    void onAccVsDetailCounted(int count);
    void onDataNotAvailable();
  }
  void getCountAccVsDetail(@NonNull GetCountAccVsDetailCallback callback);

  interface GetAccVsDetailRelatedDetailAccCallback {
    void onAccVsDetailRelated(List<AccVectorInfo> accountInfos);
    void onDataNotAvailable();
  }
  void getAccVsDetailRelatedDetailAcc(String fullId, @NonNull GetAccVsDetailRelatedDetailAccCallback callback);
}
