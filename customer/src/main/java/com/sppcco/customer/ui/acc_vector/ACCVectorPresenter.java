package com.sppcco.customer.ui.acc_vector;

import com.sppcco.core.data.local.db.repository.DetailAccRepository;
import com.sppcco.core.data.sub_model.DetailAccVectorInfo;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.core.listener.StringResponseListener;

import androidx.annotation.NonNull;

public class ACCVectorPresenter extends BasePresenter implements ACCVectorContract.Presenter {

  private final ACCVectorContract.View mView;

  private ACCVectorPresenter(@NonNull ACCVectorContract.View view) {
    mView = view;
    mView.setPresenter(this);
  }


  static ACCVectorContract.Presenter getAccountVectorPresenterInstance(@NonNull ACCVectorContract.View view) {
    return new ACCVectorPresenter(view);
  }

  @Override
  public void start() {
    updateVector();
  }

  @Override
  public void destroy() {
  }

  private void updateVector() {
    int detId;

    detId = mView.getACCVector().getDetailAcc().getId();
    if (detId != 0) {
      getDetailAccVectorInfoById(detId, new StringResponseListener() {
        @Override
        public void onSuccess(String detCode) {
          mView.updateView(detCode);
        }

        @Override
        public void onFailure() {
          mView.updateView( "*");
        }
      });
    } else {
      mView.updateView( "0");
    }
  }

  private void getDetailAccVectorInfoById(int detId, StringResponseListener listener) {
    getCoreDB().detailAccRepository().getDetailAccVectorInfoById(detId,
      new DetailAccRepository.GetDetailAccVectorInfoByIdCallback() {
        @Override
        public void onVectorInfo(DetailAccVectorInfo vectorInfo) {
          if (vectorInfo != null)
            listener.onSuccess(vectorInfo.getCode());
          else
            listener.onSuccess(null);
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }
}
