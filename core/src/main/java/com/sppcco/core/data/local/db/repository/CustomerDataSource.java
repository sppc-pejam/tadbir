package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.CustomerDao;
import com.sppcco.core.data.model.Customer;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public class CustomerDataSource implements CustomerRepository {

  //private static volatile CustomerDataSource INSTANCE;

  private CustomerDao customerDao;
  private AppExecutors appExecutors;

  @Inject
  public CustomerDataSource(AppExecutors appExecutors, CustomerDao customerDao) {
    this.customerDao = customerDao;
    this.appExecutors = appExecutors;
  }

  /*public static CustomerDataSource getInstance(@NonNull AppExecutors appExecutors,
                                               @NonNull CustomerDao customerDao) {
    if (INSTANCE == null) {
      synchronized (CustomerDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new CustomerDataSource(appExecutors, customerDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getCustomers(@NonNull final GetCustomersCallback callback) {
    Runnable runnable = () -> {
      final List<Customer> customers = customerDao.getAllCustomer();

      appExecutors.mainThread().execute(() -> {
        if (customers != null)
          callback.onCustomersLoaded(customers);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getActiveCustomers(@NonNull GetCustomersCallback callback) {
    Runnable runnable = () -> {
      final List<Customer> customers = customerDao.getActiveCustomers();

      appExecutors.mainThread().execute(() -> {
        if (customers != null)
          callback.onCustomersLoaded(customers);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCustomer(final int customerId, @NonNull final GetCustomerCallback callback) {
    Runnable runnable = () -> {
      final Customer customer = customerDao.getCustomerById(customerId);

      appExecutors.mainThread().execute(() -> {
        if (customer != null)
          callback.onCustomerLoaded(customer);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertCustomers(final List<Customer> customers, @NonNull final InsertCustomersCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = customerDao.insertCustomers(customers);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onCustomersInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertCustomer(final Customer customer, @NonNull final InsertCustomerCallback callback) {
    Runnable runnable = () -> {
      final long lCustomerId = customerDao.insertCustomer(customer);

      appExecutors.mainThread().execute(() -> {
        if (lCustomerId != 0)
          callback.onCustomerInserted(lCustomerId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateCustomers(@NonNull final UpdateCustomersCallback callback, final Customer... customers) {
    Runnable runnable = () -> {
      final int i = customerDao.updateCustomers(customers);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onCustomersUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateCustomer(final Customer customer, @NonNull final UpdateCustomerCallback callback) {
    Runnable runnable = () -> {
      final int i = customerDao.updateCustomer(customer);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onCustomerUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteCustomers(@NonNull final DeleteCustomersCallback callback, final Customer... customers) {
    Runnable runnable = () -> {
      final int i = customerDao.deleteCustomers(customers);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onCustomersDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllCustomer(@NonNull final DeleteAllCustomerCallback callback) {
    Runnable runnable = () -> {
      final int i = customerDao.deleteAllCustomer();

      appExecutors.mainThread().execute(() -> {
        if (i >= 0)
          callback.onCustomersDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteCustomerById(final int customerId, @NonNull final DeleteCustomerCallback callback) {
    Runnable runnable = () -> {
      final int i = customerDao.deleteCustomerById(customerId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onCustomerDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllCustomerId(@NonNull final GetCustomerIdsCallback callback) {
    Runnable runnable = () -> {
      final int[] ints = customerDao.getAllCustomerId();

      appExecutors.mainThread().execute(() -> {
        if (ints != null)
          callback.onCustomerIdsLoaded(ints);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllCustomerName(@NonNull final GetCustomerNamesCallback callback) {
    Runnable runnable = () -> {
      final String[] strings = customerDao.getAllCustomerName();

      appExecutors.mainThread().execute(() -> {
        if (strings != null)
          callback.onCustomerNamesLoaded(strings);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCustomerIdFromCustomerName(final String customerName, @NonNull final GetCustomerIdCallback callback) {
    Runnable runnable = () -> {
      final int i = customerDao.getCustomerIdFromCustomerName(customerName);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onCustomerIdLoaded(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void exitsCustomerId(final int customerId, @NonNull final GetCustomerIdCallback callback) {
    Runnable runnable = () -> {
      final int i = customerDao.exitsCustomerId(customerId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onCustomerIdLoaded(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCustomerNameFromCustomerId(final int customerId, @NonNull final GetCustomerNameCallback callback) {
    Runnable runnable = () -> {
      final String customerName = customerDao.getCustomerNameFromCustomerId(customerId);

      appExecutors.mainThread().execute(() -> {
        if (customerName != null)
          callback.onCustomerNameLoaded(customerName);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void exitsCustomerName(final String customerName, @NonNull final ExistCustomerNameCallback callback) {
    Runnable runnable = () -> {
      final int i = customerDao.exitsCustomerName(customerName);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onCustomerFind(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAccVectorByCustomerName(final String customerName, @NonNull final GetCustomerAccVectorCallback callback) {
   /* Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final ACCVector accVector = customerDao.getAccVectorByCustomerName(customerName);

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(accVector != null)
              callback.onCustomerAccVectorLoaded(accVector);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };
    appExecutors.diskIO().execute(runnable);*/
  }

  @Override
  public void getAccVectorByCustomerId(final int customerId, @NonNull final GetCustomerAccVectorCallback callback) {
   /* Runnable runnable = new Runnable() {
      @Override
      public void run() {
        final ACCVector accVector = customerDao.getAccVectorByCustomerId(customerId);

        appExecutors.mainThread().execute(new Runnable() {
          @Override
          public void run() {
            if(accVector != null)
              callback.onCustomerAccVectorLoaded(accVector);
            else
              callback.onDataNotAvailable();
          }
        });
      }
    };
    appExecutors.diskIO().execute(runnable);*/
  }

  @Override
  public void getCountCustomer(@NonNull GetCountCustomerCallback callback) {
    Runnable runnable = () -> {
      final int count = customerDao.getCountCustomer();

      appExecutors.mainThread().execute(() -> {
        if (count != -1)
          callback.onCustomerCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCustomerByAccIdAndFACCId(String accId, int faccId, @NonNull GetCustomerByAccIdAndFACCIdCallback callback) {
    Runnable runnable = () -> {
      final List<Customer> customers = customerDao.getCustomerByAccIdAndFACCId(accId, faccId);

      appExecutors.mainThread().execute(() -> {
        if (customers != null)
          callback.onCustomerLoaded(customers);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCustomerByAcc(String accId, int faccId, int ccId, int prjId,
                               @NonNull GetCustomerByAccCallback callback) {
    Runnable runnable = () -> {
      final List<Customer> customers = customerDao.getCustomerByAcc(accId, faccId, ccId, prjId);

      appExecutors.mainThread().execute(() -> {
        if (customers != null)
          callback.onCustomerLoaded(customers);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCustomerByFACCId(int faccId, @NonNull GetCustomerByFACCIdCallback callback) {
    Runnable runnable = () -> {
      final List<Customer> customers = customerDao.getCustomerByFACCId(faccId);

      appExecutors.mainThread().execute(() -> {
        if (customers != null)
          callback.onCustomerLoaded(customers);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCustomerSalesPriceAndSalesDiscount(int merchandiseId, int customerId,
                                                    @NonNull GetCustomerSalesPriceAndSalesDiscountCallback callback) {
    Runnable runnable = () -> {
      final CustomerDao.PriceAndDiscount priceAndDiscount = customerDao.getCustomerSalesPriceAndSalesDiscount(
        merchandiseId, customerId);

      appExecutors.mainThread().execute(() -> {
        if (priceAndDiscount != null)
          callback.onGetCustomerPriceAndDiscount(priceAndDiscount);
        else
          callback.onGetCustomerPriceAndDiscount(new CustomerDao.PriceAndDiscount());
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void isRepeatedCustomerName(String strName, @NonNull IsRepeatedCallback callback) {
    Runnable runnable = () -> {

      final boolean isRepeatedCustomerName = customerDao.isRepeatedCustomerName(strName);

      appExecutors.mainThread().execute(() -> {

        callback.onRepeated(isRepeatedCustomerName);
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void isRepeatedCustomerSubscriptionNo(String strSubscriptionNo, @NonNull IsRepeatedCallback callback) {
    Runnable runnable = () -> {

      final boolean isRepeatedCustomerSubscriptionNo = customerDao.isRepeatedCustomerSubscriptionNo(strSubscriptionNo);

      appExecutors.mainThread().execute(() -> {

        callback.onRepeated(isRepeatedCustomerSubscriptionNo);
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
