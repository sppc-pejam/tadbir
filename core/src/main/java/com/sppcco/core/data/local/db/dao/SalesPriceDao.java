package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.SalesPrice;

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
public interface SalesPriceDao {

  // Get

  @Query("SELECT * FROM __SalesPrice__")
  List<SalesPrice> getAllSalesPrice();

  @Query("SELECT * FROM __SalesPrice__ WHERE _id = :salesPriceId")
  SalesPrice getSalesPriceById(int salesPriceId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertSalesPrices(List<SalesPrice> salesPrices);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXSalesPrices(List<SalesPrice> salesPrices);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertSalesPrice(SalesPrice salesPrice);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateSalesPrices(SalesPrice... salesPrices);

  @Update
  int updateSalesPrice(SalesPrice salesPrice);

  // Delete

  @Delete
  int deleteSalesPrices(SalesPrice... salesPrices);

  @Query("DELETE FROM __SalesPrice__")
  int deleteAllSalesPrice();

  @Query("DELETE FROM __SalesPrice__ WHERE _id = :salesPriceId")
  int deleteSalesPriceById(int salesPriceId);

  // Other Method

  @Query("SELECT Val2 FROM __SalesPrice__ WHERE MerchId = :merchandiseId  AND Type = :type")
  float getVal2FromMerchandiseId(int merchandiseId, int type);

  @Query("SELECT COUNT(*) FROM __SalesPrice__")
  int getCountSalesPrice();

  @Query("SELECT Val2 FROM __SalesPrice__ s INNER JOIN __Customer__ c " +
    "ON s.Type = c.LRes AND s.FPId = c.FPId " +
    "WHERE c.FPId = :fpId AND c._id = :customerId AND s.MerchId = :merchandiseId")
  double getCustomerSalesPrice(int merchandiseId, int customerId, int fpId);

}
