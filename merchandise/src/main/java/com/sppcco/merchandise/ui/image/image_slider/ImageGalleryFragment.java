package com.sppcco.merchandise.ui.image.image_slider;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.sppcco.core.data.remote.service.Webservice;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.enums.SubsystemsId;
import com.sppcco.core.framework.activity.BaseAppCompatActivity;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.merchandise.R;
import com.sppcco.merchandise.R2;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageGalleryFragment extends DialogFragment {

  @BindView(R2.id.image_view)
  PhotoView imageView;
  @BindView(R2.id.prg_loading)
  ProgressBar prgLoading;
  @BindView(R2.id.img_close)
  ImageView imgClose;
  @BindView(R2.id.recycler_view_image)
  RecyclerView recyclerViewImage;
  /*@BindView(R.id.gallery)
  Gallery gallery;*/

  private ImageGalleryAdapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;

  private int merchId;
  private ArrayList<Integer> imageIds = new ArrayList<>();
  private int position;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.image_gallery, container, false);
    ButterKnife.bind(this, view);

    Objects.requireNonNull(((BaseAppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
    //ViewManager.closeKeyboard(Objects.requireNonNull(getActivity()));

    if (getArguments() != null) {
      setMerchId(getArguments().getInt(IntentKey.KEY_MERCH_ID.getKey()));
      setImageIds(getArguments().getIntegerArrayList(IntentKey.KEY_IMAGE_GALLERY_VALUE.getKey()));
      setPosition(getArguments().getInt(IntentKey.KEY_IMAGE_GALLERY_POSITION.getKey()));
    }

    initRecyclerView();
    loadRecyclerViewItem();

    if (getPosition() != 0)
      recyclerViewImage.scrollToPosition(getPosition());

    recyclerViewImage.addOnScrollListener(recyclerViewOnScrollListener);
    //showImages(imageView, prgLoading, getPosition());

    return view;
  }

  private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
      super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
      super.onScrolled(recyclerView, dx, dy);
      position = ((LinearLayoutManager) Objects.requireNonNull(recyclerViewImage.getLayoutManager())).findFirstCompletelyVisibleItemPosition();
      showImages(imageView, prgLoading, position);
    }
  };

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    return dialog;
  }


  private void initRecyclerView() {
    recyclerViewImage.setHasFixedSize(true);

    mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
    recyclerViewImage.setLayoutManager(mLayoutManager);
    //recyclerViewImage.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    recyclerViewImage.setAdapter(mAdapter);
  }

  private ImageGalleryAdapter getAdapterInstance() {
    if (mAdapter == null) {
      mAdapter = new ImageGalleryAdapter();
    }
    mAdapter.loadAdapterData();
    return mAdapter;
  }

  private void loadRecyclerViewItem() {
    mAdapter = getAdapterInstance();
    recyclerViewImage.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();
  }

  public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ViewHolder> {
    private ArrayList<Integer> mImageIds;

    public void loadAdapterData() {
      mImageIds = getImageIds();
    }

    private ArrayList<Integer> getIds() {
      return mImageIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.crd_gallery_merch_image, viewGroup, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
      showImages(viewHolder.imgMerchandise, viewHolder.prgLoading, position);

      viewHolder.imgMerchandise.setOnClickListener(v -> {
        if (getImageIds().size() != 0)
          showImages(imageView, prgLoading, position);
      });
    }

    @Override
    public int getItemCount() {
      return getIds().size();
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
  }

  @OnClick({R2.id.img_close})
  public void onViewClicked(View view) {
    if (view.getId() == R.id.img_close) {
      dismiss();
    }
  }

  //--------------------------------------------------------------------------------------------------------------------
  private void showImages(ImageView imageView, ProgressBar prgLoading, int position) {

    RequestOptions options = new RequestOptions()
      .fitCenter()
      .placeholder(R.drawable.ic_load_image)
      .error(R.drawable.ic_load_image);

    Glide
      .with(BaseApplication.getContext())
      .load(getImageUrl(position))
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
  private String getImageUrl(int position) {
    String image_url;

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
      .appendQueryParameter("MerchId", String.valueOf(getMerchId()))
      .appendQueryParameter("ImageId", String.valueOf(getImageIds().get(position)))
      .appendQueryParameter("SysId", String.valueOf(SubsystemsId.INVENTORY_SYS.getValue()))
      .appendQueryParameter("FormId", String.valueOf(2))
      .appendQueryParameter("FPId", String.valueOf(BaseApplication.getFPId()))
      .appendQueryParameter("ServiceKey", BaseApplication.getApiKey())
      .appendQueryParameter("key", BaseApplication.getApiKey()).toString();

    return image_url;
  }

  //--------------------------------------------------------------------------------------------------------------------
  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////////////////////////////////////////////

  public int getMerchId() {
    return merchId;
  }

  private ArrayList<Integer> getImageIds() {
    return imageIds;
  }

  public int getPosition() {
    return position;
  }

  public void setMerchId(int id) {
    merchId = id;
  }

  public void setPosition(int pos) {
    position = pos;
  }

  private void setImageIds(ArrayList<Integer> ids) {
    imageIds = ids;
  }
}
