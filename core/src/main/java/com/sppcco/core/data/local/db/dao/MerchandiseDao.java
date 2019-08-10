package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.Merchandise;
import com.sppcco.core.data.sub_model.MerchInfo;

import java.util.List;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;
import io.reactivex.CompletableSource;

/**
 * Created by m_pejam on 01/16/18.
 * MerchandiseDao
 */
@Dao
public interface MerchandiseDao {

  // LiveDate

  @RawQuery(observedEntities = MerchInfo.class)
  DataSource.Factory<Integer, MerchInfo> getAllMerchInfo(SupportSQLiteQuery query);

  @RawQuery(observedEntities = MerchInfo.class)
  DataSource.Factory<Integer, MerchInfo> getAllMerchInfoForStockCabinet(SupportSQLiteQuery query);

  // Get
  @Query("SELECT * FROM __Merchandise__ WHERE IsGroup = 0 ORDER BY Name")
  List<Merchandise> getAllMerchandise();

  @Query("SELECT * FROM __Merchandise__ WHERE _id = :merchandiseId")
  Merchandise getMerchandiseById(int merchandiseId);

  @Query("SELECT * FROM __Merchandise__ WHERE name = :merchandiseName")
  Merchandise getMerchandiseByName(String merchandiseName);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertMerchandises(List<Merchandise> merchandises);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXMerchandises(List<Merchandise> merchandises);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertMerchandise(Merchandise merchandise);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateMerchandises(Merchandise... merchandises);

  @Update
  int updateMerchandise(Merchandise merchandise);

  // Delete

  @Delete
  int deleteMerchandises(Merchandise... merchandises);

  @Query("DELETE FROM __Merchandise__")
  int deleteAllMerchandise();

  @Query("DELETE FROM __Merchandise__ WHERE _id = :merchandiseId")
  int deleteMerchandiseById(int merchandiseId);

  // Other Method

  @Query("SELECT Name FROM __Merchandise__ WHERE IsGroup <> 1 ")
  String[] getAllMerchandiseName();

  @Query("SELECT Code FROM __Merchandise__ WHERE IsGroup <> 1 ")
  String[] getAllMerchandiseCode();

  @Query("SELECT _id FROM __Merchandise__ WHERE Code = :merchandiseCode")
  int getMerchandiseIdFromMerchandiseCode(String merchandiseCode);

  @Query("SELECT Code FROM __Merchandise__ WHERE Name = :merchandiseName")
  String getMerchandiseCodeFromMerchandiseName(String merchandiseName);

  @Query("SELECT Name FROM __Merchandise__ WHERE Code = :merchandiseCode")
  String getMerchandiseNameFromMerchandiseCode(String merchandiseCode);

  @Query("SELECT UnitId FROM __Merchandise__ WHERE _id = :merchandiseId")
  int getMerchandiseUnitIdFromMerchandiseId(int merchandiseId);

  @Query("SELECT DISTINCT m.* FROM __Merchandise__ m INNER JOIN __MerchStock__ ms ON m._id = ms.MerchId " +
    "AND m.FPId = ms.FPId AND ms.TopMerchId <> 0 " +
    "WHERE m._id > 0 AND IsGroup<>1 AND m.FPId = :fpid AND ms.StockId = :stockId ORDER BY m.Name")
  List<Merchandise> getMerchandiseRelatedStock(int fpid, int stockId);

  @Query("SELECT COUNT(*) FROM __Merchandise__")
  int getCountMerchandise();

  @Query("SELECT * FROM __Merchandise__ WHERE BarCode = :barcode")
  Merchandise getMerchandiseFromBarcode(String barcode);

}
