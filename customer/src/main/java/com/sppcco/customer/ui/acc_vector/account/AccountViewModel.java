package com.sppcco.customer.ui.acc_vector.account;



import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.framework.view_model.BaseViewModel;
import com.sppcco.core.enums.AccountTree;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;


public class AccountViewModel extends BaseViewModel implements AccountContract.ViewModel {

  private AccountContract.View mView;
  private AccountContract.Presenter mPresenter;

  private LiveData<PagedList<AccVectorInfo>> accountLiveData = null;
  private MutableLiveData<SupportSQLiteQuery> allAccountMutable = new MutableLiveData<>();

  @Override
  public void setView(AccountContract.View view) {
    mView = view;
  }

  @Override
  public void setPresenter(AccountContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @SuppressWarnings("unchecked")
  void ViewModelObserveProviders() {

    setAccountList(Transformations.switchMap(allAccountMutable,
      input -> {
        setAccountList(new LivePagedListBuilder(getDataSource(input), getPageConfig()).build());
        return getAccountList();
      })
    );
  }

  public void initData() {
    SimpleSQLiteQuery query = new SimpleSQLiteQuery(null);

    if (mView.getRoot() == AccountTree.ACCOUNT) {
      if (mView.getACCVector().getAccount().getId() != 0)
        query = getCoreDB().getQueryGenerator().getAccountByFullId(
          mView.getACCVector().getAccount().getFullId());
      else
        query = getCoreDB().getQueryGenerator().getAllAccount(
          mView.getFilter(), mView.getSortPosition());

    } else if (mView.getRoot() == AccountTree.DETAIL_ACC) {
      query = getCoreDB().getQueryGenerator().getAllAccountByDetId(
        mView.getACCVector().getDetailAcc().getId()
        , mView.getFilter(), mView.getSortPosition());

    } else if (mView.getRoot() == AccountTree.COST_CENTER) {
      query = getCoreDB().getQueryGenerator().getAllAccountByCCId(
        mView.getACCVector().getCostCenter().getCCCode()
        , mView.getFilter(), mView.getSortPosition());

    } else if (mView.getRoot() == AccountTree.PROJECT) {
      query = getCoreDB().getQueryGenerator().getAllAccountByPrjId(
        mView.getACCVector().getProject().getPCode()
        , mView.getFilter(), mView.getSortPosition());

    }

    allAccountMutable.postValue(query);
  }

  private DataSource.Factory getDataSource(SupportSQLiteQuery input) {
    switch (mView.getRoot()) {

      case ACCOUNT:
        if (mView.getACCVector().getAccount().getId() != 0)
          return getCoreDB().accVectorInfoDao().getAccountByFullId(input);
        else
          return getCoreDB().accVectorInfoDao().getAllAccount(input);

      case DETAIL_ACC:
        return getCoreDB().accVectorInfoDao().getAllAccountByDetId(input);

      case COST_CENTER:
        return getCoreDB().accVectorInfoDao().getAllAccountByCCId(input);

      case PROJECT:
        return getCoreDB().accVectorInfoDao().getAllAccountByPrjId(input);

      default:
        return getCoreDB().accVectorInfoDao().getAllAccount(input);
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

  LiveData<PagedList<AccVectorInfo>> getAccountList() {
    return accountLiveData;
  }

  private void setAccountList(LiveData<PagedList<AccVectorInfo>> accountLiveData) {

    this.accountLiveData = accountLiveData;
  }

}
