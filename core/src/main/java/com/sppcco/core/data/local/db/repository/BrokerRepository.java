package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.Broker;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 12/11/18.
 */

public interface BrokerRepository {

  // ________________________________________ CRUD ________________________________________ //

  interface GetBrokersCallback {
    void onBrokersLoaded(List<Broker> brokers);
    void onDataNotAvailable();
  }
  void getBrokers(@NonNull GetBrokersCallback callback);


  interface GetBrokerCallback {
    void onBrokerLoaded(Broker broker);
    void onDataNotAvailable();
  }
  void getBroker(int brokerId, @NonNull GetBrokerCallback callback);


  // Insert
  interface InsertBrokersCallback {
    void onBrokersInserted(Long[] longs);
    void onDataNotAvailable();
  }
  void insertBrokers(List<Broker> brokers, @NonNull InsertBrokersCallback callback);

  interface InsertBrokerCallback {
    void onBrokerInserted(long brokerId);
    void onDataNotAvailable();
  }
  void insertBroker(Broker broker, @NonNull InsertBrokerCallback callback);


  // Update

  interface UpdateBrokersCallback {
    void onBrokersUpdated(int i);
    void onDataNotAvailable();
  }
  void updateBrokers(@NonNull UpdateBrokersCallback callback, Broker... brokers);


  interface UpdateBrokerCallback {
    void onBrokerUpdated(int i);
    void onDataNotAvailable();
  }
  void updateBroker(Broker broker, @NonNull UpdateBrokerCallback callback);

  // Delete


  interface DeleteBrokersCallback {
    void onBrokersDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteBrokers(@NonNull DeleteBrokersCallback callback, Broker... brokers);


  interface DeleteAllBrokerCallback {
    void onBrokersDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllBroker(@NonNull DeleteAllBrokerCallback callback);


  interface DeleteBrokerCallback {
    void onBrokerDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteBrokerById(int brokerId, @NonNull DeleteBrokerCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetBrokerIdsCallback {
    void onBrokerIdsLoaded(int[] ints);
    void onDataNotAvailable();
  }
  void getAllBrokerId(@NonNull GetBrokerIdsCallback callback);

  interface GetBrokerNamesCallback {
    void onBrokerNamesLoaded(String[] strings);
    void onDataNotAvailable();
  }
  void getAllBrokerName(@NonNull GetBrokerNamesCallback callback);


  interface GetBrokerIdCallback {
    void onBrokerIdLoaded(int i);
    void onDataNotAvailable();
  }
  void getBrokerIdFromBrokerName(String brokerName, @NonNull GetBrokerIdCallback callback);
  void exitsBrokerId(int brokerId, @NonNull GetBrokerIdCallback callback);


  interface GetBrokerNameCallback {
    void onBrokerNameLoaded(String s);
    void onDataNotAvailable();
  }
  void getBrokerNameFromBrokerId(int brokerId, @NonNull GetBrokerNameCallback callback);

  interface ExistBrokerNameCallback {
    void onBrokerFind(int i);
    void onDataNotAvailable();
  }
  void exitsBrokerName(String brokerName, @NonNull ExistBrokerNameCallback callback);


  // Count(*)
  interface GetCountBrokerCallback {
    void onBrokerCounted(int count);
    void onDataNotAvailable();
  }
  void getCountBroker(@NonNull GetCountBrokerCallback callback);

}
