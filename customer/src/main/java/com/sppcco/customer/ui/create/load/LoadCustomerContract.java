package com.sppcco.customer.ui.create.load;


import com.sppcco.core.data.sub_model.PostedCustomerInfo;
import com.sppcco.core.listener.DoneResponseListener;
import com.sppcco.core.listener.GenericResponseListener;
import com.sppcco.core.util.message.Message;
import com.sppcco.customer.IBasePresenter;
import com.sppcco.customer.IBaseView;

import java.util.List;

/**
 * Created by m_pejam on 01/21/18.
 * LoadCustomerContract
 */

public interface LoadCustomerContract {

  interface View extends IBaseView<Presenter> {

    void loadRecyclerViewItem();

    void showProgress();

    void hideProgress();

    void onAgainApproveRequest(PostedCustomerInfo postedCustomerInfo, int nAdapterPosition);

    void onDeleteApproveRequest(int nId, String strName, int nAdapterPosition);

    void updateAdapter(boolean isUpdate, boolean isDelete, int nPosition);

    void showSendMessage(Message message, int nPeriodicTime);

  }

  interface Presenter extends IBasePresenter {

    String getAccessRight();

    boolean getRight(int position);

    List<PostedCustomerInfo> getPostedCustomerList();

    void loadPostedCustomersInfo(DoneResponseListener doneResponseListener);

    void getCountOfRowsThatNeedSync(GenericResponseListener<Integer> doneResponseListener);

    void refreshPostedCustomersInfoList(DoneResponseListener doneResponseListener);

    void syncRowsOfTablesRelatedToPostedCustomers(DoneResponseListener doneResponseListener);

    void deleteRemotePostedCustomer(int id, String name, int nAdapterPosition, DoneResponseListener doneResponseListener);

    void ControlRemoteStatusCustomer(PostedCustomerInfo postedCustomerInfo, int nPosition);
  }
}
