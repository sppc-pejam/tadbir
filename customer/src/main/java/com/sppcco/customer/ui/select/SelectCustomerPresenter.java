package com.sppcco.customer.ui.select;

import android.os.Handler;
import android.os.Looper;

import com.sppcco.core.data.local.db.repository.CustomerRepository;
import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.remote.repository.CustomerRemoteRepository;
import com.sppcco.core.enums.ResponseType;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.core.listener.VoidResponseListener;
import com.sppcco.customer.R;
import com.sppcco.helperlibrary.manager.CalenderManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by m_pejam on 02/09/18.
 * SelectCustomerPresenter
 */

public class SelectCustomerPresenter extends BasePresenter implements SelectCustomerContract.Presenter {

  private final SelectCustomerContract.View mView;

  private final CompositeDisposable disposables = new CompositeDisposable();

  private List<Customer> customerList;
  private List<String> mSortList;

  private SelectCustomerPresenter(@NonNull SelectCustomerContract.View searchDialogView) {
    mView = searchDialogView;
    mView.setPresenter(this);
  }


  static SelectCustomerContract.Presenter getSearchDialogPresenterInstance(@NonNull SelectCustomerContract.View searchDialogView) {

    return new SelectCustomerPresenter(searchDialogView);
  }

  @Override
  public void destroy() {
    disposables.dispose();
    disposables.clear();
  }

  @Override
  public void start() {
    loadSortList();

    initializeData(new VoidResponseListener() {
      @Override
      public void onSuccess() {
        mView.updateView();
      }

      @Override
      public void onFailure() {
      }
    });

  }

  private void initializeData(VoidResponseListener voidResponseListener) {

    if (mView.getPrimaryData()) {
      /*if (mView.getmACCVector().getAccount().getFullId().matches("0")) {
        getCoreDB().customerRepository().getCustomerByFACCId(mView.getmACCVector().getDetailAcc().getId(),
          new CustomerRepository.GetCustomerByFACCIdCallback() {
            @Override
            public void onCustomerLoaded(List<Customer> customers) {
              setCustomerList(customers);
              voidResponseListener.onDone();
            }

            @Override
            public void onDataNotAvailable() {

            }
          });
      } else {*/
      getCoreDB().customerRepository().getCustomerByAcc(
        mView.getAccVector().getAccount().getFullId(),
        mView.getAccVector().getDetailAcc().getId(),
        mView.getAccVector().getCostCenter().getId(),
        mView.getAccVector().getProject().getId(),
        new CustomerRepository.GetCustomerByAccCallback() {
          @Override
          public void onCustomerLoaded(List<Customer> customers) {
            setCustomerList(customers);
            voidResponseListener.onSuccess();
          }

          @Override
          public void onDataNotAvailable() {
          }

        });
      //}
    } else {
      getCoreDB().customerRepository().getActiveCustomers(new CustomerRepository.GetCustomersCallback() {
        @Override
        public void onCustomersLoaded(List<Customer> customers) {
          setCustomerList(customers);
          voidResponseListener.onSuccess();
        }

        @Override
        public void onDataNotAvailable() {
        }

      });
    }
  }

  public List<Customer> getCustomerList() {
    return customerList;
  }

  private void setCustomerList(List<Customer> customerList) {
    this.customerList = customerList;
  }

  private void loadSortList() {
    if (getSortList() != null)
      return;

    mSortList = new ArrayList<>();
    mSortList.add(BaseApplication.getResourceString(R.string.cpt_merch_name));
    mSortList.add(BaseApplication.getResourceString(R.string.cpt_merch_code));
  }


  @Override
  public List<String> getSortList() {
    return mSortList;
  }

  @Override
  public void getCustomerCredit(Customer customer) {
    final int CREDIT_BALANCE_INDEX = 0;
    final int ACCOUNT_BALANCE_INDEX = 1;

    disposables.add(getCoreNet().customerRemoteControlRepository().getCustomerCredit(customer, CalenderManager.getCurrentDate(),
      new CustomerRemoteRepository.LoadStringArrayCallback() {
        @Override
        public void onResponse(String... response) {
          mView.updateCustomerCreditValues(customer.getId(), response[CREDIT_BALANCE_INDEX], response[ACCOUNT_BALANCE_INDEX]);
        }

        @Override
        public void onFailure(ResponseType responseType) {
          Handler handler = new Handler(Looper.getMainLooper());
          handler.postDelayed(() -> mView.updateCustomerCreditValues(customer.getId(), "", ""), 1);
        }
      })
    );
  }
}