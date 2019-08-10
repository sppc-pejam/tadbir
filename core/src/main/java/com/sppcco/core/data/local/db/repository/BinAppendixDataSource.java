package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.BinAppendixDao;
import com.sppcco.core.data.model.BinAppendix;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class BinAppendixDataSource implements BinAppendixRepository{

  //private static volatile BinAppendixDataSource INSTANCE;

  private BinAppendixDao binAppendixDao;
  private AppExecutors appExecutors;

  @Inject
  public BinAppendixDataSource(AppExecutors appExecutors, BinAppendixDao binAppendixDao) {
    this.binAppendixDao = binAppendixDao;
    this.appExecutors = appExecutors;
  }

  /*public static BinAppendixDataSource getInstance(@NonNull AppExecutors appExecutors,
                                               @NonNull BinAppendixDao binAppendixDao) {
    if (INSTANCE == null) {
      synchronized (CustomerDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new BinAppendixDataSource(appExecutors, binAppendixDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getBinAppendices(@NonNull GetBinAppendicesCallback callback) {
    Runnable runnable = () -> {
      final List<BinAppendix> binAppendices = binAppendixDao.getAllBinAppendix();

      appExecutors.mainThread().execute(() -> {
        if(binAppendices != null)
          callback.onBinAppendicesLoaded(binAppendices);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getBinAppendixByMerchId(int merchId, @NonNull GetBinAppendixByMerchIdCallback callback) {
    Runnable runnable = () -> {
      final BinAppendix binAppendix = binAppendixDao.getBinAppendixByMerchId(merchId);

      appExecutors.mainThread().execute(() -> {
        if(binAppendix != null)
          callback.onBinAppendixLoaded(binAppendix);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertBinAppendices(List<BinAppendix> binAppendices, @NonNull InsertBinAppendicesCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = binAppendixDao.insertBinAppendices(binAppendices);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onBinAppendicesInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertBinAppendix(BinAppendix binAppendix, @NonNull InsertBinAppendixCallback callback) {
    Runnable runnable = () -> {
      final long lBinAppendixId  = binAppendixDao.insertBinAppendix(binAppendix);

      appExecutors.mainThread().execute(() -> {
        if(lBinAppendixId != 0)
          callback.onBinAppendixInserted(lBinAppendixId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateBinAppendices(@NonNull UpdateBinAppendicesCallback callback, BinAppendix... binAppendices) {
    Runnable runnable = () -> {
      final int i = binAppendixDao.updateBinAppendices(binAppendices);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onBinAppendicesUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateBinAppendix(BinAppendix binAppendix, @NonNull UpdateBinAppendixCallback callback) {
    Runnable runnable = () -> {
      final int i  = binAppendixDao.updateBinAppendix(binAppendix);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onBinAppendixUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteBinAppendices(@NonNull DeleteBinAppendicesCallback callback, BinAppendix... binAppendices) {
    Runnable runnable = () -> {
      final int i = binAppendixDao.deleteBinAppendices(binAppendices);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onBinAppendicesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllBinAppendix(@NonNull DeleteAllBinAppendixCallback callback) {
    Runnable runnable = () -> {
      final int i = binAppendixDao.deleteAllBinAppendix();

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onBinAppendicesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteBinAppendixByMerchId(int merchId, @NonNull DeleteBinAppendixByMerchIdCallback callback) {
    Runnable runnable = () -> {
      final int i = binAppendixDao.deleteBinAppendixByMerchId(merchId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onBinAppendixDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountBinAppendix(@NonNull GetCountBinAppendixCallback callback) {
    Runnable runnable = () -> {
      final int count = binAppendixDao.getCountBinAppendix();

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onGetBinAppendixCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }
}
