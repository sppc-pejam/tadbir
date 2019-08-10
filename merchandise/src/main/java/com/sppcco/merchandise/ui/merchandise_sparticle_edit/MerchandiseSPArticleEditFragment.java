package com.sppcco.merchandise.ui.merchandise_sparticle_edit;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.sppcco.core.framework.activity.BaseAppCompatActivity;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.data_entry_widgets.UNumEditText;
import com.sppcco.helperlibrary.converter.DC;
import com.sppcco.helperlibrary.manager.CalenderManager;
import com.sppcco.helperlibrary.manager.ViewManager;
import com.sppcco.helperlibrary.message.enums.MessageType;
import com.sppcco.core.data.model.SPArticle;
import com.sppcco.core.data.model.SPFactor;
import com.sppcco.core.data.sub_model.MerchInfo;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.framework.fragment.BaseFragment;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.core.util.network.Connectivity;
import com.sppcco.merchandise.R;
import com.sppcco.merchandise.R2;
import com.sppcco.merchandise.ui.image.image_slider.ImageGalleryFragment;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
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

import static java.lang.Math.round;


public class MerchandiseSPArticleEditFragment extends BaseFragment implements MerchandiseSPArticleEditContract.View {


  @BindView(R2.id.tv_inv_total)
  AppCompatTextView tvInvTotal;
  @BindView(R2.id.tv_inv_stock)
  AppCompatTextView tvInvStock;
  @BindView(R2.id.cl_inventory)
  ConstraintLayout clInventory;
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
  @BindView(R2.id.et_unit_price)
  UNumEditText etUnitPrice;
  @BindView(R2.id.img_lock_unit_price)
  AppCompatImageView imgLockUnitPrice;
  @BindView(R2.id.et_total_price)
  UNumEditText etTotalPrice;
  @BindView(R2.id.img_lock_total_price)
  AppCompatImageView imgLockTotalPrice;
  @BindView(R2.id.et_remainder)
  UNumEditText etRemainder;
  @BindView(R2.id.img_lock_remainder)
  AppCompatImageView imgLockRemainder;
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
  private MerchandiseSPArticleEditContract.Presenter mPresenter;
  private MerchandiseSPArticleEditAdapter mAdapter;
  //--------------------------

  private SPFactor mSPFactor;
  private SPArticle mSPArticle;
  private MerchInfo mMerchInfo;
  //--------------------------
  private boolean mAllStock;
  private boolean mShowInventory;
  private boolean mMerchStock;
  //--------------------------
  private boolean mSortable;
  private boolean mHasError;
  //--------------------------
  private Mode mMode;
  //--------------------------
  private boolean isFirstInitView;
  //--------------------------

  private View mFocusedView;
  private View mPreviousFocusedView;

  private int mArticleCount = 0;

  public MerchandiseSPArticleEditFragment() {
    // Required empty public constructor
  }

  public static MerchandiseSPArticleEditFragment newInstance() {
    return new MerchandiseSPArticleEditFragment();
  }

  @Override
  public void setPresenter(MerchandiseSPArticleEditContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mPresenter = MerchandiseSPArticleEditPresenter.getMerchandiseSPArticleEditPresenterInstance(this);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_merchandise_sparticle_edit, container, false);
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
      mAdapter = new MerchandiseSPArticleEditAdapter(this, mPresenter);
    }
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
    setSPFactor((SPFactor) bundle.getSerializable(IntentKey.KEY_OBJECT.getKey()));
    setSPArticle((SPArticle) bundle.getSerializable(IntentKey.KEY_OBJECT_ARTICLE.getKey()));
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
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    menu.clear();
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  @Override
  public void onPause() {
    super.onPause();
  }

  @Override
  public void onStop() {
    super.onStop();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
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

  @OnClick({R2.id.cl_add_edit_article, R2.id.img_back})
  public void onViewClicked(View view) {

    int i = view.getId();
    if (i == R.id.cl_add_edit_article) {
      if (getMode() == Mode.NEW) {
        addSPArticle();

      } else {
        editSPArticle();

      }
    } else if (i == R.id.img_back) {
      callMerchandiseFragment();
    }

  }


  private void addSPArticle() {

    ViewManager.closeKeyboard(Objects.requireNonNull(getActivity()));

    if (updateData(true))
      if (validData(true))
        mPresenter.isRepeatedMerch(aBoolean -> {
          if(! aBoolean)
            mPresenter.insertSPArticle();
          else
            showToast(BaseApplication.getResourceString(R.string.err_msg_repeated_merch_sp));
        });

  }

  private void editSPArticle() {

    ViewManager.closeKeyboard(Objects.requireNonNull(getActivity()));

    if (updateData(true))
      if (validData(true))
        mPresenter.isRepeatedMerch(aBoolean -> {
          if(! aBoolean)
            mPresenter.updateSPArticle();
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
      bundle.putSerializable(IntentKey.KEY_OBJECT.getKey(), getSPFactor());
      bundle.putSerializable(IntentKey.KEY_OBJECT_ARTICLE.getKey(), getSPArticle());
      bundle.putSerializable(IntentKey.KEY_MERCH_INFO.getKey(), getMerchInfo());
      bundle.putInt(IntentKey.KEY_ARTICLE_COUNT.getKey(), getArticleCount());

      Navigation.findNavController(Objects.requireNonNull(getView())).navigate(
        R.id.action_merchandiseSPArticleEditFragment_to_merchandiseFragment, bundle);
    }
  }

  @Override
  public void afterSPArticleInserted() {
    Objects.requireNonNull(getActivity()).onBackPressed();
  }

  @Override
  public void afterSPArticleUpdated() {
    Objects.requireNonNull(getActivity()).finish();
  }

  @Override
  public void initLayout() {

    setFirstInitView(true);
    setHasOptionsMenu(false);
    Objects.requireNonNull(((BaseAppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
    Objects.requireNonNull(getActivity()).setTitle(R.string.title_add_edit_prefactor_article_activity);

    if (getMode() == Mode.NEW) {
      tvAddEditArticle.setText(BaseApplication.getResourceString(R.string.cpt_add_article));
      imgAddEditArticle.setImageDrawable(BaseApplication.getResourceDrawable(R.drawable.ic_add_w_24));
    } else if (getMode() == Mode.EDIT) {
      tvAddEditArticle.setText(BaseApplication.getResourceString(R.string.cpt_edit_article));
      imgAddEditArticle.setImageDrawable(BaseApplication.getResourceDrawable(R.drawable.ic_edit_w_24));
    }

    if (!isShowInventory() /*|| !isMerchStock()*/)
      clStockCabinet.setVisibility(View.GONE);

    etAmount.addTextChangedListener(priceComplexTextWatcher);
    etAmount.setOnFocusChangeListener(priceComplexFocusListener);
    etUnitPrice.addTextChangedListener(priceComplexTextWatcher);
    etUnitPrice.setOnFocusChangeListener(priceComplexFocusListener);
    etRemainder.addTextChangedListener(priceComplexTextWatcher);
    etRemainder.setOnFocusChangeListener(priceComplexFocusListener);
    etTotalPrice.addTextChangedListener(priceComplexTextWatcher);
    etTotalPrice.setOnFocusChangeListener(priceComplexFocusListener);


    etUnitPrice.setEnabled(true);
    etRemainder.setEnabled(true);
    etTotalPrice.setEnabled(true);
    imgLockUnitPrice.setVisibility(View.GONE);
    imgLockTotalPrice.setVisibility(View.GONE);
    imgLockRemainder.setVisibility(View.GONE);


    if (Connectivity.isConnectedOrConnecting(BaseApplication.getContext())) {
      if (!mPresenter.isShowImages() || getMerchInfo().getMerchThumbnailCount() == 0) {
        imageRecyclerView.setVisibility(View.GONE);
      } else {
        imageRecyclerView.setVisibility(View.VISIBLE);
      }
      clInventory.setVisibility(View.VISIBLE);
    } else {
      clInventory.setVisibility(View.GONE);
      imageRecyclerView.setVisibility(View.GONE);
    }

    if (mPresenter.isShowImages())
      initRecyclerView();
  }

  @Override
  public void initData() {

    mPresenter.start();

    if (clInventory.getVisibility() == View.VISIBLE)
      mPresenter.getLogicalInventory(getMerchInfo().getMerchId(),
        CalenderManager.getCurrentDate(), CalenderManager.getCurrentDate(), -1);
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

    if (!mPresenter.canModifyPriceDiscount()) {
      etUnitPrice.setEnabled(false);
      etRemainder.setEnabled(false);
      etTotalPrice.setEnabled(false);
      imgLockUnitPrice.setVisibility(View.VISIBLE);
      imgLockTotalPrice.setVisibility(View.VISIBLE);
      imgLockRemainder.setVisibility(View.VISIBLE);
    }

    if (/*isMerchStock() && */isShowInventory()) {
      tvStock.setText(getMerchInfo().getStockName());
      tvCabinet.setText(getMerchInfo().getCabinetName());
    }

    if (getSPArticle() == null || (getSPArticle().getMerchandiseId() != getMerchInfo().getMerchId())) {

      etAmount.setText("");
      etUnitPrice.setString(getMerchInfo().getCustSalesPrice());
      etRemainder.setText("");
      etTotalPrice.setText("");
    } else {

      /*etAmount.setText(doubleToStringNoDecimal(getSPArticle().getAmount()));
      etUnitPrice.setText(doubleToStringNoDecimal(getSPArticle().getUnitPrice()));
      etRemainder.setText(doubleToStringNoDecimal(getSPArticle().getRemainder()));
      etTotalPrice.setText(doubleToStringNoDecimal(getSPArticle().getTotalPrice()));*/


      etAmount.setString(getSPArticle().getAmount());
      etUnitPrice.setString(getSPArticle().getUnitPrice());
      etRemainder.setString(round(getSPArticle().getRemainder()));
      etTotalPrice.setString(getSPArticle().getTotalPrice());
      etTotalPrice.setString(round(getSPArticle().getTotalPrice()));

    }

    if (getSPArticle() != null)
      etDesc.setText(getSPArticle().getSPADesc());

    setFirstInitView(false);

    return false;
  }

  @Override
  public boolean validData(boolean showMsg) {

    SPArticle spArticle = mPresenter.getSPArticle();
    if (spArticle.getAmount() <= 0.0) {

      if (showMsg)
        showToast(BaseApplication.getResourceString(R.string.err_msg_invalid_amount));
      return false;
    }
    if (spArticle.getUnitPrice() <= 0.0) {

      if (showMsg)
        showToast(BaseApplication.getResourceString(R.string.err_msg_invalid_unitprice));
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

  private MerchandiseSPArticleEditAdapter getAdapterInstance() {
    if (mAdapter == null) {
      mAdapter = new MerchandiseSPArticleEditAdapter(this, mPresenter);
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
    /*if (etAmount.getText().toString().isEmpty()) {
      if (showMsg)
        etAmount.setError(BaseApplication.getResourceString(R.string.err_msg_invalid_amount));
      return false;

    } else {
      if (clInventory.getVisibility() == View.VISIBLE && !tvInvStock.getText().toString().isEmpty())
        if (!tvInvStock.getText().equals("---")) {
          float invStock = Float.parseFloat(DC.faToEn(tvInvStock.getText().toString()
            .replaceAll(",", "").replaceAll("٫", ".")));
          if (invStock <= 0 || (invStock > 0 && invStock -
            Float.parseFloat(DC.faToEn(etAmount.getText().toString().replaceAll(",", ""))) < 0))
            if (showMsg) {
              etAmount.setError(BaseApplication.getResourceString(R.string.err_msg_invalid_amount));
              return false;
            }
        }

    }

    if (etUnitPrice.getText().toString().isEmpty()) {
      if (showMsg)
        etUnitPrice.setError(BaseApplication.getResourceString(R.string.err_msg_invalid_unitprice));
      return false;

    } else {
      mPresenter.getSPArticle().setSPADesc(etDesc.getText().toString());
    }

    return true;*/


    if (etAmount.isEmpty()) {
      if (showMsg)
        etAmount.setError(BaseApplication.getResourceString(R.string.err_msg_invalid_amount));
      return false;

    } else {
      if (clInventory.getVisibility() == View.VISIBLE && !tvInvStock.getText().toString().isEmpty())
        if (!tvInvStock.getText().equals("---")) {
          float invStock = Float.parseFloat(DC.faToEn(tvInvStock.getText().toString()
            .replaceAll(",", "").replaceAll("٫", ".")));
          if (invStock <= 0 || (invStock > 0 && invStock -
            Float.parseFloat(DC.faToEn(Objects.requireNonNull(etAmount.getText()).toString().replaceAll(",", ""))) < 0))
            if (showMsg) {
              etAmount.setError(BaseApplication.getResourceString(R.string.err_msg_invalid_amount));
              return false;
            }
        }

    }

    if (etUnitPrice.isEmpty()) {
      if (showMsg)
        etUnitPrice.setError(BaseApplication.getResourceString(R.string.err_msg_invalid_unitprice));
      return false;

    } else {
      mPresenter.getSPArticle().setSPADesc(etDesc.getText().toString());
    }

    return true;


  }

/*  private String doubleToStringNoDecimal(double d) {
    String strTemp = String.valueOf(DC.dtostr(d));
    if (strTemp != null && strTemp.length() >= 2) {

      if (strTemp.substring(strTemp.length() - 2).equals(".0"))
        strTemp = strTemp.substring(0, strTemp.length() - 2);

      if (strTemp.contains("."))
        strTemp = new DecimalFormat("#.##").format(Double.parseDouble(strTemp));
    }
    return strTemp;
  }*/


  private View.OnFocusChangeListener priceComplexFocusListener = new View.OnFocusChangeListener() {
    public void onFocusChange(View v, boolean hasFocus) {
      if (hasFocus) {
        mPreviousFocusedView = mFocusedView;
        mFocusedView = v;
      } else {
        mFocusedView = null;
      }
    }
  };

  private TextWatcher priceComplexTextWatcher = new TextWatcher() {

    private double dAmount;
    private double dUnitPrice;
    private double dReminder;
    private double dTotalPrice;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable editable) {


      if (isFirstInitView())
        return;

      if (mFocusedView == etAmount) {
        //edittextTextChange(etAmount, etAmount.getDouble());

        // condition length() > 0 -----------------------------
        if (!etAmount.isEmpty()) {

          if (mPreviousFocusedView != mFocusedView) {
            //edittextTextCursor(etAmount);
            mPreviousFocusedView = mFocusedView;
          }


          dAmount = etAmount.getDouble();

          dUnitPrice = (!etUnitPrice.isEmpty()) ? etUnitPrice.getDouble() : mPresenter.getSPArticle().getUnitPrice();
          dReminder = round(((dAmount * dUnitPrice) * getMerchInfo().getCustSalesDiscount()) / 100);
          dTotalPrice = dAmount * dUnitPrice;

          // condition length() <= 0 -----------------------------
        } else {

          dAmount = 0;
          dUnitPrice = (!etUnitPrice.isEmpty()) ? etUnitPrice.getDouble() : mPresenter.getSPArticle().getUnitPrice();
          dReminder = 0;
          dTotalPrice = (!etRemainder.isEmpty()) ? etRemainder.getDouble() : mPresenter.getSPArticle().getRemainder();

        }
        // updateData sparticle -----------------------------
        updateData();

        // update dependency -----------------------------
        if (!etAmount.isEmpty() && !etUnitPrice.isEmpty()) {
          edittextTextChange(etTotalPrice, (round(dTotalPrice)));
          edittextTextChange(etRemainder, (round(dReminder)));
        } else {
          edittextTextChange(etTotalPrice, 0.0);
          edittextTextChange(etRemainder, 0.0);
        }

      } else if (mFocusedView == etUnitPrice) {


        // condition length() > 0 -----------------------------
        if (!etUnitPrice.isEmpty()) {

          // condition etAmount length() > 0 -----------------------------
          if (!etAmount.isEmpty()) {

            dAmount = etAmount.getDouble();
            dUnitPrice = etUnitPrice.getDouble();
            dReminder = round(((dAmount * dUnitPrice) * getMerchInfo().getCustSalesDiscount()) / 100);
            dTotalPrice = dAmount * dUnitPrice;

            // condition etAmount length() > 0 -----------------------------
          } else {
            dAmount = 0;
            dUnitPrice = etUnitPrice.getDouble();
            dTotalPrice = etTotalPrice.getDouble();
            dReminder = etRemainder.getDouble();
          }

          // condition length() <= 0 -----------------------------
        } else {

          dAmount = (!etAmount.isEmpty()) ? etAmount.getDouble() : mPresenter.getSPArticle().getAmount();
          dUnitPrice = 0;
          dReminder = 0;
          dTotalPrice = 0;

        }

        // updateData sparticle -----------------------------
        updateData();

        // update dependency -----------------------------
        if (!etAmount.isEmpty()) {
          edittextTextChange(etTotalPrice, round(dTotalPrice));
          edittextTextChange(etRemainder, round(dReminder));
        }

      } else if (mFocusedView == etRemainder) {


        // condition length() > 0 -----------------------------
        if (!etRemainder.isEmpty()) {

          dAmount = !etAmount.isEmpty() ? etAmount.getDouble() : mPresenter.getSPArticle().getAmount();
          dUnitPrice = !etUnitPrice.isEmpty() ? etUnitPrice.getDouble() : mPresenter.getSPArticle().getUnitPrice();
          dReminder = etRemainder.getDouble();

          // condition length() <= 0 -----------------------------
        } else {
          dAmount = etAmount.isEmpty() ? etAmount.getDouble() : mPresenter.getSPArticle().getAmount();
          dUnitPrice = (!etUnitPrice.isEmpty()) ? etUnitPrice.getDouble() : mPresenter.getSPArticle().getUnitPrice();
          dReminder = 0;
        }

        if (dAmount > 0 && dUnitPrice > 0)
          dTotalPrice = round(dAmount * dUnitPrice);// - dReminder;
        else
          dTotalPrice = 0;

        // updateData sparticle -----------------------------
        updateData();

        // update dependency -----------------------------


      } else if (mFocusedView == etTotalPrice) {

        // condition length() > 0 -----------------------------
        if (!etTotalPrice.isEmpty()) {

          // condition etAmount length() > 0 -----------------------------
          if (!etAmount.isEmpty()) {

            dAmount = etAmount.getDouble();
            dTotalPrice = etTotalPrice.getDouble();
            dUnitPrice = dTotalPrice / dAmount;
            dReminder = round(dTotalPrice * getMerchInfo().getCustSalesDiscount() / 100);

            // condition etAmount length() > 0 -----------------------------
          } else {
            dAmount = 0;
            dTotalPrice = etTotalPrice.getDouble();
            dUnitPrice = etUnitPrice.getDouble();
            dReminder = etRemainder.getDouble();
          }

          // condition length() > 0 -----------------------------
        } else {
          dAmount = ((!etAmount.isEmpty()) ? etAmount.getDouble() : mPresenter.getSPArticle().getAmount());
          dUnitPrice = 0;
          dReminder = 0;
          dTotalPrice = 0;
        }

        // updateData sparticle -----------------------------
        updateData();

        // update dependency -----------------------------
        if (!etAmount.isEmpty() && !etTotalPrice.isEmpty()) {
          edittextTextChange(etUnitPrice, dUnitPrice);
          edittextTextChange(etRemainder, dReminder);
        }
      }
    }

    void updateData() {
      mPresenter.getSPArticle().setAmount(dAmount);
      mPresenter.getSPArticle().setUnitPrice(dUnitPrice);
      mPresenter.getSPArticle().setRemainder(dReminder);
      mPresenter.getSPArticle().setTotalPrice(dTotalPrice);
    }

    void removeAllTextChangedListener() {
      etAmount.removeTextChangedListener(this);
      etUnitPrice.removeTextChangedListener(this);
      etRemainder.removeTextChangedListener(this);
      etTotalPrice.removeTextChangedListener(this);
    }

    void addAllTextChangedListener() {
      etAmount.addTextChangedListener(this);
      etUnitPrice.addTextChangedListener(this);
      etRemainder.addTextChangedListener(this);
      etTotalPrice.addTextChangedListener(this);
    }

    void edittextTextChange(UNumEditText editText, double dInput) {

      removeAllTextChangedListener();
      editText.setString(dInput);
      addAllTextChangedListener();
    }

    /*void edittextTextChange(EditTextUnSignedDouble editText, double dInput) {

      removeAllTextChangedListener();
      editText.setString(dInput);
      addAllTextChangedListener();
    }*/

    void edittextTextCursor(EditText editText) {

      removeAllTextChangedListener();
      editText.requestFocus();
      editText.setSelection(editText.getText().length());
      addAllTextChangedListener();
    }


  };

  @Override
  public void updateInventoryView(String... response) {
    if (response == null) {
      tvInvTotal.setText(BaseApplication.getResourceString(R.string.cpt_three_dash));
      tvInvStock.setText(BaseApplication.getResourceString(R.string.cpt_three_dash));

    } else if (response.length == 1) {
      tvInvTotal.setText(BaseApplication.getResourceString(R.string.cpt_three_dash));
      tvInvStock.setText(BaseApplication.getResourceString(R.string.cpt_three_dash));

    } else {
      tvInvTotal.setText(doubleToStringNoDecimalWithSeparator(Double.parseDouble(response[0])));
      tvInvStock.setText(doubleToStringNoDecimalWithSeparator(Double.parseDouble(response[1])));

    }

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

  private String doubleToStringNoDecimalWithSeparator(double dInput) {
    BigDecimal d = new BigDecimal(dInput);
    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.ENGLISH);
    formatter.applyPattern("#,###.##");
    return formatter.format(d);
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

  public boolean hasError() {
    return mHasError;
  }

  public void setHasError(boolean mHasError) {
    this.mHasError = mHasError;
  }


  @Override
  public SPFactor getSPFactor() {
    return mSPFactor;
  }

  public void setSPFactor(SPFactor spFactor) {
    mSPFactor = spFactor;
  }

  @Override
  public SPArticle getSPArticle() {
    return mSPArticle;
  }

  public void setSPArticle(SPArticle sPArticle) {
    this.mSPArticle = sPArticle;
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

  public boolean isFirstInitView() {
    return isFirstInitView;
  }

  private void setFirstInitView(boolean firstInitView) {
    isFirstInitView = firstInitView;
  }

}
