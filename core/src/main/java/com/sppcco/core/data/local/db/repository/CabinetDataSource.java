package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.CabinetDao;
import com.sppcco.core.data.model.Cabinet;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class CabinetDataSource implements CabinetRepository {

  //private static volatile CabinetDataSource INSTANCE;

  private CabinetDao CabinetDao;
  private AppExecutors appExecutors;

  @Inject
  public CabinetDataSource(AppExecutors appExecutors, CabinetDao CabinetDao) {
    this.CabinetDao = CabinetDao;
    this.appExecutors = appExecutors;
  }

  /*public static CabinetDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                @NonNull CabinetDao CabinetDao) {
    if (INSTANCE == null) {
      synchronized (CabinetDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new CabinetDataSource(appExecutors, CabinetDao);
        }
      }
    }
    return INSTANCE;
  }*/
  
  @Override
  public void getCabinets(@NonNull GetCabinetsCallback callback) {
    Runnable runnable = () -> {
      final List<Cabinet> cabinets = CabinetDao.getAllCabinet();

      appExecutors.mainThread().execute(() -> {
        if(cabinets != null)
          callback.onCabinetsLoaded(cabinets);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCabinet(int cabinetId, @NonNull GetCabinetCallback callback) {
    Runnable runnable = () -> {
      final Cabinet cabinet = CabinetDao.getCabinetById(cabinetId);

      appExecutors.mainThread().execute(() -> {
        if(cabinet != null)
          callback.onCabinetLoaded(cabinet);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertCabinet(Cabinet cabinet, @NonNull InsertCabinetCallback callback) {
    Runnable runnable = () -> {
      final long lCabinet = CabinetDao.insertCabinet(cabinet);

      appExecutors.mainThread().execute(() -> {
        if(lCabinet != 0)
          callback.onCabinetInserted(lCabinet);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertCabinetes(List<Cabinet> cabinets, @NonNull InsertCabinetesCallback callback) {
    Runnable runnable = () -> {
      final Long[] lCabinets = CabinetDao.insertCabinets(cabinets);

      appExecutors.mainThread().execute(() -> {
        if(lCabinets != null)
          callback.onCabinetsInserted(lCabinets);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateCabinet(Cabinet cabinet, @NonNull UpdateCabinetCallback callback) {
    Runnable runnable = () -> {
      final int i  = CabinetDao.updateCabinet(cabinet);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onCabinetUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllCabinet(@NonNull DeleteAllCabinetCallback callback) {
    Runnable runnable = () -> {
      final int i = CabinetDao.deleteAllCabinet();

      appExecutors.mainThread().execute(() -> {
        if(i >= 0)
          callback.onCabinetsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteCabinetById(int cabinetId, @NonNull DeleteCabinetCallback callback) {
    Runnable runnable = () -> {
      final int i = CabinetDao.deleteCabinetById(cabinetId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onCabinetDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getIdFromCabinetByName(String cabinetName, @NonNull GetIdFromCabinetByNameCallback callback) {
    Runnable runnable = () -> {
      final int id = CabinetDao.getIdFromCabinetByName(cabinetName);

      appExecutors.mainThread().execute(() -> {
        if(id != 0)
          callback.onCabinetIdLoaded(id);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCabinetNameFromCabinetById(int cabinetId, @NonNull GetCabinetNameFromCabinetByIdCallback callback) {
    Runnable runnable = () -> {
      final String name = CabinetDao.getCabinetNameFromCabinetById(cabinetId);

      appExecutors.mainThread().execute(() -> {
        if(name != null)
          callback.onCabinetNameLoaded(name);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllCabinetNameFromCabinet(@NonNull GetAllCabinetNameFromCabinetCallback callback) {
    Runnable runnable = () -> {
      final List<String> names = CabinetDao.getAllCabinetNameFromCabinet();

      appExecutors.mainThread().execute(() -> {
        if(names != null)
          callback.onCabinetNamesLoaded(names);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllCabinetNameFromCabinetByStockRoomId(int stockRoomId, @NonNull GetAccountIdFromCabinetByIdCallback callback) {
    Runnable runnable = () -> {
      final List<String> names = CabinetDao.getAllCabinetNameFromCabinetByStockRoomId(stockRoomId);

      appExecutors.mainThread().execute(() -> {
        if(names != null)
          callback.onCabinetNamesStockRoomIdLoaded(names);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCabinetsIdFromStockRoomId(int stockRoomId, @NonNull GetCabinetIdFromStockRoomIdCallback callback) {
    Runnable runnable = () -> {
      final List<Integer> cabinetIds = CabinetDao.getCabinetsIdFromStockRoomId(stockRoomId);

      appExecutors.mainThread().execute(() -> {
        if(cabinetIds != null)
          callback.onCabinetAccountsLoaded(cabinetIds);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllCabinetFromStockRoomId(int stockRoomId, @NonNull GetAllCabinetFromStockRoomIdCallback callback) {
    Runnable runnable = () -> {
      final List<Cabinet> cabinets = CabinetDao.getAllCabinetFromStockRoomId(stockRoomId);

      appExecutors.mainThread().execute(() -> {
        if(cabinets != null)
          callback.onCabinetsLoaded(cabinets);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountCabinet(@NonNull GetCountCabinetCallback callback) {
    Runnable runnable = () -> {
      final int i = CabinetDao.getCountCabinet();

      appExecutors.mainThread().execute(() -> {
        if(i != -1)
          callback.onCabinetCounted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }
}
