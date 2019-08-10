package com.sppcco.customer.ui.acc_vector.cost_center;

import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.core.framework.view_model.BaseViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

public class CostCenterViewModel extends BaseViewModel implements CostCenterContract.ViewModel {

  private CostCenterContract.View mView;
  private CostCenterContract.Presenter mPresenter;

  private LiveData<PagedList<AccVectorInfo>> costCenterLiveData = null;
  private MutableLiveData<SupportSQLiteQuery> allCostCenterMutable = new MutableLiveData<>();

  @Override
  public void setView(CostCenterContract.View view) {
    mView = view;
  }

  @Override
  public void setPresenter(CostCenterContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @SuppressWarnings("unchecked")
  void ViewModelObserveProviders() {

    setCostCenterList(Transformations.switchMap(allCostCenterMutable,
      input -> {
        setCostCenterList(new LivePagedListBuilder(getDataSource(input), getPageConfig()).build());
        return getCostCenterList();
      })
    );
  }

  public void initData() {

    SimpleSQLiteQuery query;

    if (mView.getRoot() == AccountTree.COST_CENTER) {
      if (mView.getACCVector().getCostCenter().getCCCode() != 0)
        query = getCoreDB().getQueryGenerator().getCostCenterByCode(mView.getACCVector().getCostCenter().getCCCode());
      else
        query = getCoreDB().getQueryGenerator().getAllCostCenter(mView.getFilter(), mView.getSortPosition());

    } else {
      query = getCoreDB().getQueryGenerator().getAllCostCenterByFullId(
        mView.getACCVector().getAccount().getFullId(), mView.getFilter(), mView.getSortPosition());

    }

    allCostCenterMutable.postValue(query);
  }

  private DataSource.Factory getDataSource(SupportSQLiteQuery input) {
    switch (mView.getRoot()) {

      case DETAIL_ACC:
        if (mView.getACCVector().getCostCenter().getCCCode() != 0)
          return getCoreDB().accVectorInfoDao().getCostCenterByCode(input);
        else
          return getCoreDB().accVectorInfoDao().getAllCostCenter(input);

      default:
        return getCoreDB().accVectorInfoDao().getAllCostCenterByFullId(input);
    }
  }

  private PagedList.Config getPageConfig() {
    int PAGE_SIZE = 10;
    return new PagedList.Config.Builder()
      .setPageSize(PAGE_SIZE)
      .setPrefetchDistance(PAGE_SIZE / 2)
      .setEnablePlaceholders(false)
      .build();
  }

  LiveData<PagedList<AccVectorInfo>> getCostCenterList() {
    return costCenterLiveData;
  }

  private void setCostCenterList(LiveData<PagedList<AccVectorInfo>> costCenterLiveData) {

    this.costCenterLiveData = costCenterLiveData;
  }
}
