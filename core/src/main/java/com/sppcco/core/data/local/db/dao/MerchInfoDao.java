package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.sub_model.MerchInfo;

import java.util.List;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

/**
 * Created by m_pejam on 01/16/18.
 * MerchandiseDao
 */
@Dao
public interface MerchInfoDao {

  // LiveDate

  @RawQuery(observedEntities = MerchInfo.class)
  DataSource.Factory<Integer, MerchInfo> getAllMerchInfo(SupportSQLiteQuery query);

  @RawQuery(observedEntities = MerchInfo.class)
  DataSource.Factory<Integer, MerchInfo> getAllMerchInfoForStockCabinet(SupportSQLiteQuery query);


  // Get

  @Query("SELECT * FROM MerchInfo")
  MerchInfo getAllMerchInfo();

  @Query("SELECT m._id AS merchId, m.Code AS merchCode, m.Name AS merchName,m.MDesc AS merchDesc, \n" +
    "  m.UnitId AS merchUnitId, \n" +
    "  ( SELECT Name FROM __Unit__ u WHERE  m.UnitId = u._id AND m.FPId = u.FPId ) AS merchUnitName, \n" +
    "  0 AS stockId, 0 AS stockCode, \"\" AS stockName, \"\" AS stockAccountId, \n" +
    "  0 AS stockFAccId, 0 AS stockCCId, 0 AS stockPrjId, \n" +
    "  0 AS cabinetId, 0 AS cabinetCode, \"\" AS cabinetName, \n" +
    "  CASE :bShowImages WHEN 0 THEN NULL ELSE (SELECT Thumbnail FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnail, \n" +
    "  CASE :bShowImages WHEN 0 THEN 0 ELSE (SELECT Count(*) FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnailCount, \n" +
    "  -1 AS custSalesPrice, -1 AS custSalesDiscount \n" +
    "  FROM  __Merchandise__ m  WHERE m._id > 0 AND IsGroup<>1 AND m._id = :id ")
  MerchInfo getMerchInfoByMerchIdWithoutMerchStock(int bShowImages, int id);

  @Query("SELECT m._id AS merchId, m.Code AS merchCode, m.Name AS merchName,m.MDesc AS merchDesc, \n" +
    "  m.UnitId AS merchUnitId, \n" +
    "  ( SELECT Name FROM __Unit__ u WHERE  m.UnitId = u._id AND m.FPId = u.FPId ) AS merchUnitName, \n" +
    "  s._id AS stockId, s.Code AS stockCode, s.Name AS stockName, s.AccountId AS stockAccountId, \n" +
    "  s.FAccId AS stockFAccId, s.CCId AS stockCCId, s.PrjId AS stockPrjId, \n" +
    "  c._id AS cabinetId, c.Code AS cabinetCode, c.Name AS cabinetName, \n" +
    "  CASE :bShowImages WHEN 0 THEN NULL ELSE (SELECT Thumbnail FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnail, \n" +
    "  CASE :bShowImages WHEN 0 THEN 0 ELSE (SELECT Count(*) FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnailCount, \n" +
    "  ( SELECT sp.Val2 FROM __Customer__ c INNER JOIN __SalesPrice__ sp ON sp.Type = c.LRes \n" +
    "  AND sp.FPId = c.FPId WHERE c._id = :customerId AND sp.MerchId = :merchId ) AS custSalesPrice, \n" +
    "  (SELECT sd.Discount FROM __Customer__ c INNER JOIN __SalesDiscount__ sd ON sd.Type = c.LRes AND  \n" +
    "   sd.FPId = c.FPId WHERE c._id = :customerId AND sd.MerchId = :merchId) AS custSalesDiscount \n" +
    "  FROM  __Merchandise__ m  LEFT JOIN __StockRoom__ s ON  s._id = :stockId AND m.FPId = s.FPId \n" +
    "  LEFT JOIN __Cabinet__ c ON c._id = :cabinetId AND s.FPId = c.FPId \n" +
    "  WHERE m._id > 0 AND IsGroup<>1 AND m._id = :merchId ")
  MerchInfo getMerchInfoByMerchIdWithoutMerchStock(int bShowImages, int merchId, int stockId, int cabinetId, int customerId);

  @Query(" SELECT DISTINCT m._id AS merchId, m.Code AS merchCode, m.Name AS merchName,m.MDesc AS merchDesc, \n" +
    "  m.UnitId AS merchUnitId, \n" +
    "  (SELECT Name FROM __Unit__ u WHERE  m.UnitId = u._id AND m.FPId = u.FPId ) AS merchUnitName, \n" +
    "  s._id AS stockId, s.Code AS stockCode, s.Name AS stockName, s.AccountId AS stockAccountId, \n" +
    "  s.FAccId AS stockFAccId, s.CCId AS stockCCId, s.PrjId AS stockPrjId, \n" +
    "  c._id AS cabinetId, c.Code AS cabinetCode, c.Name AS cabinetName, \n" +
    "  CASE :bShowImages WHEN 0 THEN NULL ELSE (SELECT Thumbnail FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnail, \n" +
    "  CASE :bShowImages WHEN 0 THEN 0 ELSE (SELECT Count(*) FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnailCount, \n" +
    "  -1 AS custSalesPrice, -1 AS custSalesDiscount \n" +
    "  FROM  __Merchandise__ m INNER JOIN __MerchStock__ ms ON m._id = ms.MerchId AND m.FPId = ms.FPId AND ms.TopMerchId <> 0 \n" +
    "  LEFT JOIN __StockRoom__ s ON ms.StockId = s._id AND ms.FPId = s.FPId \n" +
    "  LEFT JOIN __Cabinet__ c ON ms.StockId = c.StockRoomId AND ms.FPId = c.FPId \n" +
    "  WHERE m._id > 0 AND IsGroup<>1 AND m._id = :merchId ")
  MerchInfo getMerchInfoByMerchIdWithMerchStock(int bShowImages, int merchId);

  @Query(" SELECT DISTINCT m._id AS merchId, m.Code AS merchCode, m.Name AS merchName,m.MDesc AS merchDesc, \n" +
    "  m.UnitId AS merchUnitId, \n" +
    "  (SELECT Name FROM __Unit__ u WHERE  m.UnitId = u._id AND m.FPId = u.FPId ) AS merchUnitName, \n" +
    "  s._id AS stockId, s.Code AS stockCode, s.Name AS stockName, s.AccountId AS stockAccountId, \n" +
    "  s.FAccId AS stockFAccId, s.CCId AS stockCCId, s.PrjId AS stockPrjId, \n" +
    "  c._id AS cabinetId, c.Code AS cabinetCode, c.Name AS cabinetName, \n" +
    "  CASE :bShowImages WHEN 0 THEN NULL ELSE (SELECT Thumbnail FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnail, \n" +
    "  CASE :bShowImages WHEN 0 THEN 0 ELSE (SELECT Count(*) FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnailCount, \n" +
    "  ( SELECT sp.Val2 FROM __Customer__ c INNER JOIN __SalesPrice__ sp ON sp.Type = c.LRes \n" +
    "  AND sp.FPId = c.FPId WHERE c._id = :customerId AND sp.MerchId = :merchId ) AS custSalesPrice, \n" +
    "  (SELECT sd.Discount FROM __Customer__ c INNER JOIN __SalesDiscount__ sd ON sd.Type = c.LRes AND  \n" +
    "   sd.FPId = c.FPId WHERE c._id = :customerId AND sd.MerchId = :merchId) AS custSalesDiscount \n" +
    "  FROM  __Merchandise__ m INNER JOIN __MerchStock__ ms ON m._id = ms.MerchId AND m.FPId = ms.FPId AND ms.TopMerchId <> 0 \n" +
    "  LEFT JOIN __StockRoom__ s ON s._id = :stockId AND ms.FPId = s.FPId \n" +
    "  LEFT JOIN __Cabinet__ c ON c.StockRoomId = :stockId AND c._id = :cabinetId AND ms.FPId = c.FPId \n" +
    "  WHERE m._id > 0 AND IsGroup<>1 AND m._id = :merchId ")
  MerchInfo getMerchInfoByMerchIdWithMerchStock(int bShowImages, int merchId, int stockId, int cabinetId, int customerId);

  @Query("SELECT m._id AS merchId, m.Code AS merchCode, m.Name AS merchName,m.MDesc AS merchDesc, \n" +
    "  m.UnitId AS merchUnitId, \n" +
    "  ( SELECT Name FROM __Unit__ u WHERE  m.UnitId = u._id AND m.FPId = u.FPId ) AS merchUnitName, \n" +
    "  0 AS stockId, 0 AS stockCode, \"\" AS stockName, \"\" AS stockAccountId, \n" +
    "  0 AS stockFAccId, 0 AS stockCCId, 0 AS stockPrjId, \n" +
    "  0 AS cabinetId, 0 AS cabinetCode, \"\" AS cabinetName, \n" +
    "  CASE :bShowImages WHEN 0 THEN NULL ELSE (SELECT Thumbnail FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnail, \n" +
    "  CASE :bShowImages WHEN 0 THEN 0 ELSE (SELECT Count(*) FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnailCount, \n" +
    "  -1 AS custSalesPrice, -1 AS custSalesDiscount \n" +
    "  FROM  __Merchandise__ m  \n" +
    "  WHERE m._id > 0 AND IsGroup<>1 AND m.BarCode LIKE '%' || :barcode || '%' LIMIT 1")
  MerchInfo getMerchInfoByBarcodeWithoutMerchStock(int bShowImages, String barcode);

  @Query(" SELECT DISTINCT m._id AS merchId, m.Code AS merchCode, m.Name AS merchName,m.MDesc AS merchDesc, \n" +
    "  m.UnitId AS merchUnitId, \n" +
    "  (SELECT Name FROM __Unit__ u WHERE  m.UnitId = u._id AND m.FPId = u.FPId ) AS merchUnitName, \n" +
    "  s._id AS stockId, s.Code AS stockCode, s.Name AS stockName, s.AccountId AS stockAccountId, \n" +
    "  s.FAccId AS stockFAccId, s.CCId AS stockCCId, s.PrjId AS stockPrjId, \n" +
    "  c._id AS cabinetId, c.Code AS cabinetCode, c.Name AS cabinetName, \n" +
    "  CASE :bShowImages WHEN 0 THEN NULL ELSE (SELECT Thumbnail FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnail, \n" +
    "  CASE :bShowImages WHEN 0 THEN 0 ELSE (SELECT Count(*) FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnailCount, \n" +
    "  -1 AS custSalesPrice, -1 AS custSalesDiscount \n" +
    "  FROM  __Merchandise__ m INNER JOIN __MerchStock__ ms ON m._id = ms.MerchId AND m.FPId = ms.FPId AND ms.TopMerchId <> 0 \n" +
    "  LEFT JOIN __StockRoom__ s ON ms.StockId = s._id AND ms.FPId = s.FPId \n" +
    "  LEFT JOIN __Cabinet__ c ON ms.StockId = c.StockRoomId AND ms.FPId = c.FPId \n" +
    "  WHERE m._id > 0 AND IsGroup<>1 AND m.BarCode LIKE '%' || :barcode || '%' LIMIT 1")
  MerchInfo getMerchInfoByBarcodeWithMerchStock(int bShowImages, String barcode);


  @Query(" SELECT DISTINCT m._id AS merchId, m.Code AS merchCode, m.Name AS merchName,m.MDesc AS merchDesc, \n" +
    "  m.UnitId AS merchUnitId, \n" +
    "  (SELECT Name FROM __Unit__ u WHERE  m.UnitId = u._id AND m.FPId = u.FPId ) AS merchUnitName, \n" +
    "  0 AS stockId, 0 AS stockCode, \"\" AS stockName, \"\" AS stockAccountId, \n" +
    "  0 AS stockFAccId, 0 AS stockCCId, 0 AS stockPrjId, \n" +
    "  0 AS cabinetId, 0 AS cabinetCode, \"\" AS cabinetName, \n" +
    "  CASE :bShowImages WHEN 0 THEN NULL ELSE (SELECT Thumbnail FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnail, \n" +
    "  CASE :bShowImages WHEN 0 THEN 0 ELSE (SELECT Count(*) FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnailCount, \n" +
    "  -1 AS custSalesPrice, -1 AS custSalesDiscount \n" +
    "  FROM  __Merchandise__ m INNER join __SPArticle__ spa ON m._id = spa.MerchandiseId AND m.FPId = spa.FPId\n" +
    "  WHERE spa.[SPId] = :spId AND m.FPId = :fpId ORDER BY spa.[_id]")
  List<MerchInfo> getAllMerchInfoBySPIdWithoutMerchStock(int bShowImages, int spId, int fpId);


  @Query(" SELECT DISTINCT m._id AS merchId, m.Code AS merchCode, m.Name AS merchName,m.MDesc AS merchDesc, \n" +
    "  m.UnitId AS merchUnitId, \n" +
    "  (SELECT Name FROM __Unit__ u WHERE  m.UnitId = u._id AND m.FPId = u.FPId ) AS merchUnitName, \n" +
    "  s._id AS stockId, s.Code AS stockCode, s.Name AS stockName, s.AccountId AS stockAccountId, \n" +
    "  s.FAccId AS stockFAccId, s.CCId AS stockCCId, s.PrjId AS stockPrjId, \n" +
    "  c._id AS cabinetId, c.Code AS cabinetCode, c.Name AS cabinetName, \n" +
    "  CASE :bShowImages WHEN 0 THEN NULL ELSE (SELECT Thumbnail FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnail, \n" +
    "  CASE :bShowImages WHEN 0 THEN 0 ELSE (SELECT Count(*) FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnailCount, \n" +
    "  -1 AS custSalesPrice, -1 AS custSalesDiscount \n" +
    "  FROM  __Merchandise__ m INNER JOIN __MerchStock__ ms ON m._id = ms.MerchId AND m.FPId = ms.FPId AND ms.TopMerchId <> 0 \n" +
    "  LEFT JOIN __StockRoom__ s ON ms.StockId = s._id AND ms.FPId = s.FPId \n" +
    "  LEFT JOIN __Cabinet__ c ON ms.StockId = c.StockRoomId AND ms.FPId = c.FPId \n" +
    "  INNER join __SPArticle__ spa ON m._id = spa.MerchandiseId AND m.FPId = spa.FPId\n" +
    "  WHERE spa.[SPId] = :spId AND m.FPId = :fpId ORDER BY spa.[_id]")
  List<MerchInfo> getAllMerchInfoBySPIdWithMerchStock(int bShowImages, int spId, int fpId);

}
