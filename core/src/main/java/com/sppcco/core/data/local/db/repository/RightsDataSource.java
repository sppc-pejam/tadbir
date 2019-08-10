package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.RightsDao;
import com.sppcco.core.data.model.Rights;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class RightsDataSource implements RightsRepository {

  //private static volatile RightsDataSource INSTANCE;

  private RightsDao rightsDao;
  private AppExecutors appExecutors;

  @Inject
  public RightsDataSource(AppExecutors appExecutors, RightsDao rightsDao) {
    this.rightsDao = rightsDao;
    this.appExecutors = appExecutors;
  }

  /*public static RightsDataSource getInstance(@NonNull AppExecutors appExecutors,
                                             @NonNull RightsDao rightsDao) {
    if (INSTANCE == null) {
      synchronized (RightsDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new RightsDataSource(appExecutors, rightsDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getAllRights(@NonNull GetAllRightsCallback callback) {
    Runnable runnable = () -> {
      final List<Rights> allRights = rightsDao.getAllRights();

      appExecutors.mainThread().execute(() -> {
        if (allRights != null)
          callback.onAllRightsLoaded(allRights);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertRights(List<Rights> Rights, @NonNull InsertRightsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = rightsDao.insertRights(Rights);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onRightsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateRights(@NonNull UpdateRightsCallback callback, Rights... Rights) {
    Runnable runnable = () -> {
      final int rowNum = rightsDao.updateRights(Rights);

      appExecutors.mainThread().execute(() -> {
        if (rowNum != 0)
          callback.onRightsUpdated(rowNum);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllRight(@NonNull DeleteAllRightsCallback callback) {
    Runnable runnable = () -> {
      final int i = rightsDao.deleteAllRights();

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onRightsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccessRight(int SubSystem, int FormId, @NonNull GetAccessRightCallback callback) {
    Runnable runnable = () -> {
      final String accessRight = rightsDao.getAccessRight(SubSystem, FormId);
      appExecutors.mainThread().execute(() -> {
        if (accessRight != null)
          callback.onAccessRightLoaded(accessRight);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountRights(@NonNull GetCountRightCallback callback) {
    Runnable runnable = () -> {
      final int count = rightsDao.getCountRights();

      appExecutors.mainThread().execute(() -> {
        if (count != -1)
          callback.onRightsCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);

  }
}
