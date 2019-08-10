package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.MerchandiseDao;
import com.sppcco.core.data.model.Merchandise;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 *
 */

public class MerchandiseDataSource implements MerchandiseRepository {

  //private static volatile MerchandiseDataSource INSTANCE;

  private MerchandiseDao merchandiseDao;
  private AppExecutors appExecutors;

  @Inject
  public MerchandiseDataSource(AppExecutors appExecutors, MerchandiseDao merchandiseDao) {
    this.merchandiseDao = merchandiseDao;
    this.appExecutors = appExecutors;
  }

  /*public static MerchandiseDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                  @NonNull MerchandiseDao merchandiseDao) {
    if (INSTANCE == null) {
      synchronized (MerchandiseDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new MerchandiseDataSource(appExecutors, merchandiseDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getMerchandises(@NonNull final GetMerchandisesCallback callback) {
    Runnable runnable = () -> {
      final List<Merchandise> merchandises = merchandiseDao.getAllMerchandise();

      appExecutors.mainThread().execute(() -> {
        if (merchandises != null)
          callback.onMerchandisesLoaded(merchandises);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchandise(final int merchandiseId, @NonNull final GetMerchandiseCallback callback) {
    Runnable runnable = () -> {
      final Merchandise merchandise = merchandiseDao.getMerchandiseById(merchandiseId);

      appExecutors.mainThread().execute(() -> {
        if (merchandise != null)
          callback.onMerchandiseLoaded(merchandise);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchandise(String merchandiseName, @NonNull GetMerchandiseCallback callback) {
    Runnable runnable = () -> {
      final Merchandise merchandise = merchandiseDao.getMerchandiseByName(merchandiseName);

      appExecutors.mainThread().execute(() -> {
        if (merchandise != null)
          callback.onMerchandiseLoaded(merchandise);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchandiseFromBraCode(String barcode, @NonNull GetMerchandiseCallback callback) {
    Runnable runnable = () -> {
      final Merchandise merchandise = merchandiseDao.getMerchandiseFromBarcode(barcode);

      appExecutors.mainThread().execute(() -> {
        if (merchandise != null)
          callback.onMerchandiseLoaded(merchandise);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertMerchandises(final List<Merchandise> merchandises, @NonNull final InsertMerchandisesCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = merchandiseDao.insertMerchandises(merchandises);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onMerchandisesInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertMerchandise(final Merchandise merchandise, @NonNull final InsertMerchandiseCallback callback) {
    Runnable runnable = () -> {
      final long lMerchandiseId = merchandiseDao.insertMerchandise(merchandise);

      appExecutors.mainThread().execute(() -> {
        if (lMerchandiseId != 0)
          callback.onMerchandiseInserted(lMerchandiseId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateMerchandises(@NonNull final UpdateMerchandisesCallback callback, final Merchandise... merchandises) {
    Runnable runnable = () -> {
      final int i = merchandiseDao.updateMerchandises(merchandises);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onMerchandisesUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateMerchandise(final Merchandise merchandise, @NonNull final UpdateMerchandiseCallback callback) {
    Runnable runnable = () -> {
      final int i = merchandiseDao.updateMerchandise(merchandise);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onMerchandiseUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteMerchandises(@NonNull final DeleteMerchandisesCallback callback, final Merchandise... merchandises) {
    Runnable runnable = () -> {
      final int i = merchandiseDao.deleteMerchandises(merchandises);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onMerchandisesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllMerchandise(@NonNull final DeleteAllMerchandiseCallback callback) {
    Runnable runnable = () -> {
      final int i = merchandiseDao.deleteAllMerchandise();

      appExecutors.mainThread().execute(() -> {
        if (i >= 0)
          callback.onMerchandisesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteMerchandiseById(final int merchandiseId, @NonNull final DeleteMerchandiseCallback callback) {
    Runnable runnable = () -> {
      final int i = merchandiseDao.deleteMerchandiseById(merchandiseId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onMerchandiseDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllMerchandiseName(@NonNull final GetMerchandiseNamesCallback callback) {
    Runnable runnable = () -> {
      final String[] strings = merchandiseDao.getAllMerchandiseName();

      appExecutors.mainThread().execute(() -> {
        if (strings != null)
          callback.onMerchandiseNamesLoaded(strings);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllMerchandiseCode(@NonNull final GetMerchandiseCodesCallback callback) {
    Runnable runnable = () -> {
      final String[] strings = merchandiseDao.getAllMerchandiseCode();

      appExecutors.mainThread().execute(() -> {
        if (strings != null)
          callback.onMerchandiseCodesLoaded(strings);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchandiseIdFromMerchandiseCode(final String merchandiseCode, @NonNull final GetMerchandiseIdCallback callback) {
    Runnable runnable = () -> {
      final int i = merchandiseDao.getMerchandiseIdFromMerchandiseCode(merchandiseCode);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onMerchandiseIdLoaded(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchandiseCodeFromMerchandiseName(final String merchandiseName, @NonNull final GetMerchandiseCodeCallback callback) {
    Runnable runnable = () -> {
      final String s = merchandiseDao.getMerchandiseCodeFromMerchandiseName(merchandiseName);

      appExecutors.mainThread().execute(() -> {
        if (s != null)
          callback.onMerchandiseCodeLoaded(s);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchandiseNameFromMerchandiseCode(final String merchandiseCode, @NonNull final GetMerchandiseNameCallback callback) {
    Runnable runnable = () -> {
      final String s = merchandiseDao.getMerchandiseNameFromMerchandiseCode(merchandiseCode);

      appExecutors.mainThread().execute(() -> {
        if (s != null)
          callback.onMerchandiseNameLoaded(s);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchandiseUnitIdFromMerchandiseId(final int merchandiseId, @NonNull final GetMerchandiseUnitIdCallback callback) {
    Runnable runnable = () -> {
      final int i = merchandiseDao.getMerchandiseUnitIdFromMerchandiseId(merchandiseId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onMerchandiseUnitIdLoaded(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchandiseRelatedStock(int fpid, int stockId, @NonNull GetMerchandiseRelatedStockCallback callback) {
    Runnable runnable = () -> {
      final List<Merchandise> merchandises = merchandiseDao.getMerchandiseRelatedStock(fpid, stockId);

      appExecutors.mainThread().execute(() -> {
        if (merchandises != null)
          callback.onMerchandisesLoaded(merchandises);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountMerchandise(@NonNull GetCountMerchandiseCallback callback) {
    Runnable runnable = () -> {
      final int count = merchandiseDao.getCountMerchandise();

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onMerchandiseCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }


}