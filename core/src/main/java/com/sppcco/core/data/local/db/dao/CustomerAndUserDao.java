package com.sppcco.core.data.local.db.dao;


import com.sppcco.core.data.model.CustomerAndUser;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;

/**
 * Created by m_pejam on 01/15/18.
 */
@Dao
public interface CustomerAndUserDao {

  // Get

  @Query("SELECT * FROM __CustomerAndUser__")
  List<CustomerAndUser> getAllCustomerAndUser();

  @Query("SELECT * FROM __CustomerAndUser__ WHERE CustomerId = :customerId")
  CustomerAndUser getCustomerAndUserByCustomerId(int customerId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertCustomerAndUsers(List<CustomerAndUser> CustomerAndUsers);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXCustomerAndUsers(List<CustomerAndUser> CustomerAndUsers);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertCustomerAndUser(CustomerAndUser CustomerAndUser);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateCustomerAndUsers(CustomerAndUser... CustomerAndUsers);

  @Update
  int updateCustomerAndUser(CustomerAndUser CustomerAndUser);

  // Delete

  @Delete
  int deleteCustomerAndUsers(CustomerAndUser... CustomerAndUsers);

  @Query("DELETE FROM __CustomerAndUser__")
  int deleteAllCustomerAndUser();

  // Other Method


}
