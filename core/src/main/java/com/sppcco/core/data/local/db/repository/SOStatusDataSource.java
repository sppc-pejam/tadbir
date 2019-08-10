package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.SOStatusDao;
import com.sppcco.core.data.model.SOStatus;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/24/18.
 */

public class SOStatusDataSource implements SOStatusRepository {

  //private static volatile SOStatusDataSource INSTANCE;

  private SOStatusDao SOStatusDao;
  private AppExecutors appExecutors;

  @Inject
  public SOStatusDataSource(AppExecutors appExecutors, SOStatusDao SOStatusDao) {
    this.SOStatusDao = SOStatusDao;
    this.appExecutors = appExecutors;
  }

  /*public static SOStatusDataSource getInstance(@NonNull AppExecutors appExecutors,
                                               @NonNull SOStatusDao SOStatusDao) {
    if (INSTANCE == null) {
      synchronized (SOStatusDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new SOStatusDataSource(appExecutors, SOStatusDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getSOStatus(final int sOStatusId, @NonNull final GetSOStatusCallback callback) {
    Runnable runnable = () -> {
      final SOStatus sOStatus = SOStatusDao.getSOStatusById(sOStatusId);

      appExecutors.mainThread().execute(() -> {
        if(sOStatus != null)
          callback.onSOStatusLoaded(sOStatus);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSOStatusBySOIdAndFPId(int soId, int fpId, @NonNull GetSOStatusCallback callback) {
    Runnable runnable = () -> {
      final SOStatus sOStatus = SOStatusDao.getSOStatusBySOIdAndFPId(soId, fpId);

      appExecutors.mainThread().execute(() -> {
        if(sOStatus != null)
          callback.onSOStatusLoaded(sOStatus);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }


  @Override
  public void insertSOStatus(final SOStatus sOStatus, @NonNull final InsertSOStatusCallback callback) {
    Runnable runnable = () -> {
      final long lSOStatusId  = SOStatusDao.insertSOStatus(sOStatus);

      appExecutors.mainThread().execute(() -> {
        if(lSOStatusId != 0)
          callback.onSOStatusInserted(lSOStatusId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSOStatuses(List<SOStatus> soStatuses, @NonNull InsertSOStatusesCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = SOStatusDao.insertSOStatuses(soStatuses);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onSOStatusesInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }


  @Override
  public void updateSOStatus(final SOStatus sOStatus, @NonNull final UpdateSOStatusCallback callback) {
    Runnable runnable = () -> {
      final int i  = SOStatusDao.updateSOStatus(sOStatus);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSOStatusUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }


  @Override
  public void deleteAllSOStatus(@NonNull final DeleteAllSOStatusCallback callback) {
    Runnable runnable = () -> {
      final int i = SOStatusDao.deleteAllSOStatus();

      appExecutors.mainThread().execute(() -> {
        if(i >= 0)
          callback.onSOStatussDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSOStatusById(final int sOStatusId, @NonNull final DeleteSOStatusCallback callback) {
    Runnable runnable = () -> {
      final int i = SOStatusDao.deleteSOStatusById(sOStatusId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSOStatusDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllPostedSOStatus(int fpId, @NonNull GetAllPostedSOStatusCallback callback) {
    Runnable runnable = () -> {
      final List<SOStatus> soStatuses = SOStatusDao.getAllPostedSOStatus(fpId);

      appExecutors.mainThread().execute(() -> {
        if(soStatuses != null)
          callback.onSOStatusLoaded(soStatuses);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}

