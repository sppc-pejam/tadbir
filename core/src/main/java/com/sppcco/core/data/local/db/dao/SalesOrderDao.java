package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.SalesOrder;
import com.sppcco.core.data.sub_model.ApprovedSOInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by m_pejam on 01/16/18.
 */
@Dao
public interface SalesOrderDao {

  // Get

  @Query("SELECT * FROM __SalesOrder__")
  List<SalesOrder> getAllSalesOrder();

  @Query("SELECT * FROM __SalesOrder__ WHERE _id = :salesOrderId")
  SalesOrder getSalesOrderById(int salesOrderId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertSalesOrders(List<SalesOrder> salesOrders);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertSalesOrder(SalesOrder salesOrder);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateSalesOrders(SalesOrder... salesOrders);

  @Update
  int updateSalesOrder(SalesOrder salesOrder);

  // Delete

  @Delete
  int deleteSalesOrders(SalesOrder... salesOrders);

  @Query("DELETE FROM __SalesOrder__")
  int deleteAllSalesOrder();

  @Query("DELETE FROM __SalesOrder__ WHERE _id = :salesOrderId")
  int deleteSalesOrderById(int salesOrderId);

  // Other Method

  @Query("SELECT MAX(SONo) FROM __SalesOrder__ WHERE FPId = :fpId AND UserId = :userId")
  int getNextSONo(int fpId, int userId);

  @Query("SELECT so._id as SOId, so.SONo as SONo, so.EDate as EDate, " +
    "so.CustomerId as CustomerId, so.CustomerName as CustomerName, " +
    "sos.Approved as Approved, sos.ApprovalDate as ApprovalDate, sos.Edited as Edited, sos.EditedDate as EditedDate, " +
    "sos.Retrieved as Retrieved, sos.RetrievalDate as RetrievalDate, sos.Faulted as Faulted, sos.FaultalDate as FaultalDate "+
    "FROM __SalesOrder__ so INNER JOIN __SOStatus__ sos ON " +
    "so._id = sos.SOId WHERE sos.Approved = 1 AND sos.Posted = 0 ORDER BY so._id DESC ")
  List<ApprovedSOInfo> getApprovedSODetails();

  @Query("UPDATE __SalesOrder__ SET SOReference = :soreference WHERE _id = :soid AND FPId = :fpid")
  int updateSalesOrderReference(int soid, int soreference, int fpid);

  @Query("SELECT * from __SalesOrder__ so WHERE so.FPId = :fpId AND so.UserId = :userId AND so.[_id] NOT IN(SELECT SOId FROM [__SOStatus__]) ")
  SalesOrder getNotApprovedSO(int fpId, int userId);


}
