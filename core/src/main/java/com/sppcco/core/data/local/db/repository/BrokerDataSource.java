package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.BrokerDao;
import com.sppcco.core.data.model.Broker;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 12/11/18.
 */

public class BrokerDataSource implements BrokerRepository {

  //private static volatile BrokerDataSource INSTANCE;

  private BrokerDao brokerDao;
  private AppExecutors appExecutors;

  @Inject
  public BrokerDataSource(AppExecutors appExecutors, BrokerDao brokerDao) {
    this.brokerDao = brokerDao;
    this.appExecutors = appExecutors;
  }

  /*public static BrokerDataSource getInstance(@NonNull AppExecutors appExecutors,
                                             @NonNull BrokerDao brokerDao) {
    if (INSTANCE == null) {
      synchronized (BrokerDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new BrokerDataSource(appExecutors, brokerDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getBrokers(@NonNull final GetBrokersCallback callback) {
    Runnable runnable = () -> {
      final List<Broker> brokers = brokerDao.getAllBroker();

      appExecutors.mainThread().execute(() -> {
        if(brokers != null)
          callback.onBrokersLoaded(brokers);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getBroker(final int brokerId, @NonNull final GetBrokerCallback callback) {
    Runnable runnable = () -> {
      final Broker broker = brokerDao.getBrokerById(brokerId);

      appExecutors.mainThread().execute(() -> {
        if(broker != null)
          callback.onBrokerLoaded(broker);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertBrokers(final List<Broker> brokers, @NonNull final InsertBrokersCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = brokerDao.insertBrokers(brokers);

      appExecutors.mainThread().execute(() -> {
        if(longs != null)
          callback.onBrokersInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertBroker(final Broker broker, @NonNull final InsertBrokerCallback callback) {
    Runnable runnable = () -> {
      final long lBrokerId  = brokerDao.insertBroker(broker);

      appExecutors.mainThread().execute(() -> {
        if(lBrokerId != 0)
          callback.onBrokerInserted(lBrokerId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateBrokers(@NonNull final UpdateBrokersCallback callback, final Broker... brokers) {
    Runnable runnable = () -> {
      final int i = brokerDao.updateBrokers(brokers);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onBrokersUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateBroker(final Broker broker, @NonNull final UpdateBrokerCallback callback) {
    Runnable runnable = () -> {
      final int i  = brokerDao.updateBroker(broker);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onBrokerUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteBrokers(@NonNull final DeleteBrokersCallback callback, final Broker... brokers) {
    Runnable runnable = () -> {
      final int i = brokerDao.deleteBrokers(brokers);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onBrokersDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllBroker(@NonNull final DeleteAllBrokerCallback callback) {
    Runnable runnable = () -> {
      final int i = brokerDao.deleteAllBroker();

      appExecutors.mainThread().execute(() -> {
        if(i >= 0)
          callback.onBrokersDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteBrokerById(final int brokerId, @NonNull final DeleteBrokerCallback callback) {
    Runnable runnable = () -> {
      final int i = brokerDao.deleteBrokerById(brokerId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onBrokerDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllBrokerId(@NonNull final GetBrokerIdsCallback callback) {
    Runnable runnable = () -> {
      final int[] ints = brokerDao.getAllBrokerId();

      appExecutors.mainThread().execute(() -> {
        if(ints != null)
          callback.onBrokerIdsLoaded(ints);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllBrokerName(@NonNull final GetBrokerNamesCallback callback) {
    Runnable runnable = () -> {
      final String[] strings = brokerDao.getAllBrokerName();

      appExecutors.mainThread().execute(() -> {
        if(strings != null)
          callback.onBrokerNamesLoaded(strings);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getBrokerIdFromBrokerName(final String brokerName, @NonNull final GetBrokerIdCallback callback) {
    Runnable runnable = () -> {
      final int i = brokerDao.getBrokerIdFromBrokerName(brokerName);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onBrokerIdLoaded(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void exitsBrokerId(final int brokerId, @NonNull final GetBrokerIdCallback callback) {
    Runnable runnable = () -> {
      final int i = brokerDao.exitsBrokerId(brokerId);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onBrokerIdLoaded(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getBrokerNameFromBrokerId(final int brokerId, @NonNull final GetBrokerNameCallback callback) {
    Runnable runnable = () -> {
      final String brokerName = brokerDao.getBrokerNameFromBrokerId(brokerId);

      appExecutors.mainThread().execute(() -> {
        if(brokerName != null)
          callback.onBrokerNameLoaded(brokerName);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void exitsBrokerName(final String brokerName, @NonNull final ExistBrokerNameCallback callback) {
    Runnable runnable = () -> {
      final int i = brokerDao.exitsBrokerName(brokerName);

      appExecutors.mainThread().execute(() -> {
        if(i != 0)
          callback.onBrokerFind(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountBroker(@NonNull GetCountBrokerCallback callback) {
    Runnable runnable = () -> {
      final int count = brokerDao.getCountBroker();

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onBrokerCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
