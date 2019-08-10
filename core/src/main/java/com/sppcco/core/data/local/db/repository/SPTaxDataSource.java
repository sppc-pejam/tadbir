package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.SPTaxDao;
import com.sppcco.core.data.model.SPTax;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;


public class SPTaxDataSource implements SPTaxRepository {

  //private static volatile SPTaxDataSource INSTANCE;

  private SPTaxDao SPTaxDao;
  private AppExecutors appExecutors;

  @Inject
  public SPTaxDataSource(AppExecutors appExecutors, SPTaxDao SPTaxDao) {
    this.SPTaxDao = SPTaxDao;
    this.appExecutors = appExecutors;
  }

  /*public static SPTaxDataSource getInstance(@NonNull AppExecutors appExecutors,
                                            @NonNull SPTaxDao SPTaxDao) {
    if (INSTANCE == null) {
      synchronized (SPTaxDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new SPTaxDataSource(appExecutors, SPTaxDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getSPTaxs(@NonNull final GetSPTaxsCallback callback) {
    Runnable runnable = () -> {
      final List<SPTax> SPTaxs = SPTaxDao.getAllSPTax();

      appExecutors.mainThread().execute(() -> {
        if (SPTaxs != null)
          callback.onSPTaxsLoaded(SPTaxs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSPTaxBySPId(int spId, @NonNull GetSPTaxsCallback callback) {
    Runnable runnable = () -> {
      final List<SPTax> SPTaxs = SPTaxDao.getSPTaxBySPId(spId);

      appExecutors.mainThread().execute(() -> {
        if (SPTaxs != null)
          callback.onSPTaxsLoaded(SPTaxs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSPTaxById(final int Id, @NonNull final GetSPTaxCallback callback) {
    Runnable runnable = () -> {
      final SPTax SPTax = SPTaxDao.getSPTaxById(Id);

      appExecutors.mainThread().execute(() -> {
        if (SPTax != null)
          callback.onSPTaxLoaded(SPTax);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSPTaxs(final List<SPTax> SPTaxs, @NonNull final InsertSPTaxsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = SPTaxDao.insertSPTaxs(SPTaxs);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onSPTaxsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSPTax(final SPTax SPTax, @NonNull final InsertSPTaxCallback callback) {
    Runnable runnable = () -> {
      final long lSPTaxId = SPTaxDao.insertSPTax(SPTax);

      appExecutors.mainThread().execute(() -> {
        if (lSPTaxId != 0)
          callback.onSPTaxInserted(lSPTaxId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSPTaxs(@NonNull final UpdateSPTaxsCallback callback, final SPTax... SPTaxs) {
    Runnable runnable = () -> {
      final int i = SPTaxDao.updateSPTaxs(SPTaxs);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSPTaxsUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSPTax(final SPTax SPTax, @NonNull UpdateSPTaxCallback callback) {
    Runnable runnable = () -> {
      final int i = SPTaxDao.updateSPTax(SPTax);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSPTaxUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSPTaxs(@NonNull final DeleteSPTaxsCallback callback, final SPTax... SPTaxs) {
    Runnable runnable = () -> {
      final int i = SPTaxDao.deleteSPTaxs(SPTaxs);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSPTaxsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllSPTax(@NonNull final DeleteAllSPTaxCallback callback) {
    Runnable runnable = () -> {
      final int i = SPTaxDao.deleteAllSPTax();

      appExecutors.mainThread().execute(() -> {
        if (i >= 0)
          callback.onSPTaxsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSPTaxBySPId(final int SPId, @NonNull final DeleteSPTaxCallback callback) {
    Runnable runnable = () -> {
      final int i = SPTaxDao.deleteSPTaxBySPId(SPId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSPTaxDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
