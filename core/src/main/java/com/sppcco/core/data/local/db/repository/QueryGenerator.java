package com.sppcco.core.data.local.db.repository;



import com.sppcco.data_entry_widgets.CK;

import androidx.sqlite.db.SimpleSQLiteQuery;

/**
 * Created by m_pejam on 01/24/18.
 * DataGenerator
 */

public class QueryGenerator {

  public QueryGenerator() {
  }

  public SimpleSQLiteQuery getAllMerchInfoWithoutMerchStockQuery(
    int isShowImages,
    int fpId, int sortType, String searchType) {
    String strSql = "SELECT m._id AS merchId, m.Code AS merchCode, m.Name AS merchName,m.MDesc AS merchDesc, \n" +
      "    m.UnitId AS merchUnitId, \n" +
      "    ( SELECT Name FROM __Unit__ u WHERE  m.UnitId = u._id AND m.FPId = u.FPId ) AS merchUnitName, \n" +
      "    0 AS stockId, 0 AS stockCode, \"\" AS stockName, \"\" AS stockAccountId, \n" +
      "    0 AS stockFAccId, 0 AS stockCCId, 0 AS stockPrjId, \n" +
      "    0 AS cabinetId, 0 AS cabinetCode, \"\" AS cabinetName,    \n" +
      "  CASE ? WHEN 0 THEN NULL ELSE (SELECT Thumbnail FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnail, \n" +
      "  CASE ? WHEN 0 THEN 0 ELSE (SELECT Count(*) FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnailCount, \n" +
      "    -1 AS custSalesPrice, -1 AS custSalesDiscount \n" +
      "    FROM __Merchandise__ m WHERE m.FPId = ? " +
      (searchType == null ? " AND 1=1" : " AND " + getMerchandiseStringFilter(searchType)) +
      "    ORDER BY CASE ? WHEN 0 THEN m.Code WHEN 1 THEN m.Name ELSE m._id END ASC";

    return new SimpleSQLiteQuery(strSql,
      new Object[]{
        isShowImages, isShowImages,
        fpId, sortType});

  }

  public SimpleSQLiteQuery getAllMerchInfoWithMerchStockQuery(
    int isShowImages,
    int stockId, int cabinetId, int fpId,
    int sortType, String searchType) {

    String strSql = "SELECT DISTINCT m._id AS merchId, m.Code AS merchCode, m.Name AS merchName,m.MDesc AS merchDesc, " +
      "  m.UnitId AS merchUnitId, " +
      "  (SELECT Name FROM __Unit__ u WHERE  m.UnitId = u._id AND m.FPId = u.FPId ) AS merchUnitName, " +
      "  s._id AS stockId, s.Code AS stockCode, s.Name AS stockName, s.AccountId AS stockAccountId, \n" +
      "  s.FAccId AS stockFAccId, s.CCId AS stockCCId, s.PrjId AS stockPrjId, \n" +
      "  c._id AS cabinetId, c.Code AS cabinetCode, c.Name AS cabinetName, \n" +
      "  CASE ? WHEN 0 THEN NULL ELSE (SELECT Thumbnail FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnail, \n" +
      "  CASE ? WHEN 0 THEN 0 ELSE (SELECT Count(*) FROM __Image__ i WHERE ObjectId = m._id AND ObjectFPId = m.FpId LIMIT 1) END AS merchThumbnailCount, \n" +
      "  -1 AS custSalesPrice, -1 AS custSalesDiscount \n" +
      "  FROM ((( " +
      "  __Merchandise__ m INNER JOIN __MerchStock__ ms ON \n" +
      "  m._id = ms.MerchId AND m.FPId = ms.FPId AND ms.TopMerchId <> 0 AND ms.StockId = ? ) \n" +
      "  LEFT JOIN __StockRoom__ s ON ms.StockId = s._id AND ms.FPId = s.FPId )\n" +
      "  LEFT JOIN __Cabinet__ c ON ms.StockId = c.StockRoomId AND ms.FPId = c.FPId AND c._id = ? )\n" +
      "  WHERE m._id > 0 AND IsGroup<>1 AND ms.StockId = ? AND m.FPId = ? \n" +
      (searchType == null ? " AND 1=1" : " AND " + getMerchandiseStringFilter(searchType)) +
      "  ORDER BY CASE ? WHEN 0 THEN m.Code WHEN 1 THEN m.Name ELSE m._id END ASC";


    return new SimpleSQLiteQuery(strSql,
      new Object[]{
        isShowImages, isShowImages,
        stockId, cabinetId, stockId, fpId
        , sortType
      });

  }

  private String getMerchandiseStringFilter(String searchType) {
    String strFilter = " m.Name LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR m.Code LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR m.MDesc LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR m.Barcode LIKE '%'||'" + searchType + "'||'%' \n";
    if (CK.isIntegerNum(searchType)) {
      strFilter += " OR m.ManufactYear = " + Integer.parseInt(searchType) + " \n";
    }
    strFilter += " OR m.PartNo LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR m.SpecNo LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR m.Model LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR m.ExType LIKE '%'||'" + searchType + "'||'%' \n";

    return strFilter;
  }


  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
  // Account Vector
  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
  public SimpleSQLiteQuery getAccountByFullId(String fullId) {

    String strSql = "SELECT FullId AS code, Name As accountName, \n" +
      "(SELECT NAME FROM __Account__ a1 WHERE  a1.FullId = a.ParentId AND a1.FPId = a.FPId) As parentAccount \n" +
      "FROM __Account__ a  WHERE FullId = ? AND SType <> -1 AND  FullId NOT IN \n" +
      "(SELECT DISTINCT ParentId FROM __Account__ WHERE FullId <> '0' AND ParentId <> '0') \n" +
      "ORDER BY a.FullId ";


    return new SimpleSQLiteQuery(strSql,
      new Object[]{fullId});

  }

  //----------------------------------------------------------------------------
  public SimpleSQLiteQuery getAllAccount(
    String searchItem, int sortType) {

    String strSql = "SELECT FullId AS code, Name As accountName, \n" +
      "(SELECT NAME FROM __Account__ a1 WHERE  a1.FullId = a.ParentId AND a1.FPId = a.FPId) As parentAccount \n" +
      "FROM __Account__ a  WHERE FullId <> '0' AND SType <> -1 AND  FullId NOT IN \n" +
      "(SELECT DISTINCT ParentId FROM __Account__ WHERE FullId <> '0' AND ParentId <> '0') \n" +
      (searchItem == null ? " AND 1=1 " : " AND " + getAccountStringFilter(searchItem)) +
      "ORDER BY CASE ? WHEN 0 THEN a.FullId WHEN 1 THEN a.Name ELSE a.FullId END ASC";


    return new SimpleSQLiteQuery(strSql,
      new Object[]{sortType});

  }


  private String getAccountStringFilter(String searchType) {
    return " a.Name LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR a.FullId LIKE '%'||'" + searchType + "'||'%' \n";
  }

  //----------------------------------------------------------------------------
  public SimpleSQLiteQuery getAllAccountByDetId(int detId
    , String searchItem
    , int sortType) {

    String strSql = "SELECT a.FullId AS code, Name As accountName, \n" +
      " (SELECT NAME FROM __Account__ a1 WHERE  a1.FullId = a.ParentId AND a1.FPId = a.FPId) As parentAccount  \n" +
      " FROM __Account__ a INNER JOIN __AccVSDetail__ avd ON ( a.FullId = avd.FullId AND a.FPId = avd.FPId )  \n" +
      " WHERE avd.DetId = ? \n" +
      (searchItem == null ? " AND 1=1 " : " AND " + getAccountByDetIdStringFilter(searchItem)) +
      "ORDER BY CASE ? WHEN 0 THEN a.FullId WHEN 1 THEN Name ELSE a.FullId END ASC";

    return new SimpleSQLiteQuery(strSql
      , new Object[]{detId, sortType});

  }


  private String getAccountByDetIdStringFilter(String searchType) {
    return " a.FullId LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR Name LIKE '%'||'" + searchType + "'||'%' \n";
  }

  //----------------------------------------------------------------------------
  public SimpleSQLiteQuery getAllAccountByCCId(int ccId
    , String searchItem
    , int sortType) {

    String strSql = "SELECT a.FullId AS code, Name As accountName, \n" +
      "(SELECT NAME FROM __Account__ a1 WHERE  a1.FullId = a.ParentId AND a1.FPId = a.FPId) As parentAccount  \n" +
      "FROM __Account__ a INNER JOIN __AccVSCC__ avc ON ( a.FullId = avc.FullId AND a.FPId = avc.FPId ) \n" +
      "WHERE avc.CCId = ? \n" +
      (searchItem == null ? " AND 1=1 " : " AND " + getAccountByCCIdStringFilter(searchItem)) +
      "ORDER BY CASE ? WHEN 0 THEN a.FullId WHEN 1 THEN Name ELSE avc.CCId END ASC";

    return new SimpleSQLiteQuery(strSql
      , new Object[]{ccId, sortType});

  }


  private String getAccountByCCIdStringFilter(String searchType) {
    return " a.FullId LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR Name LIKE '%'||'" + searchType + "'||'%' \n";
  }

  //----------------------------------------------------------------------------
  public SimpleSQLiteQuery getAllAccountByPrjId(int prjId
    , String searchItem
    , int sortType) {

    String strSql = "SELECT a.FullId AS code, Name As accountName, \n" +
      "(SELECT NAME FROM __Account__ a1 WHERE  a1.FullId = a.ParentId AND a1.FPId = a.FPId) As parentAccount  \n" +
      "FROM __Account__ a INNER JOIN __AccVSPrj__ avp ON ( a.FullId = avp.FullId AND a.FPId = avp.FPId )  \n" +
      "WHERE avp.PrjId = ? \n" +
      (searchItem == null ? " AND 1=1 " : " AND " + getAccountByPrjIdStringFilter(searchItem)) +
      "ORDER BY CASE ? WHEN 0 THEN a.FullId WHEN 1 THEN Name ELSE avp.PrjId END ASC";

    return new SimpleSQLiteQuery(strSql
      , new Object[]{prjId, sortType});

  }


  private String getAccountByPrjIdStringFilter(String searchType) {
    return " a.FullId LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR Name LIKE '%'||'" + searchType + "'||'%' \n";
  }

  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
  public SimpleSQLiteQuery getAllDetailAcc(
    String searchItem, int sortType) {

    String strSql = "SELECT T1||' '||T2||' '||T3||' '||T4 AS code, Name As accountName, _id  As parentAccount, AccLevel As accLevel \n" +
      "FROM __DetailAcc__ WHERE _id > 0  AND \n" +
      "( AccLevel=7 OR ( AccLevel=4 AND T1 NOT IN (SELECT DISTINCT T1 FROM __DetailAcc__ WHERE AccLevel > 4) ) \n" +
      "OR ( AccLevel=5 AND T1+T2 NOT IN (SELECT DISTINCT T1+T2 FROM __DetailAcc__ WHERE AccLevel > 5) ) \n" +
      "OR ( AccLevel=6 AND T1+T2+T3 NOT IN (SELECT DISTINCT T1+T2+T3 FROM __DetailAcc__ WHERE AccLevel > 6) ) ) \n" +
      (searchItem == null ? " AND 1=1 " : " AND " + getDetailAccStringFilter(searchItem)) +
      "ORDER BY CASE ? WHEN 0 THEN T1||' '||T2||' '||T3||' '||T4 WHEN 1 THEN Name ELSE T1||' '||T2||' '||T3||' '||T4 END ASC";

    return new SimpleSQLiteQuery(strSql
      , new Object[]{sortType});

  }


  private String getDetailAccStringFilter(String searchType) {
    return " T1||' '||T2||' '||T3||' '||T4 LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR Name LIKE '%'||'" + searchType + "'||'%' \n";
  }

  //----------------------------------------------------------------------------
  public SimpleSQLiteQuery getAllDetailAccById(int detailId) {

    String strSql = "SELECT T1||' '||T2||' '||T3||' '||T4 AS code, Name As accountName, _id  As parentAccount, AccLevel As accLevel \n" +
      "FROM __DetailAcc__ WHERE _id = ?  AND \n" +
      "( AccLevel=7 OR ( AccLevel=4 AND T1 NOT IN (SELECT DISTINCT T1 FROM __DetailAcc__ WHERE AccLevel > 4) ) \n" +
      "OR ( AccLevel=5 AND T1+T2 NOT IN (SELECT DISTINCT T1+T2 FROM __DetailAcc__ WHERE AccLevel > 5) ) \n" +
      "OR ( AccLevel=6 AND T1+T2+T3 NOT IN (SELECT DISTINCT T1+T2+T3 FROM __DetailAcc__ WHERE AccLevel > 6) ) ) \n" +
      "ORDER BY T1||' '||T2||' '||T3||' '||T4";

    return new SimpleSQLiteQuery(strSql, new Object[]{detailId});
  }

  //----------------------------------------------------------------------------
  public SimpleSQLiteQuery getAllDetailAccByFullId(
    String fullId, String searchItem, int sortType) {

    String strSql = "SELECT T1||' '||T2||' '||T3||' '||T4 AS code, Name AS accountName, DetId AS parentAccount, AccLevel As accLevel FROM __AccVsDetail__ ad \n" +
      "INNER JOIN __DetailAcc__ facc ON (ad.DetId = facc._id AND ad.FPId = facc.FPId ) WHERE \n" +
      "Necessary <> 0 AND ad.FullId = ?  \n" +
      (searchItem == null ? " AND 1=1 " : " AND " + getAllDetailAccByFullIdStringFilter(searchItem)) +
      "ORDER BY CASE ? WHEN 0 THEN T1||T2||T3||T4 WHEN 1 THEN Name ELSE T1||T2||T3||T4 END ASC";

    return new SimpleSQLiteQuery(strSql
      , new Object[]{fullId, sortType});

  }


  private String getAllDetailAccByFullIdStringFilter(String searchType) {
    return "(T1||' '||T2||' '||T3||' '||T4 LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR Name LIKE '%'||'" + searchType + "'||'%') \n";
  }

  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------

  public SimpleSQLiteQuery getCostCenterByCode(int code) {

    String strSql = "SELECT CCCode AS code, Name AS accountName, _id AS parentAccount FROM __CostCenter__ WHERE _id > 0 AND CCCode = ?";

    return new SimpleSQLiteQuery(strSql,
      new Object[]{code});

  }
  //----------------------------------------------------------------------------
  public SimpleSQLiteQuery getAllCostCenter(
    String searchItem, int sortType) {

    String strSql = "SELECT CCCode AS code, Name AS accountName, _id AS parentAccount FROM __CostCenter__ WHERE _id > 0 \n" +
      (searchItem == null ? " AND 1=1 " : " AND " + getCostCenterStringFilter(searchItem)) +
      "ORDER BY CASE ? WHEN 0 THEN CCCode WHEN 1 THEN Name ELSE CCCode END ASC";

    return new SimpleSQLiteQuery(strSql,
      new Object[]{sortType});

  }


  private String getCostCenterStringFilter(String searchType) {
    if (CK.isIntegerNum(searchType)) {
      return " CCCode = " + Integer.parseInt(searchType) + " \n";
    } else {
      return " Name LIKE '%'||'" + searchType + "'||'%' \n";
    }
  }

  //----------------------------------------------------------------------------
  public SimpleSQLiteQuery getAllCostCenterByFullId(String fullId
    , String searchItem, int sortType) {

    String strSql = "SELECT CCCode AS code, Name AS accountName, _id AS parentAccount FROM __CostCenter__ \n" +
      " INNER JOIN __AccVsCC__ ON ( [__CostCenter__]._id = [__AccVsCC__].CCId AND  \n" +
      " [__CostCenter__].FPId = [__AccVsCC__].FPId ) WHERE [__AccVsCC__].FullId = ? \n" +
      (searchItem == null ? " AND 1=1 " : " AND " + getCostCenterByFullIdStringFilter(searchItem)) +
      "ORDER BY CASE ? WHEN 0 THEN CCCode WHEN 1 THEN Name ELSE CCCode END ASC";

    return new SimpleSQLiteQuery(strSql
      , new Object[]{fullId, sortType});

  }


  private String getCostCenterByFullIdStringFilter(String searchType) {
    return " CCCode LIKE '%'||'" + searchType + "'||'%' \n" +
      " OR Name LIKE '%'||'" + searchType + "'||'%' \n";
  }

  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------

  public SimpleSQLiteQuery getProjectByCode(int code) {

    String strSql = "SELECT PCode AS code, Name AS accountName, _id AS parentAccount FROM __Project__ WHERE _id > 0 AND PCode = ?";

    return new SimpleSQLiteQuery(strSql,
      new Object[]{code});

  }

  //----------------------------------------------------------------------------

  public SimpleSQLiteQuery getAllProject(
    String searchItem, int sortType) {

    String strSql = "SELECT PCode AS code, Name AS accountName, _id AS parentAccount FROM __Project__ WHERE _id > 0 \n" +
      (searchItem == null ? " AND 1=1 " : " AND " + getProjectStringFilter(searchItem)) +
      "ORDER BY CASE ? WHEN 0 THEN PCode WHEN 1 THEN Name ELSE PCode END ASC";

    return new SimpleSQLiteQuery(strSql,
      new Object[]{sortType});

  }


  private String getProjectStringFilter(String searchType) {
    if (CK.isIntegerNum(searchType)) {
      return " PCode = " + Integer.parseInt(searchType) + " \n";
    } else {
      return " Name LIKE '%'||'" + searchType + "'||'%' \n";
    }
  }
  //----------------------------------------------------------------------------

  public SimpleSQLiteQuery getAllProjectByFullId(String fullId,
                                                 String searchItem, int sortType) {

    String strSql = "SELECT PCode AS code, Name AS accountName, _id AS parentAccount FROM __Project__ INNER JOIN __AccVsPrj__ \n" +
      "ON ( [__Project__]._id = [__AccVsPrj__].PrjId AND [__Project__].FPId = [__AccVsPrj__].FPId ) \n" +
      "WHERE [__AccVsPrj__].FullId = ? \n" +
      (searchItem == null ? " AND 1=1 " : " AND " + getProjectByFullIdStringFilter(searchItem)) +
      " ORDER BY CASE ? WHEN 0 THEN PCode WHEN 1 THEN Name ELSE PCode END ASC";

    return new SimpleSQLiteQuery(strSql,
      new Object[]{fullId, sortType});

  }


  private String getProjectByFullIdStringFilter(String searchType) {
    if (CK.isIntegerNum(searchType)) {
      return " PCode = " + Integer.parseInt(searchType) + " \n";
    } else {
      return " Name LIKE '%'||'" + searchType + "'||'%' \n";
    }
  }
  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------


  public SimpleSQLiteQuery getAllBroker(
    String searchItem) {

    String strSql = "SELECT * FROM __Broker__ WHERE \n" +
      (searchItem == null ? " 1=1 " : getBrokerFilter(searchItem));


    return new SimpleSQLiteQuery(strSql,
      new Object[]{searchItem});

  }

  private String getBrokerFilter(String searchType) {
    return " Name LIKE '%'||'" + searchType + "'||'%' \n";
  }
  //----------------------------------------------------------------------------
}
