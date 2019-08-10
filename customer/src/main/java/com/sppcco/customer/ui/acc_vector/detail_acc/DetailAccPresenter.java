package com.sppcco.customer.ui.acc_vector.detail_acc;

import com.sppcco.core.data.local.db.repository.DetailAccRepository;
import com.sppcco.core.data.model.DetailAcc;
import com.sppcco.core.data.sub_model.DetailAccVectorInfo;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.customer.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class DetailAccPresenter extends BasePresenter implements DetailAccContract.Presenter {

  private static DetailAccPresenter INSTANCE;
  private final DetailAccContract.View mView;

  private List<String> mSortList;

  private DetailAccPresenter(@NonNull DetailAccContract.View view) {
    mView = view;
    mView.setPresenter(this);
  }


  static DetailAccContract.Presenter getDetailAccPresenterInstance(
    @NonNull DetailAccContract.View view) {
    if (INSTANCE == null) {
      INSTANCE = new DetailAccPresenter(view);
    }
    return INSTANCE;
  }

  @Override
  public void destroy() {
    INSTANCE = null;
  }

  @Override
  public void start() {
    loadSortList();
  }

  @Override
  public void prepareListData() {
    getCountDetailAccByFullId();
  }

  private void getCountDetailAccByFullId() {
    getCoreDB().detailAccRepository().getCountDetailAccByFullId(
      mView.getTempACCVector().getAccount().getFullId(),
      new DetailAccRepository.GetCountDetailAccByFullIdCallback() {
        @Override
        public void onDetailAccCounted(int count) {
          initData(count);
        }

        @Override
        public void onDataNotAvailable() {
        }
      });
  }

  private void initData(int count) {
    if (count > 0) {
      mView.initData();
      mView.initLayout();
    } else
      mView.postValue();
  }

  @Override
  public void setDetailAcc(DetailAccVectorInfo vectorInfo) {
    String faccId = vectorInfo.getParentAccount();
    String faccFullCode = vectorInfo.getCode();

    if (faccFullCode.isEmpty() && faccId.isEmpty()) {
      mView.errorMessage();
    } else {
      getFAccCode(vectorInfo);
    }
  }

  private void getFAccCode(DetailAccVectorInfo vectorInfo) {
    getCoreDB().detailAccRepository().getFAccCode(Integer.valueOf(vectorInfo.getAccLevel()),
      new DetailAccRepository.GetFAccCodeCallback() {
        @Override
        public void onFAccCode(String faccCode) {
          String FAccCode = (vectorInfo.getCode().isEmpty() || vectorInfo.getCode().matches("0")) ? faccCode : vectorInfo.getCode();
          isFAccInLeafLevel(vectorInfo, FAccCode.replace(" ", ""));
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  private void isFAccInLeafLevel(DetailAccVectorInfo accVectorInfo, String faccCode) {
    getCoreDB().detailAccRepository().isFAccInLeafLevel(faccCode,
      new DetailAccRepository.GetIsFAccInLeafLevelCallback() {
        @Override
        public void onCountFAccInLeafLevel(int inLeafLevel) {
          if (inLeafLevel <= 1) {
            setDetailAccInAccVector(accVectorInfo);
          } else {
            mView.errorMessage();
          }
        }

        @Override
        public void onDataNotAvailable() {

        }
      });

  }

  private void setDetailAccInAccVector(DetailAccVectorInfo accVectorInfo) {
    getCoreDB().detailAccRepository().getDetailAcc(
      Integer.valueOf(accVectorInfo.getParentAccount()),
      new DetailAccRepository.GetDetailAccCallback() {
        @Override
        public void onDetailAccLoaded(DetailAcc detailAcc) {
          mView.nextStep(accVectorInfo);
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  private void loadSortList() {
    if (getSortList() != null)
      return;

    mSortList = new ArrayList<>();
    mSortList.add(BaseApplication.getResourceString(R.string.cpt_detail_acc_code));
    mSortList.add(BaseApplication.getResourceString(R.string.cpt_detail_acc_name));
  }

  @Override
  public List<String> getSortList() {
    return mSortList;
  }
}