package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.AccVsDetailDao;
import com.sppcco.core.data.model.AccVsDetail;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 11/02/18.
 */

public class AccVsDetailDataSource implements AccVsDetailRepository {

  //private static volatile AccVsDetailDataSource INSTANCE;

  private AccVsDetailDao accVsDetailDao;
  private AppExecutors appExecutors;

  @Inject
  public AccVsDetailDataSource(AppExecutors appExecutors, AccVsDetailDao accVsDetailDao) {
    this.accVsDetailDao = accVsDetailDao;
    this.appExecutors = appExecutors;
  }

  /*public static AccVsDetailDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                  @NonNull AccVsDetailDao accVsDetailDao) {
    if (INSTANCE == null) {
      synchronized (AccVsDetailDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new AccVsDetailDataSource(appExecutors, accVsDetailDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getAccVsDetails(@NonNull final GetAccVsDetailsCallback callback) {
    Runnable runnable = () -> {
      final List<AccVsDetail> accVsDetails = accVsDetailDao.getAllAccVsDetail();

      appExecutors.mainThread().execute(() -> {
        if(accVsDetails != null)
          callback.onAccVsDetailsLoaded(accVsDetails);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccVsDetailByFullId(final String fullId, @NonNull final GetAccVsDetailCallback callback) {
    Runnable runnable = () -> {
      final AccVsDetail accVsDetail = accVsDetailDao.getAccVsDetailByFullId(fullId);

      appExecutors.mainThread().execute(() -> {
        if(accVsDetail != null)
          callback.onAccVsDetailLoaded(accVsDetail);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountAccVsDetail(@NonNull GetCountAccVsDetailCallback callback) {
    Runnable runnable = () -> {
      final int count = accVsDetailDao.getCountAccVsDetail();

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onAccVsDetailCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertAccVsDetails(final List<AccVsDetail> accVsDetails, @NonNull final InsertAccVsDetailsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = accVsDetailDao.insertAccVsDetails(accVsDetails);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onAccVsDetailsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertAccVsDetail(final AccVsDetail accVsDetail, @NonNull final InsertAccVsDetailCallback callback) {
    Runnable runnable = () -> {
      final long lAccVsDetailId  = accVsDetailDao.insertAccVsDetail(accVsDetail);

      appExecutors.mainThread().execute(() -> {
        if(lAccVsDetailId != 0)
          callback.onAccVsDetailInserted(lAccVsDetailId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateAccVsDetails(@NonNull final UpdateAccVsDetailsCallback callback, final AccVsDetail... accVsDetails) {
    Runnable runnable = () -> {
      final int rowNum = accVsDetailDao.updateAccVsDetails(accVsDetails);

      appExecutors.mainThread().execute(() -> {
        if(rowNum != 0)
          callback.onAccVsDetailsUpdated(rowNum);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateAccVsDetail(final AccVsDetail accVsDetail, @NonNull final UpdateAccVsDetailCallback callback) {
    Runnable runnable = () -> {
      final int i  = accVsDetailDao.updateAccVsDetail(accVsDetail);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onAccVsDetailUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAccVsDetails(@NonNull final DeleteAccVsDetailsCallback callback, final AccVsDetail... accVsDetails) {
    Runnable runnable = () -> {
      final int i = accVsDetailDao.deleteAccVsDetails(accVsDetails);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onAccVsDetailsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllAccVsDetail(@NonNull final DeleteAllAccVsDetailCallback callback) {
    Runnable runnable = () -> {
      final int i = accVsDetailDao.deleteAllAccVsDetail();

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onAccVsDetailsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccVsDetailRelatedDetailAcc(String fullId, @NonNull GetAccVsDetailRelatedDetailAccCallback callback) {
    Runnable runnable = () -> {
      final List<AccVectorInfo> accountInfos = accVsDetailDao.getAccVsDetailRelatedDetailAcc(fullId);

      appExecutors.mainThread().execute(() -> {
        if(accountInfos != null)
          callback.onAccVsDetailRelated(accountInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

}
