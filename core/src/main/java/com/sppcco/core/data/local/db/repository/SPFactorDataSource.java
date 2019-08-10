package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.SPFactorDao;
import com.sppcco.core.data.model.SPFactor;
import com.sppcco.core.data.sub_model.ApprovedPrefactorInfo;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;


public class SPFactorDataSource implements SPFactorRepository {

  //private static volatile SPFactorDataSource INSTANCE;

  private SPFactorDao spFactorDao;
  private AppExecutors appExecutors;

  @Inject
  public SPFactorDataSource(AppExecutors appExecutors, SPFactorDao spFactorDao) {
    this.spFactorDao = spFactorDao;
    this.appExecutors = appExecutors;
  }

  /*public static SPFactorDataSource getInstance(@NonNull AppExecutors appExecutors,
                                               @NonNull SPFactorDao spFactorDao) {
    if (INSTANCE == null) {
      synchronized (SPFactorDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new SPFactorDataSource(appExecutors, spFactorDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getSPFactors(@NonNull final GetSPFactorsCallback callback) {
    Runnable runnable = () -> {
      final List<SPFactor> spFactors = spFactorDao.getAllSPFactor();

      appExecutors.mainThread().execute(() -> {
        if(spFactors != null)
          callback.onSPFactorsLoaded(spFactors);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSPFactor(final int spFactorId, @NonNull final GetSPFactorCallback callback) {
    Runnable runnable = () -> {
      final SPFactor spFactor = spFactorDao.getSPFactorById(spFactorId);

      appExecutors.mainThread().execute(() -> {
        if(spFactor != null)
          callback.onSPFactorLoaded(spFactor);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSPFactors(final List<SPFactor> spFactors, @NonNull final InsertSPFactorsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = spFactorDao.insertSPFactors(spFactors);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onSPFactorsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSPFactor(final SPFactor spFactor, @NonNull final InsertSPFactorCallback callback) {
    Runnable runnable = () -> {
      final long lSPFactorId  = spFactorDao.insertSPFactor(spFactor);

      appExecutors.mainThread().execute(() -> {
        if(lSPFactorId != 0)
          callback.onSPFactorInserted(lSPFactorId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSPFactors(@NonNull final UpdateSPFactorsCallback callback, final SPFactor... spFactors) {
    Runnable runnable = () -> {
      final int i = spFactorDao.updateSPFactors(spFactors);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSPFactorsUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSPFactor(final SPFactor spFactor, @NonNull UpdateSPFactorCallback callback) {
    Runnable runnable = () -> {
      final int i  = spFactorDao.updateSPFactor(spFactor);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSPFactorUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSPFactors(@NonNull final DeleteSPFactorsCallback callback, final SPFactor... spFactors) {
    Runnable runnable = () -> {
      final int i = spFactorDao.deleteSPFactors(spFactors);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSPFactorsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllSPFactor(@NonNull final DeleteAllSPFactorCallback callback) {
    Runnable runnable = () -> {
      final int i = spFactorDao.deleteAllSPFactor();

      appExecutors.mainThread().execute(() -> {
        if(i >= 0)
          callback.onSPFactorsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSPFactorById(final int spFactorId, @NonNull final DeleteSPFactorCallback callback) {
    Runnable runnable = () -> {
      final int i = spFactorDao.deleteSPFactorById(spFactorId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSPFactorDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getNextFactorNo(int FPId, int UserId, @NonNull GetNextFactorNoCallback callback) {
    Runnable runnable = () -> {
      int nextFactorNo = spFactorDao.getNextFactorNo(FPId, UserId);

      appExecutors.mainThread().execute(() -> {
        if(nextFactorNo <0)
          callback.onDataNotAvailable();
        else
          callback.onNextFactorNoLoaded(nextFactorNo + 1);
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getApprovedSPFactorDetailes(@NonNull GetApprovedSPFactorDetailes callback) {
    Runnable runnable = () -> {
      List<ApprovedPrefactorInfo> approvedSPFactorDetailes = spFactorDao.getApprovedSPFactorDetails();

      appExecutors.mainThread().execute(() -> {
       if(approvedSPFactorDetailes != null)
         callback.onApprovedSPFactorDetailesLoaded(approvedSPFactorDetailes);
       else
         callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSPFactorReference(int spid, int spreference, int fpid, @NonNull UpdateSPFactorReference callback) {
    Runnable runnable = () -> {
      int count = spFactorDao.updateSPFactorReference(spid, spreference, fpid);

      appExecutors.mainThread().execute(() -> {
        if(count != 0)
          callback.onSPFactorReferenceUpdated(count);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getNotApprovedSPFactor(int FPId, int UserId, @NonNull GetSPFactorCallback callback) {
      Runnable runnable = () -> {
        final SPFactor spFactor = spFactorDao.getNotApprovedSPFactor(FPId,  UserId);

        appExecutors.mainThread().execute(() -> {
          if(spFactor != null)
            callback.onSPFactorLoaded(spFactor);
          else
            callback.onDataNotAvailable();
        });
      };

      appExecutors.diskIO().execute(runnable);
    }

}
