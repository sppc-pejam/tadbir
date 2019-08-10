package com.sppcco.merchandise.ui.image.image_slider;

import com.sppcco.merchandise.IBasePresenter;
import com.sppcco.merchandise.IBaseView;

import java.util.ArrayList;

/**
 * Created by b_nematzadeh on 9/6/2018.
 */

public interface ImageSlideContract {

  interface View extends IBaseView<Presenter> {
    int getMerchId();
  }

  interface Presenter extends IBasePresenter {

    void loadImages(int merchId);

    ArrayList<byte[]> getImages();
  }

}
