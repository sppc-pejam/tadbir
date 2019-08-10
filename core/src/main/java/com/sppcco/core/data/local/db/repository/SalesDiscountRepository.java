package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.SalesDiscount;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 * SalesDiscountRepository
 */

public interface SalesDiscountRepository {

  // ________________________________________ CRUD ________________________________________ //

  interface GetSalesDiscountsCallback {
    void onSalesDiscountsLoaded(List<SalesDiscount> salesDiscounts);
    void onDataNotAvailable();
  }
  void getSalesDiscounts(@NonNull GetSalesDiscountsCallback callback);


  interface GetSalesDiscountCallback {
    void onSalesDiscountLoaded(SalesDiscount salesDiscount);
    void onDataNotAvailable();
  }
  void getSalesDiscount(int salesDiscountId, @NonNull GetSalesDiscountCallback callback);


  // Insert
  interface InsertSalesDiscountsCallback {
    void onSalesDiscountsInserted(Long[] longs);
    void onDataNotAvailable();
  }
  void insertSalesDiscounts(List<SalesDiscount> salesDiscounts, @NonNull InsertSalesDiscountsCallback callback);

  interface InsertSalesDiscountCallback {
    void onSalesDiscountInserted(long salesDiscountId);
    void onDataNotAvailable();
  }
  void insertSalesDiscount(SalesDiscount salesDiscount, @NonNull InsertSalesDiscountCallback callback);


  // Update

  interface UpdateSalesDiscountsCallback {
    void onSalesDiscountsUpdated(int i);
    void onDataNotAvailable();
  }
  void updateSalesDiscounts(@NonNull UpdateSalesDiscountsCallback callback, SalesDiscount... salesDiscounts);


  interface UpdateSalesDiscountCallback {
    void onSalesDiscountUpdated(int i);
    void onDataNotAvailable();
  }
  void updateSalesDiscount(SalesDiscount salesDiscount, @NonNull UpdateSalesDiscountCallback callback);

  // Delete


  interface DeleteSalesDiscountsCallback {
    void onSalesDiscountsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSalesDiscounts(@NonNull DeleteSalesDiscountsCallback callback, SalesDiscount... salesDiscounts);


  interface DeleteAllSalesDiscountCallback {
    void onSalesDiscountsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllSalesDiscount(@NonNull DeleteAllSalesDiscountCallback callback);


  interface DeleteSalesDiscountCallback {
    void onSalesDiscountDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSalesDiscountById(int salesDiscountId, @NonNull DeleteSalesDiscountCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetDiscountCallback {
    void onDiscountLoaded(float salesDiscountVal2);
    void onDataNotAvailable();
  }
  void getDiscountFromMerchandiseId(int merchandiseId, int type, @NonNull GetDiscountCallback callback);

  // Count(*)
  interface GetCountSalesDiscountCallback {
    void onSalesDiscountCounted(int count);
    void onDataNotAvailable();
  }
  void getCountSalesDiscount(@NonNull GetCountSalesDiscountCallback callback);


  interface GetCustomerSalesDiscountCallback {
    void onCustomerSalesDiscountLoaded(double dSalesDiscount);
    void onDataNotAvailable();
  }
  void getCustomerSalesDiscount(int merchandiseId, int customerId, int fpId, @NonNull GetCustomerSalesDiscountCallback callback);

}
