package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.DetailAccDao;
import com.sppcco.core.data.model.DetailAcc;
import com.sppcco.core.data.sub_model.DetailAccVectorInfo;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public class DetailAccDataSource implements DetailAccRepository {

  //private static volatile DetailAccDataSource INSTANCE;

  private DetailAccDao detailAccDao;
  private AppExecutors appExecutors;

  @Inject
  public DetailAccDataSource(AppExecutors appExecutors, DetailAccDao detailAccDao) {
    this.detailAccDao = detailAccDao;
    this.appExecutors = appExecutors;
  }

  /*public static DetailAccDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                @NonNull DetailAccDao detailAccDao) {
    if (INSTANCE == null) {
      synchronized (DetailAccDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new DetailAccDataSource(appExecutors, detailAccDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getDetailAccs(@NonNull final GetDetailAccsCallback callback) {
    Runnable runnable = () -> {
      final List<DetailAcc> detailAccs = detailAccDao.getAllDetailAcc();

      appExecutors.mainThread().execute(() -> {
        if (detailAccs != null)
          callback.onDetailAccsLoaded(detailAccs);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getDetailAcc(final int detailAccId, @NonNull final GetDetailAccCallback callback) {
    Runnable runnable = () -> {
      final DetailAcc detailAcc = detailAccDao.getDetailAccById(detailAccId);

      appExecutors.mainThread().execute(() -> {
        if (detailAcc != null)
          callback.onDetailAccLoaded(detailAcc);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertDetailAccs(final List<DetailAcc> detailAccs, @NonNull final InsertDetailAccsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = detailAccDao.insertDetailAccs(detailAccs);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onDetailAccsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertDetailAcc(final DetailAcc detailAcc, @NonNull final InsertDetailAccCallback callback) {
    Runnable runnable = () -> {
      final long lDetailAccId = detailAccDao.insertDetailAcc(detailAcc);

      appExecutors.mainThread().execute(() -> {
        if (lDetailAccId != 0)
          callback.onDetailAccInserted(lDetailAccId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateDetailAccs(@NonNull final UpdateDetailAccsCallback callback, final DetailAcc... detailAccs) {
    Runnable runnable = () -> {
      final int i = detailAccDao.updateDetailAccs(detailAccs);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onDetailAccsUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateDetailAcc(final DetailAcc detailAcc, @NonNull final UpdateDetailAccCallback callback) {
    Runnable runnable = () -> {
      final int i = detailAccDao.updateDetailAcc(detailAcc);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onDetailAccUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteDetailAccs(@NonNull final DeleteDetailAccsCallback callback, final DetailAcc... detailAccs) {
    Runnable runnable = () -> {
      final int i = detailAccDao.deleteDetailAccs(detailAccs);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onDetailAccsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllDetailAcc(@NonNull final DeleteAllDetailAccCallback callback) {
    Runnable runnable = () -> {
      final int i = detailAccDao.deleteAllDetailAcc();

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onDetailAccsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteDetailAccById(final int detailAccId, @NonNull final DeleteDetailAccCallback callback) {
    Runnable runnable = () -> {
      final int i = detailAccDao.deleteDetailAccById(detailAccId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onDetailAccDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getDetailAccNameFromDetailAccId(final int detailAccId, @NonNull final GetDetailAccNameCallback callback) {
    Runnable runnable = () -> {
      final String detailAccName = detailAccDao.getDetailAccNameFromDetailAccId(detailAccId);

      appExecutors.mainThread().execute(() -> {
        if (detailAccName != null)
          callback.onDetailAccNameLoaded(detailAccName);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountDetailAcc(@NonNull GetCountDetailAccCallback callback) {
    Runnable runnable = () -> {
      final int count = detailAccDao.getCountDetailAcc();

      appExecutors.mainThread().execute(() -> {
        if (count != -1)
          callback.onDetailAccCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountDetailAccByFullId(String fullId, @NonNull GetCountDetailAccByFullIdCallback callback) {
    Runnable runnable = () -> {
      final int count = detailAccDao.getCountDetailAccByFullId(fullId);

      appExecutors.mainThread().execute(() -> {
        if (count != -1)
          callback.onDetailAccCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getDetailAccVectorInfoById(int id, @NonNull GetDetailAccVectorInfoByIdCallback callback) {
    Runnable runnable = () -> {
      final DetailAccVectorInfo vectorInfo = detailAccDao.getDetailAccVectorInfoById(id);

      appExecutors.mainThread().execute(() -> {
        if (vectorInfo != null)
          callback.onVectorInfo(vectorInfo);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getDetailCodeById(int detId, @NonNull GetDetailCodeByIdCallback callback) {
    Runnable runnable = () -> {
      final String detailCode = detailAccDao.getDetailAccCodeById(detId);

      appExecutors.mainThread().execute(() -> {
        if (detailCode != null)
          callback.onDetailCodeLoaded(detailCode);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void isFAccInLeafLevel(String fullId, @NonNull GetIsFAccInLeafLevelCallback callback) {
    Runnable runnable = () -> {
      final int countId = detailAccDao.isFAccInLeafLevel(fullId);

      appExecutors.mainThread().execute(() -> {
        if (countId > -1)
          callback.onCountFAccInLeafLevel(countId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getFAccCode(int faccCode, @NonNull GetFAccCodeCallback callback) {
    Runnable runnable = () -> {
      final String code = detailAccDao.getFAccCode(faccCode);

      appExecutors.mainThread().execute(() -> {
        if (code != null)
          callback.onFAccCode(code);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
