package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.MerchTax;

import java.util.Date;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;

@Dao
public interface MerchTaxDao {

  // Get
  @Query("SELECT * FROM __MerchTax__")
  List<MerchTax> getAllMerchTax();


  @Query("SELECT * FROM __MerchTax__ WHERE merchId = :merchId")
  MerchTax getMerchTaxByMerchId(int merchId);

  @Query("SELECT * FROM __MerchTax__ WHERE merchId = :merchId " +
    " AND EffectDate <= :spDate ORDER BY EffectDate DESC LIMIT 1")
  MerchTax getMerchTaxByMerchIdAndSPDate(int merchId, Date spDate);

  // Insert
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertMerchTaxs(List<MerchTax> MerchTaxs);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXMerchTaxs(List<MerchTax> MerchTaxs);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertMerchTax(MerchTax MerchTax);

  // Update
  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateMerchTaxs(MerchTax... MerchTaxs);

  @Update
  int updateMerchTax(MerchTax MerchTax);

  // Delete
  @Delete
  int deleteMerchTaxs(MerchTax... MerchTaxs);

  @Query("DELETE FROM __MerchTax__")
  int deleteAllMerchTax();

  @Query("SELECT COUNT(*) FROM __MerchTax__")
  int getCountMerchTax();

  @Query("SELECT COUNT(*) FROM __MerchTax__ WHERE merchId = :merchId " +
    " AND EffectDate <= :spDate ORDER BY EffectDate DESC LIMIT 1")
  int getCountMerchTaxByMerchIdAndSPDate(int merchId, Date spDate);

}
