package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.ApiServiceInfoDao;
import com.sppcco.core.data.model.ApiServiceInfo;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public class ApiServiceInfoDataSource implements ApiServiceInfoRepository {

  //private static volatile ApiServiceInfoDataSource INSTANCE;

  private ApiServiceInfoDao apiServiceInfoDao;
  private AppExecutors appExecutors;

  @Inject
  public ApiServiceInfoDataSource(AppExecutors appExecutors, ApiServiceInfoDao apiServiceInfoDao) {
    this.apiServiceInfoDao = apiServiceInfoDao;
    this.appExecutors = appExecutors;
  }

  /*public static ApiServiceInfoDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                     @NonNull ApiServiceInfoDao apiServiceInfoDao) {
    if (INSTANCE == null) {
      synchronized (ApiServiceInfoDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new ApiServiceInfoDataSource(appExecutors, apiServiceInfoDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getApiServiceInfos(@NonNull final GetApiServiceInfosCallback callback) {
    Runnable runnable = () -> {
      final List<ApiServiceInfo> apiServiceInfos = apiServiceInfoDao.getAllApiServiceInfo();

      appExecutors.mainThread().execute(() -> {
        if (apiServiceInfos != null)
          callback.onApiServiceInfosLoaded(apiServiceInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getApiServiceInfo(final int apiServiceInfoId, @NonNull final GetApiServiceInfoCallback callback) {
    Runnable runnable = () -> {
      final ApiServiceInfo apiServiceInfo = apiServiceInfoDao.getApiServiceInfoById(apiServiceInfoId);

      appExecutors.mainThread().execute(() -> {
        if (apiServiceInfo != null)
          callback.onApiServiceInfoLoaded(apiServiceInfo);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertApiServiceInfos(final List<ApiServiceInfo> apiServiceInfos, @NonNull final InsertApiServiceInfosCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = apiServiceInfoDao.insertApiServiceInfos(apiServiceInfos);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onApiServiceInfosInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertApiServiceInfo(final ApiServiceInfo apiServiceInfo, @NonNull final InsertApiServiceInfoCallback callback) {
    Runnable runnable = () -> {
      final long lApiServiceInfoId = apiServiceInfoDao.insertApiServiceInfo(apiServiceInfo);

      appExecutors.mainThread().execute(() -> {
        if (lApiServiceInfoId != 0)
          callback.onApiServiceInfoInserted(lApiServiceInfoId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateApiServiceInfos(@NonNull final UpdateApiServiceInfosCallback callback, final ApiServiceInfo... apiServiceInfos) {
    Runnable runnable = () -> {
      final int i = apiServiceInfoDao.updateApiServiceInfos(apiServiceInfos);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onApiServiceInfosUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateApiServiceInfo(final ApiServiceInfo apiServiceInfo, @NonNull final UpdateApiServiceInfoCallback callback) {
    Runnable runnable = () -> {
      final int i = apiServiceInfoDao.updateApiServiceInfo(apiServiceInfo);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onApiServiceInfoUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteApiServiceInfos(@NonNull final DeleteApiServiceInfosCallback callback, final ApiServiceInfo... apiServiceInfos) {
    Runnable runnable = () -> {
      final int i = apiServiceInfoDao.deleteApiServiceInfos(apiServiceInfos);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onApiServiceInfosDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllApiServiceInfo(@NonNull final DeleteAllApiServiceInfoCallback callback) {
    Runnable runnable = () -> {
      final int i = apiServiceInfoDao.deleteAllApiServiceInfo();

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onApiServiceInfosDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteApiServiceInfoById(final int apiServiceInfoId, @NonNull final DeleteApiServiceInfoCallback callback) {
    Runnable runnable = () -> {
      final int i = apiServiceInfoDao.deleteApiServiceInfoById(apiServiceInfoId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onApiServiceInfoDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
