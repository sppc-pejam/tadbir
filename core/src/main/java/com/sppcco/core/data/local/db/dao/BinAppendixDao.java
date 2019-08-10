package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.BinAppendix;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BinAppendixDao {

  @Query("SELECT * FROM __BinAppendix__")
  List<BinAppendix> getAllBinAppendix();


  @Query("SELECT * FROM __BinAppendix__ WHERE ObjectId = :merchId")
  BinAppendix getBinAppendixByMerchId(int merchId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertBinAppendices(List<BinAppendix> binAppendices);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertBinAppendix(BinAppendix binAppendix);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateBinAppendices(BinAppendix... binAppendices);

  @Update
  int updateBinAppendix(BinAppendix binAppendix);

  // Delete

  @Delete
  int deleteBinAppendices(BinAppendix... binAppendices);

  @Query("DELETE FROM __BinAppendix__")
  int deleteAllBinAppendix();

  @Query("DELETE FROM __BinAppendix__ WHERE ObjectId = :merchId")
  int deleteBinAppendixByMerchId(int merchId);

  // Other Methods
  @Query("SELECT COUNT(*) FROM __BinAppendix__")
  int getCountBinAppendix();
}
