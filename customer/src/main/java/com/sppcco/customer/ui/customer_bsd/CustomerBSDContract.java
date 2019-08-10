package com.sppcco.customer.ui.customer_bsd;

import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.listener.DoneResponseListener;
import com.sppcco.customer.IBasePresenter;
import com.sppcco.customer.IBaseView;
import com.sppcco.customer.IBaseViewModel;

import androidx.lifecycle.LiveData;

public interface CustomerBSDContract {

  interface View extends IBaseView<Presenter> {

    void updateCustomerView();

    void updateACCView();

    void clearACC();

    void updateACC(Object o);

    void updateAccountBalance(String account);

    void callACCVectorActivity(AccountTree ROOT, Object o);

    void callACCVectorActivity(AccountTree ROOT);

    void setAccCode(String accCode);

    Customer getTempCustomer();

    ACCVector getTempACCVector();

    Mode getMode();

    void enableRefresh(boolean isRefresh);
  }

  interface Presenter extends IBasePresenter {

    void loadACC();

    void setACC(DoneResponseListener listener);

    void getAccVectorBalance(Customer customer);

    void disposeRequest();
  }

  interface ViewModel extends IBaseViewModel<View, Presenter> {

    void initData();

    void ViewModelObserveProviders();

    LiveData<String> getDetailCode();
  }

}
