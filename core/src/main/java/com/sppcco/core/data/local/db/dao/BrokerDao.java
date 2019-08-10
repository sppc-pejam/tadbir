package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.Broker;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;

/**
 * Created by b_nematzadeh on 12/11/18.
 */
@Dao
public interface BrokerDao {

  @Query("SELECT * FROM __Broker__ WHERE " +
    " CASE WHEN :strFilter = '' then 1=1 " +
    " ELSE Name LIKE '%' || :strFilter || '%' END ")
  LiveData<List<Broker>> getAllBroker(String strFilter);

  // Get
  @Query("SELECT * FROM __Broker__")
  List<Broker> getAllBroker();

  @Query("SELECT * FROM __Broker__ WHERE _id = :brokerId")
  Broker getBrokerById(int brokerId);

  @Query("SELECT * FROM __Broker__ WHERE _id = :brokerId")
  LiveData<Broker> getBrokerLiveById(int brokerId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertBrokers(List<Broker> brokers);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXBrokers(List<Broker> brokers);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertBroker(Broker broker);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateBrokers(Broker... brokers);

  @Update
  int updateBroker(Broker broker);

  // Delete

  @Delete
  int deleteBrokers(Broker... brokers);

  @Query("DELETE FROM __Broker__")
  int deleteAllBroker();

  @Query("DELETE FROM __Broker__ WHERE _id = :brokerId")
  int deleteBrokerById(int brokerId);

  // Other Method

  @Query("SELECT _id FROM __Broker__")
  int[] getAllBrokerId();

  @Query("SELECT Name FROM __Broker__")
  String[] getAllBrokerName();

  @Query("SELECT _id FROM __Broker__ WHERE Name = :brokerName")
  int getBrokerIdFromBrokerName(String brokerName);

  @Query("SELECT Name FROM __Broker__ WHERE _id = :brokerId")
  String getBrokerNameFromBrokerId(int brokerId);

  @Query("SELECT COUNT( _id ) AS COUNT  FROM __Broker__ WHERE _id = :brokerId")
  int exitsBrokerId(int brokerId);

  @Query("SELECT COUNT( _id ) AS COUNT FROM __Broker__ WHERE Name = :brokerName")
  int exitsBrokerName(String brokerName);

  @Query("SELECT COUNT(*) FROM __Broker__")
  int getCountBroker();

}
