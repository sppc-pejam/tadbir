package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.AccVsPrjDao;
import com.sppcco.core.data.model.AccVsPrj;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 11/02/18.
 */

public class AccVsPrjDataSource implements AccVsPrjRepository {

  //private static volatile AccVsPrjDataSource INSTANCE;

  private AccVsPrjDao accVsPrjDao;
  private AppExecutors appExecutors;

  @Inject
  public AccVsPrjDataSource(AppExecutors appExecutors, AccVsPrjDao accVsPrjDao) {
    this.accVsPrjDao = accVsPrjDao;
    this.appExecutors = appExecutors;
  }

  /*public static AccVsPrjDataSource getInstance(@NonNull AppExecutors appExecutors,
                                               @NonNull AccVsPrjDao accVsPrjDao) {
    if (INSTANCE == null) {
      synchronized (AccVsPrjDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new AccVsPrjDataSource(appExecutors, accVsPrjDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getAccVsPrjs(@NonNull final GetAccVsPrjsCallback callback) {
    Runnable runnable = () -> {
      final List<AccVsPrj> accVsPrjs = accVsPrjDao.getAllAccVsPrj();

      appExecutors.mainThread().execute(() -> {
        if(accVsPrjs != null)
          callback.onAccVsPrjsLoaded(accVsPrjs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccVsPrjByFullId(final String fullId, @NonNull final GetAccVsPrjCallback callback) {
    Runnable runnable = () -> {
      final AccVsPrj accVsPrj = accVsPrjDao.getAccVsPrjByFullId(fullId);

      appExecutors.mainThread().execute(() -> {
        if(accVsPrj != null)
          callback.onAccVsPrjLoaded(accVsPrj);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountAccVsPrj(@NonNull GetCountAccVsPrjCallback callback) {
    Runnable runnable = () -> {
      final int count = accVsPrjDao.getCountAccVsPrj();

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onAccVsPrjCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertAccVsPrjs(final List<AccVsPrj> accVsPrjs, @NonNull final InsertAccVsPrjsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = accVsPrjDao.insertAccVsPrjs(accVsPrjs);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onAccVsPrjsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertAccVsPrj(final AccVsPrj accVsPrj, @NonNull final InsertAccVsPrjCallback callback) {
    Runnable runnable = () -> {
      final long lAccVsPrjId  = accVsPrjDao.insertAccVsPrj(accVsPrj);

      appExecutors.mainThread().execute(() -> {
        if(lAccVsPrjId != 0)
          callback.onAccVsPrjInserted(lAccVsPrjId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateAccVsPrjs(@NonNull final UpdateAccVsPrjsCallback callback, final AccVsPrj... accVsPrjs) {
    Runnable runnable = () -> {
      final int rowNum = accVsPrjDao.updateAccVsPrjs(accVsPrjs);

      appExecutors.mainThread().execute(() -> {
        if(rowNum != 0)
          callback.onAccVsPrjsUpdated(rowNum);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateAccVsPrj(final AccVsPrj accVsPrj, @NonNull final UpdateAccVsPrjCallback callback) {
    Runnable runnable = () -> {
      final int i  = accVsPrjDao.updateAccVsPrj(accVsPrj);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onAccVsPrjUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAccVsPrjs(@NonNull final DeleteAccVsPrjsCallback callback, final AccVsPrj... accVsPrjs) {
    Runnable runnable = () -> {
      final int i = accVsPrjDao.deleteAccVsPrjs(accVsPrjs);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onAccVsPrjsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllAccVsPrj(@NonNull final DeleteAllAccVsPrjCallback callback) {
    Runnable runnable = () -> {
      final int i = accVsPrjDao.deleteAllAccVsPrj();

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onAccVsPrjsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccVsPrjRelatedProject(String fullId, @NonNull GetAccVsPrjRelatedProjectCallback callback) {
    Runnable runnable = () -> {
      final List<AccVectorInfo> accountInfos = accVsPrjDao.getAccVsPrjRelatedProject(fullId);

      appExecutors.mainThread().execute(() -> {
        if(accountInfos != null)
          callback.onAccVsPrjRelated(accountInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

}
