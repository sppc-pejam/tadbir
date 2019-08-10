package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.CustomerDao;
import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.sub_model.ACCVector;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public interface CustomerRepository {


  // ________________________________________ CRUD ________________________________________ //

  interface GetCustomersCallback {
    void onCustomersLoaded(List<Customer> customers);

    void onDataNotAvailable();
  }

  void getCustomers(@NonNull GetCustomersCallback callback);
  void getActiveCustomers(@NonNull GetCustomersCallback callback);


  interface GetCustomerCallback {
    void onCustomerLoaded(Customer customer);

    void onDataNotAvailable();
  }

  void getCustomer(int customerId, @NonNull GetCustomerCallback callback);


  // Insert
  interface InsertCustomersCallback {
    void onCustomersInserted(Long[] longs);

    void onDataNotAvailable();
  }

  void insertCustomers(List<Customer> customers, @NonNull InsertCustomersCallback callback);

  interface InsertCustomerCallback {
    void onCustomerInserted(long customerId);

    void onDataNotAvailable();
  }

  void insertCustomer(Customer customer, @NonNull InsertCustomerCallback callback);


  // Update

  interface UpdateCustomersCallback {
    void onCustomersUpdated(int i);

    void onDataNotAvailable();
  }

  void updateCustomers(@NonNull UpdateCustomersCallback callback, Customer... customers);


  interface UpdateCustomerCallback {
    void onCustomerUpdated(int i);

    void onDataNotAvailable();
  }

  void updateCustomer(Customer customer, @NonNull UpdateCustomerCallback callback);

  // Delete


  interface DeleteCustomersCallback {
    void onCustomersDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteCustomers(@NonNull DeleteCustomersCallback callback, Customer... customers);


  interface DeleteAllCustomerCallback {
    void onCustomersDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteAllCustomer(@NonNull DeleteAllCustomerCallback callback);


  interface DeleteCustomerCallback {
    void onCustomerDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteCustomerById(int customerId, @NonNull DeleteCustomerCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetCustomerIdsCallback {
    void onCustomerIdsLoaded(int[] ints);

    void onDataNotAvailable();
  }

  void getAllCustomerId(@NonNull GetCustomerIdsCallback callback);

  interface GetCustomerNamesCallback {
    void onCustomerNamesLoaded(String[] strings);

    void onDataNotAvailable();
  }

  void getAllCustomerName(@NonNull GetCustomerNamesCallback callback);


  interface GetCustomerIdCallback {
    void onCustomerIdLoaded(int i);

    void onDataNotAvailable();
  }

  void getCustomerIdFromCustomerName(String customerName, @NonNull GetCustomerIdCallback callback);

  void exitsCustomerId(int customerId, @NonNull GetCustomerIdCallback callback);


  interface GetCustomerNameCallback {
    void onCustomerNameLoaded(String s);

    void onDataNotAvailable();
  }

  void getCustomerNameFromCustomerId(int customerId, @NonNull GetCustomerNameCallback callback);

  interface ExistCustomerNameCallback {
    void onCustomerFind(int i);

    void onDataNotAvailable();
  }

  void exitsCustomerName(String customerName, @NonNull ExistCustomerNameCallback callback);


  interface GetCustomerAccVectorCallback {
    void onCustomerAccVectorLoaded(ACCVector accVector);

    void onDataNotAvailable();
  }

  void getAccVectorByCustomerName(String customerName, @NonNull GetCustomerAccVectorCallback callback);

  void getAccVectorByCustomerId(int customerId, @NonNull GetCustomerAccVectorCallback callback);

  // Count(*)
  interface GetCountCustomerCallback {
    void onCustomerCounted(int count);

    void onDataNotAvailable();
  }

  void getCountCustomer(@NonNull GetCountCustomerCallback callback);

  interface GetCustomerByAccIdAndFACCIdCallback {
    void onCustomerLoaded(List<Customer> customers);

    void onDataNotAvailable();
  }

  void getCustomerByAccIdAndFACCId(String accId, int faccId, @NonNull GetCustomerByAccIdAndFACCIdCallback callback);

  interface GetCustomerByAccCallback {
    void onCustomerLoaded(List<Customer> customers);

    void onDataNotAvailable();
  }

  void getCustomerByAcc(String accId, int faccId, int ccId, int prjId,
                        @NonNull GetCustomerByAccCallback callback);

  interface GetCustomerByFACCIdCallback {
    void onCustomerLoaded(List<Customer> customers);

    void onDataNotAvailable();
  }

  void getCustomerByFACCId(int faccId, @NonNull GetCustomerByFACCIdCallback callback);


  interface GetCustomerSalesPriceAndSalesDiscountCallback {
    void onGetCustomerPriceAndDiscount(CustomerDao.PriceAndDiscount priceAndDiscount);

    void onDataNotAvailable();
  }

  void getCustomerSalesPriceAndSalesDiscount(int merchandiseId, int customerId,
                                             @NonNull GetCustomerSalesPriceAndSalesDiscountCallback callback);


  interface IsRepeatedCallback {

    void onRepeated(Boolean isRepeated);
  }

  void isRepeatedCustomerName(String strName, @NonNull IsRepeatedCallback callback);

  void isRepeatedCustomerSubscriptionNo(String strSubscriptionNo, @NonNull IsRepeatedCallback callback);

}
