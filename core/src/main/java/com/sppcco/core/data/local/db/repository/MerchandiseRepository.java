package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.Merchandise;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 *
 */

public interface MerchandiseRepository {

  // ________________________________________ CRUD ________________________________________ //

  interface GetMerchandisesCallback {
    void onMerchandisesLoaded(List<Merchandise> merchandises);
    void onDataNotAvailable();
  }
  void getMerchandises(@NonNull GetMerchandisesCallback callback);


  interface GetMerchandiseCallback {
    void onMerchandiseLoaded(Merchandise merchandise);
    void onDataNotAvailable();
  }
  void getMerchandise(int merchandiseId, @NonNull GetMerchandiseCallback callback);
  void getMerchandise(String merchandiseName, @NonNull GetMerchandiseCallback callback);
  void getMerchandiseFromBraCode(String barcode, @NonNull GetMerchandiseCallback callback);


  // Insert
  interface InsertMerchandisesCallback {
    void onMerchandisesInserted(Long[] longs);
    void onDataNotAvailable();
  }
  void insertMerchandises(List<Merchandise> merchandises, @NonNull InsertMerchandisesCallback callback);

  interface InsertMerchandiseCallback {
    void onMerchandiseInserted(long merchandiseId);
    void onDataNotAvailable();
  }
  void insertMerchandise(Merchandise merchandise, @NonNull InsertMerchandiseCallback callback);


  // Update

  interface UpdateMerchandisesCallback {
    void onMerchandisesUpdated(int i);
    void onDataNotAvailable();
  }
  void updateMerchandises(@NonNull UpdateMerchandisesCallback callback, Merchandise... merchandises);


  interface UpdateMerchandiseCallback {
    void onMerchandiseUpdated(int i);
    void onDataNotAvailable();
  }
  void updateMerchandise(Merchandise merchandise, @NonNull UpdateMerchandiseCallback callback);

  // Delete


  interface DeleteMerchandisesCallback {
    void onMerchandisesDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteMerchandises(@NonNull DeleteMerchandisesCallback callback, Merchandise... merchandises);


  interface DeleteAllMerchandiseCallback {
    void onMerchandisesDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllMerchandise(@NonNull DeleteAllMerchandiseCallback callback);


  interface DeleteMerchandiseCallback {
    void onMerchandiseDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteMerchandiseById(int merchandiseId, @NonNull DeleteMerchandiseCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetMerchandiseNamesCallback {
    void onMerchandiseNamesLoaded(String[] merchandiseNames);
    void onDataNotAvailable();
  }
  void getAllMerchandiseName(@NonNull GetMerchandiseNamesCallback callback);

  interface GetMerchandiseCodesCallback {
    void onMerchandiseCodesLoaded(String[] merchandiseCodes);
    void onDataNotAvailable();
  }
  void getAllMerchandiseCode(@NonNull GetMerchandiseCodesCallback callback);

  interface GetMerchandiseIdCallback {
    void onMerchandiseIdLoaded(int merchandiseId);
    void onDataNotAvailable();
  }
  void getMerchandiseIdFromMerchandiseCode(String merchandiseCode, @NonNull GetMerchandiseIdCallback callback);

  interface GetMerchandiseCodeCallback {
    void onMerchandiseCodeLoaded(String merchandiseCode);
    void onDataNotAvailable();
  }
  void getMerchandiseCodeFromMerchandiseName(String merchandiseName, @NonNull GetMerchandiseCodeCallback callback);

  interface GetMerchandiseNameCallback {
    void onMerchandiseNameLoaded(String merchandiseName);
    void onDataNotAvailable();
  }
  void getMerchandiseNameFromMerchandiseCode(String merchandiseCode, @NonNull GetMerchandiseNameCallback callback);

  interface GetMerchandiseUnitIdCallback {
    void onMerchandiseUnitIdLoaded(int merchandiseUnitId);
    void onDataNotAvailable();
  }
  void getMerchandiseUnitIdFromMerchandiseId(int merchandiseId, @NonNull GetMerchandiseUnitIdCallback callback);

  interface GetMerchandiseRelatedStockCallback {
    void onMerchandisesLoaded(List<Merchandise> merchandises);
    void onDataNotAvailable();
  }
  void getMerchandiseRelatedStock(int fpid, int stockId, @NonNull GetMerchandiseRelatedStockCallback callback);

  // Count(*)
  interface GetCountMerchandiseCallback {
    void onMerchandiseCounted(int count);
    void onDataNotAvailable();
  }
  void getCountMerchandise(@NonNull GetCountMerchandiseCallback callback);

}
