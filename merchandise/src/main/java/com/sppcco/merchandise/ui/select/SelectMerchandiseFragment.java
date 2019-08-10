package com.sppcco.merchandise.ui.select;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.sppcco.core.data.model.SOArticle;
import com.sppcco.core.data.model.SPArticle;
import com.sppcco.core.data.model.SPFactor;
import com.sppcco.core.data.model.SalesOrder;
import com.sppcco.core.data.sub_model.MerchInfo;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.framework.activity.BaseAppCompatActivity;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.fragment.BaseFragment;
import com.sppcco.helperlibrary.converter.DC;
import com.sppcco.helperlibrary.dialog.material_dialog.listener.SelectItemResponseListener;
import com.sppcco.helperlibrary.dialog.material_dialog.manager.MDialogManager;
import com.sppcco.helperlibrary.manager.ApplicationPermission;
import com.sppcco.merchandise.R;
import com.sppcco.merchandise.R2;
import com.sppcco.merchandise.ui.image.image_slider.ImageGalleryFragment;
import com.sppcco.merchandise.ui.util.barcode_scanner.ActivityBarcodeScanner;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Objects;

import com.google.zxing.Result;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SelectMerchandiseFragment extends BaseFragment implements SelectMerchandiseContract.View {

  @BindView(R2.id.recyclerView)
  RecyclerView recyclerView;
  @BindView(R2.id.tv_stock_title)
  AppCompatTextView tvStockTitle;
  @BindView(R2.id.tv_stock)
  AppCompatTextView tvStock;
  @BindView(R2.id.cl_stock)
  ConstraintLayout clStock;
  @BindView(R2.id.tv_cabinet_title)
  AppCompatTextView tvCabinetTitle;
  @BindView(R2.id.tv_cabinet)
  AppCompatTextView tvCabinet;
  @BindView(R2.id.cl_cabinet)
  ConstraintLayout clCabinet;
  @BindView(R2.id.img_sort)
  ImageView imgSort;

  private SelectMerchandiseContract.Presenter mPresenter;

  private SelectMerchandiseViewModel mViewModel;
  private SelectMerchandiseAdapter mAdapter;

  private ProgressDialog mProgressDialog;
  private MenuItem mMenuItem;
  private SearchView mSearchView;
  private TextView textArticleCount;
  private int mArticleCount = 0;
  //--------------------------
  private boolean mAllStock;
  private boolean mShowInventory;
  private boolean mMerchStock;
  //--------------------------
  private boolean mSortable;
  private boolean mHasError;
  //--------------------------
  private int mCustomerId = 0;
  private int mStockRoomId = 0;
  private int mCabinetId = 0;

  private int mStockPosition;
  private int mCabinetPosition;

  private int mSortPosition;

  private String mstrFilter;

  private SPFactor mSPFactor;
  private SPArticle mSPArticle;
  private SalesOrder mSalesOrder;
  private SOArticle mSOArticle;
  private MerchInfo mMerchInfo;

  private Mode mMode;

  private LinearLayoutManager mLayoutManager;
  private Bundle mBundlePreviousState = null;
  private final String KEY_RECYCLER_STATE = "KEY_RECYCLER_STATE";
  private final String KEY_FILTER = "KEY_FILTER";

  @NonNull
  public static SelectMerchandiseFragment newInstance() {
    return new SelectMerchandiseFragment();
  }

  @Override
  public void setPresenter(SelectMerchandiseContract.Presenter presenter) {
    mPresenter = presenter;
  }


  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mPresenter = SelectMerchandisePresenter.getMerchandisePresenterInstance(this);
  }


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_select_merchandise, container, false);
    ButterKnife.bind(this, view);


    if (savedInstanceState != null)
      setExtras(savedInstanceState);
    else {

      Bundle extras;
      if (getArguments() != null) {

        extras = this.getArguments();
        setExtras(extras);
      } else {

        extras = Objects.requireNonNull(getActivity()).getIntent().getExtras();
        if (extras != null)
          setExtras(extras);
      }
    }

    mLayoutManager = new LinearLayoutManager(getActivity());
    if (mBundlePreviousState == null) {
      initData();
    }

    restoreInstanceState();
    initLayout();

    Objects.requireNonNull(((BaseAppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();
    return view;
  }


  private void setExtras(Bundle bundle) {

    setMode((Mode) bundle.getSerializable(IntentKey.KEY_MODE.getKey()));
    //---------------------------------
    setAllStock(bundle.getBoolean(IntentKey.KEY_ALL_STOCK.getKey()));
    setMerchStock(bundle.getBoolean(IntentKey.KEY_MERCH_STOCK.getKey()));
    setShowInventory(bundle.getBoolean(IntentKey.KEY_SHOW_INVENTORY.getKey()));
    //---------------------------------
    setSortable(bundle.getBoolean(IntentKey.KEY_SORTABLE.getKey()));
    setHasError(bundle.getBoolean(IntentKey.KEY_HAS_ERROR_STATUS.getKey()));
    //---------------------------------

    Object object = bundle.getSerializable(IntentKey.KEY_OBJECT.getKey());
    if (object instanceof SPFactor) {

      setSPFactor((SPFactor) bundle.getSerializable(IntentKey.KEY_OBJECT.getKey()));
      setSalesOrder(null);
      setSPArticle((SPArticle) bundle.getSerializable(IntentKey.KEY_OBJECT_ARTICLE.getKey()));
      setCustomerId(getSPFactor().getCustomerId());
    } else if (object instanceof SalesOrder) {

      setSalesOrder((SalesOrder) bundle.getSerializable(IntentKey.KEY_OBJECT.getKey()));
      setSPFactor(null);
      setSOArticle((SOArticle) bundle.getSerializable(IntentKey.KEY_OBJECT_ARTICLE.getKey()));
      setCustomerId(getSalesOrder().getCustomerId());
    }

    setStockRoomId(getSPArticle() == null ? mPresenter.getLatestStockId() : getSPArticle().getStockRoomId());
    setCabinetId(getSPArticle() == null ? mPresenter.getLatestCabinetId() : getSPArticle().getCabinetId());
    setMerchInfo((MerchInfo) bundle.getSerializable(IntentKey.KEY_MERCH_INFO.getKey()));
    setArticleCount(bundle.getInt(IntentKey.KEY_ARTICLE_COUNT.getKey()));
  }


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    registerEventBus();
  }


  @Override
  public void onStart() {
    super.onStart();
    mPresenter.start();
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();

    if (!isAllStock()) {
      mPresenter.setLatestStockId();
      mPresenter.setLatestCabinetId();
    }

    mBundlePreviousState = new Bundle();
    Parcelable listState = Objects.requireNonNull(recyclerView.getLayoutManager()).onSaveInstanceState();
    mBundlePreviousState.putParcelable(KEY_RECYCLER_STATE, listState);
    if (getFilter() != null)
      mBundlePreviousState.putString(KEY_FILTER, getFilter());
  }

  @Override
  public void onStop() {
    super.onStop();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unRegisterEventBus();
    mPresenter.destroy();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  @Override
  public void onDetach() {
    super.onDetach();
  }

  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);

    outState.putBoolean(IntentKey.KEY_ALL_STOCK.getKey(), mAllStock);
    outState.putBoolean(IntentKey.KEY_MERCH_STOCK.getKey(), mMerchStock);
    outState.putBoolean(IntentKey.KEY_SHOW_INVENTORY.getKey(), mShowInventory);
    outState.putBoolean(IntentKey.KEY_SORTABLE.getKey(), mSortable);
    outState.putInt(IntentKey.KEY_CUSTOMER_ID.getKey(), getCustomerId());
    outState.putInt(IntentKey.KEY_STOCK_ID.getKey(), getStockRoomId());
    outState.putInt(IntentKey.KEY_CABINET_ID.getKey(), getCabinetId());
  }

  @Override
  public void initData() {

    setStockPosition(0);
    setCabinetPosition(0);
    setSortPosition(0);
    setFilter(null);


    // send param on constructor of view model
    /*mViewModel = ViewModelProviders.of(getActivity(), new SelectMerchandiseViewModel.MerchandiseViewModelFactory(
      this, mPresenter)).get(SelectMerchandiseViewModel.class);*/

    mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SelectMerchandiseViewModel.class);
    mViewModel.setView(this);
    mViewModel.setPresenter(mPresenter);
    mViewModel.ViewModelObserveProviders();

    mAdapter = new SelectMerchandiseAdapter(mPresenter, this);

    mViewModel.getMerchInfoList().observe(this, merchInfos -> {
      mAdapter.submitList(merchInfos);
      mAdapter.setSparseMerchIdAndMerchMoreInfo(null);
    });

    initViewModel();
  }

  @Override
  public void initViewModel() {
    mViewModel.initData();

  }

  @Override
  public void initLayout() {

    setHasOptionsMenu(true);

    if (isAllStock()) {
      clStock.setEnabled(false);
      clCabinet.setEnabled(false);
    } else {
      tvStock.setText(BaseApplication.getResourceString(R.string.cpt_three_dash));
      tvCabinet.setText(BaseApplication.getResourceString(R.string.cpt_three_dash));
    }

    if (!isSortable())
      imgSort.setEnabled(false);
  }

  private void restoreInstanceState() {
    if (mBundlePreviousState != null) {
      Parcelable previousLayoutManagerState = mBundlePreviousState.getParcelable(KEY_RECYCLER_STATE);
      mLayoutManager.onRestoreInstanceState(previousLayoutManagerState);

      if (mBundlePreviousState.getString(KEY_FILTER) != null) {
        setFilter(null);
        initViewModel();
      }
    }
    initRecyclerView();
  }

  /*-------initRecyclerView-------*/
  private void initRecyclerView() {
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setAdapter(mAdapter);
  }
  /*-------initRecyclerView-------*/

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    menu.clear();
    inflater.inflate(R.menu.menu_select_merchandise, menu);

    /*mMenuItem = menu.findItem(R.id.searchview);
    mSearchView = (SearchView) MenuItemCompat.getActionView(mMenuItem);*/

    mMenuItem = menu.findItem(R.id.searchview);
    mSearchView = (SearchView) mMenuItem.getActionView();

    mSearchView.setQueryHint(BaseApplication.getResourceString(R.string.cpt_search_by_merchandise));
    searchViewListeners();

    View badgeView = MenuItemCompat.getActionView(menu.findItem(R.id.badge));
    textArticleCount = badgeView.findViewById(R.id.cart_badge);
    setupBadge();
  }

  @Override
  public void onPrepareOptionsMenu(@NonNull Menu menu) {
    super.onPrepareOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {

    // Handle item selection
    if (item.getItemId() == R.id.barcode) {
      onBarcodeSelected();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setupBadge() {
    if (textArticleCount != null) {
      textArticleCount.setText(String.valueOf(getArticleCount()));
    }
  }


  private void searchViewListeners() {

    mMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
      @Override
      public boolean onMenuItemActionExpand(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.searchview) {
          onSearchViewExpand();
          return true;
        }
        return true;
      }

      @Override
      public boolean onMenuItemActionCollapse(MenuItem item) {

        // Handle item selection
        if (item.getItemId() == R.id.searchview) {
          onSearchViewCollapse();
          return true;
        }
        return true;
      }
    });


    mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        query = DC.faToEn(query);
        setFilter(query);
        initViewModel();
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        Log.i("LOG", newText);
        return false;
      }
    });
  }


  private void onSearchViewExpand() {

    mSearchView.setIconified(false);
    mAdapter.submitList(null);
  }

  private void onSearchViewCollapse() {

    mSearchView.setIconified(true);
    setFilter(null);
    initViewModel();
  }

  @Override
  public boolean updateModel() {
    return false;
  }


  @Override
  public boolean validData(boolean showMsg) {

    return true;
  }

  @Override
  public boolean updateView() {

    if (isAllStock()) {
      tvStock.setText(BaseApplication.getResourceString(R.string.cpt_all_stock));
      tvCabinet.setText(BaseApplication.getResourceString(R.string.cpt_all_cabinet));
    } else {
      //tvStock.setText(mPresenter.getStockNameList().get(getStockPosition()));
      //tvCabinet.setText(mPresenter.getCabinetNameList().get(getCabinetPosition()));
      tvStock.setText(mPresenter.getStockRoom().getName());
      tvCabinet.setText(mPresenter.getCabinet().getName());
    }
    return false;
  }


  public SelectMerchandiseAdapter getAdapterInstance() {
    if (mAdapter == null) {
      mAdapter = new SelectMerchandiseAdapter(mPresenter, this);
    }

    //mAdapter.loadAdapterData();
    return mAdapter;
  }


  @OnClick({R2.id.cl_stock, R2.id.cl_cabinet, R2.id.img_sort})
  public void onViewClicked(View view) {
    int i = view.getId();
    if (i == R.id.cl_stock) {
      showStockListDialog();
    } else if (i == R.id.cl_cabinet) {
      showCabinetListDialog();
    } else if (i == R.id.img_sort) {
      showSortListDialog();
    }
  }

  private void showStockListDialog() {

    int nPreviousStockPosition = getStockPosition();

    MDialogManager.basicListDialog(
      BaseApplication.getContext(), getActivity(), mPresenter.getStockNameList(),
      BaseApplication.getResourceString(R.string.cpt_stock_name), getStockPosition(), new SelectItemResponseListener() {
        @Override
        public void onItemSelected(String content, int position) {

          if (getStockPosition() != position)
            onChangeStock(position);
        }

        @Override
        public void onDisAgree() {
          setStockPosition(nPreviousStockPosition);
        }
      }
    );
  }

  private void onChangeStock(int position) {

    setStockPosition(position);
    setCabinetId(0);

    if (mSearchView.isIconified())
      mSearchView.setIconified(true);
    setFilter(null);
    mAdapter.setSparseMerchIdAndMerchMoreInfo(null);

    mPresenter.onChangeStock(position);

  }


  private void showCabinetListDialog() {

    int nPreviousCabinetPosition = getCabinetPosition();

    MDialogManager.basicListDialog(
      BaseApplication.getContext(), getActivity(), mPresenter.getCabinetNameList(),
      BaseApplication.getResourceString(R.string.cpt_cabinet_name), getCabinetPosition(), new SelectItemResponseListener() {
        @Override
        public void onItemSelected(String content, int position) {

          if (getCabinetPosition() != position)
            onChangeCabinet(position);

        }

        @Override
        public void onDisAgree() {
          setCabinetPosition(nPreviousCabinetPosition);
        }
      }
    );
  }

  private void onChangeCabinet(int position) {

    setCabinetPosition(position);
    mPresenter.onChangeCabinet(position);
  }

  private void showSortListDialog() {


    int nPreSelectedSortIndex = mSortPosition;

    MDialogManager.basicListDialog(
      BaseApplication.getContext(), getActivity(), mPresenter.getSortList(),
      BaseApplication.getResourceString(R.string.cpt_sort_by), mSortPosition, new SelectItemResponseListener() {
        @Override
        public void onItemSelected(String content, int position) {
          if (position != nPreSelectedSortIndex) {

            setSortPosition(position);
            mAdapter.submitList(null);
            mViewModel.initData();

          }
        }

        @Override
        public void onDisAgree() {
          mSortPosition = nPreSelectedSortIndex;
        }
      }
    );
  }

  private void registerEventBus() {
    if (!EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().register(this);
    }
  }

  private void unRegisterEventBus() {
    if (EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().unregister(this);
    }
  }

  private void onBarcodeSelected() {
    if (!ApplicationPermission.isCameraPermissionGranted(getActivity()))
      return;

    Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(), ActivityBarcodeScanner.class));
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEvent(Result rawResult) {

    mPresenter.getMerchInfoByBarcode(rawResult.getText());
  }


  @Override
  public void callEditFragment(MerchInfo merchInfo) {
    Bundle bundle = new Bundle();

    bundle.putSerializable(IntentKey.KEY_MODE.getKey(), getMode());
    bundle.putBoolean(IntentKey.KEY_ALL_STOCK.getKey(), isAllStock());
    bundle.putBoolean(IntentKey.KEY_MERCH_STOCK.getKey(), isMerchStock());
    bundle.putBoolean(IntentKey.KEY_SHOW_INVENTORY.getKey(), isShowInventory());
    //---------------------------------
    bundle.putBoolean(IntentKey.KEY_SORTABLE.getKey(), isSortable());
    bundle.putBoolean(IntentKey.KEY_HAS_ERROR_STATUS.getKey(), hasError());
    //---------------------------------

    if (getSPFactor() != null) {

      bundle.putSerializable(IntentKey.KEY_OBJECT.getKey(), getSPFactor());
      bundle.putSerializable(IntentKey.KEY_OBJECT_ARTICLE.getKey(), getSPArticle());
      if (getStockRoomId() != merchInfo.getStockId()) {
        merchInfo.setStockId(mPresenter.getStockRoom().getId());
        merchInfo.setStockName(mPresenter.getStockRoom().getName());
        merchInfo.setStockCode(mPresenter.getStockRoom().getCode());
        merchInfo.setStockAccountId(mPresenter.getStockRoom().getAccountId());
        merchInfo.setStockFAccId(mPresenter.getStockRoom().getFAccId());
        merchInfo.setStockCCId(mPresenter.getStockRoom().getCCId());
        merchInfo.setStockPrjId(mPresenter.getStockRoom().getPrjId());
      }
      if (getCabinetId() != merchInfo.getCabinetId()) {
        merchInfo.setCabinetId(mPresenter.getCabinet().getId());
        merchInfo.setCabinetCode(mPresenter.getCabinet().getCode());
        merchInfo.setCabinetName(mPresenter.getCabinet().getName());
      }
      bundle.putSerializable(IntentKey.KEY_MERCH_INFO.getKey(), merchInfo);
      bundle.putInt(IntentKey.KEY_ARTICLE_COUNT.getKey(), getArticleCount());

      if (getView() != null) {
        Navigation.findNavController(Objects.requireNonNull(getView())).navigate(
          R.id.action_merchandiseFragment_to_merchandiseSPArticleEditFragment, bundle);
      }
    } else if (getSalesOrder() != null) {

      bundle.putSerializable(IntentKey.KEY_OBJECT.getKey(), getSalesOrder());
      bundle.putSerializable(IntentKey.KEY_OBJECT_ARTICLE.getKey(), getSOArticle());
      bundle.putSerializable(IntentKey.KEY_MERCH_INFO.getKey(), merchInfo);
      bundle.putInt(IntentKey.KEY_ARTICLE_COUNT.getKey(), getArticleCount());

      if (getView() != null) {
        Navigation.findNavController(Objects.requireNonNull(getView())).navigate(
          R.id.action_merchandiseFragment_to_merchandiseSOArticleEditFragment, bundle);
      }
    }
  }

  @Override
  public void callImageGallery(int merchandiseId, ArrayList<Integer> ids, int position) {
    Bundle bundle = new Bundle();
    bundle.putInt(IntentKey.KEY_MERCH_ID.getKey(), merchandiseId);
    bundle.putSerializable(IntentKey.KEY_IMAGE_GALLERY_VALUE.getKey(), ids);
    bundle.putInt(IntentKey.KEY_IMAGE_GALLERY_POSITION.getKey(), position);

    FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
    ImageGalleryFragment newFragment = new ImageGalleryFragment();
    newFragment.setArguments(bundle);
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
  }

  @Override
  public void onShowProgress() {
    if (getProgress().isShowing()) return;
    getProgress().show();
  }

  @Override
  public void onHideProgress() {
    if (getProgress().isShowing()) getProgress().dismiss();
  }

  private ProgressDialog getProgress() {
    if (mProgressDialog == null) {
      mProgressDialog = new ProgressDialog(getActivity());
      mProgressDialog.setCancelable(false);
      mProgressDialog.setMessage(BaseApplication.getResourceString(R.string.msg_waiting));
      mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      mProgressDialog.setIndeterminate(false);
      mProgressDialog.setCanceledOnTouchOutside(false);
    }
    return mProgressDialog;
  }

  public void finishView() {
    Objects.requireNonNull(getActivity()).finish();
  }


  ///////////////////////////////////////////////////////////////////////////////////////

  @Override
  public int getCustomerId() {
    return mCustomerId;
  }

  public void setCustomerId(int customerId) {
    mCustomerId = customerId;
  }

  @Override
  public int getStockRoomId() {
    return mStockRoomId;
  }

  public void setStockRoomId(int stockRoomId) {
    mStockRoomId = stockRoomId;
  }

  @Override
  public int getCabinetId() {
    return mCabinetId;
  }

  public void setCabinetId(int cabinetId) {
    mCabinetId = cabinetId;
  }

  private void setAllStock(boolean allStock) {
    this.mAllStock = allStock;
  }

  @Override
  public boolean isAllStock() {
    return mAllStock;
  }

  private void setMerchStock(boolean merchStock) {
    this.mMerchStock = merchStock;
  }

  @Override
  public boolean isMerchStock() {
    return mMerchStock;
  }

  private void setShowInventory(boolean showInventory) {
    this.mShowInventory = showInventory;
  }

  @Override
  public boolean isShowInventory() {
    return mShowInventory;
  }

  private void setSortable(boolean sortable) {
    this.mSortable = sortable;
  }

  private boolean isSortable() {
    return mSortable;
  }

  private boolean hasError() {
    return mHasError;
  }

  private void setHasError(boolean mHasError) {
    this.mHasError = mHasError;
  }

  private int getStockPosition() {
    return mStockPosition;
  }

  @Override
  public void setStockPosition(int stockPosition) {
    mStockPosition = stockPosition;
  }

  private int getCabinetPosition() {
    return mCabinetPosition;
  }

  @Override
  public void setCabinetPosition(int cabinetPosition) {
    mCabinetPosition = cabinetPosition;
  }

  private void setSortPosition(int sortPosition) {
    this.mSortPosition = sortPosition;
  }

  @Override
  public int getSortPosition() {
    return mSortPosition;
  }

  @Override
  public String getFilter() {
    return mstrFilter;
  }

  public void setFilter(String strFilter) {
    mstrFilter = strFilter;
  }

  @Override
  public SPFactor getSPFactor() {
    return mSPFactor;
  }

  public void setSPFactor(SPFactor spFactor) {
    mSPFactor = spFactor;
  }

  @Override
  public SalesOrder getSalesOrder() {
    return mSalesOrder;
  }

  public void setSalesOrder(SalesOrder salesOrder) {
    mSalesOrder = salesOrder;
  }

  public SPArticle getSPArticle() {
    return mSPArticle;
  }

  public void setSPArticle(SPArticle sPArticle) {
    this.mSPArticle = sPArticle;
  }

  public SOArticle getSOArticle() {
    return mSOArticle;
  }

  public void setSOArticle(SOArticle sOArticle) {
    this.mSOArticle = sOArticle;
  }

  public MerchInfo getMerchInfo() {
    return mMerchInfo;
  }

  private void setMerchInfo(MerchInfo merchInfo) {
    this.mMerchInfo = merchInfo;
  }

  @Override
  public Mode getMode() {
    return mMode;
  }

  public void setMode(Mode mMode) {
    this.mMode = mMode;
  }

  private int getArticleCount() {
    return mArticleCount;
  }

  @Override
  public void setArticleCount(int count) {
    this.mArticleCount = count;
    setupBadge();
  }

}
