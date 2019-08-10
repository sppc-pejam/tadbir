package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.CostCenterDao;
import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public class CostCenterDataSource implements CostCenterRepository {

  //private static volatile CostCenterDataSource INSTANCE;

  private CostCenterDao costCenterDao;
  private AppExecutors appExecutors;

  @Inject
  public CostCenterDataSource(AppExecutors appExecutors, CostCenterDao costCenterDao) {
    this.costCenterDao = costCenterDao;
    this.appExecutors = appExecutors;
  }

  /*public static CostCenterDataSource getInstance(@NonNull AppExecutors appExecutors,
                                              @NonNull CostCenterDao costCenterDao) {
    if (INSTANCE == null) {
      synchronized (CostCenterDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new CostCenterDataSource(appExecutors, costCenterDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getCostCenters(@NonNull final GetCostCentersCallback callback) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final List<CostCenter> costCenters = costCenterDao.getAllCostCenter();

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(costCenters != null)
              callback.onCostCentersLoaded(costCenters);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCostCenter(final int costCenterId, @NonNull final GetCostCenterCallback callback) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final CostCenter costCenter = costCenterDao.getCostCenterById(costCenterId);

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(costCenter != null)
              callback.onCostCenterLoaded(costCenter);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCostCenterById(int id, @NonNull GetCostCenterByIdCallback callback) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final CostCenter costCenter = costCenterDao.getCostCenterById(id);

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(costCenter != null)
              callback.onCostCenterLoaded(costCenter);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCostCenterByCode(int code, @NonNull GetCostCenterByCodeCallback callback) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final CostCenter costCenter = costCenterDao.getCostCenterByCode(code);

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(costCenter != null)
              callback.onCostCenterLoaded(costCenter);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertCostCenters(final List<CostCenter> costCenters, @NonNull final InsertCostCentersCallback callback) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final Long[] longs = costCenterDao.insertCostCenters(costCenters);

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(longs != null)
              callback.onCostCentersInserted(longs);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertCostCenter(final CostCenter costCenter, @NonNull final InsertCostCenterCallback callback) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final long lCostCenterId  = costCenterDao.insertCostCenter(costCenter);

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(lCostCenterId != 0)
              callback.onCostCenterInserted(lCostCenterId);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateCostCenters(@NonNull final UpdateCostCentersCallback callback, final CostCenter... costCenters) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final int i = costCenterDao.updateCostCenters(costCenters);

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(i != 0)
              callback.onCostCentersUpdated(i);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateCostCenter(final CostCenter costCenter, @NonNull final UpdateCostCenterCallback callback) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final int i  = costCenterDao.updateCostCenter(costCenter);

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(i != 0)
              callback.onCostCenterUpdated(i);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteCostCenters(@NonNull final DeleteCostCentersCallback callback, final CostCenter... costCenters) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final int i = costCenterDao.deleteCostCenters(costCenters);

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(i != 0)
              callback.onCostCentersDeleted(i);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllCostCenter(@NonNull final DeleteAllCostCenterCallback callback) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final int i = costCenterDao.deleteAllCostCenter();

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(i != 0)
              callback.onCostCentersDeleted(i);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteCostCenterById(final int costCenterId, @NonNull final DeleteCostCenterCallback callback) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final int i = costCenterDao.deleteCostCenterById(costCenterId);

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(i != 0)
              callback.onCostCenterDeleted(i);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };
    appExecutors.diskIO().execute(runnable);
  }


  @Override
  public void GetCostCenterNameFromCostCenterId(final int costCenterId, @NonNull final GetCostCenterNameCallback callback) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final String s = costCenterDao.GetCostCenterNameFromCostCenterId(costCenterId);
        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(s != null)
              callback.onCostCenterNameLoaded(s);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountCostCenters(@NonNull GetCountCostCenterCallback callback) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final int count = costCenterDao.getCountCostCenter();

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(count != -1)
              callback.onAccountsCounted(count);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountCostCenterByFullId(String fullId, @NonNull GetCountCostCenterByFullIdCallback callback) {
    Runnable runnable = () -> {
      final int count = costCenterDao.getCountCostCenterByFullId(fullId);

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onAccountsCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCostCenterVectorInfoByCode(int code, @NonNull GetCostCenterVectorInfoByCodeCallback callback) {
    Runnable runnable = () -> {
      final AccVectorInfo vectorInfo = costCenterDao.getCostCenterVectorInfoByCode(code);

      appExecutors.mainThread().execute(() -> {
        if(vectorInfo != null)
          callback.onVectorInfo(vectorInfo);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }


}
