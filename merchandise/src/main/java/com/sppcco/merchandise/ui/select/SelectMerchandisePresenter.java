package com.sppcco.merchandise.ui.select;

import android.util.Log;

import com.sppcco.core.data.local.db.dao.CustomerDao;
import com.sppcco.core.data.local.db.repository.CabinetRepository;
import com.sppcco.core.data.local.db.repository.CustomerRepository;
import com.sppcco.core.data.local.db.repository.MerchInfoRepository;
import com.sppcco.core.data.local.db.repository.SOArticleRepository;
import com.sppcco.core.data.local.db.repository.SPArticleRepository;
import com.sppcco.core.data.local.db.repository.SalesDiscountRepository;
import com.sppcco.core.data.local.db.repository.SalesPriceRepository;
import com.sppcco.core.data.local.db.repository.StockRoomRepository;
import com.sppcco.core.data.model.Cabinet;
import com.sppcco.core.data.model.StockRoom;
import com.sppcco.core.data.remote.repository.ImageRemoteRepository;
import com.sppcco.core.data.remote.repository.MerchandiseRemoteRepository;
import com.sppcco.core.data.sub_model.MerchInfo;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.enums.ResponseType;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.core.listener.DoneResponseListener;
import com.sppcco.core.listener.DoubleResponseListener;
import com.sppcco.core.listener.StringArrayResponseListener;
import com.sppcco.core.listener.StringResponseListener;
import com.sppcco.core.listener.VoidResponseListener;
import com.sppcco.merchandise.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;

import static com.sppcco.core.util.app.CoreConstants.APP_TAG;


public class SelectMerchandisePresenter extends BasePresenter implements SelectMerchandiseContract.Presenter {


  private SelectMerchandiseContract.View mView;


  private List<StockRoom> mStockRoomList;
  private List<String> mStockNameList;
  private List<Cabinet> mCabinetList;
  private List<String> mCabinetNameList;

  private List<String> mSortList;
  private ArrayList<Integer> mImageIds;

  private StockRoom mStockRoom;
  private Cabinet mCabinet;

  private boolean bSetStockList = false;
  private boolean bSetCabinetList = false;

  private boolean bFirstMerchStockLoad = false;

  private final CompositeDisposable disposables = new CompositeDisposable();

  private SelectMerchandisePresenter(@NonNull SelectMerchandiseContract.View view) {

    mView = view;
    mView.setPresenter(this);
  }


  static SelectMerchandiseContract.Presenter getMerchandisePresenterInstance(
    @NonNull SelectMerchandiseContract.View view) {

    return new SelectMerchandisePresenter(view);
  }

  @Override
  public void destroy() {
    disposables.clear();
  }

  @Override
  public void start() {
    loadSortList();
    getArticleCount();

    if (mView.isMerchStock() || (!mView.isAllStock() && !mView.isMerchStock())) {

      initializeStockRoomAndCabinet(new VoidResponseListener() {
        @Override
        public void onSuccess() {
          if (bSetStockList && bSetCabinetList) {

            if (bFirstMerchStockLoad)
              mView.initViewModel();

            mView.updateView();

            bSetStockList = false;
            bSetCabinetList = false;
          }
        }

        @Override
        public void onFailure() {

        }
      });

    } else {

      mView.updateView();
    }
  }


  private void initializeStockRoomAndCabinet(VoidResponseListener voidResponseListener) {

    loadStockRoom(voidResponseListener);
  }


  @Override
  public boolean isShowImages() {
    return getCorePref().getPrefImplementation().isShowImages();
  }

  private void loadStockRoom(VoidResponseListener voidResponseListener) {

    getCoreDB().stockRoomRepository().getStockRooms(new StockRoomRepository.GetStockRoomsCallback() {
      @Override
      public void onStockRoomsLoaded(List<StockRoom> stockRooms) {
        setStockRoomList(stockRooms);
        setStockNameList();

        if (mView.getMode() == Mode.NEW) {

          if (getLatestStockId() != 0) {
            for (int i = 0; i < stockRooms.size(); ++i)
              if (stockRooms.get(i).getId() == getLatestStockId()) {
                setStockRoom(stockRooms.get(i));
                mView.setStockPosition(i);
                mView.setStockRoomId(getStockRoom().getId());
              }
          } else {
            setStockRoom(stockRooms.get(0));
            mView.setStockPosition(0);
            mView.setStockRoomId(getStockRoom().getId());
            bFirstMerchStockLoad = true;
          }
        } else if (mView.getMode() == Mode.EDIT) {

          if (mView.getStockRoomId() != 0) {
            for (int i = 0; i < stockRooms.size(); ++i) {
              if (stockRooms.get(i).getId() == mView.getStockRoomId()) {
                setStockRoom(stockRooms.get(i));
                mView.setStockPosition(i);
              }
            }

            if(getStockRoom() == null)
              setStockRoom(stockRooms.get(0));

          } else {
            setStockRoom(stockRooms.get(0));
            mView.setStockPosition(0);
            mView.setStockRoomId(getStockRoom().getId());
            bFirstMerchStockLoad = true;
          }
        }
        bSetStockList = true;
        loadCabinet(voidResponseListener, false);
      }

      @Override
      public void onDataNotAvailable() {

      }
    });
  }

  private void setStockRoomList(List<StockRoom> stockRoomList) {
    if (mStockRoomList == null)
      mStockRoomList = new ArrayList<>();

    mStockRoomList = stockRoomList;
  }


  private List<StockRoom> getStockRoomList() {
    return mStockRoomList;
  }


  @Override
  public List<String> getStockNameList() {

    return mStockNameList;
  }

  private void setStockNameList() {

    mStockNameList = null;
    mStockNameList = new ArrayList<>();

    for (int i = 0; i < getStockRoomList().size(); i++)
      mStockNameList.add(getStockRoomList().get(i).getName());
  }

  @Override
  public void onChangeStock(int position) {

    setStockRoom(getStockRoomList().get(position));
    mView.setStockRoomId(getStockRoom().getId());

    loadCabinet(new VoidResponseListener() {
      @Override
      public void onSuccess() {
        if (bSetCabinetList) {
          mView.updateView();
          mView.initViewModel();
          bSetCabinetList = false;
        }
      }

      @Override
      public void onFailure() {

      }
    }, true);
  }

  private void loadCabinet(VoidResponseListener voidResponseListener, boolean bAfterStockChange) {

    getCoreDB().cabinetRepository().getAllCabinetFromStockRoomId(getStockRoom().getId(),
      new CabinetRepository.GetAllCabinetFromStockRoomIdCallback() {
        @Override
        public void onCabinetsLoaded(List<Cabinet> cabinets) {
          setCabinetList(cabinets);
          setCabinetNameList();

          if (bAfterStockChange) {

            setCabinet(cabinets.get(0));
            mView.setCabinetPosition(0);
            mView.setCabinetId(getCabinet().getId());

          } else {

            if (mView.getMode() == Mode.NEW) {

              if (getLatestCabinetId() != 0) {
                for (int i = 0; i < cabinets.size(); ++i)
                  if (cabinets.get(i).getId() == getLatestCabinetId()) {
                    setCabinet(cabinets.get(i));
                    mView.setCabinetPosition(i);
                    mView.setCabinetId(getCabinet().getId());
                  }
              } else {
                setCabinet(cabinets.get(0));
                mView.setCabinetPosition(0);
                mView.setCabinetId(getCabinet().getId());
              }
            } else if (mView.getMode() == Mode.EDIT) {

              if (mView.getCabinetId() != 0) {
                for (int i = 0; i < cabinets.size(); ++i)
                  if (cabinets.get(i).getId() == mView.getCabinetId()) {
                    setCabinet(cabinets.get(i));
                    mView.setCabinetPosition(i);
                  }
              } else {
                setCabinet(cabinets.get(0));
                mView.setCabinetPosition(0);
                mView.setCabinetId(getCabinet().getId());
              }
            }
          }

          bSetCabinetList = true;
          voidResponseListener.onSuccess();
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  private void setCabinetList(List<Cabinet> cabinetList) {
    if (mCabinetList == null)
      mCabinetList = new ArrayList<>();

    mCabinetList = cabinetList;
  }


  private List<Cabinet> getCabinetList() {
    return mCabinetList;
  }

  @Override
  public List<String> getCabinetNameList() {

    return mCabinetNameList;
  }

  private void setCabinetNameList() {
    mCabinetNameList = null;
    mCabinetNameList = new ArrayList<>();

    for (int i = 0; i < getCabinetList().size(); i++)
      mCabinetNameList.add(getCabinetList().get(i).getName());
  }

  @Override
  public void onChangeCabinet(int position) {

    setCabinet(getCabinetList().get(position));
    mView.setCabinetId(getCabinet().getId());
    mView.updateView();
  }


  public void getLogicalInventory(int merchId,
                                  String startDate, String endDate,
                                  int committed, StringArrayResponseListener stringArrayResponseListener) {

    disposables.add(getCoreNet().merchandiseRemoteControlRepository().getLogicalMerchInventory(
      merchId, getStockRoom().getId(), getCabinet().getId(), startDate, endDate, committed,
      new MerchandiseRemoteRepository.LoadStringArrayCallback() {
        @Override
        public void onResponse(String... response) {
          String[] strings = new String[3];
          strings[0] = response[0];
          strings[1] = response[1];
          strings[2] = String.valueOf(merchId);
          stringArrayResponseListener.onSuccess(strings);
        }

        @Override
        public void onFailure(ResponseType responseType) {
          stringArrayResponseListener.onFailure();
        }
      })
    );

  }

  private void loadSortList() {
    if (getSortList() != null)
      return;

    mSortList = new ArrayList<>();
    mSortList.add(BaseApplication.getResourceString(R.string.cpt_merch_code));
    mSortList.add(BaseApplication.getResourceString(R.string.cpt_merch_name));
  }


  @Override
  public List<String> getSortList() {
    return mSortList;
  }


  @Override
  public void getMerchInfoByBarcode(String barcode) {

    if (!mView.isAllStock() && mView.isMerchStock()) {

      getCoreDB().merchInfoRepository().getMerchInfoByBarcodeWithMerchStock(isShowImages() ? 1 : 0, 
        barcode, new MerchInfoRepository.GetMerchInfoCallback() {
        @Override
        public void onMerchInfoLoaded(MerchInfo merchInfo) {
          getSalesPriceAndSalesDiscount(merchInfo);
        }

        @Override
        public void onDataNotAvailable() {

        }
      });

    } else {

      getCoreDB().merchInfoRepository().getMerchInfoByBarcodeWithoutMerchStock(isShowImages() ? 1 : 0, barcode, new MerchInfoRepository.GetMerchInfoCallback() {
        @Override
        public void onMerchInfoLoaded(MerchInfo merchInfo) {
          getSalesPriceAndSalesDiscount(merchInfo);
        }

        @Override
        public void onDataNotAvailable() {

        }
      });

    }

  }


  private void getSalesPriceAndSalesDiscount(MerchInfo merchInfo) {
    getSalesPriceAndSalesDiscount(merchInfo, new VoidResponseListener() {
      @Override
      public void onSuccess() {
        if (merchInfo.getCustSalesPrice() >= 0 && merchInfo.getCustSalesDiscount() >= 0)
          mView.callEditFragment(merchInfo);
      }

      @Override
      public void onFailure() {

      }
    });
  }

  @Override
  public void getSalesPriceAndSalesDiscount(MerchInfo merchInfo, VoidResponseListener voidResponseListener) {

    getCoreDB().customerRepository().getCustomerSalesPriceAndSalesDiscount(
      merchInfo.getMerchId(), mView.getCustomerId(),
      new CustomerRepository.GetCustomerSalesPriceAndSalesDiscountCallback() {
        @Override
        public void onGetCustomerPriceAndDiscount(CustomerDao.PriceAndDiscount priceAndDiscount) {
          merchInfo.setCustSalesDiscount(priceAndDiscount.salesDiscount);
          merchInfo.setCustSalesPrice(priceAndDiscount.salesPrice);
          voidResponseListener.onSuccess();
        }

        @Override
        public void onDataNotAvailable() {
          Log.e(APP_TAG, "getCustomerSalesPriceAndSalesDiscount - onDataNotAvailable");
        }
      });
  }

  @Override
  public void getImageBase64(int merchId, StringResponseListener responseListener) {
    disposables.add(getCoreNet().imageRemoteControlRepository().getBase64ImageById(merchId, false,
      new ImageRemoteRepository.LoadListStringCallback() {
        @Override
        public void onResponse(List<String> imagesList) {
          responseListener.onSuccess(imagesList.get(0));
        }

        @Override
        public void onFailure(ResponseType responseType) {
          responseListener.onFailure();
        }
      })
    );
  }

  private void getCustomerSalesPrice(int merchandiseId, DoubleResponseListener doubleResponseListener) {
    getCoreDB().salesPriceRepository().getCustomerSalesPrice(merchandiseId, mView.getCustomerId(), BaseApplication.getFPId(),
      new SalesPriceRepository.GetCustomerSalesPriceCallback() {
        @Override
        public void onCustomerSalesPriceLoaded(double dSalesPrice) {
          doubleResponseListener.onSuccess(dSalesPrice);
        }

        @Override
        public void onDataNotAvailable() {
          Log.e(APP_TAG, "getCustomerSalesPrice - onFailure");
          doubleResponseListener.onFailure();
        }
      });
  }


  private void getCustomerSalesDiscount(int merchandiseId, DoubleResponseListener doubleResponseListener) {
    getCoreDB().salesDiscountRepository().getCustomerSalesDiscount(merchandiseId, mView.getCustomerId(), BaseApplication.getFPId(),
      new SalesDiscountRepository.GetCustomerSalesDiscountCallback() {
        @Override
        public void onCustomerSalesDiscountLoaded(double dSalesDiscount) {
          doubleResponseListener.onSuccess(dSalesDiscount);
        }

        @Override
        public void onDataNotAvailable() {
          Log.e(APP_TAG, "getCustomerSalesPrice - onFailure");
          doubleResponseListener.onFailure();
        }
      });
  }

  private void getArticleCount() {
    if (mView.getSPFactor() != null) {
      getCoreDB().sPArticleRepository().getSPArticleCount(mView.getSPFactor().getId(), BaseApplication.getFPId(),
        new SPArticleRepository.GetSPArticleCountCallback() {
          @Override
          public void onSPArticleCountLoaded(int count) {
            mView.setArticleCount(count);
          }

          @Override
          public void onDataNotAvailable() {

          }
        });
    } else if (mView.getSalesOrder() != null) {
      getCoreDB().sOArticleRepository().getSOArticleCount(mView.getSalesOrder().getId(), BaseApplication.getFPId(),
        new SOArticleRepository.GetSOArticleCountCallback() {
          @Override
          public void onSOArticleCountLoaded(int count) {
            mView.setArticleCount(count);
          }

          @Override
          public void onDataNotAvailable() {

          }
        });
    }
  }


  @Override
  public void getRemoteImageIds(int merchId, DoneResponseListener listener) {
    mView.onShowProgress();
    disposables.add(getCoreNet().imageRemoteControlRepository().GetImageIds(merchId,
      new ImageRemoteRepository.LoadListIntegerCallback() {

        @Override
        public void onResponse(List<Integer> list) {
          setImageIds(list);
          listener.onDone();
        }

        @Override
        public void onFailure(ResponseType responseType) {
          mView.onHideProgress();
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
  }
//----------------------------------------------------------------------------------------------------------


  @Override
  public StockRoom getStockRoom() {
    return mStockRoom;
  }

  private void setStockRoom(StockRoom stockRoom) {
    mStockRoom = stockRoom;
  }

  @Override
  public Cabinet getCabinet() {
    return mCabinet;
  }

  private void setCabinet(Cabinet cabinet) {
    mCabinet = cabinet;
  }

  public void setLatestStockId() {
    getCorePref().getPrefImplementation().setLatestInsertedStockId(getStockRoom().getId());
  }

  public void setLatestCabinetId() {
    getCorePref().getPrefImplementation().setLatestInsertedCabinetId(getCabinet().getId());
  }

  public int getLatestStockId() {
    return getCorePref().getPrefImplementation().getLatestInsertedStockId();
  }

  public int getLatestCabinetId() {
    return getCorePref().getPrefImplementation().getLatestInsertedCabinetId();
  }

}