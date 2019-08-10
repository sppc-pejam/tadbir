package com.sppcco.core.data.remote.repository;

import com.sppcco.core.enums.ResponseType;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.helperlibrary.converter.DC;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by b_nematzadeh on 8/31/2018.
 */

public class MerchandiseRemoteDataSource implements MerchandiseRemoteRepository {


  @Override
  public Disposable getLogicalMerchInventory(int MerchId, int StockId, int CabinetId,
                                             String startDate, String endDate, int committed,
                                             @NotNull LoadStringArrayCallback callback) {
    final int TOTAL_INV = 0;
    final int STOCK_INV = 1;
    //final int CABINET_INV = 2;

    CompositeDisposable disposable = new CompositeDisposable();

    Observable<ResponseBody> observable = BaseApplication.getApiService().getMerchInventory(
      BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(),
      MerchId, StockId, CabinetId, startDate, endDate, committed,
      BaseApplication.getFPId(), BaseApplication.getApiKey());

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadValidationAccessCallback<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {
          disposable.add(d);
        }

        @Override
        public void onNext(ResponseBody responseBody) {
          try {
            String[] inventory = new String[2];
            String res = responseBody.string();
            if (res != null && !res.matches("null") && !res.matches("")) {
              inventory = DC.jsnToStrArr(res, "TotalInv", "StockInv");

              /*DecimalFormat df = new DecimalFormat("#.##");
              df.setRoundingMode(RoundingMode.CEILING);
              Double total = Double.parseDouble(result[TOTAL_INV]);
              Double stock = Double.parseDouble(result[STOCK_INV]);

              inventory[TOTAL_INV] = df.format(total);
              inventory[STOCK_INV] = df.format(stock);*/
            }
            callback.onResponse(inventory);
          } catch (IOException e) {
            e.printStackTrace();
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          } catch (NullPointerException e) {
            e.printStackTrace();
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }

        @Override
        public void onComplete() {

        }
      });

    return disposable;
  }
}
