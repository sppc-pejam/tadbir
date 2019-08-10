package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.data.sub_model.DetailAccVectorInfo;

import java.util.List;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

/**
 * Created by b_nematzadeh on 11/02/18.
 */

@Dao
public interface AccVectorInfoDao {

  // LiveDate

  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, AccVectorInfo> getAccountByFullId(SupportSQLiteQuery query);

  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, AccVectorInfo> getAllAccount(SupportSQLiteQuery query);

  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, AccVectorInfo> getAllAccountByDetId(SupportSQLiteQuery query);

  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, AccVectorInfo> getAllAccountByCCId(SupportSQLiteQuery query);

  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, AccVectorInfo> getAllAccountByPrjId(SupportSQLiteQuery query);

  //============================================================================================
  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, DetailAccVectorInfo> getAllDetailAcc(SupportSQLiteQuery query);

  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, DetailAccVectorInfo> getAllDetailAccById(SupportSQLiteQuery query);

  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, DetailAccVectorInfo> getAllDetailAccByFullId(SupportSQLiteQuery query);

  //============================================================================================
  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, AccVectorInfo> getCostCenterByCode(SupportSQLiteQuery query);

  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, AccVectorInfo> getAllCostCenter(SupportSQLiteQuery query);

  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, AccVectorInfo> getAllCostCenterByFullId(SupportSQLiteQuery query);

  //============================================================================================
  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, AccVectorInfo> getProjectByCode(SupportSQLiteQuery query);

  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, AccVectorInfo> getAllProject(SupportSQLiteQuery query);

  @RawQuery(observedEntities = AccVectorInfo.class)
  DataSource.Factory<Integer, AccVectorInfo> getAllProjectByFullId(SupportSQLiteQuery query);

  //============================================================================================
  @Query("SELECT FullId AS code, Name As accountName, \n" +
    "(SELECT NAME FROM __Account__ a1 WHERE  a1.FullId = a.ParentId AND a1.FPId = a.FPId) As parentAccount \n" +
    "FROM __Account__ a  WHERE FullId = :fullId AND SType <> -1 AND  Name LIKE '%%%'  AND FullId NOT IN \n" +
    "(SELECT DISTINCT ParentId FROM __Account__ WHERE FullId = :fullId AND \n" +
    "ParentId <> '0')  ORDER BY FullId")
  List<AccVectorInfo> getAccountByFullId(String fullId);

  @Query("SELECT FullId AS code, Name As accountName, \n" +
    "(SELECT NAME FROM __Account__ a1 WHERE  a1.FullId = a.ParentId AND a1.FPId = a.FPId) As parentAccount \n" +
    "FROM __Account__ a  WHERE FullId <> '0' AND SType <> -1 AND  Name LIKE '%%%'  AND FullId NOT IN \n" +
    "(SELECT DISTINCT ParentId FROM __Account__ WHERE FullId <> '0' AND \n" +
    "ParentId <> '0')  ORDER BY FullId")
  List<AccVectorInfo> getAllAccount();

  @Query("SELECT T1+' '+T2+' '+T3+' '+T4 AS code, Name As accountName, _id  As parentAccount \n" +
    "FROM __DetailAcc__ WHERE _id > 0 AND  Name LIKE '%%%'  AND \n" +
    "( AccLevel=7 OR ( AccLevel=4 AND T1 NOT IN (SELECT DISTINCT T1 FROM __DetailAcc__ WHERE AccLevel > 4) ) \n" +
    "OR ( AccLevel=5 AND T1+T2 NOT IN (SELECT DISTINCT T1+T2 FROM __DetailAcc__ WHERE AccLevel > 5) ) \n" +
    "OR ( AccLevel=6 AND T1+T2+T3 NOT IN (SELECT DISTINCT T1+T2+T3 FROM __DetailAcc__ WHERE AccLevel > 6) ) )  \n" +
    "ORDER BY T1,T2,T3,T4")
  List<AccVectorInfo> getAllDetailAcc();

  @Query("SELECT CCCode AS code, Name AS accountName, _id AS parentAccount FROM __CostCenter__ WHERE _id > 0 ORDER BY CCCode")
  List<AccVectorInfo> getAllCostCenter();

  @Query("SELECT PCode AS code, Name AS accountName, _id AS parentAccount FROM __Project__ WHERE _id > 0  ORDER BY PCode")
  List<AccVectorInfo> getAllProject();

  @Query("SELECT a.FullId AS code, Name As accountName, \n" +
    "(SELECT NAME FROM __Account__ a1 WHERE  a1.FullId = a.ParentId AND a1.FPId = a.FPId) As parentAccount  \n" +
    "FROM __Account__ a INNER JOIN __AccVSDetail__ avd ON ( a.FullId = avd.FullId AND a.FPId = avd.FPId )  \n" +
    "WHERE avd.DetId = :detId ORDER BY a.FullId")
  List<AccVectorInfo> getAccountByDetId(int detId);

  @Query("SELECT a.FullId AS code, Name As accountName, \n" +
    "(SELECT NAME FROM __Account__ a1 WHERE  a1.FullId = a.ParentId AND a1.FPId = a.FPId) As parentAccount  \n" +
    "FROM __Account__ a INNER JOIN __AccVSCC__ avc ON ( a.FullId = avc.FullId AND a.FPId = avc.FPId )  \n" +
    "WHERE avc.FPId = :fpId AND avc.CCId = :ccId ORDER BY a.FullId ")
  List<AccVectorInfo> getRelatedAccountInfoByAccVsCC(int fpId, int ccId);

  @Query("SELECT a.FullId AS code, Name As accountName, \n" +
    "(SELECT NAME FROM __Account__ a1 WHERE  a1.FullId = a.ParentId AND a1.FPId = a.FPId) As parentAccount  \n" +
    "FROM __Account__ a INNER JOIN __AccVSPrj__ avp ON ( a.FullId = avp.FullId AND a.FPId = avp.FPId )  \n" +
    "WHERE avp.FPId = :fpId AND avp.PrjId = :prjId ORDER BY a.FullId ")
  List<AccVectorInfo> getRelatedAccountInfoByAccVsPrj(int fpId, int prjId);
}
