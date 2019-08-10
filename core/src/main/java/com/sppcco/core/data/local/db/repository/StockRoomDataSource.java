package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.StockRoomDao;
import com.sppcco.core.data.model.StockRoom;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class StockRoomDataSource implements StockRoomRepository {

  //private static volatile StockRoomDataSource INSTANCE;

  private StockRoomDao StockRoomDao;
  private AppExecutors appExecutors;

  @Inject
  public StockRoomDataSource(AppExecutors appExecutors, StockRoomDao StockRoomDao) {
    this.StockRoomDao = StockRoomDao;
    this.appExecutors = appExecutors;
  }

  /*public static StockRoomDataSource getInstance(@NonNull AppExecutors appExecutors,
                                               @NonNull StockRoomDao StockRoomDao) {
    if (INSTANCE == null) {
      synchronized (StockRoomDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new StockRoomDataSource(appExecutors, StockRoomDao);
        }
      }
    }
    return INSTANCE;
  }*/
  
  @Override
  public void getStockRooms(@NonNull GetStockRoomsCallback callback) {
    Runnable runnable = () -> {
      final List<StockRoom> stockRooms = StockRoomDao.getAllStockRoom();

      appExecutors.mainThread().execute(() -> {
        if(stockRooms != null)
          callback.onStockRoomsLoaded(stockRooms);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getStockRoom(int stockRoomId, @NonNull GetStockRoomCallback callback) {
    Runnable runnable = () -> {
      final StockRoom stockRoom = StockRoomDao.getStockRoomById(stockRoomId);

      appExecutors.mainThread().execute(() -> {
        if(stockRoom != null)
          callback.onStockRoomLoaded(stockRoom);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertStockRoom(StockRoom stockRoom, @NonNull InsertStockRoomCallback callback) {
    Runnable runnable = () -> {
      final long lStockRoom = StockRoomDao.insertStockRoom(stockRoom);

      appExecutors.mainThread().execute(() -> {
        if(lStockRoom != 0)
          callback.onStockRoomInserted(lStockRoom);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertStockRoomes(List<StockRoom> stockRooms, @NonNull InsertStockRoomesCallback callback) {
    Runnable runnable = () -> {
      final Long[] lStockRooms = StockRoomDao.insertStockRooms(stockRooms);

      appExecutors.mainThread().execute(() -> {
        if(lStockRooms != null)
          callback.onStockRoomesInserted(lStockRooms);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateStockRoom(StockRoom stockRoom, @NonNull UpdateStockRoomCallback callback) {
    Runnable runnable = () -> {
      final int i  = StockRoomDao.updateStockRoom(stockRoom);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onStockRoomUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllStockRoom(@NonNull DeleteAllStockRoomCallback callback) {
    Runnable runnable = () -> {
      final int i = StockRoomDao.deleteAllStockRoom();

      appExecutors.mainThread().execute(() -> {
        if(i >= 0)
          callback.onStockRoomsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteStockRoomById(int stockRoomId, @NonNull DeleteStockRoomCallback callback) {
    Runnable runnable = () -> {
      final int i = StockRoomDao.deleteStockRoomById(stockRoomId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onStockRoomDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getStockIdFromStockRoomByName(String sockName, @NonNull GetStockIdFromStockRoomByNameCallback callback) {
    Runnable runnable = () -> {
      final int i = StockRoomDao.getStockIdFromStockRoomByName(sockName);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onStockRoomIdLoaded(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getStockNameFromStockRoomById(int stockRoomId, @NonNull GetStockNameFromStockRoomByIdCallback callback) {
    Runnable runnable = () -> {
      final String name = StockRoomDao.getStockNameFromStockRoomById(stockRoomId);

      appExecutors.mainThread().execute(() -> {
        if(name != null)
          callback.onStockRoomNameLoaded(name);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllStockNameFromStockRoom(@NonNull GetAllStockNameFromStockRoomCallback callback) {
    Runnable runnable = () -> {
      final List<String> names = StockRoomDao.getAllStockNameFromStockRoom();

      appExecutors.mainThread().execute(() -> {
        if(names != null)
          callback.onStockRoomNamesLoaded(names);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccountIdFromStockRoomById(int stockRoomId, @NonNull GetAccountIdFromStockRoomByIdCallback callback) {
    Runnable runnable = () -> {
      final String id = StockRoomDao.getAccountIdFromStockRoomById(stockRoomId);

      appExecutors.mainThread().execute(() -> {
        if(id != null)
          callback.onStockRoomAccountIdLoaded(id);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllAccountIdFromStockRoom(@NonNull GetAllAccountIdFromStockRoomCallback callback) {
    Runnable runnable = () -> {
      final List<String> ids = StockRoomDao.getAllStockNameFromStockRoom();

      appExecutors.mainThread().execute(() -> {
        if(ids != null)
          callback.onStockRoomAccountsLoaded(ids);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountStockRoom(@NonNull GetCountStockRoomCallback callback) {
    Runnable runnable = () -> {
      final int count = StockRoomDao.getCountStockRoom();

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onStockRoomCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }
}
