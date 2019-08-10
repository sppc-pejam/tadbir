package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.MerchTax;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;

public interface MerchTaxRepository {
  // ________________________________________ CRUD ________________________________________ //

  interface GetMerchTaxsCallback {
    void onMerchTaxsLoaded(List<MerchTax> MerchTaxs);

    void onDataNotAvailable();
  }

  void getAllMerchTax(@NonNull GetMerchTaxsCallback callback);


  interface GetMerchTaxCallback {
    void onMerchTaxLoaded(MerchTax merchandise);

    void onDataNotAvailable();
  }

  void getMerchTaxByMerchId(int merchId, @NonNull GetMerchTaxCallback callback);

  void getMerchTaxByMerchIdAndSPDate(int merchId, Date spDate, @NonNull GetMerchTaxCallback callback);


  // Insert
  interface InsertMerchTaxsCallback {
    void onMerchTaxsInserted(Long[] longs);

    void onDataNotAvailable();
  }

  void insertMerchTaxs(List<MerchTax> MerchTaxs, @NonNull InsertMerchTaxsCallback callback);

  interface InsertMerchTaxCallback {
    void onMerchTaxInserted(long merchandiseId);

    void onDataNotAvailable();
  }

  void insertMerchTax(MerchTax merchandise, @NonNull InsertMerchTaxCallback callback);


  // Update
  interface UpdateMerchTaxsCallback {
    void onMerchTaxsUpdated(int i);

    void onDataNotAvailable();
  }

  void updateMerchTaxs(@NonNull UpdateMerchTaxsCallback callback, MerchTax... MerchTaxs);


  interface UpdateMerchTaxCallback {
    void onMerchTaxUpdated(int i);

    void onDataNotAvailable();
  }

  void updateMerchTax(MerchTax MerchTax, @NonNull UpdateMerchTaxCallback callback);

  // Delete
  interface DeleteMerchTaxsCallback {
    void onMerchTaxsDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteMerchTaxs(@NonNull DeleteMerchTaxsCallback callback, MerchTax... MerchTaxs);


  interface DeleteAllMerchTaxCallback {
    void onMerchTaxsDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteAllMerchTax(@NonNull DeleteAllMerchTaxCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Count(*)
  interface GetCountMerchTaxCallback {
    void onMerchTaxCounted(int count);
  }

  void getCountMerchTax(@NonNull GetCountMerchTaxCallback callback);
  void getCountMerchTaxByMerchIdAndSPDate(int merchId, Date spDate, @NonNull GetCountMerchTaxCallback callback);
}
