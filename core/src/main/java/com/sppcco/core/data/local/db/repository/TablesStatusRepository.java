package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.TablesStatus;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/24/18.
 */

public interface TablesStatusRepository {

  // ________________________________________ CRUD ________________________________________ //


  interface GetTablesStatussCallback {
    void onTablesStatussLoaded(List<TablesStatus> tablesStatuss);
    void onDataNotAvailable();
  }

  void getTablesStatuss(@NonNull GetTablesStatussCallback callback);


  interface GetTablesStatusCallback {
    void onTablesStatusLoaded(TablesStatus tablesStatus);
    void onDataNotAvailable();
  }

  void getTablesStatus(int tablesStatusId, @NonNull GetTablesStatusCallback callback);


  // Insert
  interface InsertTablesStatussCallback {
    void onTablesStatussInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertTablesStatuss(List<TablesStatus> tablesStatuss, @NonNull InsertTablesStatussCallback callback);

  interface InsertTablesStatusCallback {
    void onTablesStatusInserted(long tablesStatusId);
    void onDataNotAvailable();
  }

  void insertTablesStatus(TablesStatus tablesStatus, @NonNull InsertTablesStatusCallback callback);


  // Update

  interface UpdateTablesStatussCallback {
    void onTablesStatussUpdated(int i);
    void onDataNotAvailable();
  }

  void updateTablesStatuss(@NonNull UpdateTablesStatussCallback callback, TablesStatus... tablesStatuss);


  interface UpdateTablesStatusCallback {
    void onTablesStatusUpdated(int i);
    void onDataNotAvailable();
  }

  void updateTablesStatus(TablesStatus tablesStatus, @NonNull UpdateTablesStatusCallback callback);

  // Delete


  interface DeleteTablesStatussCallback {
    void onTablesStatussDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteTablesStatuss(@NonNull DeleteTablesStatussCallback callback, TablesStatus... tablesStatuss);


  interface DeleteAllTablesStatusCallback {
    void onTablesStatussDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllTablesStatus(@NonNull DeleteAllTablesStatusCallback callback);


  interface DeleteTablesStatusCallback {
    void onTablesStatusDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteTablesStatusById(int tablesStatusId, @NonNull DeleteTablesStatusCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method
  interface UpdateLastSyncTableStatusCallback {
    void onTablesStatusSync(int i);
    void onDataNotAvailable();
  }
  void updateLastSyncTablesStatus(String tableName,
                                  String lastUpdate,
                                  int FPId,
                                  int lastUpdateRowCount,
                                  @NonNull UpdateLastSyncTableStatusCallback callback);

  interface GetCountUnsendDataCallback {
    void onCountUnsendData(int i);
    void onDataNotAvailable();
  }
  void getCountUnsendData(@NonNull GetCountUnsendDataCallback callback);
}
