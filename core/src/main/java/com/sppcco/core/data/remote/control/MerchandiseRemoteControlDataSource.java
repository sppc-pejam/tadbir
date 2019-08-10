package com.sppcco.core.data.remote.control;

import com.sppcco.core.data.remote.repository.MerchandiseRemoteRepository;
import com.sppcco.core.framework.application.BaseApplication;


import io.reactivex.disposables.Disposable;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by b_nematzadeh on 8/31/2018.
 */

public class MerchandiseRemoteControlDataSource implements MerchandiseRemoteControlRepository {


  @Override
  public Disposable getLogicalMerchInventory(int merchId, int stockId, int cabinetId, String startDate, String endDate, int committed,
                                             MerchandiseRemoteRepository.LoadStringArrayCallback callback) {
    checkNotNull(callback);
    return BaseApplication.getCoreNetComponent().merchandiseRemoteRepository().getLogicalMerchInventory(merchId, stockId, cabinetId, startDate, endDate, committed, callback);
  }
}
