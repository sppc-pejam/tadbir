package com.sppcco.merchandise.ui.merchandise_sparticle_edit;

import com.sppcco.core.data.model.SPArticle;
import com.sppcco.core.data.model.SPFactor;
import com.sppcco.core.data.sub_model.MerchInfo;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.listener.GenericResponseListener;
import com.sppcco.merchandise.IBasePresenter;
import com.sppcco.merchandise.IBaseView;

import java.util.ArrayList;

public interface MerchandiseSPArticleEditContract {

  interface View extends IBaseView<Presenter> {

    void initViewPager();

    Mode getMode();

    SPFactor getSPFactor();

    SPArticle getSPArticle();

    MerchInfo getMerchInfo();

    void afterSPArticleInserted();

    void afterSPArticleUpdated();

    boolean hasError();

    void setHasError(boolean mHasError);

    void updateInventoryView(String... response);

    void callImageGallery(int merchandiseId, ArrayList<Integer> ids, int position);

    int getMerchId();
  }

  interface Presenter extends IBasePresenter {

    boolean isShowImages();

    SPArticle getSPArticle();

    void insertSPArticle();

    void updateSPArticle();

    void getLogicalInventory(int merchId, String startDate, String endDate, int committed);

    void getImageBase64(int merchId);

    void loadImages(int merchId);

    ArrayList<byte[]> getImages();

    ArrayList<Integer> getImageIds();

    boolean canModifyPriceDiscount();

    void isRepeatedMerch(GenericResponseListener<Boolean> booleanGenericResponseListener);
  }

}
