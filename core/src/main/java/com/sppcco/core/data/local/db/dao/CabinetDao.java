package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.Cabinet;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;

@Dao
public interface CabinetDao {
  // Get
  @Query("SELECT * FROM __Cabinet__")
  List<Cabinet> getAllCabinet();

  @Query("SELECT * FROM __Cabinet__ WHERE _id = :cabinetId")
  Cabinet getCabinetById(int cabinetId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertCabinets(List<Cabinet> cabinets);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXCabinets(List<Cabinet> cabinets);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertCabinet(Cabinet cabinet);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateCabinets(Cabinet... cabinets);

  @Update
  int updateCabinet(Cabinet cabinet);

  // Delete

  @Delete
  int deleteCabinets(Cabinet... cabinets);

  @Query("DELETE FROM __Cabinet__")
  int deleteAllCabinet();

  @Query("DELETE FROM __Cabinet__ WHERE _id = :cabinetId")
  int deleteCabinetById(int cabinetId);

  // Other Method
  @Query("SELECT _id FROM __Cabinet__ WHERE Name = :cabinetName" )
  int getIdFromCabinetByName(String cabinetName);

  @Query("SELECT Name FROM __Cabinet__ WHERE _id = :cabinetId" )
  String getCabinetNameFromCabinetById(int cabinetId);

  @Query("SELECT Name FROM __Cabinet__" )
  List<String> getAllCabinetNameFromCabinet();

  @Query("SELECT c.Name FROM __Cabinet__ c INNER JOIN __StockRoom__ s "+
    "ON c.StockRoomId = s._id AND c.FPId = s.FPId "+
    "WHERE c._id<>0 AND s._id<>0 AND c.StockRoomId = :stockRoomId" )
  List<String> getAllCabinetNameFromCabinetByStockRoomId(int stockRoomId);

  @Query("SELECT c._id FROM __Cabinet__ c INNER JOIN __StockRoom__ s "+
    "ON c.StockRoomId = s._id AND c.FPId = s.FPId "+
    "WHERE c._id<>0 AND s._id<>0 AND c.StockRoomId = :stockRoomId" )
  List<Integer> getCabinetsIdFromStockRoomId(int stockRoomId);

  @Query("SELECT c.* FROM __Cabinet__ c INNER JOIN __StockRoom__ s "+
    "ON c.StockRoomId = s._id AND c.FPId = s.FPId "+
    "WHERE c._id<>0 AND s._id<>0 AND c.StockRoomId = :stockRoomId" )
  List<Cabinet> getAllCabinetFromStockRoomId(int stockRoomId);

  @Query("SELECT COUNT(*) FROM __Cabinet__")
  int getCountCabinet();
}
