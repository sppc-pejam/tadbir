package com.sppcco.core.data.remote.control;

import com.sppcco.core.data.remote.repository.MerchandiseRemoteRepository;

import io.reactivex.disposables.Disposable;

/**
 * Created by b_nematzadeh on 8/31/2018.
 */

public interface MerchandiseRemoteControlRepository {

  Disposable getLogicalMerchInventory(int merchId, int stockId, int cabinetId,
                                      String startDate, String endDate, int committed,
                                      MerchandiseRemoteRepository.LoadStringArrayCallback callback);
}
