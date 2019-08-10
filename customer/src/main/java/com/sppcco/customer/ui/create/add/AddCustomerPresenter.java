package com.sppcco.customer.ui.create.add;

import com.sppcco.core.data.remote.repository.CustomerRemoteRepository;
import com.sppcco.core.data.sub_model.PostedCustomerInfo;
import com.sppcco.core.enums.MessageCode;
import com.sppcco.core.enums.ResponseType;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.core.listener.DoneResponseListener;
import com.sppcco.core.listener.GenericResponseListener;
import com.sppcco.helperlibrary.converter.DC;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 02/09/18.
 * AddCustomerPresenter
 */

public class AddCustomerPresenter extends BasePresenter implements AddCustomerContract.Presenter {

  private final AddCustomerContract.View mView;

  private AddCustomerPresenter(@NonNull AddCustomerContract.View view) {
    mView = view;
    mView.setPresenter(this);
  }

  static AddCustomerContract.Presenter getPresenterInstance(@NonNull AddCustomerContract.View view) {

    return new AddCustomerPresenter(view);
  }

  @Override
  public void destroy() {
  }

  @Override
  public void start() {

  }

  @Override
  public void createCustomer(DoneResponseListener doneResponseListener) {

    validCustomerName(isValidCustomerName -> {

      if (isValidCustomerName != 0)
        mView.handleModelValidationError(isValidCustomerName);

      else
        validCustomerSubscriptionNo(isValidCustomerSubscriptionNo -> {
          if (isValidCustomerSubscriptionNo != 0) {
            mView.handleModelValidationError(isValidCustomerSubscriptionNo);
          } else {
            sendCustomer();
          }
        });
    });

  }


  private void sendCustomer() {
    controlValidationCustomer();
  }

  private void controlValidationCustomer() {

    getCoreNet().customerRemoteControlRepository().getValidationCustomerResult(mView.getCustomer(),
      new CustomerRemoteRepository.LoadStringCallback() {
        @Override
        public void onResponse(String response) {
          String[] result = DC.jsnToStrArr(response, "ValidationResult");
          if (result != null) {
            if (Integer.parseInt(result[0]) == 0)
              insertRemoteCustomer();
            else {
              //mPreferencesComponent.PreferencesHelper().setTempInsertedSPId(0);
              mView.handlePostError(Integer.parseInt(result[0]));
            }
          } else {
            mView.hideProgress();
          }
        }

        @Override
        public void onFailure(ResponseType responseType) {
          mView.hideProgress();
        }
      });
  }

  private void insertRemoteCustomer() {
    mView.getCustomer().setEDate(DC.faToEn(mView.getCustomer().getEDate()));
    mView.getCustomer().setETime(DC.faToEn(mView.getCustomer().getETime()));

    getCoreNet().customerRemoteControlRepository().insertCustomer(mView.getCustomer(),
      new CustomerRemoteRepository.LoadVoidCallback() {
        @Override
        public void onResponse() {
          getRemoteLatestPostedCustomer();
        }

        @Override
        public void onFailure(ResponseType responseType) {
          mView.hideProgress();
        }
      });
  }

  private void getRemoteLatestPostedCustomer() {

    getCoreNet().customerRemoteControlRepository().getLastPostedCustomerInfo(mView.getCustomer().getName(),
      new CustomerRemoteRepository.LoadStringCallback() {
        @Override
        public void onResponse(String response) {
          PostedCustomerInfo postedCustomerInfo = PostedCustomerInfo.getPostedCustomerInfoWithDefaultValue();

          String[] result = DC.jsnToStrArr(response, "Id");
          assert result != null;
          postedCustomerInfo.setId(Integer.parseInt(result[0]));
          postedCustomerInfo.setName(mView.getCustomer().getName());
          postedCustomerInfo.setSubscriptionNo(mView.getCustomer().getSubscriptionNo());
          mView.finishByResult(postedCustomerInfo);

        }

        @Override
        public void onFailure(ResponseType responseType) {
          mView.hideProgress();
        }
      });
  }


  @Override
  public void validCustomerName(GenericResponseListener<Integer> responseListener) {

    getCoreDB().customerRepository().isRepeatedCustomerName(
      mView.getInputName(), isRepeated -> {
        if (isRepeated)
          responseListener.onResponse(MessageCode.E_REPEATED_CUSTOMER_NAME.getValue());
        else
          responseListener.onResponse(0);
      });

  }

  @Override
  public void validCustomerSubscriptionNo(GenericResponseListener<Integer> responseListener) {

    if (mView.getInputSubscriptionNo().trim().length() > 0)
      getCoreDB().customerRepository().isRepeatedCustomerSubscriptionNo(
        mView.getInputSubscriptionNo(), isRepeated -> {
          if (isRepeated)
            responseListener.onResponse(MessageCode.E_REPEATED_CUSTOMER_SUBSCRIPTION_NO.getValue());
          else
            responseListener.onResponse(0);
        });
    else
      responseListener.onResponse(0);

  }


}