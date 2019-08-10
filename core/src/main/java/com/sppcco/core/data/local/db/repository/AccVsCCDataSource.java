package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.AccVsCCDao;
import com.sppcco.core.data.model.AccVsCC;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 11/02/18.
 */

public class AccVsCCDataSource implements AccVsCCRepository {

  //private static volatile AccVsCCDataSource INSTANCE;

  private AccVsCCDao accVsCCDao;
  private AppExecutors appExecutors;

  @Inject
  public AccVsCCDataSource(AppExecutors appExecutors, AccVsCCDao accVsCCDao) {
    this.accVsCCDao = accVsCCDao;
    this.appExecutors = appExecutors;
  }

  /*public static AccVsCCDataSource getInstance(@NonNull AppExecutors appExecutors,
                                              @NonNull AccVsCCDao accVsCCDao) {
    if (INSTANCE == null) {
      synchronized (AccVsCCDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new AccVsCCDataSource(appExecutors, accVsCCDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getAccVsCCs(@NonNull final GetAccVsCCsCallback callback) {
    Runnable runnable = () -> {
      final List<AccVsCC> accVsCCs = accVsCCDao.getAllAccVsCC();

      appExecutors.mainThread().execute(() -> {
        if(accVsCCs != null)
          callback.onAccVsCCsLoaded(accVsCCs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccVsCCByFullId(final String fullId, @NonNull final GetAccVsCCCallback callback) {
    Runnable runnable = () -> {
      final AccVsCC accVsCC = accVsCCDao.getAccVsCCByFullId(fullId);

      appExecutors.mainThread().execute(() -> {
        if(accVsCC != null)
          callback.onAccVsCCLoaded(accVsCC);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountAccVsCC(@NonNull GetCountAccVsCCCallback callback) {
    Runnable runnable = () -> {
      final int count = accVsCCDao.getCountAccVsCC();

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onAccVsCCCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertAccVsCCs(final List<AccVsCC> accVsCCs, @NonNull final InsertAccVsCCsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = accVsCCDao.insertAccVsCCs(accVsCCs);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onAccVsCCsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertAccVsCC(final AccVsCC accVsCC, @NonNull final InsertAccVsCCCallback callback) {
    Runnable runnable = () -> {
      final long lAccVsCCId  = accVsCCDao.insertAccVsCC(accVsCC);

      appExecutors.mainThread().execute(() -> {
        if(lAccVsCCId != 0)
          callback.onAccVsCCInserted(lAccVsCCId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateAccVsCCs(@NonNull final UpdateAccVsCCsCallback callback, final AccVsCC... accVsCCs) {
    Runnable runnable = () -> {
      final int rowNum = accVsCCDao.updateAccVsCCs(accVsCCs);

      appExecutors.mainThread().execute(() -> {
        if(rowNum != 0)
          callback.onAccVsCCsUpdated(rowNum);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateAccVsCC(final AccVsCC accVsCC, @NonNull final UpdateAccVsCCCallback callback) {
    Runnable runnable = () -> {
      final int i  = accVsCCDao.updateAccVsCC(accVsCC);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onAccVsCCUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAccVsCCs(@NonNull final DeleteAccVsCCsCallback callback, final AccVsCC... accVsCCs) {
    Runnable runnable = () -> {
      final int i = accVsCCDao.deleteAccVsCCs(accVsCCs);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onAccVsCCsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllAccVsCC(@NonNull final DeleteAllAccVsCCCallback callback) {
    Runnable runnable = () -> {
      final int i = accVsCCDao.deleteAllAccVsCC();

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onAccVsCCsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccVsCCRelatedCostCenter(String fullId, @NonNull GetAccVsCCRelatedCostCenterCallback callback) {
    Runnable runnable = () -> {
      final List<AccVectorInfo> accountInfos = accVsCCDao.getAccVsCCRelatedCostCenter(fullId);

      appExecutors.mainThread().execute(() -> {
        if(accountInfos != null)
          callback.onAccVsCCRelated(accountInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

}
