package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.SPStatusDao;
import com.sppcco.core.data.model.SPStatus;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;


public class SPStatusDataSource implements SPStatusRepository {

  //private static volatile SPStatusDataSource INSTANCE;

  private SPStatusDao SPStatusDao;
  private AppExecutors appExecutors;

  @Inject
  public SPStatusDataSource(AppExecutors appExecutors, SPStatusDao SPStatusDao) {
    this.SPStatusDao = SPStatusDao;
    this.appExecutors = appExecutors;
  }

  /*public static SPStatusDataSource getInstance(@NonNull AppExecutors appExecutors,
                                               @NonNull SPStatusDao SPStatusDao) {
    if (INSTANCE == null) {
      synchronized (SPStatusDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new SPStatusDataSource(appExecutors, SPStatusDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getSPStatus(final int sPStatusId, @NonNull final GetSPStatusCallback callback) {
    Runnable runnable = () -> {
      final SPStatus sPStatus = SPStatusDao.getSPStatusById(sPStatusId);

      appExecutors.mainThread().execute(() -> {
        if(sPStatus != null)
          callback.onSPStatusLoaded(sPStatus);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSPStatusBySPIdAndFPId(int spId, int factorType, int fpId, @NonNull GetSPStatusCallback callback) {
    Runnable runnable = () -> {
      final SPStatus sPStatus = SPStatusDao.getSPStatusBySPIdAndFPId(spId, factorType, fpId);

      appExecutors.mainThread().execute(() -> {
        if(sPStatus != null)
          callback.onSPStatusLoaded(sPStatus);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }


  @Override
  public void insertSPStatus(final SPStatus sPStatus, @NonNull final InsertSPStatusCallback callback) {
    Runnable runnable = () -> {
      final long lSPStatusId  = SPStatusDao.insertSPStatus(sPStatus);

      appExecutors.mainThread().execute(() -> {
        if(lSPStatusId != 0)
          callback.onSPStatusInserted(lSPStatusId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSPStatuses(List<SPStatus> spStatuses, @NonNull InsertSPStatusesCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = SPStatusDao.insertSPStatuses(spStatuses);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onSPStatusesInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }


  @Override
  public void updateSPStatus(final SPStatus sPStatus, @NonNull final UpdateSPStatusCallback callback) {
    Runnable runnable = () -> {
      final int i  = SPStatusDao.updateSPStatus(sPStatus);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSPStatusUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }


  @Override
  public void deleteAllSPStatus(@NonNull final DeleteAllSPStatusCallback callback) {
    Runnable runnable = () -> {
      final int i = SPStatusDao.deleteAllSPStatus();

      appExecutors.mainThread().execute(() -> {
        if(i >= 0)
          callback.onSPStatussDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSPStatusById(final int sPStatusId, @NonNull final DeleteSPStatusCallback callback) {
    Runnable runnable = () -> {
      final int i = SPStatusDao.deleteSPStatusById(sPStatusId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSPStatusDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllPostedSPStatus(int factorType, int fpId, @NonNull GetAllPostedSPStatusCallback callback) {
    Runnable runnable = () -> {
      final List<SPStatus> spStatuses = SPStatusDao.getAllPostedSPStatus(factorType, fpId);

      appExecutors.mainThread().execute(() -> {
        if(spStatuses != null)
          callback.onSPStatusLoaded(spStatuses);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}

