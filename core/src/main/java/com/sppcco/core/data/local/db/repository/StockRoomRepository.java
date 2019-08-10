package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.StockRoom;

import java.util.List;

import androidx.annotation.NonNull;

public interface StockRoomRepository {

  // ________________________________________ CRUD ________________________________________ //

  interface GetStockRoomsCallback {
    void onStockRoomsLoaded(List<StockRoom> stockRooms);
    void onDataNotAvailable();
  }
  void getStockRooms(@NonNull StockRoomRepository.GetStockRoomsCallback callback);


  interface GetStockRoomCallback {
    void onStockRoomLoaded(StockRoom stockRoom);
    void onDataNotAvailable();
  }
  void getStockRoom(int stockRoomId, @NonNull StockRoomRepository.GetStockRoomCallback callback);

  // Insert

  interface InsertStockRoomCallback {
    void onStockRoomInserted(long stockRoomId);
    void onDataNotAvailable();
  }
  void insertStockRoom(StockRoom stockRoom, @NonNull StockRoomRepository.InsertStockRoomCallback callback);

  interface InsertStockRoomesCallback {
    void onStockRoomesInserted(Long[] longs);
    void onDataNotAvailable();
  }
  void insertStockRoomes(List<StockRoom> stockRooms, @NonNull StockRoomRepository.InsertStockRoomesCallback callback);


  // Update

  interface UpdateStockRoomCallback {
    void onStockRoomUpdated(int i);
    void onDataNotAvailable();
  }
  void updateStockRoom(StockRoom stockRoom, @NonNull StockRoomRepository.UpdateStockRoomCallback callback);


  // Delete

  interface DeleteAllStockRoomCallback {
    void onStockRoomsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllStockRoom(@NonNull StockRoomRepository.DeleteAllStockRoomCallback callback);


  interface DeleteStockRoomCallback {
    void onStockRoomDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteStockRoomById(int stockRoomId, @NonNull StockRoomRepository.DeleteStockRoomCallback callback);

  // ________________________________________ CRUD ________________________________________ //
  // Other Method

  interface GetStockIdFromStockRoomByNameCallback{
    void onStockRoomIdLoaded(int stockId);
    void onDataNotAvailable();
  }
  void getStockIdFromStockRoomByName(String sockName, @NonNull StockRoomRepository.GetStockIdFromStockRoomByNameCallback callback);


  interface GetStockNameFromStockRoomByIdCallback{
    void onStockRoomNameLoaded(String stockName);
    void onDataNotAvailable();
  }
  void getStockNameFromStockRoomById(int stockRoomId, @NonNull StockRoomRepository.GetStockNameFromStockRoomByIdCallback callback);


  interface GetAllStockNameFromStockRoomCallback{
    void onStockRoomNamesLoaded(List<String> stockNames);
    void onDataNotAvailable();
  }
  void getAllStockNameFromStockRoom(@NonNull StockRoomRepository.GetAllStockNameFromStockRoomCallback callback);


  interface GetAccountIdFromStockRoomByIdCallback{
    void onStockRoomAccountIdLoaded(String accountId);
    void onDataNotAvailable();
  }
  void getAccountIdFromStockRoomById(int stockRoomId, @NonNull StockRoomRepository.GetAccountIdFromStockRoomByIdCallback callback);


  interface GetAllAccountIdFromStockRoomCallback{
    void onStockRoomAccountsLoaded(List<String> accountIds);
    void onDataNotAvailable();
  }
  void getAllAccountIdFromStockRoom(@NonNull StockRoomRepository.GetAllAccountIdFromStockRoomCallback callback);


  // Count(*)
  interface GetCountStockRoomCallback {
    void onStockRoomCounted(int count);
    void onDataNotAvailable();
  }
  void getCountStockRoom(@NonNull StockRoomRepository.GetCountStockRoomCallback callback);
}
