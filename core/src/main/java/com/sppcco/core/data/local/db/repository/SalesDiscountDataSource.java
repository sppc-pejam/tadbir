package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.SalesDiscountDao;
import com.sppcco.core.data.model.SalesDiscount;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 * SalesDiscountDataSource
 */

public class SalesDiscountDataSource implements SalesDiscountRepository {

  //private static volatile SalesDiscountDataSource INSTANCE;

  private SalesDiscountDao salesDiscountDao;
  private AppExecutors appExecutors;

  @Inject
  public SalesDiscountDataSource(AppExecutors appExecutors, SalesDiscountDao salesDiscountDao) {
    this.salesDiscountDao = salesDiscountDao;
    this.appExecutors = appExecutors;
  }

  /*public static SalesDiscountDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                    @NonNull SalesDiscountDao salesDiscountDao) {
    if (INSTANCE == null) {
      synchronized (SalesDiscountDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new SalesDiscountDataSource(appExecutors, salesDiscountDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getSalesDiscounts(@NonNull final GetSalesDiscountsCallback callback) {
    Runnable runnable = () -> {
      final List<SalesDiscount> salesDiscounts = salesDiscountDao.getAllSalesDiscount();

      appExecutors.mainThread().execute(() -> {
        if(salesDiscounts != null)
          callback.onSalesDiscountsLoaded(salesDiscounts);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSalesDiscount(final int salesDiscountId, @NonNull final GetSalesDiscountCallback callback) {
    Runnable runnable = () -> {
      final SalesDiscount salesDiscount = salesDiscountDao.getSalesDiscountById(salesDiscountId);

      appExecutors.mainThread().execute(() -> {
        if(salesDiscount != null)
          callback.onSalesDiscountLoaded(salesDiscount);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSalesDiscounts(final List<SalesDiscount> salesDiscounts, @NonNull final InsertSalesDiscountsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = salesDiscountDao.insertSalesDiscounts(salesDiscounts);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onSalesDiscountsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSalesDiscount(final SalesDiscount salesDiscount, @NonNull final InsertSalesDiscountCallback callback) {
    Runnable runnable = () -> {
      final long lSalesDiscountId  = salesDiscountDao.insertSalesDiscount(salesDiscount);

      appExecutors.mainThread().execute(() -> {
        if(lSalesDiscountId != 0)
          callback.onSalesDiscountInserted(lSalesDiscountId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSalesDiscounts(@NonNull final UpdateSalesDiscountsCallback callback, final SalesDiscount... salesDiscounts) {
    Runnable runnable = () -> {
      final int i = salesDiscountDao.updateSalesDiscounts(salesDiscounts);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesDiscountsUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSalesDiscount(final SalesDiscount salesDiscount, @NonNull final UpdateSalesDiscountCallback callback) {
    Runnable runnable = () -> {
      final int i  = salesDiscountDao.updateSalesDiscount(salesDiscount);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesDiscountUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSalesDiscounts(@NonNull final DeleteSalesDiscountsCallback callback, final SalesDiscount... salesDiscounts) {
    Runnable runnable = () -> {
      final int i = salesDiscountDao.deleteSalesDiscounts(salesDiscounts);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesDiscountsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllSalesDiscount(@NonNull final DeleteAllSalesDiscountCallback callback) {
    Runnable runnable = () -> {
      final int i = salesDiscountDao.deleteAllSalesDiscount();

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesDiscountsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSalesDiscountById(final int salesDiscountId, @NonNull final DeleteSalesDiscountCallback callback) {
    Runnable runnable = () -> {
      final int i = salesDiscountDao.deleteSalesDiscountById(salesDiscountId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesDiscountDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getDiscountFromMerchandiseId(final int merchandiseId, final int type, @NonNull final GetDiscountCallback callback) {
    Runnable runnable = () -> {
      final float f = salesDiscountDao.getDiscountFromMerchandiseId(merchandiseId,type);

      appExecutors.mainThread().execute(() -> {
        if(f != 0)
          callback.onDiscountLoaded(f);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountSalesDiscount(@NonNull GetCountSalesDiscountCallback callback) {
    Runnable runnable = () -> {
      final int count = salesDiscountDao.getCountSalesDiscount();

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onSalesDiscountCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCustomerSalesDiscount(int merchandiseId, int customerId, int fpId, @NonNull GetCustomerSalesDiscountCallback callback) {
    Runnable runnable = () -> {
      double dCustomerSalesDiscount = salesDiscountDao.getCustomerSalesDiscount(merchandiseId, customerId, fpId);
      appExecutors.mainThread().execute(() -> {
        if(dCustomerSalesDiscount >= 0)
          callback.onCustomerSalesDiscountLoaded(dCustomerSalesDiscount);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
