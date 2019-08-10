package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.FiscalPeriod;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public interface FiscalPeriodRepository {

  // ________________________________________ CRUD ________________________________________ //


  interface GetFiscalPeriodsCallback {
    void onFiscalPeriodsLoaded(List<FiscalPeriod> fiscalPeriods);
    void onDataNotAvailable();
  }

  void getFiscalPeriods(@NonNull GetFiscalPeriodsCallback callback);


  interface GetFiscalPeriodCallback {
    void onFiscalPeriodLoaded(FiscalPeriod fiscalPeriod);
    void onDataNotAvailable();
  }

  void getFiscalPeriod(int fiscalPeriodId, @NonNull GetFiscalPeriodCallback callback);


  // Insert
  interface InsertFiscalPeriodsCallback {
    void onFiscalPeriodsInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertFiscalPeriods(List<FiscalPeriod> fiscalPeriods, @NonNull InsertFiscalPeriodsCallback callback);

  interface InsertFiscalPeriodCallback {
    void onFiscalPeriodInserted(long fiscalPeriodId);
    void onDataNotAvailable();
  }

  void insertFiscalPeriod(FiscalPeriod fiscalPeriod, @NonNull InsertFiscalPeriodCallback callback);


  // Update

  interface UpdateFiscalPeriodsCallback {
    void onFiscalPeriodsUpdated(int i);
    void onDataNotAvailable();
  }

  void updateFiscalPeriods(@NonNull UpdateFiscalPeriodsCallback callback, FiscalPeriod... fiscalPeriods);


  interface UpdateFiscalPeriodCallback {
    void onFiscalPeriodUpdated(int i);
    void onDataNotAvailable();
  }

  void updateFiscalPeriod(FiscalPeriod fiscalPeriod, @NonNull UpdateFiscalPeriodCallback callback);

  // Delete


  interface DeleteFiscalPeriodsCallback {
    void onFiscalPeriodsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteFiscalPeriods(@NonNull DeleteFiscalPeriodsCallback callback, FiscalPeriod... fiscalPeriods);


  interface DeleteAllFiscalPeriodCallback {
    void onFiscalPeriodsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllFiscalPeriod(@NonNull DeleteAllFiscalPeriodCallback callback);


  interface DeleteFiscalPeriodCallback {
    void onFiscalPeriodDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteFiscalPeriodById(int fiscalPeriodId, @NonNull DeleteFiscalPeriodCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method


}
