package com.sppcco.core.data.local.db.repository;


import com.sppcco.core.data.local.db.dao.UnitDao;
import com.sppcco.core.data.model.Unit;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;


public class UnitDataSource implements UnitRepository {

  //private static volatile UnitDataSource INSTANCE;

  private UnitDao unitDao;
  private AppExecutors appExecutors;

  @Inject
  public UnitDataSource(AppExecutors appExecutors, UnitDao unitDao) {
    this.unitDao = unitDao;
    this.appExecutors = appExecutors;
  }

  /*public static UnitDataSource getInstance(@NonNull AppExecutors appExecutors,
                                           @NonNull UnitDao unitDao) {
    if (INSTANCE == null) {
      synchronized (UnitDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new UnitDataSource(appExecutors, unitDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getUnits(@NonNull final GetUnitsCallback callback) {
    Runnable runnable = () -> {
      final List<Unit> units = unitDao.getAllUnit();

      appExecutors.mainThread().execute(() -> {
        if (units != null)
          callback.onUnitsLoaded(units);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getUnit(final int unitId, @NonNull final GetUnitCallback callback) {
    Runnable runnable = () -> {
      final Unit unit = unitDao.getUnitById(unitId);

      appExecutors.mainThread().execute(() -> {
        if (unit != null)
          callback.onUnitLoaded(unit);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertUnits(final List<Unit> units, @NonNull final InsertUnitsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = unitDao.insertUnits(units);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onUnitsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertUnit(final Unit unit, @NonNull final InsertUnitCallback callback) {
    Runnable runnable = () -> {
      final long lUnitId = unitDao.insertUnit(unit);

      appExecutors.mainThread().execute(() -> {
        if (lUnitId != 0)
          callback.onUnitInserted(lUnitId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateUnits(@NonNull final UpdateUnitsCallback callback, final Unit... units) {
    Runnable runnable = () -> {
      final int i = unitDao.updateUnits(units);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onUnitsUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateUnit(final Unit unit, @NonNull final UpdateUnitCallback callback) {
    Runnable runnable = () -> {
      final int i = unitDao.updateUnit(unit);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onUnitUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteUnits(@NonNull final DeleteUnitsCallback callback, final Unit... units) {
    Runnable runnable = () -> {
      final int i = unitDao.deleteUnits(units);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onUnitsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllUnit(@NonNull final DeleteAllUnitCallback callback) {
    Runnable runnable = () -> {
      final int i = unitDao.deleteAllUnit();

      appExecutors.mainThread().execute(() -> {
        if (i >= 0)
          callback.onUnitsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteUnitById(final int unitId, @NonNull final DeleteUnitCallback callback) {
    Runnable runnable = () -> {
      final int i = unitDao.deleteUnitById(unitId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onUnitDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getUnitIds(@NonNull final GetUnitIdsCallback callback) {
    Runnable runnable = () -> {
      final int[] ints = unitDao.getAllUnitId();

      appExecutors.mainThread().execute(() -> {
        if (ints != null)
          callback.onUnitIdsLoaded(ints);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllUnitName(@NonNull final GetUnitNamesCallback callback) {
    Runnable runnable = () -> {
      final String[] strings = unitDao.getAllUnitName();

      appExecutors.mainThread().execute(() -> {
        if (strings != null)
          callback.onUnitNamesLoaded(strings);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getUnitIdFromUnitName(final String unitName, @NonNull final GetUnitIdCallback callback) {
    Runnable runnable = () -> {
      final int i = unitDao.getUnitIdFromUnitName(unitName);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onUnitIdLoaded(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getUnitNameFromUnitId(final int unitId, @NonNull final GetUnitNameCallback callback) {
    Runnable runnable = () -> {
      final String string = unitDao.getUnitNameFromUnitId(unitId);

      appExecutors.mainThread().execute(() -> {
        if (string != null)
          callback.onUnitNameLoaded(string);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountUnit(@NonNull GetCountUnitCallback callback) {
    Runnable runnable = () -> {
      final int count = unitDao.getCountUnit();

      appExecutors.mainThread().execute(() -> {
        if (count != -1)
          callback.onUnitCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
