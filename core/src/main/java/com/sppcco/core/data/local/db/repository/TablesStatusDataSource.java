package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.TablesStatusDao;
import com.sppcco.core.data.model.TablesStatus;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/24/18.
 */

public class TablesStatusDataSource implements TablesStatusRepository {

  //private static volatile TablesStatusDataSource INSTANCE;

  private TablesStatusDao tablesStatusDao;
  private AppExecutors appExecutors;

  @Inject
  public TablesStatusDataSource(AppExecutors appExecutors, TablesStatusDao tablesStatusDao) {
    this.tablesStatusDao = tablesStatusDao;
    this.appExecutors = appExecutors;
  }

  /*public static TablesStatusDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull TablesStatusDao tablesStatusDao) {
    if (INSTANCE == null) {
      synchronized (TablesStatusDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new TablesStatusDataSource(appExecutors, tablesStatusDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getTablesStatuss(@NonNull final GetTablesStatussCallback callback) {
    Runnable runnable = () -> {
      final List<TablesStatus> tablesStatuses = tablesStatusDao.getAllTablesStatus();

      appExecutors.mainThread().execute(() -> {
        if (tablesStatuses != null)
          callback.onTablesStatussLoaded(tablesStatuses);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getTablesStatus(final int tablesStatusId, @NonNull final GetTablesStatusCallback callback) {
    Runnable runnable = () -> {
      final TablesStatus tablesStatus = tablesStatusDao.getTablesStatusById(tablesStatusId);

      appExecutors.mainThread().execute(() -> {
        if (tablesStatus != null)
          callback.onTablesStatusLoaded(tablesStatus);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertTablesStatuss(final List<TablesStatus> tablesStatuss, @NonNull final InsertTablesStatussCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = tablesStatusDao.insertTablesStatuss(tablesStatuss);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onTablesStatussInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertTablesStatus(final TablesStatus tablesStatus, @NonNull final InsertTablesStatusCallback callback) {
    Runnable runnable = () -> {
      final long lTablesStatusId = tablesStatusDao.insertTablesStatus(tablesStatus);

      appExecutors.mainThread().execute(() -> {
        if (lTablesStatusId != 0)
          callback.onTablesStatusInserted(lTablesStatusId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateTablesStatuss(@NonNull final UpdateTablesStatussCallback callback, final TablesStatus... tablesStatuss) {
    Runnable runnable = () -> {
      final int rowNum = tablesStatusDao.updateTablesStatuss(tablesStatuss);

      appExecutors.mainThread().execute(() -> {
        if (rowNum != 0)
          callback.onTablesStatussUpdated(rowNum);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateTablesStatus(final TablesStatus tablesStatus, @NonNull final UpdateTablesStatusCallback callback) {
    Runnable runnable = () -> {
      final int i = tablesStatusDao.updateTablesStatus(tablesStatus);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onTablesStatusUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteTablesStatuss(@NonNull final DeleteTablesStatussCallback callback, final TablesStatus... tablesStatuss) {
    Runnable runnable = () -> {
      final int i = tablesStatusDao.deleteTablesStatuss(tablesStatuss);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onTablesStatussDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllTablesStatus(@NonNull final DeleteAllTablesStatusCallback callback) {
    Runnable runnable = () -> {
      final int i = tablesStatusDao.deleteAllTablesStatus();

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onTablesStatussDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteTablesStatusById(final int tablesStatusId, @NonNull final DeleteTablesStatusCallback callback) {
    Runnable runnable = () -> {
      final int i = tablesStatusDao.deleteTablesStatusById(tablesStatusId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onTablesStatusDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateLastSyncTablesStatus(String tableName,
                                         String lastUpdate,
                                         int FPId,
                                         int lastUpdateRowCount,
                                         @NonNull UpdateLastSyncTableStatusCallback callback) {
    Runnable runnable = () -> {
      final int i = tablesStatusDao.updateLastSyncTableStatus(tableName, lastUpdate, FPId, lastUpdateRowCount);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onTablesStatusSync(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);

  }

  @Override
  public void getCountUnsendData(@NonNull GetCountUnsendDataCallback callback) {
    Runnable runnable = () -> {
      final int i = tablesStatusDao.getCountUnsendData();

      appExecutors.mainThread().execute(() -> {
        if (i >= 0)
          callback.onCountUnsendData(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
