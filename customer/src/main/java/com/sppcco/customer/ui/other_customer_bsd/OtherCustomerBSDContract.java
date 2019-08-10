package com.sppcco.customer.ui.other_customer_bsd;


import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.data.sub_model.OtherCustomer;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.listener.DoneResponseListener;
import com.sppcco.customer.IBasePresenter;
import com.sppcco.customer.IBaseView;
import com.sppcco.customer.IBaseViewModel;

import androidx.lifecycle.LiveData;

public interface OtherCustomerBSDContract {

  interface View extends IBaseView<Presenter> {

    void updateCustomerView();

    void updateACCView();

    void clearACC();

    void updateACC(Object o);

    void callACCVectorActivity(AccountTree ROOT);

    void setAccCode(String accCode);

    OtherCustomer getTempOtherCustomer();

    ACCVector getTempACCVector();

    void setDependentOnAccount(boolean isDependency);

    Mode getMode();
  }

  interface Presenter extends IBasePresenter {

    void loadACC();

    void setACC(DoneResponseListener listener);
  }

  interface ViewModel extends IBaseViewModel<View, Presenter> {

    void initData();

    void ViewModelObserveProviders();

    LiveData<String> getDetailCode();
  }

}
