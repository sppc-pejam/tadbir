package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.MerchInfoDao;
import com.sppcco.core.data.sub_model.MerchInfo;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 * MerchInfoDataSource
 */

public class MerchInfoDataSource implements MerchInfoRepository {

  //private static volatile MerchInfoDataSource INSTANCE;

  private MerchInfoDao merchInfoDao;
  private AppExecutors appExecutors;

  @Inject
  public MerchInfoDataSource(AppExecutors appExecutors, MerchInfoDao merchInfoDao) {
    this.merchInfoDao = merchInfoDao;
    this.appExecutors = appExecutors;
  }

  /*public static MerchInfoDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                @NonNull MerchInfoDao merchInfoDao) {
    if (INSTANCE == null) {
      synchronized (MerchInfoDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new MerchInfoDataSource(appExecutors, merchInfoDao);
        }
      }
    }
    return INSTANCE;
  }*/


  @Override
  public void getAllMerchInfo(final int merchandiseId, @NonNull final GetMerchInfoCallback callback) {
    Runnable runnable = () -> {
      final MerchInfo merchInfo = merchInfoDao.getAllMerchInfo();

      appExecutors.mainThread().execute(() -> {
        if (merchInfo != null)
          callback.onMerchInfoLoaded(merchInfo);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchInfoByIdWithoutMerchStock(int isShowImage, int merchId, @NonNull GetMerchInfoCallback callback) {
    Runnable runnable = () -> {
      final MerchInfo merchInfo = merchInfoDao.getMerchInfoByMerchIdWithoutMerchStock(isShowImage, merchId);

      appExecutors.mainThread().execute(() -> {
        if (merchInfo != null)
          callback.onMerchInfoLoaded(merchInfo);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchInfoByIdWithoutMerchStock(int isShowImage, int merchId, int stockId, int cabinetId,int customerId,
                                                @NonNull GetMerchInfoCallback callback) {
    Runnable runnable = () -> {
      final MerchInfo merchInfo = merchInfoDao.getMerchInfoByMerchIdWithoutMerchStock(isShowImage, merchId, stockId, cabinetId, customerId);

      appExecutors.mainThread().execute(() -> {
        if (merchInfo != null)
          callback.onMerchInfoLoaded(merchInfo);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchInfoByIdWithMerchStock(int isShowImage, int id, @NonNull GetMerchInfoCallback callback) {
    Runnable runnable = () -> {
      final MerchInfo merchInfo = merchInfoDao.getMerchInfoByMerchIdWithMerchStock(isShowImage, id);

      appExecutors.mainThread().execute(() -> {
        if (merchInfo != null)
          callback.onMerchInfoLoaded(merchInfo);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchInfoByIdWithMerchStock(int isShowImage, int merchId, int stockId, int cabinetId,int customerId,
                                             @NonNull GetMerchInfoCallback callback) {
    Runnable runnable = () -> {
      final MerchInfo merchInfo = merchInfoDao.getMerchInfoByMerchIdWithMerchStock(isShowImage, merchId, stockId, cabinetId, customerId);

      appExecutors.mainThread().execute(() -> {
        if (merchInfo != null)
          callback.onMerchInfoLoaded(merchInfo);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }


  @Override
  public void getMerchInfoByBarcodeWithoutMerchStock(int isShowImage, String barcode, @NonNull GetMerchInfoCallback callback) {
    Runnable runnable = () -> {
      final MerchInfo merchInfo = merchInfoDao.getMerchInfoByBarcodeWithoutMerchStock(isShowImage, barcode);

      appExecutors.mainThread().execute(() -> {
        if (merchInfo != null)
          callback.onMerchInfoLoaded(merchInfo);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getMerchInfoByBarcodeWithMerchStock(int isShowImage, String barcode, @NonNull GetMerchInfoCallback callback) {
    Runnable runnable = () -> {
      final MerchInfo merchInfo = merchInfoDao.getMerchInfoByBarcodeWithMerchStock(isShowImage, barcode);

      appExecutors.mainThread().execute(() -> {
        if (merchInfo != null)
          callback.onMerchInfoLoaded(merchInfo);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllMerchInfoBySPIdWithoutMerchStock(int bShowImages, int spId, int fpId, @NonNull GetMerchInfosCallback callback) {
    Runnable runnable = () -> {
      final List<MerchInfo> merchInfos = merchInfoDao.getAllMerchInfoBySPIdWithoutMerchStock(bShowImages, spId, fpId);

      appExecutors.mainThread().execute(() -> {
        if (merchInfos != null)
          callback.onMerchInfosLoaded(merchInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllMerchInfoBySPIdWithMerchStock(int bShowImages, int spId, int fpId, @NonNull GetMerchInfosCallback callback) {
    Runnable runnable = () -> {
      final List<MerchInfo> merchInfos = merchInfoDao.getAllMerchInfoBySPIdWithMerchStock(bShowImages, spId, fpId);

      appExecutors.mainThread().execute(() -> {
        if (merchInfos != null)
          callback.onMerchInfosLoaded(merchInfos);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

}