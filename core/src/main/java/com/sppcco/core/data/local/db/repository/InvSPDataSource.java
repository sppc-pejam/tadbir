package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.InvSPDao;
import com.sppcco.core.util.app.AppExecutors;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 *
 */

public class InvSPDataSource implements InvSPRepository {

  //private static volatile InvSPDataSource INSTANCE;

  private InvSPDao invSPDao;
  private AppExecutors appExecutors;

  @Inject
  public InvSPDataSource(AppExecutors appExecutors, InvSPDao invSPDao) {
    this.invSPDao = invSPDao;
    this.appExecutors = appExecutors;
  }

  /*public static InvSPDataSource getInstance(@NonNull AppExecutors appExecutors,
                                            @NonNull InvSPDao invSPDao) {
    if (INSTANCE == null) {
      synchronized (InvSPDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new InvSPDataSource(appExecutors, invSPDao);
        }
      }
    }
    return INSTANCE;
  }*/


  @Override
  public void getSumOfSPArticleDiscounts(int SPId, int FPId, @NonNull GetSumOfSPArticleDiscountsCallback callback) {
    Runnable runnable  = () -> {
      double dSum = invSPDao.getSumOfSPArticleDiscounts(SPId,FPId);
      appExecutors.mainThread().execute(() -> {
        if(dSum >= 0 )
          callback.onSumOfSPArticleDiscountsLoaded(dSum);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void computeSPTotal(int SPId, int FPId, @NonNull GetComputeSPTotalCallback callback) {
    Runnable runnable  = () -> {
      double dTotal = invSPDao.computeSPTotal(SPId,FPId);
      appExecutors.mainThread().execute(() -> {
        if(dTotal >= 0 )
          callback.onComputeSPTotalLoaded(dTotal);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
