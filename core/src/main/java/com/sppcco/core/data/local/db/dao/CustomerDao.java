package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.Customer;

import java.util.List;

import androidx.room.ColumnInfo;
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
public interface CustomerDao {

  // Get

  @Query("SELECT * FROM __Customer__ WHERE _id <> 0")
  List<Customer> getAllCustomer();

  @Query("SELECT * FROM __Customer__ WHERE _id = :customerId")
  Customer getCustomerById(int customerId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertCustomers(List<Customer> customers);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXCustomers(List<Customer> customers);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertCustomer(Customer customer);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateCustomers(Customer... customers);

  @Update
  int updateCustomer(Customer customer);

  // Delete

  @Delete
  int deleteCustomers(Customer... customers);

  @Query("DELETE FROM __Customer__")
  int deleteAllCustomer();

  @Query("DELETE FROM __Customer__ WHERE _id = :customerId")
  int deleteCustomerById(int customerId);

  // Other Method

  @Query("SELECT _id FROM __Customer__")
  int[] getAllCustomerId();

  @Query("SELECT Name FROM __Customer__")
  String[] getAllCustomerName();

  @Query("SELECT _id FROM __Customer__ WHERE Name = :customerName")
  int getCustomerIdFromCustomerName(String customerName);

  @Query("SELECT Name FROM __Customer__ WHERE _id = :customerId")
  String getCustomerNameFromCustomerId(int customerId);

  @Query("SELECT COUNT( _id ) AS COUNT  FROM __Customer__ WHERE _id = :customerId")
  int exitsCustomerId(int customerId);

  @Query("SELECT COUNT( _id ) AS COUNT FROM __Customer__ WHERE Name = :customerName")
  int exitsCustomerName(String customerName);

  //@Query("SELECT AccId, FAccId, CCId, PrjId FROM __Customer__ WHERE Name = :customerName" )
  //ACCVector getAccVectorByCustomerName(String customerName);

  //@Query("SELECT AccId, FAccId, CCId, PrjId FROM __Customer__ WHERE _id = :customerId" )
  //ACCVector getAccVectorByCustomerId(int customerId);

  @Query("SELECT COUNT(*) FROM __Customer__")
  int getCountCustomer();


  @Query("SELECT * FROM __Customer__ WHERE AccId = :accId AND FAccId = :faccId ")
  List<Customer> getCustomerByAccIdAndFACCId(String accId, int faccId);

  @Query("SELECT * FROM __Customer__ WHERE AccId = :accId AND FAccId = :faccId AND \n" +
    "(1 = 0 OR (CCId = :ccId OR CCId = 0 ) AND (PrjId = :prjId OR PrjId = 0 ) ) AND \n" +
    "(1 = 1 OR (CCId = :ccId AND PrjId = :prjId ) ) AND \n" +
    "( Type <> -1 OR  -1 = -1 ) \n" +
    "AND ( Active = 1  OR  1 = -1 ) AND Active = 1")
  List<Customer> getCustomerByAcc(String accId, int faccId, int ccId, int prjId);

  @Query("SELECT * FROM __Customer__ WHERE  FAccId = :faccId")
  List<Customer> getCustomerByFACCId(int faccId);


  class PriceAndDiscount {
    @ColumnInfo(name = "price")
    public double salesPrice;
    @ColumnInfo(name = "discount")
    public double salesDiscount;
  }
  /*@Query("SELECT sp.Val2 AS price, sd.Discount AS discount FROM __Customer__ c INNER JOIN __SalesPrice__ sp " +
    "ON sp.Type = c.LRes AND sp.FPId = c.FPId INNER JOIN __SalesDiscount__ sd ON sd.Type = c.LRes AND sd.FPId = c.FPId " +
    "WHERE c.FPId = :fpId AND c._id = :customerId AND sp.MerchId = :merchandiseId")*/
  @Query("SELECT (SELECT sp.Val2 FROM __Customer__ c INNER JOIN __SalesPrice__ sp ON sp.Type = c.LRes \n" +
    "AND sp.FPId = c.FPId WHERE c._id = :customerId AND sp.MerchId = :merchandiseId) AS price, \n" +
    "(SELECT sd.Discount FROM __Customer__ c INNER JOIN __SalesDiscount__ sd ON sd.Type = c.LRes AND \n" +
    "sd.FPId = c.FPId WHERE c._id = :customerId AND sd.MerchId = :merchandiseId) AS discount")
  PriceAndDiscount getCustomerSalesPriceAndSalesDiscount(int merchandiseId, int customerId);

  @Query("SELECT CASE WHEN EXISTS ( SELECT * FROM [__Customer__]  WHERE Name LIKE :customerName ) " +
    " THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END ")
  boolean isRepeatedCustomerName(String customerName);

  @Query("SELECT CASE WHEN EXISTS ( SELECT * FROM [__Customer__]  WHERE SubscriptionNo LIKE :customerSubscriptionNo ) " +
    " THEN CAST(1 AS BIT) ELSE CAST(0 AS BIT) END ")
  boolean isRepeatedCustomerSubscriptionNo(String customerSubscriptionNo);


  @Query("SELECT * FROM __Customer__ WHERE  Active = 1")
  List<Customer> getActiveCustomers();
}
