package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.sub_model.MerchInfo;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public interface MerchInfoRepository {

  // ________________________________________ CRUD ________________________________________ //

  interface GetMerchInfoCallback {
    void onMerchInfoLoaded(MerchInfo merchInfo);

    void onDataNotAvailable();
  }

  void getAllMerchInfo(int merchandiseId, @NonNull GetMerchInfoCallback callback);

  void getMerchInfoByIdWithoutMerchStock(int isShowImage, int merchId, @NonNull GetMerchInfoCallback callback);

  void getMerchInfoByIdWithoutMerchStock(int isShowImage, int merchId, int stockId, int cabinetId, int customerId, @NonNull GetMerchInfoCallback callback);

  void getMerchInfoByIdWithMerchStock(int isShowImage, int merchId, @NonNull GetMerchInfoCallback callback);

  void getMerchInfoByIdWithMerchStock(int isShowImage, int merchId, int stockId, int cabinetId, int customerId, @NonNull GetMerchInfoCallback callback);

  void getMerchInfoByBarcodeWithoutMerchStock(int isShowImage, String barcode, @NonNull GetMerchInfoCallback callback);

  void getMerchInfoByBarcodeWithMerchStock(int isShowImage, String barcode, @NonNull GetMerchInfoCallback callback);

  interface GetMerchInfosCallback {
    void onMerchInfosLoaded(List<MerchInfo> merchInfo);

    void onDataNotAvailable();
  }

  void getAllMerchInfoBySPIdWithoutMerchStock(int bShowImages, int spId, int fpId, @NonNull GetMerchInfosCallback callback);

  void getAllMerchInfoBySPIdWithMerchStock(int bShowImages, int spId, int fpId, @NonNull GetMerchInfosCallback callback);

  // ________________________________________ CRUD ________________________________________ //

}
