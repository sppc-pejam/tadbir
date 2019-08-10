package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.AccSpAcc;
import com.sppcco.core.data.model.Account;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public interface AccSpAccRepository {

  // ________________________________________ CRUD ________________________________________ //


  interface GetAllAccSpAccCallback {
    void onAllAccSpAccLoaded(List<AccSpAcc> allAccSpAcc);

    void onDataNotAvailable();
  }

  void getAllAccSpAcc(@NonNull GetAllAccSpAccCallback callback);


  interface GetAccSpAccCallback {
    void onAccSpAccLoaded(AccSpAcc AccSpAcc);

    void onDataNotAvailable();
  }

  void getAccSpAccBySpId(int spId, @NonNull GetAccSpAccCallback callback);

  void getAccSpAccByAccId(String fullId, @NonNull GetAccSpAccCallback callback);


  // Insert
  interface InsertAccSpAccsCallback {
    void onAccSpAccsInserted(Long[] longs);

    void onDataNotAvailable();
  }

  void insertAccSpAccs(List<AccSpAcc> AccSpAccs, @NonNull InsertAccSpAccsCallback callback);

  interface InsertAccSpAccCallback {
    void onAccSpAccInserted(long AccSpAccId);

    void onDataNotAvailable();
  }

  void insertAccSpAcc(AccSpAcc AccSpAcc, @NonNull InsertAccSpAccCallback callback);


  // Update

  interface UpdateAccSpAccsCallback {
    void onAccSpAccsUpdated(int i);

    void onDataNotAvailable();
  }

  void updateAccSpAccs(@NonNull UpdateAccSpAccsCallback callback, AccSpAcc... AccSpAccs);


  interface UpdateAccSpAccCallback {
    void onAccSpAccUpdated(int i);

    void onDataNotAvailable();
  }

  void updateAccSpAcc(AccSpAcc AccSpAcc, @NonNull UpdateAccSpAccCallback callback);

  // Delete


  interface DeleteAccSpAccsCallback {
    void onAccSpAccsDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteAccSpAccs(@NonNull DeleteAccSpAccsCallback callback, AccSpAcc... AccSpAccs);


  interface DeleteAllAccSpAccCallback {
    void onAccSpAccsDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteAllAccSpAcc(@NonNull DeleteAllAccSpAccCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  // Count(*)
  interface GetCountAccSpAccCallback {
    void onAccSpAccCounted(int count);

    void onDataNotAvailable();
  }

  void getCountAccSpAcc(@NonNull GetCountAccSpAccCallback callback);

  interface GetAccIdFromSpIdCallback {
    void onAccIdLoaded(String strAccId);

    void onDataNotAvailable();
  }

  void getAccIdFromSpId(int SpId, @NonNull GetAccIdFromSpIdCallback callback);


  interface GetTaxAvarezCallback {
    void onAccIdLoaded(String SpecialOfTaxId, String SpecialOfAvarezId);

    void onDataNotAvailable();
  }
  void getTaxAvarezFromSpId(int SpecialOfTaxId, int SpecialOfAvarezId, @NonNull GetTaxAvarezCallback callback);

  interface GetAvailableSpAccountCallback {
    void onAccIdLoaded(Account account);

    void onDataNotAvailable();
  }
  void getAvailableSpAccount(@NonNull GetAvailableSpAccountCallback callback);
}
