package com.sppcco.customer.ui.select;

import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.customer.IBasePresenter;
import com.sppcco.customer.IBaseView;

import java.util.List;

/**
 * Created by m_pejam on 01/21/18.
 * SelectCustomerContract
 */

public interface SelectCustomerContract {

  interface View extends IBaseView<Presenter> {

    void finishView();

    void getObject(Object object);

    boolean isShowCredit();

    void updateCustomerCreditValues(int customerId, String customerCredit, String accountBalance);

    boolean getPrimaryData();

    ACCVector getAccVector();
  }

  interface Presenter extends IBasePresenter {

    List<Customer> getCustomerList();

    List<String> getSortList();

    void getCustomerCredit(Customer customer);
  }
}
