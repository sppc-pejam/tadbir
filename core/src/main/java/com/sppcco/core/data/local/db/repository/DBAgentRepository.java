package com.sppcco.core.data.local.db.repository;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public interface DBAgentRepository {

  interface TablesClearCallback {
    void onTablesCleared();
  }
  void clearAllTables(@NonNull TablesClearCallback callback);

}
