package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.MerchStock;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;

@Dao
public interface MerchStockDao {

  // Get
  @Query("SELECT * FROM __MerchStock__")
  List<MerchStock> getAllMerchStock();


  @Query("SELECT * FROM __MerchStock__ WHERE MerchId = :merchId")
  List<MerchStock> getMerchStockByMerchId(int merchId);

  @Query("SELECT * FROM __MerchStock__ WHERE StockId = :stockId")
  List<MerchStock> getMerchStockByStockId(int stockId);

  // Insert
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertMerchStocks(List<MerchStock> merchStocks);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXMerchStocks(List<MerchStock> merchStocks);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertMerchStock(MerchStock merchStock);

  // Update
  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateMerchStocks(MerchStock... merchStocks);

  @Update
  int updateMerchStock(MerchStock merchStock);

  // Delete
  @Delete
  int deleteMerchStocks(MerchStock... merchStocks);

  @Query("DELETE FROM __MerchStock__")
  int deleteAllMerchStock();

  @Query("SELECT COUNT(*) FROM __MerchStock__ WHERE MerchId <> 0")
  int getCountMerchStock();
}
