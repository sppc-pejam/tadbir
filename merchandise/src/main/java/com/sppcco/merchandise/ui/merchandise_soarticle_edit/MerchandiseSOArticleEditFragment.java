package com.sppcco.merchandise.ui.merchandise_soarticle_edit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.sppcco.core.data.model.SOArticle;
import com.sppcco.core.data.model.SalesOrder;
import com.sppcco.core.data.sub_model.MerchInfo;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.framework.activity.BaseAppCompatActivity;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.fragment.BaseFragment;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.core.util.network.Connectivity;
import com.sppcco.data_entry_widgets.UNumEditText;
import com.sppcco.helperlibrary.converter.DC;
import com.sppcco.helperlibrary.manager.ViewManager;
import com.sppcco.merchandise.R;
import com.sppcco.merchandise.R2;
import com.sppcco.merchandise.ui.image.image_slider.ImageGalleryFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MerchandiseSOArticleEditFragment extends BaseFragment implements MerchandiseSOArticleEditContract.View {


  @BindView(R2.id.tv_name)
  AppCompatTextView tvName;
  @BindView(R2.id.tv_code)
  AppCompatTextView tvCode;
  @BindView(R2.id.tv_unit)
  AppCompatTextView tvUnit;
  @BindView(R2.id.tv_stock)
  AppCompatTextView tvStock;
  @BindView(R2.id.tv_cabinet)
  AppCompatTextView tvCabinet;
  @BindView(R2.id.cl_stock_cabinet)
  ConstraintLayout clStockCabinet;
  @BindView(R2.id.et_amount)
  UNumEditText etAmount;
  @BindView(R2.id.et_desc)
  EditText etDesc;
  @BindView(R2.id.cl_edit)
  ConstraintLayout clEdit;
  @BindView(R2.id.img_add_edit_article)
  ImageView imgAddEditArticle;
  @BindView(R2.id.tv_add_edit_article)
  AppCompatTextView tvAddEditArticle;
  @BindView(R2.id.cl_add_edit_article)
  ConstraintLayout clAddEditArticle;
  @BindView(R2.id.img_back)
  ImageView imgBack;
  @BindView(R2.id.image_recycler_view)
  RecyclerView imageRecyclerView;

  //--------------------------
  private int imageId;
  private MerchandiseSOArticleEditContract.Presenter mPresenter;
  private MerchandiseSOArticleEditAdapter mAdapter;
  //--------------------------

  private SalesOrder mSalesOrder;
  private SOArticle mSOArticle;
  private MerchInfo mMerchInfo;

  private boolean mAllStock;
  private boolean mShowInventory;
  private boolean mMerchStock;
  private boolean mSortable;

  private Mode mMode;

  private int mArticleCount = 0;

  public MerchandiseSOArticleEditFragment() {
    // Required empty public constructor
  }

  public static MerchandiseSOArticleEditFragment newInstance() {
    return new MerchandiseSOArticleEditFragment();
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mPresenter = MerchandiseSOArticleEditPresenter.getMerchandiseEditPresenterInstance(this);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_merchandise_soarticle_edit, container, false);
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

    initLayout();
    setImageId(getMerchInfo().getMerchId());
    initData();
    if (mAdapter == null) {
      mAdapter = new MerchandiseSOArticleEditAdapter(this, mPresenter);
    }

    return view;
  }

  private void setExtras(Bundle bundle) {

    setMode((Mode) bundle.getSerializable(IntentKey.KEY_MODE.getKey()));
    setAllStock(bundle.getBoolean(IntentKey.KEY_ALL_STOCK.getKey()));
    setMerchStock(bundle.getBoolean(IntentKey.KEY_MERCH_STOCK.getKey()));
    setShowInventory(bundle.getBoolean(IntentKey.KEY_SHOW_INVENTORY.getKey()));
    setSortable(bundle.getBoolean(IntentKey.KEY_SORTABLE.getKey()));
    setSalesOrder((SalesOrder) bundle.getSerializable(IntentKey.KEY_OBJECT.getKey()));
    setSOArticle((SOArticle) bundle.getSerializable(IntentKey.KEY_OBJECT_ARTICLE.getKey()));
    setMerchInfo((MerchInfo) bundle.getSerializable(IntentKey.KEY_MERCH_INFO.getKey()));
    setArticleCount(bundle.getInt(IntentKey.KEY_ARTICLE_COUNT.getKey()));
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @OnClick({R2.id.cl_add_edit_article, R2.id.img_back})
  public void onViewClicked(View view) {

    int i = view.getId();
    if (i == R.id.cl_add_edit_article) {
      if (getMode() == Mode.NEW) {
        addSOArticle();

      } else if (getMode() == Mode.EDIT) {
        editSOArticle();
      }
    } else if (i == R.id.img_back) {
      callMerchandiseFragment();
    }
  }

  private void addSOArticle() {

    ViewManager.closeKeyboard(Objects.requireNonNull(getActivity()));

    if (updateData(true))
      if (validData(true))
        mPresenter.isRepeatedMerch(aBoolean -> {
          if(! aBoolean)
            mPresenter.insertSOArticle();
          else
            showToast(BaseApplication.getResourceString(R.string.err_msg_repeated_merch_sp));
        });

  }

  private void editSOArticle() {

    ViewManager.closeKeyboard(Objects.requireNonNull(getActivity()));

    if (updateData(true))
      if (validData(true))
        mPresenter.isRepeatedMerch(aBoolean -> {
          if(! aBoolean)
            mPresenter.updateSOArticle();
          else
            showToast(BaseApplication.getResourceString(R.string.err_msg_repeated_merch_sp));
        });
  }

  private void callMerchandiseFragment() {
    ViewManager.closeKeyboard(Objects.requireNonNull(getActivity()));

    if (getMode() == Mode.NEW) {
      Objects.requireNonNull(getActivity()).onBackPressed();

    } else if (getMode() == Mode.EDIT) {
      Bundle bundle = new Bundle();

      bundle.putSerializable(IntentKey.KEY_MODE.getKey(), getMode());
      bundle.putBoolean(IntentKey.KEY_ALL_STOCK.getKey(), isAllStock());
      bundle.putBoolean(IntentKey.KEY_MERCH_STOCK.getKey(), isMerchStock());
      bundle.putBoolean(IntentKey.KEY_SHOW_INVENTORY.getKey(), isShowInventory());
      bundle.putBoolean(IntentKey.KEY_SORTABLE.getKey(), isSortable());
      bundle.putSerializable(IntentKey.KEY_OBJECT.getKey(), getSalesOrder());
      bundle.putSerializable(IntentKey.KEY_OBJECT_ARTICLE.getKey(), getSOArticle());
      bundle.putSerializable(IntentKey.KEY_MERCH_INFO.getKey(), getMerchInfo());
      bundle.putInt(IntentKey.KEY_ARTICLE_COUNT.getKey(), getArticleCount());

      Navigation.findNavController(Objects.requireNonNull(getView())).navigate(
        R.id.action_merchandiseSOArticleEditFragment_to_merchandiseFragment, bundle);
    }
  }

  @Override
  public void afterSOArticleInserted() {
    Objects.requireNonNull(getActivity()).onBackPressed();
  }

  @Override
  public void afterSOArticleUpdated() {
    Objects.requireNonNull(getActivity()).onBackPressed();
  }

  @Override
  public void setPresenter(MerchandiseSOArticleEditContract.Presenter presenter) {
    mPresenter = presenter;
  }


  @Override
  public void initLayout() {

    setHasOptionsMenu(false);
    Objects.requireNonNull(((BaseAppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();

    Objects.requireNonNull(getActivity()).setTitle(R.string.title_add_edit_so_article_activity);

    if (getMode() == Mode.NEW) {
      tvAddEditArticle.setText(BaseApplication.getResourceString(R.string.cpt_add_article));
      imgAddEditArticle.setImageDrawable(BaseApplication.getResourceDrawable(R.drawable.ic_add_w_24));
    } else if (getMode() == Mode.EDIT) {
      tvAddEditArticle.setText(BaseApplication.getResourceString(R.string.cpt_edit_article));
      imgAddEditArticle.setImageDrawable(BaseApplication.getResourceDrawable(R.drawable.ic_edit_w_24));
    }

    if (!mPresenter.isShowImages())
      imageRecyclerView.setVisibility(View.GONE);

    if (!isShowInventory() || !isMerchStock())
      clStockCabinet.setVisibility(View.GONE);


    if (Connectivity.isConnectedOrConnecting(BaseApplication.getContext())) {
      if (!mPresenter.isShowImages() || getMerchInfo().getMerchThumbnailCount() == 0) {
        imageRecyclerView.setVisibility(View.GONE);
      } else {
        imageRecyclerView.setVisibility(View.VISIBLE);
      }
    } else {
      imageRecyclerView.setVisibility(View.GONE);
    }

    if (mPresenter.isShowImages())
      initRecyclerView();
  }

  @Override
  public void initData() {

    mPresenter.start();
  }

  @Override
  public void initViewPager() {
    mAdapter.loadAdapterData();

    if (mPresenter.getImageIds() != null) {
      loadRecyclerViewItem();
    } else {
      imageRecyclerView.setVisibility(View.GONE);
    }
  }

  @Override
  public boolean updateModel() {
    return false;
  }

  @Override
  public boolean updateView() {
    tvName.setText(getMerchInfo().getMerchName());
    tvCode.setText(getMerchInfo().getMerchCode());
    tvUnit.setText(getMerchInfo().getMerchUnitName());

    if (isMerchStock() && isShowInventory()) {
      tvStock.setText(getMerchInfo().getStockName());
      tvCabinet.setText(getMerchInfo().getCabinetName());
    }

    if (getSOArticle() == null || (getSOArticle().getMerchandiseId() != getMerchInfo().getMerchId())) {

      etAmount.setText("");
    } else {

      etAmount.setString(getSOArticle().getAmount());
    }

    if (getSOArticle() != null)
      etDesc.setText(getSOArticle().getSOADesc());

    return false;
  }

  @Override
  public boolean validData(boolean showMsg) {

    if (mPresenter.getSOArticle().getAmount() <= 0.0) {

      if (showMsg)
        showToast(BaseApplication.getResourceString(R.string.err_msg_invalid_amount));
      return false;
    }

    if (getMode() == Mode.NEW && getArticleCount() + 1 > CoreConstants.ROW_LIMITED) {
      //TODO
      //showToast(getActivity(), getResources().getString(R.string.err_msg_row_limited), MessageType.DANGER);
      return false;
    }

    return true;
  }

  private void initRecyclerView() {
    imageRecyclerView.setHasFixedSize(true);
    imageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    imageRecyclerView.setAdapter(mAdapter);
  }

  private MerchandiseSOArticleEditAdapter getAdapterInstance() {
    if (mAdapter == null) {
      mAdapter = new MerchandiseSOArticleEditAdapter(this, mPresenter);
    }
    mAdapter.loadAdapterData();

    return mAdapter;
  }

  private void loadRecyclerViewItem() {
    mAdapter = getAdapterInstance();
    imageRecyclerView.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();

    if (mAdapter.getItemPosition() != 0) {
      imageRecyclerView.scrollToPosition(mAdapter.getItemPosition());
    }
  }

  private void showToast(String message) {
    //TODO
    //showToast(getActivity(), message, MessageType.DANGER);
  }

  private boolean updateData(boolean showMsg) {
    if (Objects.requireNonNull(etAmount.getText()).toString().isEmpty()) {
      if (showMsg) {
        etAmount.setError(BaseApplication.getResourceString(R.string.err_msg_invalid_amount));
        return false;
      }
    } else {
      if (etAmount.getText().charAt(0) == '.')
        etAmount.setText(etAmount.getText().toString().replace(".", "0."));

      //mPresenter.getSOArticle().setAmount(Double.parseDouble(DC.faToEn(etAmount.getText().toString())));
      mPresenter.getSOArticle().setAmount(etAmount.getDouble());
      mPresenter.getSOArticle().setSOADesc(etDesc.getText().toString());
    }

    return true;
  }

  private String doubleToStringNoDecimal(double d) {
    String strTemp = String.valueOf(DC.dtostr(d));
    if (strTemp != null && strTemp.length() >= 2) {

      if (strTemp.substring(strTemp.length() - 2).equals(".0"))
        strTemp = strTemp.substring(0, strTemp.length() - 2);

      if (strTemp.contains("."))
        strTemp = new DecimalFormat("#.##").format(Double.parseDouble(strTemp));
    }
    return strTemp;
  }

  @Override
  public void callImageGallery(int merchandiseId, ArrayList<Integer> ids, int position) {
    ViewManager.closeKeyboard(Objects.requireNonNull(getActivity()));

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

///////////////////////////////////////////////////////////////////////////////////////////////////////

  @Override
  public int getMerchId() {
    return imageId;
  }

  private void setImageId(int id) {
    imageId = id;
  }

  private void setAllStock(boolean allStock) {
    this.mAllStock = allStock;
  }

  private boolean isAllStock() {
    return mAllStock;
  }

  private void setMerchStock(boolean merchStock) {
    this.mMerchStock = merchStock;
  }

  public boolean isMerchStock() {
    return mMerchStock;
  }

  private void setShowInventory(boolean showInventory) {
    this.mShowInventory = showInventory;
  }

  private boolean isShowInventory() {
    return mShowInventory;
  }

  private void setSortable(boolean sortable) {
    this.mSortable = sortable;
  }

  private boolean isSortable() {
    return mSortable;
  }

  @Override
  public SalesOrder getSalesOrder() {
    return mSalesOrder;
  }

  public void setSalesOrder(SalesOrder salesOrder) {
    mSalesOrder = salesOrder;
  }

  @Override
  public SOArticle getSOArticle() {
    return mSOArticle;
  }

  public void setSOArticle(SOArticle soArticle) {
    this.mSOArticle = soArticle;
  }

  @Override
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

  private void setArticleCount(int count) {
    this.mArticleCount = count;
  }
}
