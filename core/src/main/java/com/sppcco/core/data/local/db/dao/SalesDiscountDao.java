package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.SalesDiscount;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;

/**
 * Created by m_pejam on 01/16/18.
 *
 */
@Dao
public interface SalesDiscountDao {

  // Get

  @Query("SELECT * FROM __SalesDiscount__")
  List<SalesDiscount> getAllSalesDiscount();

  @Query("SELECT * FROM __SalesDiscount__ WHERE _id = :salesDiscountId")
  SalesDiscount getSalesDiscountById(int salesDiscountId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertSalesDiscounts(List<SalesDiscount> salesDiscounts);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXSalesDiscounts(List<SalesDiscount> salesDiscounts);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertSalesDiscount(SalesDiscount salesDiscount);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateSalesDiscounts(SalesDiscount... salesDiscounts);

  @Update
  int updateSalesDiscount(SalesDiscount salesDiscount);

  // Delete

  @Delete
  int deleteSalesDiscounts(SalesDiscount... salesDiscounts);

  @Query("DELETE FROM __SalesDiscount__")
  int deleteAllSalesDiscount();

  @Query("DELETE FROM __SalesDiscount__ WHERE _id = :salesDiscountId")
  int deleteSalesDiscountById(int salesDiscountId);

  // Other Method

  @Query("SELECT Discount FROM __SalesDiscount__ WHERE MerchId = :merchandiseId  AND Type = :type")
  float getDiscountFromMerchandiseId(int merchandiseId, int type);

  @Query("SELECT COUNT(*) FROM __SalesDiscount__")
  int getCountSalesDiscount();

  @Query("SELECT Discount FROM __SalesDiscount__ s INNER JOIN __Customer__ c " +
    "ON s.Type = c.LRes AND s.FPId = c.FPId " +
    "WHERE c.FPId = :fpId AND c._id = :customerId AND s.MerchId = :merchandiseId")
  double getCustomerSalesDiscount(int merchandiseId, int customerId, int fpId);

}
