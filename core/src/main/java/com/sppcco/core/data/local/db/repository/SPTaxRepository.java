package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.SPTax;

import java.util.List;

import androidx.annotation.NonNull;

public interface SPTaxRepository {


  // ________________________________________ CRUD ________________________________________ //

  interface GetSPTaxsCallback {
    void onSPTaxsLoaded(List<SPTax> SPTaxs);

    void onDataNotAvailable();
  }

  void getSPTaxs(@NonNull GetSPTaxsCallback callback);
  void getSPTaxBySPId(int spId, @NonNull GetSPTaxsCallback callback);


  interface GetSPTaxCallback {
    void onSPTaxLoaded(SPTax SPTax);

    void onDataNotAvailable();
  }

  void getSPTaxById(int Id, @NonNull GetSPTaxCallback callback);


  // Insert
  interface InsertSPTaxsCallback {
    void onSPTaxsInserted(Long[] longs);

    void onDataNotAvailable();
  }

  void insertSPTaxs(List<SPTax> SPTaxs, @NonNull InsertSPTaxsCallback callback);

  interface InsertSPTaxCallback {
    void onSPTaxInserted(long SPTaxId);

    void onDataNotAvailable();
  }

  void insertSPTax(SPTax SPTax, @NonNull InsertSPTaxCallback callback);


  // Update

  interface UpdateSPTaxsCallback {
    void onSPTaxsUpdated(int i);

    void onDataNotAvailable();
  }

  void updateSPTaxs(@NonNull UpdateSPTaxsCallback callback, SPTax... SPTaxs);


  interface UpdateSPTaxCallback {
    void onSPTaxUpdated(int i);

    void onDataNotAvailable();
  }

  void updateSPTax(SPTax SPTax, @NonNull UpdateSPTaxCallback callback);

  // Delete


  interface DeleteSPTaxsCallback {
    void onSPTaxsDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteSPTaxs(@NonNull DeleteSPTaxsCallback callback, SPTax... SPTaxs);


  interface DeleteAllSPTaxCallback {
    void onSPTaxsDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteAllSPTax(@NonNull DeleteAllSPTaxCallback callback);


  interface DeleteSPTaxCallback {
    void onSPTaxDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteSPTaxBySPId(int spId, @NonNull DeleteSPTaxCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

}
