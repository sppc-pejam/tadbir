package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.sub_model.CustomerAcc;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 11/02/18.
 */

public interface CustomerAccRepository {

  interface GetCustomerAccCallback {
    void onCustomerAcc(CustomerAcc customerAcc);

    void onDataNotAvailable();
  }
  void getCustomerAcc(String fullId, int detId, int ccId, int prjId,
                      @NonNull GetCustomerAccCallback callback);
}
