package com.sppcco.customer.ui.acc_vector.project;

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

public class ProjectViewModel extends BaseViewModel implements ProjectContract.ViewModel {

  private ProjectContract.View mView;
  private ProjectContract.Presenter mPresenter;

  private LiveData<PagedList<AccVectorInfo>> projectLiveData = null;
  private MutableLiveData<SupportSQLiteQuery> allProjectMutable = new MutableLiveData<>();

  @Override
  public void setView(ProjectContract.View view) {
    mView = view;
  }

  @Override
  public void setPresenter(ProjectContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @SuppressWarnings("unchecked")
  void ViewModelObserveProviders() {

    setProjectList(Transformations.switchMap(allProjectMutable,
      input -> {
        setProjectList(new LivePagedListBuilder(getDataSource(input), getPageConfig()).build());
        return getProjectList();
      })
    );
  }

  public void initData() {
    SimpleSQLiteQuery query;

    if (mView.getRoot() == AccountTree.PROJECT) {
      if (mView.getACCVector().getProject().getPCode() != 0)
        query = getCoreDB().getQueryGenerator().getProjectByCode(mView.getACCVector().getProject().getPCode());
      else
        query = getCoreDB().getQueryGenerator().getAllProject(mView.getFilter(), mView.getSortPosition());

    } else {
      query = getCoreDB().getQueryGenerator().getAllProjectByFullId(
        mView.getACCVector().getAccount().getFullId(), mView.getFilter(), mView.getSortPosition());

    }

    allProjectMutable.postValue(query);
  }

  private DataSource.Factory getDataSource(SupportSQLiteQuery input) {
    switch (mView.getRoot()) {

      case DETAIL_ACC:
        if (mView.getACCVector().getProject().getPCode() != 0)
          return getCoreDB().accVectorInfoDao().getProjectByCode(input);
        else
          return getCoreDB().accVectorInfoDao().getAllProject(input);

      default:
        return getCoreDB().accVectorInfoDao().getAllProjectByFullId(input);
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

  LiveData<PagedList<AccVectorInfo>> getProjectList() {
    return projectLiveData;
  }

  private void setProjectList(LiveData<PagedList<AccVectorInfo>> projectLiveData) {

    this.projectLiveData = projectLiveData;
  }
}
