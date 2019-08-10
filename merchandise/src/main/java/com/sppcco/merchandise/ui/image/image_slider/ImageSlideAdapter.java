package com.sppcco.merchandise.ui.image.image_slider;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.sppcco.core.framework.application.BaseApplication;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by b_nematzadeh on 9/6/2018.
 *
 */

public class ImageSlideAdapter extends PagerAdapter {

  ImageSlideContract.Presenter mPresenter;
  private ArrayList<byte[]> mImages;

  public ImageSlideAdapter(ImageSlideContract.Presenter presenter) {
    mPresenter = presenter;
  }

  public void loadAdapterData() {
    getImagesList();
  }

  private void getImagesList() {
    mImages = mPresenter.getImages();
  }

  @Override
  public int getCount() {
    return mImages.size();
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view == (ImageView) object;
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    PhotoView imageView = new PhotoView(BaseApplication.getContext());
    //imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    imageView.setPadding(10, 10, 10, 10);

    Glide.with(BaseApplication.getContext())
      .asBitmap()
      .load(mImages.get(position))
      .into(imageView);

    ((ViewPager) container).addView(imageView, 0);
    return imageView;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    ((ViewPager) container).removeView((ImageView) object);
  }
}
