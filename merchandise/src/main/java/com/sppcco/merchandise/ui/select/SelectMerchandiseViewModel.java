package com.sppcco.merchandise.ui.select;

import com.sppcco.core.data.sub_model.MerchInfo;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.view_model.BaseViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.sqlite.db.SupportSQLiteQuery;


/**
 * Created by m_pejam on 09/03/18.
 * SelectMerchandiseViewModel
 */

public class SelectMerchandiseViewModel extends BaseViewModel implements SelectMerchandiseContract.ViewModel {

  private SelectMerchandiseContract.View mView;
  private SelectMerchandiseContract.Presenter mPresenter;

  private LiveData<PagedList<MerchInfo>> merchInfoListLiveData = null;
  private MutableLiveData<SupportSQLiteQuery> allMerchInfoMutable = new MutableLiveData<>();
  private MutableLiveData<SupportSQLiteQuery> allMerchInfoForStockCabinetMutable = new MutableLiveData<>();

  private final int PAGE_SIZE = 10;
  private final boolean ENABLE_PLACEHOLDERS = false;


  @Override
  public void setView(SelectMerchandiseContract.View view) {
    mView = view;
  }

  @Override
  public void setPresenter(SelectMerchandiseContract.Presenter presenter) {
    mPresenter = presenter;
  }

  public SelectMerchandiseContract.View getView() {
    return mView;
  }

  public SelectMerchandiseViewModel() {

  }

  void ViewModelObserveProviders() {

    if (/*mView.isAllStock() && */!mView.isMerchStock()) {

      setMerchInfoList(Transformations.switchMap(allMerchInfoMutable,
        input -> {

          setMerchInfoList(new LivePagedListBuilder(
            getCoreDB().merchInfoDao().getAllMerchInfo(input),
            new PagedList.Config.Builder()
              .setPageSize(PAGE_SIZE)
              .setPrefetchDistance(PAGE_SIZE / 2)
              .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
              .build()
          )
            //.setInitialLoadKey(170)
            .build());
          return getMerchInfoList();
        }));
    } else {

      setMerchInfoList(Transformations.switchMap(allMerchInfoForStockCabinetMutable, input -> {
        setMerchInfoList(

          new LivePagedListBuilder(
            getCoreDB().merchInfoDao().getAllMerchInfoForStockCabinet(input),
            new PagedList.Config.Builder()
              .setPageSize(PAGE_SIZE)
              .setPrefetchDistance(PAGE_SIZE / 2)
              .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
              .build()
          )
            //.setInitialLoadKey(170)
            .build());
        return getMerchInfoList();
      }));

    }
  }

  LiveData<PagedList<MerchInfo>> getMerchInfoList() {
    return merchInfoListLiveData;
  }

  private void setMerchInfoList(LiveData<PagedList<MerchInfo>> merchInfoListLiveData) {

    this.merchInfoListLiveData = merchInfoListLiveData;
  }

  public void initData() {

    if ( !mView.isAllStock() && mView.isMerchStock())
      allMerchInfoForStockCabinetMutable.setValue(getCoreDB().getQueryGenerator().getAllMerchInfoWithMerchStockQuery(
        mPresenter.isShowImages()?1:0, mView.getStockRoomId(), mView.getCabinetId(), BaseApplication.getFPId(), mView.getSortPosition(), mView.getFilter()));
    else
      allMerchInfoMutable.setValue(getCoreDB().getQueryGenerator().getAllMerchInfoWithoutMerchStockQuery(
        mPresenter.isShowImages()?1:0, BaseApplication.getFPId(), mView.getSortPosition(), mView.getFilter()));
  }

  /*public void insert(String strAdd) {

    Sample sample = new Sample(new Random().nextInt(5000) + 125, new Random().nextInt(5000), strAdd);
    Runnable runnable = () -> getDBComponent().sampleDao().insert(sample);
    getDBComponent().getAppComponent().getAppExecutors().diskIO().execute(runnable);
  }*/


  // create param for add item to constructor

/*  public static class MerchandiseViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private SelectMerchandiseContract.View mView;
    private SelectMerchandiseContract.Presenter mPresenter;


    public MerchandiseViewModelFactory(SelectMerchandiseContract.View view, SelectMerchandiseContract.Presenter presenter) {
      mView = view;
      mPresenter = presenter;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      return (T) new SelectMerchandiseViewModel(mView, mPresenter);
    }
  }*/

}
