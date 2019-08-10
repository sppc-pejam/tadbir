package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.CustomerAndUserDao;
import com.sppcco.core.data.model.CustomerAndUser;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public class CustomerAndUserDataSource implements CustomerAndUserRepository {

  //private static volatile CustomerAndUserDataSource INSTANCE;

  private CustomerAndUserDao customerAndUserDao;
  private AppExecutors appExecutors;

  @Inject
  public CustomerAndUserDataSource(AppExecutors appExecutors, CustomerAndUserDao customerAndUserDao) {
    this.customerAndUserDao = customerAndUserDao;
    this.appExecutors = appExecutors;
  }

  /*public static CustomerAndUserDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                      @NonNull CustomerAndUserDao CustomerAndUserDao) {
    if (INSTANCE == null) {
      synchronized (CustomerAndUserDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new CustomerAndUserDataSource(appExecutors, CustomerAndUserDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getCustomerAndUsers(@NonNull final GetCustomerAndUsersCallback callback) {
    Runnable runnable = () -> {
      final List<CustomerAndUser> customerAndUsers = customerAndUserDao.getAllCustomerAndUser();

      appExecutors.mainThread().execute(() -> {
        if (customerAndUsers != null)
          callback.onCustomerAndUsersLoaded(customerAndUsers);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }


  @Override
  public void getCustomerAndUser(final int customerId, @NonNull final GetCustomerAndUserCallback callback) {
    Runnable runnable = () -> {
      final CustomerAndUser CustomerAndUser = customerAndUserDao.getCustomerAndUserByCustomerId(customerId);

      appExecutors.mainThread().execute(() -> {
        if (CustomerAndUser != null)
          callback.onCustomerAndUserLoaded(CustomerAndUser);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertCustomerAndUsers(final List<CustomerAndUser> CustomerAndUsers, @NonNull final InsertCustomerAndUsersCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = customerAndUserDao.insertCustomerAndUsers(CustomerAndUsers);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onCustomerAndUsersInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertCustomerAndUser(final CustomerAndUser CustomerAndUser, @NonNull final InsertCustomerAndUserCallback callback) {
    Runnable runnable = () -> {
      final long lCustomerAndUserId = customerAndUserDao.insertCustomerAndUser(CustomerAndUser);

      appExecutors.mainThread().execute(() -> {
        if (lCustomerAndUserId != 0)
          callback.onCustomerAndUserInserted(lCustomerAndUserId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateCustomerAndUsers(@NonNull final UpdateCustomerAndUsersCallback callback, final CustomerAndUser... CustomerAndUsers) {
    Runnable runnable = () -> {
      final int i = customerAndUserDao.updateCustomerAndUsers(CustomerAndUsers);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onCustomerAndUsersUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateCustomerAndUser(final CustomerAndUser CustomerAndUser, @NonNull final UpdateCustomerAndUserCallback callback) {
    Runnable runnable = () -> {
      final int i = customerAndUserDao.updateCustomerAndUser(CustomerAndUser);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onCustomerAndUserUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteCustomerAndUsers(@NonNull final DeleteCustomerAndUsersCallback callback, final CustomerAndUser... CustomerAndUsers) {
    Runnable runnable = () -> {
      final int i = customerAndUserDao.deleteCustomerAndUsers(CustomerAndUsers);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onCustomerAndUsersDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllCustomerAndUser(@NonNull final DeleteAllCustomerAndUserCallback callback) {
    Runnable runnable = () -> {
      final int i = customerAndUserDao.deleteAllCustomerAndUser();

      appExecutors.mainThread().execute(() -> {
        if (i >= 0)
          callback.onCustomerAndUsersDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
