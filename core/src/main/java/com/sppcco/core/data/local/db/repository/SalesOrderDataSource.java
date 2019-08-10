package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.SalesOrderDao;
import com.sppcco.core.data.model.SalesOrder;
import com.sppcco.core.data.sub_model.ApprovedSOInfo;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 *
 */

public class SalesOrderDataSource implements SalesOrderRepository {

  //private static volatile SalesOrderDataSource INSTANCE;

  private SalesOrderDao salesOrderDao;
  private AppExecutors appExecutors;

  @Inject
  public SalesOrderDataSource(AppExecutors appExecutors, SalesOrderDao salesOrderDao) {
    this.salesOrderDao = salesOrderDao;
    this.appExecutors = appExecutors;
  }

  /*public static SalesOrderDataSource getInstance(@NonNull AppExecutors appExecutors,
                                              @NonNull SalesOrderDao salesOrderDao) {
    if (INSTANCE == null) {
      synchronized (SalesOrderDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new SalesOrderDataSource(appExecutors, salesOrderDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getSalesOrders(@NonNull final GetSalesOrdersCallback callback) {
    Runnable runnable = () -> {
      final List<SalesOrder> salesOrders = salesOrderDao.getAllSalesOrder();

      appExecutors.mainThread().execute(() -> {
        if(salesOrders != null)
          callback.onSalesOrdersLoaded(salesOrders);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSalesOrder(final int salesOrderId, @NonNull final GetSalesOrderCallback callback) {
    Runnable runnable = () -> {
      final SalesOrder salesOrder = salesOrderDao.getSalesOrderById(salesOrderId);

      appExecutors.mainThread().execute(() -> {
        if(salesOrder != null)
          callback.onSalesOrderLoaded(salesOrder);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSalesOrders(final List<SalesOrder> salesOrders, @NonNull final InsertSalesOrdersCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = salesOrderDao.insertSalesOrders(salesOrders);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onSalesOrdersInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSalesOrder(final SalesOrder salesOrder, @NonNull final InsertSalesOrderCallback callback) {
    Runnable runnable = () -> {
      final long lSalesOrderId  = salesOrderDao.insertSalesOrder(salesOrder);

      appExecutors.mainThread().execute(() -> {
        if(lSalesOrderId != 0)
          callback.onSalesOrderInserted(lSalesOrderId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSalesOrders(@NonNull final UpdateSalesOrdersCallback callback, final SalesOrder... salesOrders) {
    Runnable runnable = () -> {
      final int i = salesOrderDao.updateSalesOrders(salesOrders);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesOrdersUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSalesOrder(final SalesOrder salesOrder, @NonNull final UpdateSalesOrderCallback callback) {
    Runnable runnable = () -> {
      final int i  = salesOrderDao.updateSalesOrder(salesOrder);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesOrderUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSalesOrders(@NonNull final DeleteSalesOrdersCallback callback, final SalesOrder... salesOrders) {
    Runnable runnable = () -> {
      final int i = salesOrderDao.deleteSalesOrders(salesOrders);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesOrdersDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllSalesOrder(@NonNull final DeleteAllSalesOrderCallback callback) {
    Runnable runnable = () -> {
      final int i = salesOrderDao.deleteAllSalesOrder();

      appExecutors.mainThread().execute(() -> {
        if(i >= 0)
          callback.onSalesOrdersDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSalesOrderById(final int salesOrderId, @NonNull final DeleteSalesOrderCallback callback) {
    Runnable runnable = () -> {
      final int i = salesOrderDao.deleteSalesOrderById(salesOrderId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onSalesOrderDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getNextSONo(int FPId, int UserId, @NonNull GetNextSONoCallback callback) {
    Runnable runnable = () -> {
      int nextSONo = salesOrderDao.getNextSONo(FPId, UserId);

      appExecutors.mainThread().execute(() -> {
        if(nextSONo <0)
          callback.onDataNotAvailable();
        else
          callback.onNextSONoLoaded(nextSONo + 1);
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getApprovedSODetailes(@NonNull GetApprovedSODetailes callback) {
    Runnable runnable = () -> {
      List<ApprovedSOInfo> approvedSODetailes = salesOrderDao.getApprovedSODetails();

      appExecutors.mainThread().execute(() -> {
       if(approvedSODetailes != null)
         callback.onApprovedSODetailesLoaded(approvedSODetailes);
       else
         callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSalesOrderReference(int soid, int soreference, int fpid, @NonNull UpdateSalesOrderReference callback) {
    Runnable runnable = () -> {
      int count = salesOrderDao.updateSalesOrderReference(soid, soreference, fpid);

      appExecutors.mainThread().execute(() -> {
        if(count != 0)
          callback.onSalesOrderReferenceUpdated(count);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getNotApprovedSO(int FPId, int UserId, @NonNull GetSalesOrderCallback callback) {
      Runnable runnable = () -> {
        final SalesOrder salesOrder = salesOrderDao.getNotApprovedSO(FPId,  UserId);

        appExecutors.mainThread().execute(() -> {
          if(salesOrder != null)
            callback.onSalesOrderLoaded(salesOrder);
          else
            callback.onDataNotAvailable();
        });
      };

      appExecutors.diskIO().execute(runnable);
    }

}
