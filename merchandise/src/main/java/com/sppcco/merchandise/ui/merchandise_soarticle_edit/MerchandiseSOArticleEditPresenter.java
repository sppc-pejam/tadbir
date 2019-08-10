package com.sppcco.merchandise.ui.merchandise_soarticle_edit;

import android.util.Base64;
import android.util.Log;

import com.sppcco.core.data.local.db.repository.ImageRepository;
import com.sppcco.core.data.local.db.repository.OptionRepository;
import com.sppcco.core.data.local.db.repository.SOArticleRepository;
import com.sppcco.core.data.model.SOArticle;
import com.sppcco.core.data.remote.repository.ImageRemoteRepository;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.enums.OptionId;
import com.sppcco.core.enums.ResponseType;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.core.listener.DoneResponseListener;
import com.sppcco.core.listener.GenericResponseListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;

import static com.sppcco.core.util.app.CoreConstants.APP_TAG;


public class MerchandiseSOArticleEditPresenter extends BasePresenter implements MerchandiseSOArticleEditContract.Presenter {

  private MerchandiseSOArticleEditContract.View mView;


  private SOArticle mSOArticle;
  private ArrayList<byte[]> mImages;
  private ArrayList<Integer> mImageIds;

  private boolean mCheckRepeatedMerch;

  private final CompositeDisposable disposables = new CompositeDisposable();

  private MerchandiseSOArticleEditPresenter(@NonNull MerchandiseSOArticleEditContract.View view) {

    mView = view;
    mView.setPresenter(this);
  }


  static MerchandiseSOArticleEditContract.Presenter getMerchandiseEditPresenterInstance(
    @NonNull MerchandiseSOArticleEditContract.View view) {

    return new MerchandiseSOArticleEditPresenter(view);
  }

  @Override
  public void destroy() {

  }

  @Override
  public void start() {

    initOptions(() -> {

      if (mView.getMode() == Mode.NEW) {

        setSOArticle(SOArticle.getSOArticleWithDefaultValue());
        getSOArticle().setSOId(mView.getSalesOrder().getId());
        initSOArticleMerchInfo();

      } else if (mView.getMode() == Mode.EDIT) {

        setSOArticle(mView.getSOArticle());
        if (getSOArticle().getMerchandiseId() != mView.getMerchInfo().getMerchId())
          initSOArticleMerchInfo();
      }

      if (isShowImages()) {
        if (mImages == null)
          getImageIds(mView.getMerchId());
          //getImageBase64(mView.getMerchId());
        else
          mView.initViewPager();
      }

      mView.updateView();
    });
  }

  private void initOptions(DoneResponseListener doneResponseListener) {
    getCoreDB().optionRepository().getOptionValueById(
      OptionId.OPT_CHECK_REPEATED_MERCH.getValue(), new OptionRepository.GetOptionValueCallback() {
        @Override
        public void onOptionValueLoaded(String optionValue) {
          setCheckRepeatedMerch(optionValue.equals("1"));
          doneResponseListener.onDone();
        }

        @Override
        public void onDataNotAvailable() {
        }
      });
  }

  @Override
  public boolean isShowImages() {
    return getCorePref().getPrefImplementation().isShowImages();
  }

  private void initSOArticleMerchInfo() {

    getSOArticle().setMerchandiseId(mView.getMerchInfo().getMerchId());
    getSOArticle().setMerchandiseCode(mView.getMerchInfo().getMerchCode());
    getSOArticle().setMerchandiseName(mView.getMerchInfo().getMerchName());
    getSOArticle().setUnitId(mView.getMerchInfo().getMerchUnitId());
    getSOArticle().setUnitName(mView.getMerchInfo().getMerchUnitName());
  }

  @Override
  public void insertSOArticle() {

    getCoreDB().sOArticleRepository().insertSOArticle(getSOArticle(), new SOArticleRepository.InsertSOArticleCallback() {

      @Override
      public void onSOArticleInserted(long sOArticleId) {

        mView.afterSOArticleInserted();
      }

      @Override
      public void onDataNotAvailable() {
        Log.e(APP_TAG, "saveSOArticle - onFailure");
      }

    });
  }

  @Override
  public void updateSOArticle() {
    getCoreDB().sOArticleRepository().updateSOArticle(getSOArticle(), new SOArticleRepository.UpdateSOArticleCallback() {
      @Override
      public void onSOArticleUpdated(int i) {

        mView.afterSOArticleUpdated();
      }

      @Override
      public void onDataNotAvailable() {

        Log.e(APP_TAG, "updateSOArticle - onFailure");
      }
    });
  }

  @Override
  public void getImageBase64(int merchId) {

    disposables.add(getCoreNet().imageRemoteControlRepository().getBase64ImageById(merchId, true,
      new ImageRemoteRepository.LoadListStringCallback() {

        @Override
        public void onResponse(List<String> imagesList) {
          setImages(imagesList);
        }

        @Override
        public void onFailure(ResponseType responseType) {
          mView.initViewPager();
        }
      })
    );
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
          mView.initViewPager();
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
      mImages.add(decodedString);
    }

    mView.initViewPager();
  }

  private void getImageIds(int merchId) {
    disposables.add(getCoreNet().imageRemoteControlRepository().GetImageIds(merchId,
      new ImageRemoteRepository.LoadListIntegerCallback() {

        @Override
        public void onResponse(List<Integer> list) {
          setImageIds(list);
        }

        @Override
        public void onFailure(ResponseType responseType) {
        }
      })
    );
  }

  @Override
  public ArrayList<Integer> getImageIds() {
    return mImageIds;
  }

  private void setImageIds(List<Integer> ids) {
    mImageIds = new ArrayList<>();
    mImageIds.addAll(ids);
    mView.initViewPager();
  }

  @Override
  public void isRepeatedMerch(GenericResponseListener<Boolean> booleanGenericResponseListener) {

    if (!isCheckRepeatedMerch())
      booleanGenericResponseListener.onResponse(false);
    else
      getCoreDB().sOArticleRepository().existMerchInSO(mView.getSalesOrder().getId(), getSOArticle().getId(),
        getSOArticle().getMerchandiseId(), BaseApplication.getFPId(), count -> {
          if(count == 0)
            booleanGenericResponseListener.onResponse(false);
          else
            booleanGenericResponseListener.onResponse(true);
        });
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public SOArticle getSOArticle() {
    return mSOArticle;
  }

  private void setSOArticle(SOArticle mSOArticle) {
    this.mSOArticle = mSOArticle;
  }

  private boolean isCheckRepeatedMerch() {
    return mCheckRepeatedMerch;
  }

  private void setCheckRepeatedMerch(boolean checkRepeatedMerch) {
    this.mCheckRepeatedMerch = checkRepeatedMerch;
  }

}