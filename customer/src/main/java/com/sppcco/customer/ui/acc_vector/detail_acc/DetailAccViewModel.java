package com.sppcco.customer.ui.acc_vector.detail_acc;

import com.sppcco.core.data.sub_model.DetailAccVectorInfo;
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

public class DetailAccViewModel extends BaseViewModel implements DetailAccContract.ViewModel {

  private DetailAccContract.View mView;
  private DetailAccContract.Presenter mPresenter;

  private LiveData<PagedList<DetailAccVectorInfo>> detailAccLiveData = null;
  private MutableLiveData<SupportSQLiteQuery> allDetailAccMutable = new MutableLiveData<>();

  @Override
  public void setView(DetailAccContract.View view) {
    mView = view;
  }

  @Override
  public void setPresenter(DetailAccContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @SuppressWarnings("unchecked")
  void ViewModelObserveProviders() {

    setDetailAccList(Transformations.switchMap(allDetailAccMutable,
      input -> {
        setDetailAccList(new LivePagedListBuilder(getDataSource(input), getPageConfig()).build());
        return getDetailAccList();
      })
    );
  }

  public void initData() {
    SimpleSQLiteQuery query;

    if ((mView.getRoot() == AccountTree.DETAIL_ACC && mView.getACCVector().getDetailAcc().getId() != 0) ||
      (mView.getRoot() == AccountTree.ACCOUNT && mView.getACCVector().getAccount().getId() != 0 && mView.getACCVector().getDetailAcc().getId() != 0)) {
      query = getCoreDB().getQueryGenerator().getAllDetailAccById(mView.getACCVector().getDetailAcc().getId());

    } else if (mView.getRoot() == AccountTree.DETAIL_ACC &&
      (mView.getACCVector().getDetailAcc() == null || mView.getACCVector().getDetailAcc().getId() == 0)) {
      query = getCoreDB().getQueryGenerator().getAllDetailAcc(mView.getFilter(), mView.getSortPosition());
    } else {
      query = getCoreDB().getQueryGenerator().getAllDetailAccByFullId(
        mView.getACCVector().getAccount().getFullId(), mView.getFilter(), mView.getSortPosition());

    }

    allDetailAccMutable.postValue(query);
  }

  private DataSource.Factory getDataSource(SupportSQLiteQuery input) {
    switch (mView.getRoot()) {

      case DETAIL_ACC:
        if (mView.getACCVector().getDetailAcc().getId() == 0)
          return getCoreDB().accVectorInfoDao().getAllDetailAcc(input);
        else
          return getCoreDB().accVectorInfoDao().getAllDetailAccById(input);

      case ACCOUNT:
        if (mView.getACCVector().getAccount().getId() != 0)
          return getCoreDB().accVectorInfoDao().getAllDetailAccByFullId(input);
        else
          return getCoreDB().accVectorInfoDao().getAllDetailAcc(input);

      default:
        return getCoreDB().accVectorInfoDao().getAllDetailAccByFullId(input);
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

  LiveData<PagedList<DetailAccVectorInfo>> getDetailAccList() {
    return detailAccLiveData;
  }

  private void setDetailAccList(LiveData<PagedList<DetailAccVectorInfo>> detailAccLiveData) {
    this.detailAccLiveData = detailAccLiveData;
  }
}
