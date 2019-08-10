package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.SalesPriceDao;
import com.sppcco.core.data.model.SalesPrice;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 *
 */

public class SalesPriceDataSource implements SalesPriceRepository {

  //private static volatile SalesPriceDataSource INSTANCE;

  private SalesPriceDao salesPriceDao;
  private AppExecutors appExecutors;

  @Inject
  public SalesPriceDataSource(AppExecutors appExecutors, SalesPriceDao salesPriceDao) {
    this.salesPriceDao = salesPriceDao;
    this.appExecutors = appExecutors;
  }

  /*public static SalesPriceDataSource getInstance(@NonNull AppExecutors appExecutors,
                                              @NonNull SalesPriceDao salesPriceDao) {
    if (INSTANCE == null) {
      synchronized (SalesPriceDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new SalesPriceDataSource(appExecutors, salesPriceDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getSalesPrices(@NonNull final GetSalesPricesCallback callback) {
    Runnable runnable = () -> {
      final List<SalesPrice> salesPrices = salesPriceDao.getAllSalesPrice();

      appExecutors.mainThread().execute(() -> {
        if(salesPrices != null)
          callback.onSalesPricesLoaded(salesPrices);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSalesPrice(final int salesPriceId, @NonNull final GetSalesPriceCallback callback) {
    Runnable runnable = () -> {
      final SalesPrice salesPrice = salesPriceDao.getSalesPriceById(salesPriceId);

      appExecutors.mainThread().execute(() -> {
        if(salesPrice != null)
          callback.onSalesPriceLoaded(salesPrice);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSalesPrices(final List<SalesPrice> salesPrices, @NonNull final InsertSalesPricesCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = salesPriceDao.insertSalesPrices(salesPrices);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onSalesPricesInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSalesPrice(final SalesPrice salesPrice, @NonNull final InsertSalesPriceCallback callback) {
    Runnable runnable = () -> {
      final long lSalesPriceId  = salesPriceDao.insertSalesPrice(salesPrice);

      appExecutors.mainThread().execute(() -> {
        if(lSalesPriceId != 0)
          callback.onSalesPriceInserted(lSalesPriceId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSalesPrices(@NonNull final UpdateSalesPricesCallback callback, final SalesPrice... salesPrices) {
    Runnable runnable = () -> {
      final int i = salesPriceDao.updateSalesPrices(salesPrices);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesPricesUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSalesPrice(final SalesPrice salesPrice, @NonNull final UpdateSalesPriceCallback callback) {
    Runnable runnable = () -> {
      final int i  = salesPriceDao.updateSalesPrice(salesPrice);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesPriceUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSalesPrices(@NonNull final DeleteSalesPricesCallback callback, final SalesPrice... salesPrices) {
    Runnable runnable = () -> {
      final int i = salesPriceDao.deleteSalesPrices(salesPrices);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesPricesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllSalesPrice(@NonNull final DeleteAllSalesPriceCallback callback) {
    Runnable runnable = () -> {
      final int i = salesPriceDao.deleteAllSalesPrice();

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesPricesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSalesPriceById(final int salesPriceId, @NonNull final DeleteSalesPriceCallback callback) {
    Runnable runnable = () -> {
      final int i = salesPriceDao.deleteSalesPriceById(salesPriceId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesPriceDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getVal2FromMerchandiseId(final int merchandiseId, final int type, @NonNull final GetSVal2Callback callback) {
    Runnable runnable = () -> {
      final float f = salesPriceDao.getVal2FromMerchandiseId(merchandiseId,type);

      appExecutors.mainThread().execute(() -> {
        if(f != 0)
          callback.onVal2Loaded(f);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountSalesPrice(@NonNull GetCountSalesPriceCallback callback) {
    Runnable runnable = () -> {
      final int count = salesPriceDao.getCountSalesPrice();

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onSalesPriceCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCustomerSalesPrice(int merchandiseId, int customerId, int fpId, @NonNull GetCustomerSalesPriceCallback callback) {
    Runnable runnable = () -> {
      double dCustomerSalesPrice = salesPriceDao.getCustomerSalesPrice(merchandiseId, customerId, fpId);
      appExecutors.mainThread().execute(() -> {
        if(dCustomerSalesPrice >= 0)
          callback.onCustomerSalesPriceLoaded(dCustomerSalesPrice);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
