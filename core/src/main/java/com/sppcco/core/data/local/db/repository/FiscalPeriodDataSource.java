package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.FiscalPeriodDao;
import com.sppcco.core.data.model.FiscalPeriod;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public class FiscalPeriodDataSource implements FiscalPeriodRepository {

  //private static volatile FiscalPeriodDataSource INSTANCE;

  private FiscalPeriodDao fiscalPeriodDao;
  private AppExecutors appExecutors;

  @Inject
  public FiscalPeriodDataSource(AppExecutors appExecutors, FiscalPeriodDao fiscalPeriodDao) {
    this.fiscalPeriodDao = fiscalPeriodDao;
    this.appExecutors = appExecutors;
  }

  /*public static FiscalPeriodDataSource getInstance(@NonNull AppExecutors appExecutors,
                                              @NonNull FiscalPeriodDao fiscalPeriodDao) {
    if (INSTANCE == null) {
      synchronized (FiscalPeriodDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new FiscalPeriodDataSource(appExecutors, fiscalPeriodDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getFiscalPeriods(@NonNull final GetFiscalPeriodsCallback callback) {
    Runnable runnable = () -> {
      final List<FiscalPeriod> fiscalPeriods = fiscalPeriodDao.getAllFiscalPeriod();

      appExecutors.mainThread().execute(() -> {
        if(fiscalPeriods != null)
          callback.onFiscalPeriodsLoaded(fiscalPeriods);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getFiscalPeriod(final int fiscalPeriodId, @NonNull final GetFiscalPeriodCallback callback) {
    Runnable runnable = () -> {
      final FiscalPeriod fiscalPeriod = fiscalPeriodDao.getFiscalPeriodById(fiscalPeriodId);

      appExecutors.mainThread().execute(() -> {
        if(fiscalPeriod != null)
          callback.onFiscalPeriodLoaded(fiscalPeriod);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertFiscalPeriods(final List<FiscalPeriod> fiscalPeriods, @NonNull final InsertFiscalPeriodsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = fiscalPeriodDao.insertFiscalPeriods(fiscalPeriods);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onFiscalPeriodsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertFiscalPeriod(final FiscalPeriod fiscalPeriod, @NonNull final InsertFiscalPeriodCallback callback) {
    Runnable runnable = () -> {
      final long lFiscalPeriodId  = fiscalPeriodDao.insertFiscalPeriod(fiscalPeriod);

      appExecutors.mainThread().execute(() -> {
        if(lFiscalPeriodId != 0)
          callback.onFiscalPeriodInserted(lFiscalPeriodId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateFiscalPeriods(@NonNull final UpdateFiscalPeriodsCallback callback, final FiscalPeriod... fiscalPeriods) {
    Runnable runnable = () -> {
      final int i = fiscalPeriodDao.updateFiscalPeriods(fiscalPeriods);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onFiscalPeriodsUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateFiscalPeriod(final FiscalPeriod fiscalPeriod, @NonNull final UpdateFiscalPeriodCallback callback) {
    Runnable runnable = () -> {
      final int i  = fiscalPeriodDao.updateFiscalPeriod(fiscalPeriod);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onFiscalPeriodUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteFiscalPeriods(@NonNull final DeleteFiscalPeriodsCallback callback, final FiscalPeriod... fiscalPeriods) {
    Runnable runnable = () -> {
      final int i = fiscalPeriodDao.deleteFiscalPeriods(fiscalPeriods);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onFiscalPeriodsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllFiscalPeriod(@NonNull final DeleteAllFiscalPeriodCallback callback) {
    Runnable runnable = () -> {
      final int i = fiscalPeriodDao.deleteAllFiscalPeriod();

      appExecutors.mainThread().execute(() -> {
        if(i >= 0)
          callback.onFiscalPeriodsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteFiscalPeriodById(final int fiscalPeriodId, @NonNull final DeleteFiscalPeriodCallback callback) {
    Runnable runnable = () -> {
      final int i = fiscalPeriodDao.deleteFiscalPeriodById(fiscalPeriodId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onFiscalPeriodDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
