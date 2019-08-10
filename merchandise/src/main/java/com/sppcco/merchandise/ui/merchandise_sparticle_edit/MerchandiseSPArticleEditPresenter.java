package com.sppcco.merchandise.ui.merchandise_sparticle_edit;

import android.util.Base64;
import android.util.Log;

import com.sppcco.core.data.local.db.repository.ErrorStatusRepository;
import com.sppcco.core.data.local.db.repository.ImageRepository;
import com.sppcco.core.data.local.db.repository.OptionRepository;
import com.sppcco.core.data.local.db.repository.RightsRepository;
import com.sppcco.core.data.local.db.repository.SPArticleRepository;
import com.sppcco.core.data.model.SPArticle;
import com.sppcco.core.data.remote.repository.ImageRemoteRepository;
import com.sppcco.core.data.remote.repository.MerchandiseRemoteRepository;
import com.sppcco.core.enums.DocType;
import com.sppcco.core.enums.FormId;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.enums.OptionId;
import com.sppcco.core.enums.PreFactorRightPos;
import com.sppcco.core.enums.ResponseType;
import com.sppcco.core.enums.SubsystemsId;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.core.listener.DoneResponseListener;
import com.sppcco.core.listener.GenericResponseListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;

import static java.lang.Math.round;

import static com.sppcco.core.util.app.CoreConstants.APP_TAG;


public class MerchandiseSPArticleEditPresenter extends BasePresenter implements MerchandiseSPArticleEditContract.Presenter {


  private MerchandiseSPArticleEditContract.View mView;

  private SPArticle mSPArticle;
  private ArrayList<byte[]> mImages;
  private ArrayList<Integer> mImageIds;

  private boolean mModifyPriceDiscount;
  private boolean mCheckRepeatedMerch;

  private final CompositeDisposable disposables = new CompositeDisposable();


  private MerchandiseSPArticleEditPresenter(@NonNull MerchandiseSPArticleEditContract.View view) {

    mView = view;
    mView.setPresenter(this);
  }

  static MerchandiseSPArticleEditContract.Presenter getMerchandiseSPArticleEditPresenterInstance(
    @NonNull MerchandiseSPArticleEditContract.View view) {

    return new MerchandiseSPArticleEditPresenter(view);
  }

  @Override
  public void destroy() {
    disposables.clear();
  }

  @Override
  public void start() {

    initOptionAndAccessRight(() -> {

      if (mView.getMode() == Mode.NEW) {
        setSPArticle(SPArticle.getSPArticleWithDefaultValue());
        getSPArticle().setSPId(mView.getSPFactor().getId());
        initSPArticleMerchInfo();

      } else if (mView.getMode() == Mode.EDIT) {
        if (mView.getSPArticle() == null) {
          setSPArticle(SPArticle.getSPArticleWithDefaultValue());
          getSPArticle().setSPId(mView.getSPFactor().getId());
          initSPArticleMerchInfo();

        } else {
          setSPArticle(mView.getSPArticle());
          if (getSPArticle().getMerchandiseId() != mView.getMerchInfo().getMerchId())
            initSPArticleMerchInfo();

          if (mView.hasError())
            deleteErrorStatus();
        }
      }

      if (isShowImages()) {
        if (mImages == null)
          getImageIds(getSPArticle().getMerchandiseId());
          //getImageBase64(mView.getMerchId());
        else
          mView.initViewPager();
      }


      mView.updateView();
    });
  }

  private void initOptionAndAccessRight(DoneResponseListener doneResponseListener) {

    final int ADMIN_USER = 1;

    if (BaseApplication.getUserId() == ADMIN_USER) {
      setModifyPriceDiscount(true);
      initOptions(doneResponseListener);

    } else {
      getCoreDB().rightsRepository().getAccessRight(SubsystemsId.SALESPURCHASE_SYS.getValue(), FormId.SP_PRESALES.getValue(),
        new RightsRepository.GetAccessRightCallback() {
          @Override
          public void onAccessRightLoaded(String accessRight) {
            boolean bModifyPriceDiscount = accessRight.charAt(PreFactorRightPos.CHANGE_SALES_PRICE.getValue()) == '1';
            setModifyPriceDiscount(bModifyPriceDiscount);
            initOptions(doneResponseListener);
          }

          @Override
          public void onDataNotAvailable() {

          }
        });
    }
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


  private void initSPArticleMerchInfo() {

    getSPArticle().setStockRoomId(mView.getMerchInfo().getStockId());
    getSPArticle().setStockAccId(mView.getMerchInfo().getStockAccountId());
    getSPArticle().setStockFAccId(mView.getMerchInfo().getStockFAccId());
    getSPArticle().setStockCCId(mView.getMerchInfo().getStockCCId());
    getSPArticle().setStockPrjId(mView.getMerchInfo().getStockPrjId());
    getSPArticle().setCabinetId(mView.getMerchInfo().getCabinetId());
    getSPArticle().setMerchandiseId(mView.getMerchInfo().getMerchId());
    getSPArticle().setMerchandiseCode(mView.getMerchInfo().getMerchCode());
    getSPArticle().setMerchandiseName(mView.getMerchInfo().getMerchName());
    getSPArticle().setUnitId(mView.getMerchInfo().getMerchUnitId());
    getSPArticle().setUnitName(mView.getMerchInfo().getMerchUnitName());
    getSPArticle().setUnitPrice(mView.getMerchInfo().getCustSalesPrice());
    getSPArticle().setRemainder(
      round((getSPArticle().getAmount() * getSPArticle().getUnitPrice()) * mView.getMerchInfo().getCustSalesDiscount() / 100));
  }

  private void initSPArticleStockInfo() {

    getSPArticle().setStockRoomId(mView.getMerchInfo().getStockId());
    getSPArticle().setStockAccId(mView.getMerchInfo().getStockAccountId());
    getSPArticle().setStockFAccId(mView.getMerchInfo().getStockFAccId());
    getSPArticle().setStockCCId(mView.getMerchInfo().getStockCCId());
    getSPArticle().setStockPrjId(mView.getMerchInfo().getStockPrjId());
    getSPArticle().setCabinetId(mView.getMerchInfo().getCabinetId());
  }

  @Override
  public void insertSPArticle() {
    getCoreDB().sPArticleRepository().insertSPArticle(getSPArticle(), new SPArticleRepository.InsertSPArticleCallback() {
      @Override
      public void onSPArticleInserted(long sPArticleId) {

        mView.afterSPArticleInserted();
      }

      @Override
      public void onDataNotAvailable() {
        Log.e(APP_TAG, "saveSOArticle - onFailure");
      }
    });
  }

  @Override
  public void updateSPArticle() {
    initSPArticleStockInfo();
    getCoreDB().sPArticleRepository().updateSPArticle(getSPArticle(), new SPArticleRepository.UpdateSPArticleCallback() {
      @Override
      public void onSPArticleUpdated(int i) {

        mView.afterSPArticleUpdated();
      }

      @Override
      public void onDataNotAvailable() {
        Log.e(APP_TAG, "updateSOArticle - onFailure");
      }
    });
  }


  private void deleteErrorStatus() {
    getCoreDB().errorStatusRepository().deleteErrorStatusByIdAndArticleId(mView.getSPFactor().getId(),
      mView.getSPArticle().getId(), DocType.SPFACTOR.getValue(), new ErrorStatusRepository.DeleteErrorStatusesCallback() {
        @Override
        public void onErrorStatusesDeleted(int i) {
          mView.setHasError(false);
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  public void getLogicalInventory(int merchId,
                                  String startDate, String endDate,
                                  int committed) {

    disposables.add(getCoreNet().merchandiseRemoteControlRepository().getLogicalMerchInventory(
      merchId, mView.getMerchInfo().getStockId(), mView.getMerchInfo().getCabinetId(), startDate, endDate, committed,
      new MerchandiseRemoteRepository.LoadStringArrayCallback() {
        @Override
        public void onResponse(String... response) {
          String[] strings = new String[3];
          strings[0] = response[0];
          strings[1] = response[1];
          strings[2] = String.valueOf(merchId);
          mView.updateInventoryView(strings);
        }

        @Override
        public void onFailure(ResponseType responseType) {
          mView.updateInventoryView((String) null);
        }
      })
    );

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
      getCoreDB().sPArticleRepository().existMerchInSP(mView.getSPFactor().getId(), getSPArticle().getId(),
        getSPArticle().getMerchandiseId(), getSPArticle().getStockRoomId(), BaseApplication.getFPId(),
        count -> {
          if (count == 0)
            booleanGenericResponseListener.onResponse(false);
          else
            booleanGenericResponseListener.onResponse(true);
        });
  }


  //region Getter Setter

  public SPArticle getSPArticle() {
    return mSPArticle;
  }

  private void setSPArticle(SPArticle mSPArticle) {
    this.mSPArticle = mSPArticle;
  }

  @Override
  public boolean canModifyPriceDiscount() {
    return mModifyPriceDiscount;
  }

  private void setModifyPriceDiscount(boolean canModifyPriceDiscount) {
    mModifyPriceDiscount = canModifyPriceDiscount;
  }

  private boolean isCheckRepeatedMerch() {
    return mCheckRepeatedMerch;
  }

  private void setCheckRepeatedMerch(boolean checkRepeatedMerch) {
    this.mCheckRepeatedMerch = checkRepeatedMerch;
  }

  //endregion Getter Setter


}