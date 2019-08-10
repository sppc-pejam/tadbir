package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.MerchStock;

import java.util.List;

import androidx.annotation.NonNull;

public interface MerchStockRepository {
  // ________________________________________ CRUD ________________________________________ //

  interface GetMerchStocksCallback {
    void onMerchStocksLoaded(List<MerchStock> merchStocks);
    void onDataNotAvailable();
  }
  void getAllMerchStock(@NonNull MerchStockRepository.GetMerchStocksCallback callback);


  interface GetMerchStockByIdCallback {
    void onMerchStockLoaded(List<MerchStock> merchandise);
    void onDataNotAvailable();
  }
  void getMerchStockByMerchId(int merchId, @NonNull MerchStockRepository.GetMerchStockByIdCallback callback);
  void getMerchStockByStockId(int stockId, @NonNull MerchStockRepository.GetMerchStockByIdCallback callback);


  // Insert
  interface InsertMerchStocksCallback {
    void onMerchStocksInserted(Long[] longs);
    void onDataNotAvailable();
  }
  void insertMerchStocks(List<MerchStock> merchStocks, @NonNull MerchStockRepository.InsertMerchStocksCallback callback);

  interface InsertMerchStockCallback {
    void onMerchStockInserted(long merchandiseId);
    void onDataNotAvailable();
  }
  void insertMerchStock(MerchStock merchandise, @NonNull MerchStockRepository.InsertMerchStockCallback callback);


  // Update
  interface UpdateMerchStocksCallback {
    void onMerchStocksUpdated(int i);
    void onDataNotAvailable();
  }
  void updateMerchStocks(@NonNull MerchStockRepository.UpdateMerchStocksCallback callback, MerchStock... merchStocks);


  interface UpdateMerchStockCallback {
    void onMerchStockUpdated(int i);
    void onDataNotAvailable();
  }
  void updateMerchStock(MerchStock merchStock, @NonNull MerchStockRepository.UpdateMerchStockCallback callback);

  // Delete
  interface DeleteMerchStocksCallback {
    void onMerchStocksDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteMerchStocks(@NonNull MerchStockRepository.DeleteMerchStocksCallback callback, MerchStock... merchStocks);


  interface DeleteAllMerchStockCallback {
    void onMerchStocksDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllMerchStock(@NonNull MerchStockRepository.DeleteAllMerchStockCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Count(*)
  interface GetCountMerchStockCallback {
    void onMerchStockCounted(int count);
    void onDataNotAvailable();
  }
  void getCountMerchStock(@NonNull MerchStockRepository.GetCountMerchStockCallback callback);
}
