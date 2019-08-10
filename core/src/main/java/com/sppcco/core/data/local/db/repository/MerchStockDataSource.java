package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.MerchStockDao;
import com.sppcco.core.data.model.MerchStock;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class MerchStockDataSource  implements MerchStockRepository {

  //private static volatile MerchStockDataSource INSTANCE;

  private MerchStockDao merchStockDao;
  private AppExecutors appExecutors;

  @Inject
  public MerchStockDataSource(AppExecutors appExecutors, MerchStockDao merchStockDao) {
    this.merchStockDao = merchStockDao;
    this.appExecutors = appExecutors;
  }

  /*public static MerchStockDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                  @NonNull MerchStockDao merchStockDao) {
    if (INSTANCE == null) {
      synchronized (MerchStockDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new MerchStockDataSource(appExecutors, merchStockDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getAllMerchStock(@NonNull GetMerchStocksCallback callback) {
    Runnable runnable = () -> {
      final List<MerchStock> merchStocks = merchStockDao.getAllMerchStock();

      appExecutors.mainThread().execute(() -> {
        if (merchStocks != null)
          callback.onMerchStocksLoaded(merchStocks);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchStockByMerchId(int merchId, @NonNull GetMerchStockByIdCallback callback) {
    Runnable runnable = () -> {
      final List<MerchStock> merchStocks = merchStockDao.getMerchStockByMerchId(merchId);

      appExecutors.mainThread().execute(() -> {
        if (merchStocks != null)
          callback.onMerchStockLoaded(merchStocks);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchStockByStockId(int stockId, @NonNull GetMerchStockByIdCallback callback) {
    Runnable runnable = () -> {
      final List<MerchStock> merchStocks = merchStockDao.getMerchStockByMerchId(stockId);

      appExecutors.mainThread().execute(() -> {
        if (merchStocks != null)
          callback.onMerchStockLoaded(merchStocks);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertMerchStocks(final List<MerchStock> merchStocks, @NonNull InsertMerchStocksCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = merchStockDao.insertMerchStocks(merchStocks);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onMerchStocksInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertMerchStock(final MerchStock merchStock, @NonNull InsertMerchStockCallback callback) {
    Runnable runnable = () -> {
      final long longs = merchStockDao.insertMerchStock(merchStock);

      appExecutors.mainThread().execute(() -> {
        if (longs != 0)
          callback.onMerchStockInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateMerchStocks(@NonNull UpdateMerchStocksCallback callback, MerchStock... merchStocks) {
    Runnable runnable = () -> {
      final int i = merchStockDao.updateMerchStocks(merchStocks);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onMerchStocksUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateMerchStock(MerchStock merchStock, @NonNull UpdateMerchStockCallback callback) {
    Runnable runnable = () -> {
      final int i = merchStockDao.updateMerchStock(merchStock);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onMerchStockUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteMerchStocks(@NonNull DeleteMerchStocksCallback callback, MerchStock... merchStocks) {
    Runnable runnable = () -> {
      final int i = merchStockDao.deleteMerchStocks(merchStocks);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onMerchStocksDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllMerchStock(@NonNull DeleteAllMerchStockCallback callback) {
    Runnable runnable = () -> {
      final int i = merchStockDao.deleteAllMerchStock();

      appExecutors.mainThread().execute(() -> {
        if (i >= 0)
          callback.onMerchStocksDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountMerchStock(@NonNull GetCountMerchStockCallback callback) {
    Runnable runnable = () -> {
      final int count = merchStockDao.getCountMerchStock();

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onMerchStockCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }
}
