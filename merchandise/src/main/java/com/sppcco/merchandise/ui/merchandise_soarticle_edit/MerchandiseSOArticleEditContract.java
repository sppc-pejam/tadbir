package com.sppcco.merchandise.ui.merchandise_soarticle_edit;

import com.sppcco.core.data.model.SOArticle;
import com.sppcco.core.data.model.SalesOrder;
import com.sppcco.core.data.sub_model.MerchInfo;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.listener.GenericResponseListener;
import com.sppcco.merchandise.IBasePresenter;
import com.sppcco.merchandise.IBaseView;

import java.util.ArrayList;

public interface MerchandiseSOArticleEditContract {

  interface View extends IBaseView<Presenter> {

    void initViewPager();

    SalesOrder getSalesOrder();

    SOArticle getSOArticle();

    MerchInfo getMerchInfo();

    Mode getMode();

    void afterSOArticleInserted();

    void afterSOArticleUpdated();

    void callImageGallery(int merchandiseId, ArrayList<Integer> ids, int position);

    int getMerchId();
  }

  interface Presenter extends IBasePresenter {

    boolean isShowImages();

    SOArticle getSOArticle();

    void insertSOArticle();

    void updateSOArticle();

    void getImageBase64(int merchId);

    void loadImages(int merchId);

    ArrayList<byte[]> getImages();

    ArrayList<Integer> getImageIds();

    void isRepeatedMerch(GenericResponseListener<Boolean> booleanGenericResponseListener);
  }

}
