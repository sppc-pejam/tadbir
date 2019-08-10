package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.SalesOrder;
import com.sppcco.core.data.sub_model.ApprovedSOInfo;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public interface SalesOrderRepository {


  // ________________________________________ CRUD ________________________________________ //

  interface GetSalesOrdersCallback {
    void onSalesOrdersLoaded(List<SalesOrder> salesOrders);
    void onDataNotAvailable();
  }

  void getSalesOrders(@NonNull GetSalesOrdersCallback callback);


  interface GetSalesOrderCallback {
    void onSalesOrderLoaded(SalesOrder salesOrder);
    void onDataNotAvailable();
  }

  void getSalesOrder(int salesOrderId, @NonNull GetSalesOrderCallback callback);


  // Insert
  interface InsertSalesOrdersCallback {
    void onSalesOrdersInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertSalesOrders(List<SalesOrder> salesOrders, @NonNull InsertSalesOrdersCallback callback);

  interface InsertSalesOrderCallback {
    void onSalesOrderInserted(long salesOrderId);
    void onDataNotAvailable();
  }

  void insertSalesOrder(SalesOrder salesOrder, @NonNull InsertSalesOrderCallback callback);


  // Update

  interface UpdateSalesOrdersCallback {
    void onSalesOrdersUpdated(int i);
    void onDataNotAvailable();
  }

  void updateSalesOrders(@NonNull UpdateSalesOrdersCallback callback, SalesOrder... salesOrders);


  interface UpdateSalesOrderCallback {
    void onSalesOrderUpdated(int i);
    void onDataNotAvailable();
  }

  void updateSalesOrder(SalesOrder salesOrder, @NonNull UpdateSalesOrderCallback callback);

  // Delete


  interface DeleteSalesOrdersCallback {
    void onSalesOrdersDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSalesOrders(@NonNull DeleteSalesOrdersCallback callback, SalesOrder... salesOrders);


  interface DeleteAllSalesOrderCallback {
    void onSalesOrdersDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllSalesOrder(@NonNull DeleteAllSalesOrderCallback callback);


  interface DeleteSalesOrderCallback {
    void onSalesOrderDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSalesOrderById(int salesOrderId, @NonNull DeleteSalesOrderCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetNextSONoCallback {
    void onNextSONoLoaded(int nextSONo);
    void onDataNotAvailable();
  }

  void getNextSONo(int FPId, int UserId, @NonNull GetNextSONoCallback callback);

  interface GetApprovedSODetailes {
    void onApprovedSODetailesLoaded(List<ApprovedSOInfo> approvedSODetailes);
    void onDataNotAvailable();
  }
  void getApprovedSODetailes(@NonNull GetApprovedSODetailes callback);

  interface UpdateSalesOrderReference{
    void onSalesOrderReferenceUpdated(int i);
    void onDataNotAvailable();
  }
  void updateSalesOrderReference(int soid, int soreference, int fpid, @NonNull UpdateSalesOrderReference callback);

  void getNotApprovedSO(int FPId, int UserId, @NonNull GetSalesOrderCallback callback);
}
