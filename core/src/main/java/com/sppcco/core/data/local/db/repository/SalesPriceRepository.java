package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.SalesPrice;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 *
 */

public interface SalesPriceRepository {

  // ________________________________________ CRUD ________________________________________ //

  interface GetSalesPricesCallback {
    void onSalesPricesLoaded(List<SalesPrice> salesPrices);
    void onDataNotAvailable();
  }
  void getSalesPrices(@NonNull GetSalesPricesCallback callback);


  interface GetSalesPriceCallback {
    void onSalesPriceLoaded(SalesPrice salesPrice);
    void onDataNotAvailable();
  }
  void getSalesPrice(int salesPriceId, @NonNull GetSalesPriceCallback callback);


  // Insert
  interface InsertSalesPricesCallback {
    void onSalesPricesInserted(Long[] longs);
    void onDataNotAvailable();
  }
  void insertSalesPrices(List<SalesPrice> salesPrices, @NonNull InsertSalesPricesCallback callback);

  interface InsertSalesPriceCallback {
    void onSalesPriceInserted(long salesPriceId);
    void onDataNotAvailable();
  }
  void insertSalesPrice(SalesPrice salesPrice, @NonNull InsertSalesPriceCallback callback);


  // Update

  interface UpdateSalesPricesCallback {
    void onSalesPricesUpdated(int i);
    void onDataNotAvailable();
  }
  void updateSalesPrices(@NonNull UpdateSalesPricesCallback callback, SalesPrice... salesPrices);


  interface UpdateSalesPriceCallback {
    void onSalesPriceUpdated(int i);
    void onDataNotAvailable();
  }
  void updateSalesPrice(SalesPrice salesPrice, @NonNull UpdateSalesPriceCallback callback);

  // Delete


  interface DeleteSalesPricesCallback {
    void onSalesPricesDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSalesPrices(@NonNull DeleteSalesPricesCallback callback, SalesPrice... salesPrices);


  interface DeleteAllSalesPriceCallback {
    void onSalesPricesDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllSalesPrice(@NonNull DeleteAllSalesPriceCallback callback);


  interface DeleteSalesPriceCallback {
    void onSalesPriceDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSalesPriceById(int salesPriceId, @NonNull DeleteSalesPriceCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetSVal2Callback {
    void onVal2Loaded(float salesPriceVal2);
    void onDataNotAvailable();
  }
  void getVal2FromMerchandiseId(int merchandiseId, int type, @NonNull GetSVal2Callback callback);

  // Count(*)
  interface GetCountSalesPriceCallback {
    void onSalesPriceCounted(int count);
    void onDataNotAvailable();
  }
  void getCountSalesPrice(@NonNull GetCountSalesPriceCallback callback);


  interface GetCustomerSalesPriceCallback {
    void onCustomerSalesPriceLoaded(double dSalesPrice);
    void onDataNotAvailable();
  }
  void getCustomerSalesPrice(int merchandiseId, int customerId, int fpId, @NonNull GetCustomerSalesPriceCallback callback);

}
