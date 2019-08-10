package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.StockRoom;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;

@Dao
public interface StockRoomDao {

  // Get
  @Query("SELECT * FROM __StockRoom__")
  List<StockRoom> getAllStockRoom();

  @Query("SELECT * FROM __StockRoom__ WHERE _id = :stockRoomId")
  StockRoom getStockRoomById(int stockRoomId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertStockRooms(List<StockRoom> stockRooms);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXStockRooms(List<StockRoom> stockRooms);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertStockRoom(StockRoom stockRoom);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateStockRooms(StockRoom... stockRooms);

  @Update
  int updateStockRoom(StockRoom stockRoom);

  // Delete

  @Delete
  int deleteStockRooms(StockRoom... stockRooms);

  @Query("DELETE FROM __StockRoom__")
  int deleteAllStockRoom();

  @Query("DELETE FROM __StockRoom__ WHERE _id = :stockRoomId")
  int deleteStockRoomById(int stockRoomId);

  // Other Method
  @Query("SELECT _id FROM __StockRoom__ WHERE Name = :sockName" )
  int getStockIdFromStockRoomByName(String sockName);

  @Query("SELECT Name FROM __StockRoom__ WHERE _id = :stockRoomId" )
  String getStockNameFromStockRoomById(int stockRoomId);

  @Query("SELECT Name FROM __StockRoom__" )
  List<String> getAllStockNameFromStockRoom();

  @Query("SELECT AccountId FROM __StockRoom__ WHERE _id = :stockRoomId" )
  String getAccountIdFromStockRoomById(int stockRoomId);

  @Query("SELECT AccountId FROM __StockRoom__" )
  List<String> getAllAccountIdFromStockRoom();

  @Query("SELECT COUNT(*) FROM __StockRoom__ Where _id <> 0")
  int getCountStockRoom();
}
