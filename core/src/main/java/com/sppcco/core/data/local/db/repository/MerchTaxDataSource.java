package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.MerchTaxDao;
import com.sppcco.core.data.model.MerchTax;
import com.sppcco.core.util.app.AppExecutors;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class MerchTaxDataSource implements MerchTaxRepository {

  //private static volatile MerchTaxDataSource INSTANCE;

  private MerchTaxDao MerchTaxDao;
  private AppExecutors appExecutors;

  @Inject
  public MerchTaxDataSource(AppExecutors appExecutors, MerchTaxDao MerchTaxDao) {
    this.MerchTaxDao = MerchTaxDao;
    this.appExecutors = appExecutors;
  }

  /*public static MerchTaxDataSource getInstance(@NonNull AppExecutors appExecutors,
                                               @NonNull MerchTaxDao MerchTaxDao) {
    if (INSTANCE == null) {
      synchronized (MerchTaxDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new MerchTaxDataSource(appExecutors, MerchTaxDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getAllMerchTax(@NonNull GetMerchTaxsCallback callback) {
    Runnable runnable = () -> {
      final List<MerchTax> MerchTaxs = MerchTaxDao.getAllMerchTax();

      appExecutors.mainThread().execute(() -> {
        if (MerchTaxs != null)
          callback.onMerchTaxsLoaded(MerchTaxs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchTaxByMerchId(int merchId, @NonNull GetMerchTaxCallback callback) {
    Runnable runnable = () -> {
      final MerchTax MerchTaxs = MerchTaxDao.getMerchTaxByMerchId(merchId);

      appExecutors.mainThread().execute(() -> {
        if (MerchTaxs != null)
          callback.onMerchTaxLoaded(MerchTaxs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchTaxByMerchIdAndSPDate(int merchId, Date spDate, @NonNull GetMerchTaxCallback callback) {
    Runnable runnable = () -> {
      final MerchTax MerchTaxs = MerchTaxDao.getMerchTaxByMerchIdAndSPDate(merchId, spDate);

      appExecutors.mainThread().execute(() -> {
        if (MerchTaxs != null)
          callback.onMerchTaxLoaded(MerchTaxs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }


  @Override
  public void insertMerchTaxs(final List<MerchTax> MerchTaxs, @NonNull InsertMerchTaxsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = MerchTaxDao.insertMerchTaxs(MerchTaxs);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onMerchTaxsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertMerchTax(final MerchTax MerchTax, @NonNull InsertMerchTaxCallback callback) {
    Runnable runnable = () -> {
      final long longs = MerchTaxDao.insertMerchTax(MerchTax);

      appExecutors.mainThread().execute(() -> {
        if (longs != 0)
          callback.onMerchTaxInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateMerchTaxs(@NonNull UpdateMerchTaxsCallback callback, MerchTax... MerchTaxs) {
    Runnable runnable = () -> {
      final int i = MerchTaxDao.updateMerchTaxs(MerchTaxs);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onMerchTaxsUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateMerchTax(MerchTax MerchTax, @NonNull UpdateMerchTaxCallback callback) {
    Runnable runnable = () -> {
      final int i = MerchTaxDao.updateMerchTax(MerchTax);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onMerchTaxUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteMerchTaxs(@NonNull DeleteMerchTaxsCallback callback, MerchTax... MerchTaxs) {
    Runnable runnable = () -> {
      final int i = MerchTaxDao.deleteMerchTaxs(MerchTaxs);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onMerchTaxsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllMerchTax(@NonNull DeleteAllMerchTaxCallback callback) {
    Runnable runnable = () -> {
      final int i = MerchTaxDao.deleteAllMerchTax();

      appExecutors.mainThread().execute(() -> {
        if (i >= 0)
          callback.onMerchTaxsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountMerchTax(@NonNull GetCountMerchTaxCallback callback) {
    Runnable runnable = () -> {
      final int count = MerchTaxDao.getCountMerchTax();

      appExecutors.mainThread().execute(() -> {
          callback.onMerchTaxCounted(count);
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountMerchTaxByMerchIdAndSPDate(int merchId, Date spDate, @NonNull GetCountMerchTaxCallback callback) {
    Runnable runnable = () -> {
      final int count = MerchTaxDao.getCountMerchTaxByMerchIdAndSPDate(merchId, spDate);

      appExecutors.mainThread().execute(() -> {
        callback.onMerchTaxCounted(count);
      });
    };
    appExecutors.diskIO().execute(runnable);
  }
}
