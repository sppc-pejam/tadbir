package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.Account;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;

/**
 * Created by m_pejam on 01/15/18.
 */
@Dao
public interface AccountDao {

  // Get

  @Query("SELECT * FROM __Account__")
  List<Account> getAllAccount();


  @Query("SELECT * FROM __Account__ WHERE _id = :accountId")
  Account getAccountById(int accountId);

  @Query("SELECT * FROM __Account__ WHERE fullId = :fullId")
  Account getAccountByFullId(String fullId);

  @Query("SELECT * FROM __Account__ WHERE fullId = :fullId")
  LiveData<Account> getLiveDataAccountByFullId(String fullId);

  @Query("SELECT * FROM __Account__ WHERE fullId = :fullId")
  Flowable<Account> getRXAccountByFullId(String fullId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertAccounts(List<Account> accounts);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXAccounts(List<Account> accounts);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertAccount(Account account);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateAccounts(Account... accounts);

  @Update
  int updateAccount(Account account);

  // Delete

  @Delete
  int deleteAccounts(Account... accounts);

  @Query("DELETE FROM __Account__")
  int deleteAllAccount();

  @Query("DELETE FROM __Account__ WHERE _id = :accountId")
  int deleteAccountById(int accountId);

  // Other Methods

  @Query("SELECT Name FROM __Account__ WHERE FullId = :accountFullId")
  String GetAccountNameFromAccountFullId(int accountFullId);

  @Query("SELECT COUNT(*) FROM __Account__")
  int getCountAccount();

  @Query("SELECT a.* FROM __Account__ a INNER JOIN __AccVSDetail__ avd ON ( a.FullId = avd.FullId AND a.FPId = avd.FPId )  \n" +
    "WHERE avd.DetId = :detId ORDER BY a.FullId")
  List<Account> getAccountsByDetId(int detId);

  @Query("SELECT CASE WHEN " +
    "((SELECT COUNT(*) FROM __AccVsDetail__ ad INNER JOIN __DetailAcc__ facc ON (ad.DetId = facc._id AND ad.FPId = facc.FPId ) " +
    "WHERE Necessary <> 0 AND ad.FullId = :fullId) > 0) \n" +
    "OR (((SELECT COUNT(*) FROM __CostCenter__ INNER JOIN __AccVsCC__ ON ( [__CostCenter__]._id = [__AccVsCC__].CCId " +
    "AND  [__CostCenter__].FPId = [__AccVsCC__].FPId ) WHERE [__AccVsCC__].FullId = :fullId) > 0 )) \n" +
    "OR (((SELECT COUNT(*) FROM __Project__ INNER JOIN __AccVsPrj__ ON ( [__Project__]._id = [__AccVsPrj__].PrjId " +
    "AND [__Project__].FPId = [__AccVsPrj__].FPId ) WHERE [__AccVsPrj__].FullId = :fullId) > 0 )) \n" +
    "THEN 1 ELSE 0 END")
  int getIsDependentOnAccount(String fullId);

  @Query("SELECT CASE WHEN " +
    "((SELECT COUNT(*) FROM __CostCenter__ INNER JOIN __AccVsCC__ ON ( [__CostCenter__]._id = [__AccVsCC__].CCId " +
    "AND  [__CostCenter__].FPId = [__AccVsCC__].FPId ) WHERE [__AccVsCC__].FullId = :fullId) > 0 ) \n" +
    "OR (((SELECT COUNT(*) FROM __Project__ INNER JOIN __AccVsPrj__ ON ( [__Project__]._id = [__AccVsPrj__].PrjId " +
    "AND [__Project__].FPId = [__AccVsPrj__].FPId ) WHERE [__AccVsPrj__].FullId = :fullId) > 0 )) \n" +
    "THEN 1 ELSE 0 END")
  int is_CC_Prj_DependentOnAccount(String fullId);

  @Query("SELECT COUNT(*) FROM __Account__ a  WHERE FullId <> '0' AND SType <> -1 AND  FullId NOT IN \n" +
    "(SELECT DISTINCT ParentId FROM __Account__ WHERE FullId <> '0' AND ParentId <> '0')")
  int getCountAccountsRelated();

  @Query("SELECT COUNT(*) FROM __Account__ a INNER JOIN __AccVSDetail__ avd ON ( a.FullId = avd.FullId AND a.FPId = avd.FPId )  \n" +
    "WHERE avd.DetId = :detId")
  int getCountAccountsRelatedDetailAcc(int detId);

  @Query("SELECT COUNT(*) FROM __Account__ a INNER JOIN __AccVSCC__ avc ON ( a.FullId = avc.FullId AND a.FPId = avc.FPId ) \n" +
    "WHERE avc.CCId = :ccId")
  int getCountAccountsRelatedCostCenter(int ccId);

  @Query("SELECT COUNT(*) FROM __Account__ a INNER JOIN __AccVSPrj__ avp ON ( a.FullId = avp.FullId AND a.FPId = avp.FPId )  \n" +
    "WHERE avp.PrjId = :prjId")
  int getCountAccountsRelatedProject(int prjId);
}
