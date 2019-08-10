package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.Cabinet;

import java.util.List;

import androidx.annotation.NonNull;

public interface CabinetRepository {
  // ________________________________________ CRUD ________________________________________ //

  interface GetCabinetsCallback {
    void onCabinetsLoaded(List<Cabinet> cabinets);
    void onDataNotAvailable();
  }
  void getCabinets(@NonNull CabinetRepository.GetCabinetsCallback callback);


  interface GetCabinetCallback {
    void onCabinetLoaded(Cabinet cabinet);
    void onDataNotAvailable();
  }
  void getCabinet(int cabinetId, @NonNull CabinetRepository.GetCabinetCallback callback);

  // Insert

  interface InsertCabinetCallback {
    void onCabinetInserted(long cabinetId);
    void onDataNotAvailable();
  }
  void insertCabinet(Cabinet cabinet, @NonNull CabinetRepository.InsertCabinetCallback callback);

  interface InsertCabinetesCallback {
    void onCabinetsInserted(Long[] longs);
    void onDataNotAvailable();
  }
  void insertCabinetes(List<Cabinet> cabinets, @NonNull CabinetRepository.InsertCabinetesCallback callback);


  // Update

  interface UpdateCabinetCallback {
    void onCabinetUpdated(int i);
    void onDataNotAvailable();
  }
  void updateCabinet(Cabinet cabinet, @NonNull CabinetRepository.UpdateCabinetCallback callback);


  // Delete

  interface DeleteAllCabinetCallback {
    void onCabinetsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllCabinet(@NonNull CabinetRepository.DeleteAllCabinetCallback callback);


  interface DeleteCabinetCallback {
    void onCabinetDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteCabinetById(int cabinetId, @NonNull CabinetRepository.DeleteCabinetCallback callback);

  // ________________________________________ CRUD ________________________________________ //
  // Other Method

  interface GetIdFromCabinetByNameCallback{
    void onCabinetIdLoaded(int cabinetId);
    void onDataNotAvailable();
  }
  void getIdFromCabinetByName(String cabinetName, @NonNull CabinetRepository.GetIdFromCabinetByNameCallback callback);


  interface GetCabinetNameFromCabinetByIdCallback{
    void onCabinetNameLoaded(String cabinetName);
    void onDataNotAvailable();
  }
  void getCabinetNameFromCabinetById(int cabinetId, @NonNull CabinetRepository.GetCabinetNameFromCabinetByIdCallback callback);


  interface GetAllCabinetNameFromCabinetCallback{
    void onCabinetNamesLoaded(List<String> cabinetNames);
    void onDataNotAvailable();
  }
  void getAllCabinetNameFromCabinet(@NonNull CabinetRepository.GetAllCabinetNameFromCabinetCallback callback);


  interface GetAccountIdFromCabinetByIdCallback{
    void onCabinetNamesStockRoomIdLoaded(List<String> cabinetNames);
    void onDataNotAvailable();
  }
  void getAllCabinetNameFromCabinetByStockRoomId(int stockRoomId, @NonNull CabinetRepository.GetAccountIdFromCabinetByIdCallback callback);


  interface GetCabinetIdFromStockRoomIdCallback {
    void onCabinetAccountsLoaded(List<Integer> cabinetIds);
    void onDataNotAvailable();
  }
  void getCabinetsIdFromStockRoomId(int stockRoomId, @NonNull GetCabinetIdFromStockRoomIdCallback callback);


  interface GetAllCabinetFromStockRoomIdCallback {
    void onCabinetsLoaded(List<Cabinet> cabinets);
    void onDataNotAvailable();
  }
  void getAllCabinetFromStockRoomId(int stockRoomId, @NonNull GetAllCabinetFromStockRoomIdCallback callback);


  // Count(*)
  interface GetCountCabinetCallback {
    void onCabinetCounted(int count);
    void onDataNotAvailable();
  }
  void getCountCabinet(@NonNull CabinetRepository.GetCountCabinetCallback callback);
}
