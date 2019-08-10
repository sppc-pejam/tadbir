package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.sub_model.AccVectorInfo;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 11/02/18.
 */

public interface AccVectorInfoRepository {

  interface GetAllAccountCallback {
    void onAllAccount(List<AccVectorInfo> accVectorInfos);
    void onDataNotAvailable();
  }
  void getAllAccount(@NonNull GetAllAccountCallback callback);

  interface GetAllDetailAccCallback {
    void onAllDetailAcc(List<AccVectorInfo> accVectorInfos);
    void onDataNotAvailable();
  }
  void getAllDetailAcc(@NonNull GetAllDetailAccCallback callback);

  interface GetAllCostCenterCallback {
    void onAllCostCenter(List<AccVectorInfo> accVectorInfos);
    void onDataNotAvailable();
  }
  void getAllCostCenter(@NonNull GetAllCostCenterCallback callback);

  interface GetAllProjectCallback {
    void onAllProject(List<AccVectorInfo> accVectorInfos);
    void onDataNotAvailable();
  }
  void getAllProject(@NonNull GetAllProjectCallback callback);

  interface GetAccountByDetIdCallback {
    void onAccVectorInfo(List<AccVectorInfo> accVectorInfos);
    void onDataNotAvailable();
  }
  void getAccountByDetId(int detId, @NonNull GetAccountByDetIdCallback callback);

  interface GetRelatedAccountInfoAccVsCCCallback {
    void onRelatedAccountInfo(List<AccVectorInfo> accVectorInfos);
    void onDataNotAvailable();
  }
  void getRelatedAccountInfoByAccVsCC(int fpId, int ccId, @NonNull GetRelatedAccountInfoAccVsCCCallback callback);

  interface GetRelatedAccountInfoAccVsPrjCallback {
    void onRelatedAccountInfo(List<AccVectorInfo> accVectorInfos);
    void onDataNotAvailable();
  }
  void getRelatedAccountInfoByAccVsPrj(int fpId, int prjId, @NonNull GetRelatedAccountInfoAccVsPrjCallback callback);
}
