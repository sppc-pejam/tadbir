package com.sppcco.merchandise.ui.image.image_slider;

import android.util.Base64;

import com.sppcco.core.data.local.db.repository.ImageRepository;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by b_nematzadeh on 9/6/2018.
 *
 */

public class ImageSlidePresenter extends BasePresenter implements ImageSlideContract.Presenter {

  private static ImageSlidePresenter INSTANCE;
  private ImageSlideContract.View mView;

  private ArrayList<byte[]> mImages;

  public ImageSlidePresenter(@NonNull ImageSlideContract.View view) {
    mView = view;
    mView.setPresenter(this);
  }

  public static ImageSlideContract.Presenter getImageSlidePresenterInstance(@NonNull ImageSlideContract.View view) {
    if (INSTANCE == null) {
      INSTANCE = new ImageSlidePresenter(view);
    }
    return INSTANCE;
  }

  @Override
  public void start() {
    loadImages(mView.getMerchId());
    mView.updateView();
  }

  @Override
  public void destroy() {
    INSTANCE = null;
  }

  @Override
  public void loadImages(int merchId) {
    getCoreDB().imageRepository().getThumbnailFormMerchandiseId(merchId, BaseApplication.getFPId(),
      new ImageRepository.GetThumbnailCallback() {
        @Override
        public void onGetThumbnailLoaded(List<String> thumbnailL) {
          setImages(thumbnailL);
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  @Override
  public ArrayList<byte[]> getImages() {
    return mImages;
  }

  private void setImages(List<String> images) {
    mImages = new ArrayList<>();
    for (int i = 0; i < images.size(); i++) {
      byte[] decodedString = Base64.decode(images.get(i), Base64.DEFAULT);
      //Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
      mImages.add(decodedString);
    }

    mView.initData();
  }
}
