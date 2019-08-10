package com.sppcco.customer.ui.other_customer_bsd;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.sppcco.core.data.model.Account;
import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.model.DetailAcc;
import com.sppcco.core.data.model.Project;
import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.data.sub_model.OtherCustomer;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.enums.Mode;
import com.sppcco.customer.R;
import com.sppcco.customer.R2;
import com.sppcco.customer.ui.acc_vector.ACCVectorActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherCustomerBSDFragment extends BottomSheetDialogFragment implements OtherCustomerBSDContract.View {

  //region Views

  @BindView(R2.id.btn_close)
  ImageButton btnClose;

  @BindView(R2.id.btn_done)
  ImageButton btnDone;

  @BindView(R2.id.name_toolbar)
  TextView nameToolbar;

  @BindView(R2.id.app_bar_layout)
  AppBarLayout appBarLayout;

  @BindView(R2.id.et_customer_name)
  EditText etCustomerName;

  @BindView(R2.id.et_customer_phone)
  EditText etCustomerPhone;

  @BindView(R2.id.et_customer_ec_code)
  EditText etCustomerEcCode;

  @BindView(R2.id.et_customer_address)
  EditText etCustomerAddress;

  private View mView;

  private Button btnAccount;
  private Button btnDetailAcc;
  private Button btnCostCenter;
  private Button btnProject;

  private TextView tvCustomerACC;

  //endregion Views

  //----------------------------------------
  private OtherCustomerBSDContract.Presenter mPresenter;

  private ACCVector tmp_accVector = null;

  private OtherCustomer otherCustomer = null;
  private OtherCustomer tmp_otherCustomer = null;

  private Mode mode;
  private boolean isDependentOnAccount = false;
  //----------------------------------------

  public OtherCustomerBSDFragment() {
    super();
  }

  public static OtherCustomerBSDFragment getInstance() {
    return new OtherCustomerBSDFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = OtherCustomerBSDPresenter.getPresenterInstance(this);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    super.onCreateDialog(savedInstanceState);

    mView = View.inflate(getContext(), R.layout.sheet_other_customer, null);
    ButterKnife.bind(this, mView);

    final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
    dialog.setContentView(mView);

    setExtras();
    initLayout();
    updateCustomerView();
    mPresenter.start();

    btnClose.setOnClickListener(v -> dismiss());
    return dialog;
  }

  private void setExtras() {
    if (getArguments() != null && getArguments().getSerializable(IntentKey.KEY_OTHER_CUSTOMER.getKey()) != null) {
      OtherCustomer inputArgument = (OtherCustomer) Objects.requireNonNull(getArguments().getSerializable(IntentKey.KEY_OTHER_CUSTOMER.getKey()));
      setOtherCustomer(inputArgument);
      setTempOtherCustomer(inputArgument);

      clearACC();
      if (getTempOtherCustomer().getName() == null || getTempOtherCustomer().getName().length() == 0) {
        setMode(Mode.NEW);
      } else {
        setMode(Mode.EDIT);
      }

    } else {
      dismiss();
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    registerEventBus();
  }

  private void registerEventBus() {
    if (!EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().register(this);
    }
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEvent(Object o) {

    if (o instanceof ACCVector) {

      setTempACCVector((ACCVector) o);
      updateACCView();

      if (isEmptyACCVector()) {
        setTempOtherCustomer(new OtherCustomer());
        updateACCView();
        setDependentOnAccount(true);

      } else {
        attachACCInCustomer();
        mPresenter.setACC(this::updateACCView);
        setDependentOnAccount(false);
      }
    }
  }

  @Override
  public void setPresenter(OtherCustomerBSDContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void initLayout() {
    BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) mView.getParent());
    mBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
    mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    View mACCView = mView.findViewById(R.id.inc_fragment_acc_vector);

    btnAccount = mACCView.findViewById(R.id.tv_account);
    btnAccount.setClickable(false);
    btnAccount.setText(null);

    btnDetailAcc = mACCView.findViewById(R.id.tv_detail_acc);
    btnDetailAcc.setClickable(false);
    btnDetailAcc.setText(null);

    btnCostCenter = mACCView.findViewById(R.id.tv_cost_center);
    btnCostCenter.setClickable(false);
    btnCostCenter.setText(null);

    btnProject = mACCView.findViewById(R.id.tv_project);
    btnProject.setClickable(false);
    btnProject.setText(null);

    tvCustomerACC = mACCView.findViewById(R.id.tv_customer_account);

    mACCView.findViewById(R.id.cl_account_balance_selected).setVisibility(View.GONE);
    mACCView.findViewById(R.id.cl_vector).setClickable(true);
    mACCView.findViewById(R.id.cl_vector).setOnClickListener(view -> attachCustomerInACC());

    hideKeyBoard();
  }

  @Override
  public boolean updateView() {

    return false;
  }

  @Override
  public void updateCustomerView() {
    etCustomerName.setText(getTempOtherCustomer() == null ? null : getTempOtherCustomer().getName());
    etCustomerPhone.setText(getTempOtherCustomer() == null ? null : getTempOtherCustomer().getPhoneNo());
    etCustomerEcCode.setText(getTempOtherCustomer() == null ? null : getTempOtherCustomer().getEcCode());
    etCustomerAddress.setText(getTempOtherCustomer() == null ? null : getTempOtherCustomer().getAddress());
  }

  @Override
  public void updateACCView() {
    btnAccount.setText(getTempACCVector() == null || getTempACCVector().getAccount() == null ? null : getTempACCVector().getAccount().getName());
    btnDetailAcc.setText(getTempACCVector() == null || getTempACCVector().getDetailAcc() == null ? null : getTempACCVector().getDetailAcc().getName());
    btnCostCenter.setText(getTempACCVector() == null || getTempACCVector().getCostCenter() == null ? null : getTempACCVector().getCostCenter().getName());
    btnProject.setText(getTempACCVector() == null || getTempACCVector().getProject() == null ? null : getTempACCVector().getProject().getName());

    tvCustomerACC.setText(getTempACCVector() == null ? null : getTempACCVector().getAccCode());
  }

  @Override
  public void clearACC() {
    setTempACCVector(new ACCVector());
    getTempACCVector().setAccount(new Account());
    getTempACCVector().setDetailAcc(new DetailAcc());
    getTempACCVector().setCostCenter(new CostCenter());
    getTempACCVector().setProject(new Project());
  }

  @Override
  public void updateACC(Object o) {
    if (o instanceof Account)
      getTempACCVector().setAccount((Account) o);
    else if (o instanceof DetailAcc)
      getTempACCVector().setDetailAcc((DetailAcc) o);
    else if (o instanceof CostCenter)
      getTempACCVector().setCostCenter((CostCenter) o);
    else if (o instanceof Project)
      getTempACCVector().setProject((Project) o);
  }

  @Override
  public void initData() {
  }

  @Override
  public boolean updateModel() {
    getOtherCustomer().setName(etCustomerName.getText().toString());
    getOtherCustomer().setPhoneNo(etCustomerPhone.getText().toString());
    getOtherCustomer().setEcCode(etCustomerEcCode.getText().toString());
    getOtherCustomer().setAddress(etCustomerAddress.getText().toString());

    return false;
  }

  @Override
  public boolean validData(boolean showMsg) {

    boolean result = true;
    hideKeyBoard();

    if (etCustomerName.getText() == null || etCustomerName.getText().length() == 0) {
      Toast.makeText(getActivity(), "نام خریدار معتبر نمی باشد", Toast.LENGTH_SHORT).show();
      result = false;
    } else if (isDependentOnAccount() || getTempOtherCustomer().getAccId().matches("")) {
      Toast.makeText(getActivity(), "حساب خریدار انتخاب شده معتبر نمی باشد", Toast.LENGTH_SHORT).show();
      result = false;
    }

    return result;
  }

  //TODO
  /*@Override
  public void showToast(Activity activity, String strContent, MessageType messageType) {
    new ToastMangaerBuilder.Builder()
      .setActivity(activity)
      .setMessage(strContent)
      .setType(messageType)
      .build();
  }*/

  @OnClick({R2.id.btn_done})
  public void onViewClicked(View view) {
    if (view.getId() == R.id.btn_done) {
      sendDialogResult();
    }
  }

  private void attachCustomerInACC() {
    clearACC();

    getTempOtherCustomer().setAccId(getOtherCustomer().getAccId());
    getTempOtherCustomer().setFAccId(0);
    getTempOtherCustomer().setCCId(0);
    getTempOtherCustomer().setPrjId(0);

    mPresenter.loadACC();
  }

  private void attachACCInCustomer() {
    getTempOtherCustomer().setAccId(getTempACCVector().getAccount() == null ? null : getTempACCVector().getAccount().getFullId());
    getTempOtherCustomer().setFAccId(getTempACCVector().getDetailAcc() == null ? 0 : getTempACCVector().getDetailAcc().getId());
    getTempOtherCustomer().setCCId(getTempACCVector().getCostCenter() == null ? 0 : getTempACCVector().getCostCenter().getCCCode());
    getTempOtherCustomer().setPrjId(getTempACCVector().getProject() == null ? 0 : getTempACCVector().getProject().getPCode());
  }

  @Override
  public void callACCVectorActivity(AccountTree ROOT) {
    Intent intent = new Intent(getActivity(), ACCVectorActivity.class);
    intent.putExtra(IntentKey.KEY_ACC_VECTOR.getKey(), getTempACCVector());
    intent.putExtra(IntentKey.KEY_ACC_VECTOR_ROOT.getKey(), ROOT);
    startActivity(intent);
  }

  @Override
  public void setAccCode(String accCode) {
    String fullId = getTempACCVector().getAccount().getId() == 0 || getTempACCVector().getAccount().getFullId().matches("0") ?
      " " : getTempACCVector().getAccount().getFullId();

    String detailCode = accCode == null || accCode.matches("0") ? " " : accCode;
    String ccCode = getTempACCVector().getCostCenter().getCCCode() == 0 ? " " : String.valueOf(getTempACCVector().getCostCenter().getCCCode());
    String pCode = getTempACCVector().getProject().getPCode() == 0 ? " " : String.valueOf(getTempACCVector().getProject().getPCode());

    getTempACCVector().setAccCode(String.format("%s - %s - %s - %s", fullId, detailCode, ccCode, pCode));
  }

  private void sendDialogResult() {
    if (getTargetFragment() == null) {
      return;
    }

    if (!validData(true))
      return;

    setOtherCustomer(getTempOtherCustomer());
    updateModel();

    Bundle bundle = new Bundle();
    bundle.putSerializable(IntentKey.KEY_OTHER_CUSTOMER.getKey(), getOtherCustomer());

    Intent intent = new Intent();
    intent.putExtras(bundle);
    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    dismiss();
  }

  private boolean isEmptyCustomer() {
    return getTempOtherCustomer() == null || getTempOtherCustomer().getAccId() == null ||
      getTempOtherCustomer().getAccId().matches("0") || btnAccount.getText() == null;
  }

  private boolean isEmptyACCVector() {
    return getTempACCVector().getAccount() == null && getTempACCVector().getDetailAcc() == null &&
      getTempACCVector().getCostCenter() == null && getTempACCVector().getProject() == null;
  }

  private void hideKeyBoard() {
    InputMethodManager inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Activity.INPUT_METHOD_SERVICE);
    Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(etCustomerName.getWindowToken(), 0);
    Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(etCustomerPhone.getWindowToken(), 0);
    Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(etCustomerEcCode.getWindowToken(), 0);
    Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(etCustomerAddress.getWindowToken(), 0);
  }

  private void unRegisterEventBus() {
    if (EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().unregister(this);
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mPresenter.destroy();
    unRegisterEventBus();
  }

  //region Getter Setter  --------------------------------------------------------------------------

  @Override
  public ACCVector getTempACCVector() {
    if (tmp_accVector == null)
      tmp_accVector = new ACCVector();
    return tmp_accVector;
  }

  private void setTempACCVector(ACCVector tmp_accVector) {
    this.tmp_accVector = tmp_accVector;
  }

  private OtherCustomer getOtherCustomer() {
    return otherCustomer;
  }

  private void setOtherCustomer(OtherCustomer otherCustomer) {
    this.otherCustomer = otherCustomer;
  }

  @Override
  public OtherCustomer getTempOtherCustomer() {
    return tmp_otherCustomer;
  }

  private void setTempOtherCustomer(OtherCustomer tmp_otherCustomer) {
    this.tmp_otherCustomer = tmp_otherCustomer;
  }

  @Override
  public Mode getMode() {
    return mode;
  }

  private void setMode(Mode mode) {
    this.mode = mode;
  }

  private boolean isDependentOnAccount() {
    return isDependentOnAccount;
  }

  @Override
  public void setDependentOnAccount(boolean dependentOnAccount) {
    isDependentOnAccount = dependentOnAccount;
  }

  //endregion Getter Setter --------------------------------------------------------------------------
}
