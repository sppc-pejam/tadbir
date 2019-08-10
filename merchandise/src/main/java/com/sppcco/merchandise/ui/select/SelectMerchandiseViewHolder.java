package com.sppcco.merchandise.ui.select;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.sppcco.core.data.remote.service.Webservice;
import com.sppcco.core.data.sub_model.MerchInfo;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.enums.SubsystemsId;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.listener.StringArrayResponseListener;
import com.sppcco.core.listener.VoidResponseListener;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.core.util.network.Connectivity;
import com.sppcco.helperlibrary.converter.DC;
import com.sppcco.helperlibrary.manager.CalenderManager;
import com.sppcco.merchandise.R;
import com.sppcco.merchandise.R2;
import com.sppcco.merchandise.ui.image.image_slider.ImageSlideActivity;
import com.sppcco.merchandise.ui.select.SelectMerchandiseAdapter.MerchMoreInfo;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static com.sppcco.core.util.app.CoreConstants.APP_TAG;

/**
 * Created by m_pejam on 09/09/18.
 * SelectMerchandiseViewHolder
 */

class SelectMerchandiseViewHolder extends RecyclerView.ViewHolder {

  private SelectMerchandiseContract.View mView;
  private SelectMerchandiseContract.Presenter mPresenter;
  private SelectMerchandiseAdapter mAdapter;

  private MerchInfo mMerchInfo;


  @BindView(R2.id.img_merch)
  ImageView imgMerch;
  @BindView(R2.id.tv_name)
  AppCompatTextView tvName;
  @BindView(R2.id.tv_code)
  AppCompatTextView tvCode;
  @BindView(R2.id.cl_more)
  ConstraintLayout clMore;
  @BindView(R2.id.tv_inv_total)
  AppCompatTextView tvInvTotal;
  @BindView(R2.id.tv_inv_stock)
  AppCompatTextView tvInvStock;
  @BindView(R2.id.prg_sync_inv)
  ProgressBar prgSyncInv;
  @BindView(R2.id.btn_sync_inv)
  Button btnSyncInv;
  @BindView(R2.id.expl_actions)
  ExpandableLayout explActions;
  @BindView(R2.id.cl_actions)
  ConstraintLayout clActions;
  @BindView(R2.id.cl_merchandise)
  ConstraintLayout clMerchandise;
  @BindView(R2.id.tv_sales_price)
  AppCompatTextView tvSalesPrice;
  @BindView(R2.id.tv_sales_discount)
  AppCompatTextView tvSalesDiscount;
  @BindView(R2.id.tv_merchandise_desc)
  AppCompatTextView tvMerchandiseDesc;
  @BindView(R2.id.btn_more)
  AppCompatTextView btnMore;
  @BindView(R2.id.cl_inventory)
  ConstraintLayout clInventory;


  /*public SelectMerchandiseViewHolder(View view) {
    super(view);
  }*/

  SelectMerchandiseViewHolder(View view,
                              SelectMerchandiseAdapter selectMerchandiseAdapter,
                              SelectMerchandiseContract.Presenter merchandisePresenter,
                              SelectMerchandiseContract.View merchandiseView) {
    super(view);
    ButterKnife.bind(this, view);

    mAdapter = selectMerchandiseAdapter;
    mView = merchandiseView;
    mPresenter = merchandisePresenter;

    initLayout();
  }

  void bindTo(MerchInfo merchInfo) {

    setMerchInfo(merchInfo);

    itemView.setTag(merchInfo.getMerchId());
    tvName.setText(merchInfo.getMerchName());
    tvCode.setText(merchInfo.getMerchCode());
    tvMerchandiseDesc.setText(merchInfo.getMerchDesc());

    if (mAdapter.getSparseMerchIdAndMerchMoreInfo().indexOfKey(getMerchInfo().getMerchId()) >= 0) {

      explActions.expand();
      updateData(mAdapter.getMerchMoreInfo(getMerchInfo().getMerchId()), true);
    } else {

      explActions.collapse(false);
      btnMore.setText(BaseApplication.getResourceString(R.string.cpt_more_information));
    }

    if (getMerchInfo().getMerchThumbnailCount() > 0)
      showImages(merchInfo.getMerchId());
    else {
      imgMerch.setImageDrawable(BaseApplication.getResourceDrawable((R.drawable.ic_no_photo_g_512)));
    }
  }

  void clear() {

    itemView.invalidate();
    tvName.invalidate();
    tvCode.invalidate();
    tvMerchandiseDesc.invalidate();
    imgMerch.invalidate();
    explActions.invalidate();
  }

  private MerchInfo getMerchInfo() {
    return mMerchInfo;
  }

  private void setMerchInfo(MerchInfo merchInfo) {
    this.mMerchInfo = merchInfo;
  }


  @OnClick(R2.id.cl_merchandise)
  void onClMerchandiseClickListener() {

    MerchMoreInfo merchMoreInfo;
    if (mAdapter.getSparseMerchIdAndMerchMoreInfo().indexOfKey(getMerchInfo().getMerchId()) < 0) {
      merchMoreInfo = MerchMoreInfo.getMerchMoreInfoWithDefaultValue();
      updateData(merchMoreInfo, false);
    } else {
      merchMoreInfo = mAdapter.getSparseMerchIdAndMerchMoreInfo().get(getMerchInfo().getMerchId());
      getMerchInfo().setCustSalesPrice(merchMoreInfo.getSalesPrice());
      getMerchInfo().setCustSalesDiscount(merchMoreInfo.getSalesDiscount());

      mView.callEditFragment(getMerchInfo());
    }
  }

  @OnLongClick(R2.id.cl_merchandise)
  boolean onClMerchandiseLongClickListener() {
    onClMerchandiseClickListener();
    return true;
  }

  @OnClick(R2.id.cl_more)
  void onBtnMoreClickListener() {
    //Toast.makeText(BaseApplication.getCurrentActivity(), "onclMoreClickListener", Toast.LENGTH_SHORT).show();

    explActions.toggle();
    if (explActions.isExpanded()) {

      MerchMoreInfo merchMoreInfo = MerchMoreInfo.getMerchMoreInfoWithDefaultValue();
      merchMoreInfo.setAdapterPosition(getAdapterPosition());
      mAdapter.getSparseMerchIdAndMerchMoreInfo().put(getMerchInfo().getMerchId(), merchMoreInfo);

      tvInvTotal.setText(null);
      tvInvStock.setText(null);

      bindTo(getMerchInfo());
    } else {

      mAdapter.getSparseMerchIdAndMerchMoreInfo().delete(getMerchInfo().getMerchId());
      getMerchInfo().setCustSalesPrice(-1);
      getMerchInfo().setCustSalesDiscount(-1);
      btnMore.setText(BaseApplication.getResourceString(R.string.cpt_more_information));
    }

  }


  @OnClick(R2.id.btn_sync_inv)
  void onBtnSyncInvClickListener() {
    //Toast.makeText(BaseApplication.getCurrentActivity(), "onbtnSyncInvClickListener", Toast.LENGTH_SHORT).show();

    tvInvTotal.setVisibility(View.INVISIBLE);
    tvInvStock.setVisibility(View.INVISIBLE);
    prgSyncInv.setVisibility(View.VISIBLE);

    mPresenter.getLogicalInventory(getMerchInfo().getMerchId(),
      CalenderManager.getCurrentDate(), CalenderManager.getCurrentDate(),
      -1, new StringArrayResponseListener() {
        @Override
        public void onSuccess(String... strings) {
          setInventoryValues(strings);
        }

        @Override
        public void onFailure() {
          tvInvTotal.setVisibility(View.INVISIBLE);
          tvInvStock.setVisibility(View.INVISIBLE);
          prgSyncInv.setVisibility(View.INVISIBLE);
        }
      });
  }


  @OnClick(R2.id.img_merch)
  void onImgMerchClickListener() {
    if (getMerchInfo().getMerchThumbnail() != null) {
      mPresenter.getRemoteImageIds(getMerchInfo().getMerchId(),
        () -> {
          mView.onHideProgress();
          mView.callImageGallery(getMerchInfo().getMerchId(), mPresenter.getImageIds(), 0);
        });
      /*if (getMerchInfo().getMerchThumbnailCount() == 1) {
        showImageDialog(imgMerch.getDrawable(), tvName.getText().toString());
      } else if (getMerchInfo().getMerchThumbnailCount() > 1) {
        showImageSlider();
      }*/
    } else
      onClMerchandiseClickListener();
  }


  @OnClick(R2.id.cl_actions)
  void onClActionsClickListener() {
    //Toast.makeText(BaseApplication.getCurrentActivity(), "oclActionsnClickListener", Toast.LENGTH_SHORT).show();
  }


  //--------------------------------------------------------------------------------------------------------------------

  private void setInventoryValues(String... inventoryValues) {
    final int TOTAL_INV_POS = 0;
    final int STOCK_INV_POS = 1;
    final int MERCH_ID_POS = 2;

    if (mAdapter.getSparseMerchIdAndMerchMoreInfo().indexOfKey(Integer.parseInt(inventoryValues[MERCH_ID_POS])) < 0)
      return;

    MerchMoreInfo merchMoreInfo = mAdapter.getSparseMerchIdAndMerchMoreInfo().get(Integer.parseInt(inventoryValues[MERCH_ID_POS]));

    try {
      double invTotal = Double.parseDouble(inventoryValues[TOTAL_INV_POS]);

      if (invTotal <= 0)
        merchMoreInfo.setColorInvTotal(Color.RED);
      else
        merchMoreInfo.setColorInvTotal(Color.GREEN);

      //merchMoreInfo.setInvTotal(new DecimalFormat("##.##").format(invTotal));
      merchMoreInfo.setInvTotal(doubleToStringNoDecimalWithSeparator(invTotal));
    } catch (NumberFormatException e) {
      merchMoreInfo.setColorInvTotal(Color.BLACK);
      merchMoreInfo.setInvTotal("");
    }

    try {
      double invStock = Double.parseDouble(inventoryValues[STOCK_INV_POS]);

      if (invStock <= 0)
        merchMoreInfo.setColorInvStock(Color.RED);
      else
        merchMoreInfo.setColorInvStock(Color.GREEN);

      //merchMoreInfo.setInvStock(new DecimalFormat("##.##").format(invStock));
      merchMoreInfo.setInvStock(doubleToStringNoDecimalWithSeparator(invStock));
    } catch (NumberFormatException e) {

      merchMoreInfo.setColorInvStock(Color.BLACK);
      merchMoreInfo.setInvStock("");
    }

    tvInvTotal.setVisibility(View.VISIBLE);
    tvInvStock.setVisibility(View.VISIBLE);
    prgSyncInv.setVisibility(View.INVISIBLE);

    mAdapter.notifyItemChanged(merchMoreInfo.getAdapterPosition());
  }


  //--------------------------------------------------------------------------------------------------------------------

  private String doubleToStringNoDecimalWithSeparator(double dInput) {
    BigDecimal d = new BigDecimal(dInput);
    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.ENGLISH);
    formatter.applyPattern("#,###.##");
    return formatter.format(d);
  }

  /*
   * init and show image on crad merchandise
   */
  private void showImages(int merchId) {
    Glide
      .with(BaseApplication.getContext())
      .load(getImageUrl(merchId))
      .listener(getRequestListener())
      .apply(getRequestOptions())
      .into(imgMerch);
  }

  //--------------------------------------------------------------------------------------------------------------------
  private String getImageUrl(int merchId) {
    String image_url = null;

    if (Connectivity.isConnectedOrConnecting(BaseApplication.getContext()) && mPresenter.isShowImages()) {
      image_url = new Uri.Builder()
        .scheme("http")
        .encodedAuthority(BaseApplication.getIPServer() + ":" + BaseApplication.getBusinessServicePort())
        .appendPath(Webservice.BUSINESS_ENTITY)
        .appendPath(Webservice.BUSINESS_IMAGE_ID_FUNC)
        .appendQueryParameter("IP", BaseApplication.getIPServer())
        .appendQueryParameter("Port", BaseApplication.getDataBaseServicePort())
        .appendQueryParameter("dbName", BaseApplication.getDatabaseName())
        .appendQueryParameter("Version", CoreConstants.getServiceVersion())
        .appendQueryParameter("Group", Webservice.DIR_IMAGE_DLL_GROUP_NAME)
        .appendQueryParameter("Entity", Webservice.DIR_IMAGE_DLL_ENTITY_NAME)
        .appendQueryParameter("Function", Webservice.DIR_IMAGE_DLL_FUNCTION_NAME)
        .appendQueryParameter("MerchId", String.valueOf(merchId))
        .appendQueryParameter("SysId", String.valueOf(SubsystemsId.INVENTORY_SYS.getValue()))
        .appendQueryParameter("FormId", String.valueOf(2))
        .appendQueryParameter("ImageId", String.valueOf(0))
        .appendQueryParameter("FPId", String.valueOf(BaseApplication.getFPId()))
        .appendQueryParameter("ServiceKey", BaseApplication.getApiKey())
        .appendQueryParameter("key", BaseApplication.getApiKey()).toString();
    }

    return image_url;
  }

  //--------------------------------------------------------------------------------------------------------------------
  private RequestOptions getRequestOptions() {

    RequestOptions requestOptions =
      new RequestOptions()
        .placeholder(R.drawable.ic_dots_g_24)
        .error(R.drawable.ic_no_photo_g_512)
        .fitCenter();

    if (!Connectivity.isConnectedOrConnecting(BaseApplication.getContext()))
      return requestOptions.fallback(R.drawable.ic_no_wifi_g_24);
    else
      return requestOptions;
  }

  //--------------------------------------------------------------------------------------------------------------------
  private RequestListener getRequestListener() {
    return new RequestListener<Drawable>() {
      @Override
      public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
        imgMerch.setBackgroundColor(BaseApplication.getCoreResources().getColor(R.color.backgroundColor));
        return false;
      }

      @Override
      public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
        imgMerch.setBackgroundColor(Color.WHITE);
        return false;
      }
    };
  }

  //--------------------------------------------------------------------------------------------------------------------
  private void showImageSlider() {

    Intent intent = new Intent(BaseApplication.getCurrentActivity(), ImageSlideActivity.class);
    Bundle bundle = new Bundle();
    bundle.putInt(IntentKey.KEY_MERCH_ID.getKey(), getMerchInfo().getMerchId());
    intent.putExtras(bundle);
    assert BaseApplication.getCurrentActivity() != null;
    BaseApplication.getCurrentActivity().startActivity(intent);
  }

  //--------------------------------------------------------------------------------------------------------------------
  private void showImageDialog(Drawable imageDrawable, String title) {
    assert BaseApplication.getCurrentActivity() != null;
    final Dialog dialog = new Dialog(BaseApplication.getCurrentActivity(), R.style.AppThemeDialog);
    dialog.setContentView(R.layout.dialog_show_image);
    Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.background_light);

    TextView textView = dialog.findViewById(R.id.tv_name);
    textView.setText(title);
    textView.setTextColor(Color.BLACK);

    PhotoView photoView = dialog.findViewById(R.id.img_merch);
    photoView.setImageDrawable(imageDrawable);

    dialog.getWindow().getAttributes().width = ConstraintLayout.LayoutParams.MATCH_PARENT;
    dialog.getWindow().getAttributes().height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
    dialog.show();
  }

  //--------------------------------------------------------------------------------------------------------------------
  private void initLayout() {

    if (!mView.isShowInventory()) {
      clInventory.setVisibility(View.GONE);
    } else {
      tvInvTotal.setVisibility(View.VISIBLE);
      tvInvStock.setVisibility(View.VISIBLE);
      prgSyncInv.setVisibility(View.INVISIBLE);
    }

    if (!mPresenter.isShowImages())
      imgMerch.setVisibility(View.GONE);
  }

  //--------------------------------------------------------------------------------------------------------------------
  private void updateView(MerchMoreInfo merchMoreInfo) {

    tvSalesPrice.setText(String.valueOf(DC.dtostr(merchMoreInfo.getSalesPrice())));
    tvSalesDiscount.setText(String.valueOf(DC.dtostr(merchMoreInfo.getSalesDiscount())));

    if (mView.isShowInventory())
      if (!merchMoreInfo.getInvTotal().equals("") && !merchMoreInfo.getInvStock().equals("")) {
        tvInvTotal.setText(merchMoreInfo.getInvTotal());
        tvInvTotal.setTextColor(merchMoreInfo.getColorInvTotal());
        tvInvStock.setText(merchMoreInfo.getInvStock());
        tvInvStock.setTextColor(merchMoreInfo.getColorInvStock());
      }
  }

  //--------------------------------------------------------------------------------------------------------------------
  private void updateData(MerchMoreInfo merchMoreInfo, boolean bUpdateView) {

    if (merchMoreInfo.getSalesPrice() < 0 || merchMoreInfo.getSalesDiscount() < 0) {

      mPresenter.getSalesPriceAndSalesDiscount(getMerchInfo(), new VoidResponseListener() {
        @Override
        public void onSuccess() {
          if (getMerchInfo().getCustSalesPrice() >= 0 && getMerchInfo().getCustSalesDiscount() >= 0) {

            merchMoreInfo.setSalesPrice(getMerchInfo().getCustSalesPrice());
            merchMoreInfo.setSalesDiscount(getMerchInfo().getCustSalesDiscount());

            if (bUpdateView) {
              updateView(merchMoreInfo);
              btnMore.setText(BaseApplication.getResourceString(R.string.cpt_close));

            } else {
              mView.callEditFragment(getMerchInfo());
            }
          }
        }

        @Override
        public void onFailure() {
          Log.e(APP_TAG, "getSalesPriceAndSalesDiscount - onFailure");
        }
      });

    } else {
      updateView(merchMoreInfo);
      btnMore.setText(BaseApplication.getResourceString(R.string.cpt_close));

    }
  }
}

