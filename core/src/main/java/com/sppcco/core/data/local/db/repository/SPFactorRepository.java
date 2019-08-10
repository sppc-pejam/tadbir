package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.SPFactor;
import com.sppcco.core.data.sub_model.ApprovedPrefactorInfo;

import java.util.List;

import androidx.annotation.NonNull;

public interface SPFactorRepository {


  // ________________________________________ CRUD ________________________________________ //

  interface GetSPFactorsCallback {
    void onSPFactorsLoaded(List<SPFactor> spFactors);
    void onDataNotAvailable();
  }

  void getSPFactors(@NonNull GetSPFactorsCallback callback);


  interface GetSPFactorCallback {
    void onSPFactorLoaded(SPFactor spFactor);
    void onDataNotAvailable();
  }

  void getSPFactor(int spFactorId, @NonNull GetSPFactorCallback callback);


  // Insert
  interface InsertSPFactorsCallback {
    void onSPFactorsInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertSPFactors(List<SPFactor> spFactors, @NonNull InsertSPFactorsCallback callback);

  interface InsertSPFactorCallback {
    void onSPFactorInserted(long spFactorId);
    void onDataNotAvailable();
  }

  void insertSPFactor(SPFactor spFactor, @NonNull InsertSPFactorCallback callback);


  // Update

  interface UpdateSPFactorsCallback {
    void onSPFactorsUpdated(int i);
    void onDataNotAvailable();
  }

  void updateSPFactors(@NonNull UpdateSPFactorsCallback callback, SPFactor... spFactors);


  interface UpdateSPFactorCallback {
    void onSPFactorUpdated(int i);
    void onDataNotAvailable();
  }

  void updateSPFactor(SPFactor spFactor, @NonNull UpdateSPFactorCallback callback);

  // Delete


  interface DeleteSPFactorsCallback {
    void onSPFactorsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSPFactors(@NonNull DeleteSPFactorsCallback callback, SPFactor... spFactors);


  interface DeleteAllSPFactorCallback {
    void onSPFactorsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllSPFactor(@NonNull DeleteAllSPFactorCallback callback);


  interface DeleteSPFactorCallback {
    void onSPFactorDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSPFactorById(int spFactorId, @NonNull DeleteSPFactorCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetNextFactorNoCallback {
    void onNextFactorNoLoaded(int nextFactorNo);
    void onDataNotAvailable();
  }

  void getNextFactorNo(int FPId, int UserId, @NonNull GetNextFactorNoCallback callback);

  interface GetApprovedSPFactorDetailes {
    void onApprovedSPFactorDetailesLoaded(List<ApprovedPrefactorInfo> approvedSPFactorDetailes);
    void onDataNotAvailable();
  }
  void getApprovedSPFactorDetailes(@NonNull GetApprovedSPFactorDetailes callback);

  interface UpdateSPFactorReference{
    void onSPFactorReferenceUpdated(int i);
    void onDataNotAvailable();
  }
  void updateSPFactorReference(int spid, int spreference, int fpid, @NonNull UpdateSPFactorReference callback);

  void getNotApprovedSPFactor(int FPId, int UserId, @NonNull GetSPFactorCallback callback);
}
