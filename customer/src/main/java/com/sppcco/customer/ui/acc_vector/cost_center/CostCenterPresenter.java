package com.sppcco.customer.ui.acc_vector.cost_center;



import com.sppcco.core.data.local.db.repository.CostCenterRepository;
import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.customer.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;


public class CostCenterPresenter extends BasePresenter implements CostCenterContract.Presenter {

  private static CostCenterPresenter INSTANCE;
  private final CostCenterContract.View mView;

  private List<String> mSortList;

  private CostCenterPresenter(@NonNull CostCenterContract.View view) {
    mView = view;
    mView.setPresenter(this);
  }


  static CostCenterContract.Presenter getCostCenterPresenterInstance(
    @NonNull CostCenterContract.View view) {
    if (INSTANCE == null) {
      INSTANCE = new CostCenterPresenter(view);
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
    getCountCostCenterByFullId();
  }

  private void getCountCostCenterByFullId() {

    getCoreDB().costCenterRepository().getCountCostCenterByFullId(
      mView.getACCVector().getAccount().getFullId(),
      new CostCenterRepository.GetCountCostCenterByFullIdCallback() {
        @Override
        public void onAccountsCounted(int count) {
          if (count != 0) {
            mView.initData();
            mView.initLayout();
          } else {
            mView.skipNextStep();
          }
        }

        @Override
        public void onDataNotAvailable() {

        }
      });

  }

  @Override
  public void setCostCenter(AccVectorInfo data) {
    getCoreDB().costCenterRepository().getCostCenterById(Integer.valueOf(data.getCode()),
      new CostCenterRepository.GetCostCenterByIdCallback() {
        @Override
        public void onCostCenterLoaded(CostCenter costCenter) {
          mView.getTempACCVector().setCostCenter(costCenter);
          mView.nextStep(data);
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
    mSortList.add(BaseApplication.getResourceString(R.string.cpt_code));
    mSortList.add(BaseApplication.getResourceString(R.string.cpt_name));
  }

  @Override
  public List<String> getSortList() {
    return mSortList;
  }
}