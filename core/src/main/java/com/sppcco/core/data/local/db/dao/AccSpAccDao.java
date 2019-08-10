package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.AccSpAcc;
import com.sppcco.core.data.model.Account;

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
public interface AccSpAccDao {

  // Get

  @Query("SELECT * FROM __AccSpAcc__")
  List<AccSpAcc> getAllAccSpAcc();


  @Query("SELECT * FROM __AccSpAcc__ WHERE SpId = :SpId")
  AccSpAcc getAccSpAccBySpId(int SpId);

  @Query("SELECT * FROM __AccSpAcc__ WHERE AccId = :accId")
  AccSpAcc getAccSpAccByAccId(String accId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertAccSpAccs(List<AccSpAcc> AccSpAccs);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXAccSpAccs(List<AccSpAcc> AccSpAccs);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertAccSpAcc(AccSpAcc AccSpAcc);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateAccSpAccs(AccSpAcc... AccSpAccs);

  @Update
  int updateAccSpAcc(AccSpAcc AccSpAcc);

  // Delete

  @Delete
  int deleteAccSpAccs(AccSpAcc... AccSpAccs);

  @Query("DELETE FROM __AccSpAcc__")
  int deleteAllAccSpAcc();

  // Other Methods

  @Query("SELECT COUNT(*) FROM __AccSpAcc__")
  int getCountAccSpAcc();

  @Query("SELECT AccId FROM __AccSpAcc__ WHERE SpId = :SpecialId")
  String getAccIdFromSpId(int SpecialId);


  class TaxAvarez {

    @ColumnInfo(name = "taxAccId")
    public String taxAccId;
    @ColumnInfo(name = "avarezAccId")
    public String avarezAccId;
  }

  @Query("SELECT (SELECT AccId FROM __AccSpAcc__ WHERE SpId=:SpecialOfTaxId) AS taxAccId, " +
    "(SELECT AccId FROM __AccSpAcc__ WHERE SpId = :SpecialOfAvarezId) AS avarezAccId")
  TaxAvarez getCTaxAvarezFromSpId(int SpecialOfTaxId, int SpecialOfAvarezId);

  // 18 : فروشنده / خریدار متفرقه
  @Query("SELECT a.* FROM __AccSpAcc__ sp INNER JOIN __Account__ a ON sp.AccId = a.FullId WHERE SpId = 18")
  Account getAvailableSpAccount();

}
