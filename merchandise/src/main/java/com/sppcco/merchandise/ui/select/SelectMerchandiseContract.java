package com.sppcco.merchandise.ui.select;

import com.sppcco.core.data.model.Cabinet;
import com.sppcco.core.data.model.SPFactor;
import com.sppcco.core.data.model.SalesOrder;
import com.sppcco.core.data.model.StockRoom;
import com.sppcco.core.data.sub_model.MerchInfo;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.listener.DoneResponseListener;
import com.sppcco.core.listener.StringArrayResponseListener;
import com.sppcco.core.listener.StringResponseListener;
import com.sppcco.core.listener.VoidResponseListener;
import com.sppcco.merchandise.IBasePresenter;
import com.sppcco.merchandise.IBaseView;
import com.sppcco.merchandise.IBaseViewModel;

import java.util.ArrayList;
import java.util.List;

public interface SelectMerchandiseContract {

  interface View extends IBaseView<Presenter> {

    boolean isAllStock();

    boolean isMerchStock();

    boolean isShowInventory();

    void initViewModel();

    int getStockRoomId();

    void setStockPosition(int stockPosition);

    void setStockRoomId(int stockRoomId);

    void callEditFragment(MerchInfo merchInfo);

    void callImageGallery(int merchandiseId, ArrayList<Integer> ids, int position);

    void onShowProgress();

    void onHideProgress();

    int getCabinetId();

    void setCabinetPosition(int cabinetPosition);

    void setCabinetId(int cabinetId);

    int getCustomerId();

    int getSortPosition();

    SPFactor getSPFactor();

    SalesOrder getSalesOrder();

    String getFilter();

    void setArticleCount(int count);

    Mode getMode();
  }

  interface Presenter extends IBasePresenter {

    int getLatestStockId();

    void setLatestStockId();

    int getLatestCabinetId();

    void setLatestCabinetId();

    void getMerchInfoByBarcode(String barcode);

    List<String> getSortList();

    List<String> getStockNameList();

    List<String> getCabinetNameList();

    void onChangeStock(int index);

    void onChangeCabinet(int index);

    StockRoom getStockRoom();

    Cabinet getCabinet();

    boolean isShowImages();

    void getLogicalInventory(int merchId, String startDate, String endDate, int committed, StringArrayResponseListener stringArrayResponseListener);

    void getSalesPriceAndSalesDiscount(MerchInfo merchInfo, VoidResponseListener voidResponseListener);

    void getImageBase64(int merchId, StringResponseListener responseListener);

    void getRemoteImageIds(int merchId, DoneResponseListener listener);

    ArrayList<Integer> getImageIds();
  }

  interface ViewModel extends IBaseViewModel<View, Presenter> {

  }

}
