package com.sppcco.core.data.remote.repository;

import com.sppcco.core.enums.ResponseType;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.Disposable;

/**
 * Created by b_nematzadeh on 8/31/2018.
 */

public interface MerchandiseRemoteRepository {

  interface LoadStringArrayCallback {
    void onResponse(String... response);

    void onFailure(ResponseType responseType);
  }

  Disposable getLogicalMerchInventory(int MerchId, int StockId, int CabinetId,
                                      String startDate, String endDate, int commited,
                                      @NotNull LoadStringArrayCallback callback);
}
