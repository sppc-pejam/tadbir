package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.BinAppendix;

import java.util.List;

import androidx.annotation.NonNull;

public interface BinAppendixRepository {

  interface GetBinAppendicesCallback {
    void onBinAppendicesLoaded(List<BinAppendix> binAppendices);
    void onDataNotAvailable();
  }

  void getBinAppendices(@NonNull BinAppendixRepository.GetBinAppendicesCallback callback);


  interface GetBinAppendixByMerchIdCallback {
    void onBinAppendixLoaded(BinAppendix binAppendix);
    void onDataNotAvailable();
  }

  void getBinAppendixByMerchId(int merchId, @NonNull BinAppendixRepository.GetBinAppendixByMerchIdCallback callback);



  // Insert
  interface InsertBinAppendicesCallback {
    void onBinAppendicesInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertBinAppendices(List<BinAppendix> binAppendices, @NonNull BinAppendixRepository.InsertBinAppendicesCallback callback);

  interface InsertBinAppendixCallback {
    void onBinAppendixInserted(long accountId);
    void onDataNotAvailable();
  }

  void insertBinAppendix(BinAppendix binAppendix, @NonNull BinAppendixRepository.InsertBinAppendixCallback callback);


  // Update

  interface UpdateBinAppendicesCallback {
    void onBinAppendicesUpdated(int i);
    void onDataNotAvailable();
  }

  void updateBinAppendices(@NonNull BinAppendixRepository.UpdateBinAppendicesCallback callback, BinAppendix... binAppendices);


  interface UpdateBinAppendixCallback {
    void onBinAppendixUpdated(int i);
    void onDataNotAvailable();
  }

  void updateBinAppendix(BinAppendix binAppendix, @NonNull BinAppendixRepository.UpdateBinAppendixCallback callback);

  // Delete


  interface DeleteBinAppendicesCallback {
    void onBinAppendicesDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteBinAppendices(@NonNull BinAppendixRepository.DeleteBinAppendicesCallback callback, BinAppendix... binAppendices);


  interface DeleteAllBinAppendixCallback {
    void onBinAppendicesDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllBinAppendix(@NonNull BinAppendixRepository.DeleteAllBinAppendixCallback callback);


  interface DeleteBinAppendixByMerchIdCallback {
    void onBinAppendixDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteBinAppendixByMerchId(int merchId, @NonNull BinAppendixRepository.DeleteBinAppendixByMerchIdCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  // Count(*)
  interface GetCountBinAppendixCallback {
    void onGetBinAppendixCounted(int count);
    void onDataNotAvailable();
  }
  void getCountBinAppendix(@NonNull BinAppendixRepository.GetCountBinAppendixCallback callback);
}
