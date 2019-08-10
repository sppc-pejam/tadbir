package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.AccVectorInfoDao;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 11/02/18.
 */

public class AccVectorInfoDataSource implements AccVectorInfoRepository {

  //private static volatile AccVectorInfoDataSource INSTANCE;

  private AccVectorInfoDao accVectorInfoDao;
  private AppExecutors appExecutors;

  @Inject
  public AccVectorInfoDataSource(AppExecutors appExecutors, AccVectorInfoDao accVectorInfoDao) {
    this.accVectorInfoDao = accVectorInfoDao;
    this.appExecutors = appExecutors;
  }

  /*public static AccVectorInfoDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                    @NonNull AccVectorInfoDao accVectorInfoDao) {
    if (INSTANCE == null) {
      synchronized (AccVectorInfoDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new AccVectorInfoDataSource(appExecutors, accVectorInfoDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getAllAccount(@NonNull GetAllAccountCallback callback) {
    Runnable runnable = () -> {
      final List<AccVectorInfo> accVectorInfos = accVectorInfoDao.getAllAccount();

      appExecutors.mainThread().execute(() -> {
        if(accVectorInfos != null)
          callback.onAllAccount(accVectorInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllDetailAcc(@NonNull GetAllDetailAccCallback callback) {
    Runnable runnable = () -> {
      final List<AccVectorInfo> accVectorInfos = accVectorInfoDao.getAllDetailAcc();

      appExecutors.mainThread().execute(() -> {
        if(accVectorInfos != null)
          callback.onAllDetailAcc(accVectorInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllCostCenter(@NonNull GetAllCostCenterCallback callback) {
    Runnable runnable = () -> {
      final List<AccVectorInfo> accVectorInfos = accVectorInfoDao.getAllCostCenter();

      appExecutors.mainThread().execute(() -> {
        if(accVectorInfos != null)
          callback.onAllCostCenter(accVectorInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllProject(@NonNull GetAllProjectCallback callback) {
    Runnable runnable = () -> {
      final List<AccVectorInfo> accVectorInfos = accVectorInfoDao.getAllProject();

      appExecutors.mainThread().execute(() -> {
        if(accVectorInfos != null)
          callback.onAllProject(accVectorInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccountByDetId(int detId, @NonNull GetAccountByDetIdCallback callback) {
    Runnable runnable = () -> {
      final List<AccVectorInfo> accVectorInfos = accVectorInfoDao.getAccountByDetId(detId);

      appExecutors.mainThread().execute(() -> {
        if(accVectorInfos != null)
          callback.onAccVectorInfo(accVectorInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getRelatedAccountInfoByAccVsCC(int fpId, int ccId, @NonNull GetRelatedAccountInfoAccVsCCCallback callback) {
    Runnable runnable = () -> {
      final List<AccVectorInfo> accVectorInfos = accVectorInfoDao.getRelatedAccountInfoByAccVsCC(fpId, ccId);

      appExecutors.mainThread().execute(() -> {
        if(accVectorInfos != null)
          callback.onRelatedAccountInfo(accVectorInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getRelatedAccountInfoByAccVsPrj(int fpId, int prjId, @NonNull GetRelatedAccountInfoAccVsPrjCallback callback) {
    Runnable runnable = () -> {
      final List<AccVectorInfo> accVectorInfos = accVectorInfoDao.getRelatedAccountInfoByAccVsPrj(fpId, prjId);

      appExecutors.mainThread().execute(() -> {
        if(accVectorInfos != null)
          callback.onRelatedAccountInfo(accVectorInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

}
