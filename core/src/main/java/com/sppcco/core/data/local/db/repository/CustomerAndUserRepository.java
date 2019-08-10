package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.CustomerAndUser;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public interface CustomerAndUserRepository {


  // ________________________________________ CRUD ________________________________________ //

  interface GetCustomerAndUsersCallback {
    void onCustomerAndUsersLoaded(List<CustomerAndUser> CustomerAndUsers);

    void onDataNotAvailable();
  }

  void getCustomerAndUsers(@NonNull GetCustomerAndUsersCallback callback);


  interface GetCustomerAndUserCallback {
    void onCustomerAndUserLoaded(CustomerAndUser CustomerAndUser);

    void onDataNotAvailable();
  }

  void getCustomerAndUser(int customerAndUserId, @NonNull GetCustomerAndUserCallback callback);


  // Insert
  interface InsertCustomerAndUsersCallback {
    void onCustomerAndUsersInserted(Long[] longs);

    void onDataNotAvailable();
  }

  void insertCustomerAndUsers(List<CustomerAndUser> CustomerAndUsers, @NonNull InsertCustomerAndUsersCallback callback);

  interface InsertCustomerAndUserCallback {
    void onCustomerAndUserInserted(long CustomerAndUserId);

    void onDataNotAvailable();
  }

  void insertCustomerAndUser(CustomerAndUser CustomerAndUser, @NonNull InsertCustomerAndUserCallback callback);


  // Update

  interface UpdateCustomerAndUsersCallback {
    void onCustomerAndUsersUpdated(int i);

    void onDataNotAvailable();
  }

  void updateCustomerAndUsers(@NonNull UpdateCustomerAndUsersCallback callback, CustomerAndUser... CustomerAndUsers);


  interface UpdateCustomerAndUserCallback {
    void onCustomerAndUserUpdated(int i);

    void onDataNotAvailable();
  }

  void updateCustomerAndUser(CustomerAndUser CustomerAndUser, @NonNull UpdateCustomerAndUserCallback callback);

  // Delete


  interface DeleteCustomerAndUsersCallback {
    void onCustomerAndUsersDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteCustomerAndUsers(@NonNull DeleteCustomerAndUsersCallback callback, CustomerAndUser... CustomerAndUsers);


  interface DeleteAllCustomerAndUserCallback {
    void onCustomerAndUsersDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteAllCustomerAndUser(@NonNull DeleteAllCustomerAndUserCallback callback);


  // ________________________________________ CRUD ________________________________________ //

  // Other Method


}
