package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.CustomerAccDao;
import com.sppcco.core.data.sub_model.CustomerAcc;
import com.sppcco.core.util.app.AppExecutors;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 01/16/18.
 */

public class CustomerAccDataSource implements CustomerAccRepository {

  //private static volatile CustomerAccDataSource INSTANCE;

  private CustomerAccDao customerAccDao;
  private AppExecutors appExecutors;

  @Inject
  public CustomerAccDataSource(AppExecutors appExecutors, CustomerAccDao customerAccDao) {
    this.customerAccDao = customerAccDao;
    this.appExecutors = appExecutors;
  }

  /*public static CustomerAccDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                  @NonNull CustomerAccDao customerAccDao) {
    if (INSTANCE == null) {
      synchronized (CustomerAccDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new CustomerAccDataSource(appExecutors, customerAccDao);
        }
      }
    }
    return INSTANCE;
  }
*/
  @Override
  public void getCustomerAcc(String fullId, int detId, int ccId, int prjId, @NonNull GetCustomerAccCallback callback) {
    Runnable runnable = () -> {
      final CustomerAcc customerAcc = customerAccDao.getCustomerAcc(fullId, detId, ccId, prjId);

      appExecutors.mainThread().execute(() -> {
        if(customerAcc != null)
          callback.onCustomerAcc(customerAcc);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }
}
