package com.sppcco.customer.ui.create.add;


import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.sub_model.PostedCustomerInfo;
import com.sppcco.core.listener.DoneResponseListener;
import com.sppcco.core.listener.GenericResponseListener;
import com.sppcco.customer.IBasePresenter;
import com.sppcco.customer.IBaseView;

/**
 * Created by m_pejam on 01/21/18.
 * AddCustomerContract
 */

public interface AddCustomerContract {

  interface View extends IBaseView<Presenter> {

    Customer getCustomer();

    String getInputName();

    String getInputSubscriptionNo();

    void handleModelValidationError(int errorCode);

    void handlePostError(int errorCode);

    void hideProgress();

    void finishByResult(PostedCustomerInfo postedCustomerInfo);
  }

  interface Presenter extends IBasePresenter {

    void createCustomer(DoneResponseListener doneResponseListener);

    void validCustomerName(GenericResponseListener<Integer> responseListener);

    void validCustomerSubscriptionNo(GenericResponseListener<Integer> responseListener);
  }
}
