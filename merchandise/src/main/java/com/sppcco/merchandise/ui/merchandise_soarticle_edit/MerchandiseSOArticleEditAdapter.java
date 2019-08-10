package com.sppcco.merchandise.ui.merchandise_soarticle_edit;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.sppcco.core.data.remote.service.Webservice;
import com.sppcco.core.enums.SubsystemsId;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.core.util.network.Connectivity;
import com.sppcco.merchandise.R;
import com.sppcco.merchandise.R2;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MerchandiseSOArticleEditAdapter extends RecyclerView.Adapter<MerchandiseSOArticleEditAdapter.ViewHolder> {

  private MerchandiseSOArticleEditContract.View mView;
  private MerchandiseSOArticleEditContract.Presenter mPresenter;
  private ArrayList<Integer> mImageIds;
  private int itemPosition;

  MerchandiseSOArticleEditAdapter(
    MerchandiseSOArticleEditContract.View view,
    MerchandiseSOArticleEditContract.Presenter presenter) {
    mView = view;
    mPresenter = presenter;
  }

  public void loadAdapterData() {
    mImageIds = mPresenter.getImageIds();
  }

  private ArrayList<Integer> getImageIds() {
    return mImageIds;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.crd_merch_image, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
    showImages(viewHolder.imgMerchandise, viewHolder.prgLoading, mPresenter.getSOArticle().getMerchandiseId(), position);


    viewHolder.imgMerchandise.setOnClickListener(v -> {
      if (getImageIds().size() != 0) {
        mView.callImageGallery(mPresenter.getSOArticle().getMerchandiseId(), getImageIds(), position);
        setItemPosition(position);
      }
    });
  }

  int getItemPosition(){
    return itemPosition;
  }

  private void setItemPosition(int pos){
    itemPosition = pos;
  }

  @Override
  public int getItemCount() {
    return mPresenter.getImageIds().size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {


    @BindView(R2.id.img_merch)
    ImageView imgMerchandise;

    @BindView(R2.id.prg_loading)
    ProgressBar prgLoading;

    public ViewHolder(View view) {
      super(view);

      ButterKnife.bind(this, view);
      initLayout();
    }

    private void initLayout() {
      prgLoading.setVisibility(View.VISIBLE);
      imgMerchandise.setVisibility(View.VISIBLE);
    }

  }

  //--------------------------------------------------------------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------------------------
  private void showImages(ImageView imageView, ProgressBar prgLoading, int merchId, int position) {

    RequestOptions options = new RequestOptions()
      .fitCenter()
      .placeholder(R.drawable.ic_load_image)
      .error(R.drawable.ic_load_image);

    Glide
      .with(BaseApplication.getContext())
      .load(getImageUrl(merchId, position))
      .listener(new RequestListener<Drawable>() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
          prgLoading.setVisibility(View.GONE);
          return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
          prgLoading.setVisibility(View.GONE);
          return false;
        }
      })
      .apply(options)
      .into(imageView);
  }

  //--------------------------------------------------------------------------------------------------------------------
  private String getImageUrl(int merchId, int position) {
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
        .appendQueryParameter("ImageId", String.valueOf(getImageIds().get(position)))
        .appendQueryParameter("FPId", String.valueOf(BaseApplication.getFPId()))
        .appendQueryParameter("ServiceKey", BaseApplication.getApiKey())
        .appendQueryParameter("key", BaseApplication.getApiKey()).toString();
    }

    return image_url;
  }

  //--------------------------------------------------------------------------------------------------------------------
}
