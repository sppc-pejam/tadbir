package com.sppcco.customer.ui.create.add;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;


import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.sub_model.PostedCustomerInfo;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.enums.MessageCode;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.listener.GenericResponseListener;
import com.sppcco.core.util.message.Message;
import com.sppcco.customer.R;
import com.sppcco.customer.R2;
import com.sppcco.customer.framework.widget.BaseVerticalStepper;
import com.sppcco.helperlibrary.converter.DC;
import com.sppcco.helperlibrary.manager.ValidationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by m_pejam on 01/21/18.
 * AddCustomerFragment
 */

public class AddCustomerFragment extends BaseVerticalStepper implements AddCustomerContract.View {

  private final String TAG = AddCustomerFragment.class.getSimpleName();

  @BindView(R2.id.et_name)
  AppCompatEditText etName;
  @BindView(R2.id.et_subscription_no)
  AppCompatEditText etSubscriptionNo;
  @BindView(R2.id.et_phone)
  AppCompatEditText etPhone;
  @BindView(R2.id.et_fax)
  AppCompatEditText etFax;
  @BindView(R2.id.et_mobile)
  AppCompatEditText etMobile;
  @BindView(R2.id.et_email)
  AppCompatEditText etEmail;
  @BindView(R2.id.et_address)
  AppCompatEditText etAddress;
  @BindView(R2.id.et_postal_code)
  AppCompatEditText etPostalCode;
  @BindView(R2.id.et_register_code)
  AppCompatEditText etRegisterCode;
  @BindView(R2.id.et_ecc_code)
  AppCompatEditText etEccCode;
  @BindView(R2.id.et_description)
  AppCompatEditText etDescription;
  @BindView(R2.id.chk_national_code)
  CheckBox chkNationalCode;


  private ProgressDialog mProgressDialog;

  private final int STEPS_COUNT = 5;
  //------------------------------------------------
  private final int NAME_CODE_INDEX = 1;
  private final int COMMUNICATION_INDEX = 2;
  private final int ADDRESS_INDEX = 3;
  private final int IDENTIFY_INDEX = 4;
  private final int DESC_CONFIRM_INDEX = 5;
  //------------------------------------------------
  private final boolean FORCED_PASSAGE_STEPS = false;


  private int mCurrentStep = NAME_CODE_INDEX;
  private int mSuccessStep = 0;
  //----------------------------------
  private Mode mMode;
  //----------------------------------
  private Customer mCustomer;
  //----------------------------------
  private List<String> lstTitles = new ArrayList<>();


  private AddCustomerContract.Presenter mPresenter;
  private View mParentView;

  @NonNull
  public static AddCustomerFragment newInstance() {
    return new AddCustomerFragment();
  }

  @Override
  public void setPresenter(AddCustomerContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void onAttach(Context context) {

    super.onAttach(context);
    Log.i(TAG, "onAttach");
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    if (savedInstanceState != null)
      setExtras(savedInstanceState);
    else {
      Bundle extras = getArguments();
      if (extras != null)
        setExtras(extras);
    }

    mPresenter.start();

    initData();
    Log.i(TAG, "onCreate");
  }

  private void setExtras(Bundle bundle) {

    setMode((Mode) bundle.getSerializable(IntentKey.KEY_MODE.getKey()));
    setCustomer((Customer) bundle.getSerializable(IntentKey.KEY_CUSTOMER.getKey()));
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    mParentView = onParentCreateView(inflater, container, savedInstanceState,
      R.layout.fragment_add_customer, lstTitles, STEPS_COUNT, FORCED_PASSAGE_STEPS);
    assert mParentView != null;
    ButterKnife.bind(this, mParentView);


    return mParentView;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    initLayout();
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    menu.clear();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void initLayout() {
    setHasOptionsMenu(true);

    hideSoftKeyboard();

    //TODO
    // Objects.requireNonNull(getActivity()).setTitle(BaseApplication.getResourceString(R.string.cpt_create_update_customer));

  }

  private void hideSoftKeyboard() {
    Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
  }

  @Override
  public boolean updateModel() {

    if (getCurrentStep() == NAME_CODE_INDEX) {

      if (!Objects.requireNonNull(etName.getText()).toString().equals(getCustomer().getName())) {
        getCustomer().setName(DC.enToFa(etName.getText().toString()));
        setModified(true);
      }
      if (!Objects.requireNonNull(etSubscriptionNo.getText()).toString().equals(getCustomer().getSubscriptionNo())) {
        getCustomer().setSubscriptionNo(DC.enToFa(etSubscriptionNo.getText().toString()));
        setModified(true);
      }

    }
    if (getCurrentStep() == COMMUNICATION_INDEX) {

      if (!Objects.requireNonNull(etPhone.getText()).toString().equals(getCustomer().getPhoneNo())) {
        getCustomer().setPhoneNo(DC.enToFa(etPhone.getText().toString()));
        setModified(true);
      }
      if (!Objects.requireNonNull(etFax.getText()).toString().equals(getCustomer().getFaxNo())) {
        getCustomer().setFaxNo(DC.enToFa(etFax.getText().toString()));
        setModified(true);
      }
      if (!Objects.requireNonNull(etMobile.getText()).toString().equals(getCustomer().getMobileNo())) {
        getCustomer().setMobileNo(DC.enToFa(etMobile.getText().toString()));
        setModified(true);
      }
      if (!Objects.requireNonNull(etEmail.getText()).toString().equals(getCustomer().getEmail())) {
        getCustomer().setEmail(DC.enToFa(etEmail.getText().toString()));
        setModified(true);
      }

    }
    if (getCurrentStep() == ADDRESS_INDEX) {

      if (!Objects.requireNonNull(etAddress.getText()).toString().equals(getCustomer().getAddress())) {
        getCustomer().setAddress(DC.enToFa(etAddress.getText().toString()));
        setModified(true);
      }
      if (!Objects.requireNonNull(etPostalCode.getText()).toString().equals(getCustomer().getPostalCode())) {
        getCustomer().setPostalCode(DC.enToFa(etPostalCode.getText().toString()));
        setModified(true);
      }


    }
    if (getCurrentStep() == IDENTIFY_INDEX) {

      if (!Objects.requireNonNull(etRegisterCode.getText()).toString().equals(getCustomer().getRegisterCode())) {
        getCustomer().setRegisterCode(DC.enToFa(etRegisterCode.getText().toString()));
        setModified(true);
      }
      if (!Objects.requireNonNull(etEccCode.getText()).toString().equals(getCustomer().getEcCode())) {
        getCustomer().setEcCode(DC.enToFa(etEccCode.getText().toString()));
        setModified(true);
      }

    }
    if (getCurrentStep() == DESC_CONFIRM_INDEX) {

      if (!Objects.requireNonNull(etDescription.getText()).toString().equals(getCustomer().getCDesc())) {
        getCustomer().setCDesc(DC.enToFa(etDescription.getText().toString()));
        setModified(true);
      }

    }

    return false;
  }

  @Override
  public void initData() {

    lstTitles.add(BaseApplication.getResourceString(R.string.cpt_customer_name));
    lstTitles.add(BaseApplication.getResourceString(R.string.cpt_communication));
    lstTitles.add(BaseApplication.getResourceString(R.string.cpt_address));
    lstTitles.add(BaseApplication.getResourceString(R.string.cpt_identify));
    lstTitles.add(BaseApplication.getResourceString(R.string.cpt_desc_confirm));
  }


  @Override
  public boolean validData(boolean showMsg) {

    if (getCustomer().getName().trim().length() < 1 && getCustomer().getName().equals("")) {
      snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_INPUT_NAME), null);
      return false;
    }

    /*if (getCustomer().getPostalCode().trim().length() != 10) {
      snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_INPUT_POSTAL_CODE), null);
      return false;
    }

    if (chkNationalCode.isChecked() && !ValidationManager.validationNationalCode(getCustomer().getRegisterCode())) {
      snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_INPUT_NATIONAL_CODE), null);
      return false;
    }*/

    /*if (Objects.requireNonNull(etName.getText()).toString().trim().length() > 0 &&
      Objects.requireNonNull(etName.getText()).toString().trim().length() != 10) {
      snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_INPUT_POSTAL_CODE), null);
      return false;
    }

    if (Objects.requireNonNull(etRegisterCode.getText()).toString().trim().length() > 0 &&
      !ValidationManager.validationNationalCode(Objects.requireNonNull(etRegisterCode.getText()).toString())) {
      snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_INPUT_NATIONAL_CODE), null);
      return false;
    }*/

    return true;
  }

  private void validDataByIndex(int index, GenericResponseListener<Boolean> booleanGenericResponseListener) {

    if (index == NAME_CODE_INDEX) {
      if (Objects.requireNonNull(etName.getText()).toString().trim().length() <= 1) {
        snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_INPUT_NAME), null);
        booleanGenericResponseListener.onResponse(false);
      } else {

        mPresenter.validCustomerName(nRepeatedNameCount -> {

          if (nRepeatedNameCount != 0) {
            handleModelValidationError(nRepeatedNameCount);
            booleanGenericResponseListener.onResponse(false);

          } else if (getInputSubscriptionNo().trim().length() > 0) {

            mPresenter.validCustomerSubscriptionNo(nRepeatedSubscriptionNoCount -> {
              if (nRepeatedSubscriptionNoCount != 0) {

                handleModelValidationError(nRepeatedSubscriptionNoCount);
                booleanGenericResponseListener.onResponse(false);
              } else
                booleanGenericResponseListener.onResponse(true);

            });

          } else
            booleanGenericResponseListener.onResponse(true);
        });
      }

    }

    if (index == ADDRESS_INDEX) {
      if (Objects.requireNonNull(etPostalCode.getText()).toString().trim().length() != 10) {
        snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_INPUT_POSTAL_CODE), null);
        booleanGenericResponseListener.onResponse(false);
      } else
        booleanGenericResponseListener.onResponse(true);
    }

    if (index == IDENTIFY_INDEX) {
      if (chkNationalCode.isChecked() && !ValidationManager.validationNationalCode(Objects.requireNonNull(etRegisterCode.getText()).toString())) {
        snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_INPUT_NATIONAL_CODE), null);
        booleanGenericResponseListener.onResponse(false);
      } else
        booleanGenericResponseListener.onResponse(true);
    }

  }

  @Override
  public boolean updateView() {

    return false;
  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();

    Log.i(TAG, "onDestroyView");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.i(TAG, "onDestroy");
  }

  @Override
  public void onDetach() {
    super.onDetach();

    Log.i(TAG, "onDetach");
  }

  @OnClick({R2.id.btn_continue_name_code, R2.id.btn_continue_communication,
    R2.id.btn_continue_address, R2.id.btn_continue_identify, R2.id.btn_confirm,
    R2.id.tv_national_code})
  public void onViewClicked(View view) {


    int i = view.getId();
    if (i == R.id.btn_continue_name_code) {/*if (validDataByIndex(NAME_CODE_INDEX))
          collapseAndContinue(NAME_CODE_INDEX);*/
      validDataByIndex(NAME_CODE_INDEX, aBoolean -> {
        if (aBoolean) {
          updateModel();
          collapseAndContinue(NAME_CODE_INDEX);
        }
      });
    } else if (i == R.id.btn_continue_communication) {
      updateModel();
      collapseAndContinue(COMMUNICATION_INDEX);
    } else if (i == R.id.btn_continue_address) {
      validDataByIndex(ADDRESS_INDEX, aBoolean -> {
        if (aBoolean) {
          updateModel();
          collapseAndContinue(ADDRESS_INDEX);
        }
      });
    } else if (i == R.id.btn_continue_identify) {
      validDataByIndex(IDENTIFY_INDEX, aBoolean -> {
        if (aBoolean) {
          updateModel();
          collapseAndContinue(IDENTIFY_INDEX);
        }
      });
    } else if (i == R.id.btn_confirm) {
      updateModel();
      if (validData(true)) {
        showProgress();
        mPresenter.createCustomer(this::finishView);
      }
      return;
    } else if (i == R.id.tv_national_code) {
      chkNationalCode.setChecked(!chkNationalCode.isChecked());
      return;
    } else {
      super.onViewClicked(view);
    }
  }


  @Override
  public void handleModelValidationError(int errorCode) {
    hideProgress();

    if (errorCode == MessageCode.E_REPEATED_CUSTOMER_NAME.getValue())
      snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_REPEATED_CUSTOMER_NAME), null);

    if (errorCode == MessageCode.E_REPEATED_CUSTOMER_SUBSCRIPTION_NO.getValue())
      snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_REPEATED_CUSTOMER_SUBSCRIPTION_NO), null);
  }

  @Override
  public void handlePostError(int errorCode) {
    hideProgress();

    //TODO
    //showToast(getActivity(), ErrorMessagesOnPost.SendCustomerMessages(errorCode), MessageType.DANGER);
  }


  private void showProgress() {
    if (getProgress().isShowing()) return;
    getProgress().show();
  }

  private ProgressDialog getProgress() {
    if (mProgressDialog == null) {
      mProgressDialog = new ProgressDialog(getActivity());
      mProgressDialog.setMessage(getResources().getString(R.string.msg_waiting));
      mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      mProgressDialog.setIndeterminate(false);
      mProgressDialog.setCanceledOnTouchOutside(false);
    }
    return mProgressDialog;
  }

  @Override
  public void hideProgress() {
    if (getProgress().isShowing()) getProgress().dismiss();
  }


  @Override
  public void finishByResult(PostedCustomerInfo postedCustomerInfo) {
    hideProgress();

    Intent resultIntent = new Intent();
    resultIntent.putExtra("RESULT_INTENT", postedCustomerInfo);
    Objects.requireNonNull(getActivity()).setResult(Activity.RESULT_OK, resultIntent);
    getActivity().finish();
  }


  //region Getter Setter

  @Override
  public int getCurrentStep() {
    return mCurrentStep;
  }

  public void setCurrentStep(int currentStep) {
    this.mCurrentStep = currentStep;
  }

  @Override
  public int getSuccessStep() {
    return mSuccessStep;
  }

  public void setSuccessStep(int successStep) {
    this.mSuccessStep = successStep;
  }

  @Override
  public boolean create() {
    if (validData(true))
      return false;

    mPresenter.createCustomer(this::finishView);
    return true;
  }


  public Mode getMode() {
    return mMode;
  }

  private void setMode(Mode mMode) {
    this.mMode = mMode;
  }

  public Customer getCustomer() {
    if (mCustomer == null) {
      mCustomer = Customer.getCustomerWithDefaultValue(Customer.BUYER);
      mCustomer.setMashmoolMaliat(1);
    }
    return mCustomer;
  }

  public void setCustomer(Customer mCustomer) {
    this.mCustomer = mCustomer;
  }

  @Override
  public String getInputName() {
    return Objects.requireNonNull(etName.getText()).toString();
  }

  @Override
  public String getInputSubscriptionNo() {
    return Objects.requireNonNull(etSubscriptionNo.getText()).toString();
  }

  //endregion Getter Setter
}
