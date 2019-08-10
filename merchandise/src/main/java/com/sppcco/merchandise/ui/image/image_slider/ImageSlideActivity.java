package com.sppcco.merchandise.ui.image.image_slider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;

import com.itsronald.widget.ViewPagerIndicator;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.merchandise.R;
import com.sppcco.merchandise.R2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by b_nematzadeh on 9/6/2018.
 */

public class ImageSlideActivity extends AppCompatActivity implements ImageSlideContract.View {


  private ImageSlideContract.Presenter mPresenter;
  private ImageSlideAdapter imageSlideAdapter;
  private ViewPagerIndicator viewPagerIndicator;
  private ViewPager.LayoutParams layoutParams;
  private int imageId;

  Unbinder unbinder;

  @BindView(R2.id.view_pager)
  ViewPager viewPager;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

    int width = (int)(getResources().getDisplayMetrics().widthPixels*0.95);
    int height = (int)(getResources().getDisplayMetrics().heightPixels*0.75);
    this.getWindow().setLayout(width, height);

    setContentView(R.layout.activity_image_slider);

    unbinder = ButterKnife.bind(this);
    initLayout();

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      setImageId(extras.getInt(IntentKey.KEY_MERCH_ID.getKey()));
    }

    mPresenter = ImageSlidePresenter.getImageSlidePresenterInstance(this);
    mPresenter.start();

    if (imageSlideAdapter == null) {
      imageSlideAdapter = new ImageSlideAdapter(mPresenter);
    }
  }

  @Override
  public void setPresenter(ImageSlideContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void initLayout() {

    layoutParams = new ViewPager.LayoutParams();
    layoutParams.width = ConstraintLayout.LayoutParams.MATCH_PARENT;
    layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
    layoutParams.gravity = Gravity.BOTTOM;

    viewPagerIndicator = new ViewPagerIndicator(this);
    viewPagerIndicator.setSelectedDotColor(Color.RED);
    viewPagerIndicator.setUnselectedDotColor(Color.GRAY);
    viewPagerIndicator.setPadding(0, 0, 0, 20);
  }

  @Override
  public void initData() {
    imageSlideAdapter.loadAdapterData();
    viewPager.setAdapter(imageSlideAdapter);
    viewPager.addView(viewPagerIndicator, layoutParams);
  }

  @Override
  public boolean updateModel() {
    return false;
  }

  @Override
  public boolean updateView() {
    return false;
  }

  @Override
  public boolean validData(boolean showMsg) {
    return false;
  }


  @Override
  protected void onDestroy() {
    super.onDestroy();

    unbinder.unbind();
    if (mPresenter != null)
      mPresenter.destroy();
  }

  @Override
  public int getMerchId() {
    return imageId;
  }

  public void setImageId(int id) {
    imageId = id;
  }
}
