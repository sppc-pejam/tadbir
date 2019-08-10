package com.sppcco.customer.ui.customer_bsd;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.sppcco.core.data.model.Account;
import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.model.DetailAcc;
import com.sppcco.core.data.model.Project;
import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.customer.R;
import com.sppcco.customer.R2;
import com.sppcco.customer.ui.acc_vector.ACCVectorActivity;
import com.sppcco.customer.ui.select.SelectCustomerActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sppcco.core.util.app.CoreConstants.APP_TAG;


public class CustomerBSDFragment extends BottomSheetDialogFragment implements CustomerBSDContract.View {

  //public static final String TAG = CustomerBSDFragment.class.getSimpleName();

  //region Views

  @BindView(R2.id.btn_close)
  ImageButton btnClose;
  @BindView(R2.id.btn_done)
  ImageButton btnDone;
  @BindView(R2.id.name_toolbar)
  TextView nameToolbar;
  @BindView(R2.id.app_bar_layout)
  AppBarLayout appBarLayout;
  @BindView(R2.id.img_customer)
  ImageButton imgCustomer;
  @BindView(R2.id.tv_customer_name)
  TextView tvCustomerName;
  @BindView(R2.id.tv_customer_code)
  TextView tvCustomerCode;
  @BindView(R2.id.cl_customer_name_code)
  ConstraintLayout clCustomerNameCode;
  @BindView(R2.id.tv_customer_phone)
  TextView tvCustomerPhone;
  @BindView(R2.id.tv_customer_mobile)
  TextView tvCustomerMobile;
  @BindView(R2.id.tv_customer_address)
  TextView tvCustomerAddress;
  @BindView(R2.id.cl_customer)
  ConstraintLayout clCustomer;

  private Button btnAccount;
  private Button btnDetailAcc;
  private Button btnCostCenter;
  private Button btnProject;
  private TextView tvCustomerACC;
  private TextView tvAccountBalance;
  private LottieAnimationView animRefresh;

  private boolean isPending;
  private View mView;

  //endregion Views

  //----------------------------------------
  private CustomerBSDContract.Presenter mPresenter;

  //private ACCVector accVector = null;
  private ACCVector tmp_accVector = null;

  private Customer customer = null;
  private Customer tmp_customer = null;

  private Mode mode;
  //----------------------------------------

  public CustomerBSDFragment() {
    super();
  }

  public static CustomerBSDFragment getInstance() {
    return new CustomerBSDFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = CustomerBSDPresenter.getPresenterInstance(this);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    super.onCreateDialog(savedInstanceState);

    setExtras();
    final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
    mView = View.inflate(getContext(), R.layout.sheet_customer, null);

    ButterKnife.bind(this, mView);

    dialog.setContentView(mView);
    initLayout();

    btnClose.setOnClickListener(v -> dismiss());
    return dialog;
  }

  private void setExtras() {
    if (getArguments() != null && getArguments().getSerializable(IntentKey.KEY_CUSTOMER.getKey()) != null) {
      setCustomer((Customer) getArguments().getSerializable(IntentKey.KEY_CUSTOMER.getKey()));
      setTempCustomer(getCustomer());
      setMode(Mode.EDIT);
    } else {
      setMode(Mode.NEW);
      callSearchCustomer();
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    registerEventBus();
    mPresenter.start();
  }

  private void registerEventBus() {
    if (!EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().register(this);
    }
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEvent(Object o) {
    updateAccountBalance("---");

    if (o instanceof Customer) {
      setTempCustomer((Customer) o);
      updateCustomerView();
      attachCustomerInACC();
    } else if (o instanceof ACCVector) {
      setTempACCVector((ACCVector) o);
      if (isEmptyACCVector()) {
        setTempCustomer(new Customer());
        updateView();
      } else {
        attachACCInCustomer();
        mPresenter.setACC(this::updateView);
      }
    }
  }

  @Override
  public void setPresenter(CustomerBSDContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void initLayout() {
    BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) mView.getParent());
    mBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
    mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    View mACCView = mView.findViewById(R.id.inc_fragment_acc_vector);

    btnAccount = mACCView.findViewById(R.id.tv_account);
    btnDetailAcc = mACCView.findViewById(R.id.tv_detail_acc);
    btnCostCenter = mACCView.findViewById(R.id.tv_cost_center);
    btnProject = mACCView.findViewById(R.id.tv_project);
    tvCustomerACC = mACCView.findViewById(R.id.tv_customer_account);
    tvAccountBalance = mACCView.findViewById(R.id.tv_account_balance);
    animRefresh = mACCView.findViewById(R.id.anim_refresh);

    //mACCView.findViewById(R.id.tv_account).setOnClickListener(accVectorListener);
    //mACCView.findViewById(R.id.tv_detail_acc).setOnClickListener(accVectorListener);
    //mACCView.findViewById(R.id.tv_cost_center).setOnClickListener(accVectorListener);
    //mACCView.findViewById(R.id.tv_project).setOnClickListener(accVectorListener);

    mACCView.findViewById(R.id.cl_vector).setClickable(false);
    btnAccount.setClickable(false);
    btnDetailAcc.setClickable(false);
    btnCostCenter.setClickable(false);
    btnProject.setClickable(false);

    enableRefresh(true);
    animRefresh.setOnClickListener(view ->
    {
      if (!isPending()) {
        if (getTempCustomer() == null ||
          getTempCustomer().getAccId() == null ||
          getTempCustomer().getAccId().matches("0") ||
          getTempCustomer().getAccId().matches("0")) {
          return;
        } else {
          enableRefresh(false);
          updateAccountBalance("---");
          mPresenter.getAccVectorBalance(getTempCustomer());
        }
      } else
        enableRefresh(true);
    });

  }

  @Override
  public boolean updateView() {
    updateCustomerView();
    updateACCView();
    return false;
  }

  @Override
  public void updateCustomerView() {
    tvCustomerCode.setText(isEmptyCustomer() ? null : String.valueOf(getTempCustomer().getId()));
    tvCustomerName.setText(getTempCustomer() == null ? null : getTempCustomer().getName());
    tvCustomerPhone.setText(getTempCustomer() == null ? null : getTempCustomer().getPhoneNo());
    tvCustomerMobile.setText(getTempCustomer() == null ? null : getTempCustomer().getMobileNo());
    tvCustomerAddress.setText(getTempCustomer() == null ? null : getTempCustomer().getAddress());
    imgCustomer.setImageDrawable(isEmptyCustomer() ?
      BaseApplication.getResourceDrawable(R.drawable.ic_person_add_w_24) : BaseApplication.getResourceDrawable(R.drawable.ic_person_w_24));
  }

  @Override
  public void updateACCView() {
    Log.e(APP_TAG, "CustomerBSDFragment - updateACCView");

    btnAccount.setText(getTempACCVector() == null || getTempACCVector().getAccount() == null ?
      BaseApplication.getResourceString(R.string.cpt_account) : getTempACCVector().getAccount().getName());

    btnDetailAcc.setText(getTempACCVector() == null || getTempACCVector().getDetailAcc() == null ?
      BaseApplication.getResourceString(R.string.cpt_detail_acc) : getTempACCVector().getDetailAcc().getName());

    btnCostCenter.setText(getTempACCVector() == null || getTempACCVector().getCostCenter() == null ?
      BaseApplication.getResourceString(R.string.cpt_cost_center) : getTempACCVector().getCostCenter().getName());

    btnProject.setText(getTempACCVector() == null || getTempACCVector().getProject() == null ?
      BaseApplication.getResourceString(R.string.cpt_project) : getTempACCVector().getProject().getName());

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
    if (o instanceof Account) {
      Log.e(APP_TAG, "updateVector - Account - onNext");
      getTempACCVector().setAccount((Account) o);
    }
    if (o instanceof DetailAcc) {
      Log.e(APP_TAG, "updateVector - DetailAcc - onNext");
      getTempACCVector().setDetailAcc((DetailAcc) o);
    }
    if (o instanceof CostCenter) {
      Log.e(APP_TAG, "updateVector - CostCenter - onNext");
      getTempACCVector().setCostCenter((CostCenter) o);
    }
    if (o instanceof Project) {
      Log.e(APP_TAG, "updateVector - Project - onNext");
      getTempACCVector().setProject((Project) o);
    }
  }

  @Override
  public void updateAccountBalance(String account) {
    tvAccountBalance.setText(account);
  }

  @Override
  public void initData() {
  }

  @Override
  public boolean updateModel() {
    return false;
  }

  @Override
  public boolean validData(boolean showMsg) {
    return false;
  }

  /*@Override
  public void showToast(Activity activity, String strContent, MessageType messageType) {

  }*/

  /*private View.OnClickListener accVectorListener = view -> {
    switch (view.getId()) {
      case R.id.tv_account:
        callACCVectorActivity(AccountTree.ACCOUNT);
        break;
      case R.id.tv_detail_acc:
        callACCVectorActivity(AccountTree.DETAIL_ACC);
        break;
      case R.id.tv_cost_center:
        callACCVectorActivity(AccountTree.COST_CENTER);
        break;
      case R.id.tv_project:
        callACCVectorActivity(AccountTree.PROJECT);
        break;
    }

  };*/

  @OnClick({
    R2.id.btn_done,
    R2.id.img_customer, R2.id.cl_customer_name_code,
    R2.id.cl_customer_address, R2.id.cl_customer_phone})
  public void onViewClicked(View view) {
    int i = view.getId();
    if (i == R.id.btn_done) {
      sendDialogResult();
    } else if (i == R.id.img_customer || i == R.id.cl_customer_name_code || i == R.id.cl_customer_phone || i == R.id.cl_customer_address) {
      enableRefresh(true);
      mPresenter.disposeRequest();
      callSearchCustomer();
    }
  }

  private void attachCustomerInACC() {
    clearACC();
    mPresenter.loadACC();
  }

  private void attachACCInCustomer() {
    getTempCustomer().setAccId(getTempACCVector().getAccount() == null ? null : getTempACCVector().getAccount().getFullId());
    getTempCustomer().setFAccId(getTempACCVector().getDetailAcc() == null ? 0 : getTempACCVector().getDetailAcc().getId());
    getTempCustomer().setCCId(getTempACCVector().getCostCenter() == null ? 0 : getTempACCVector().getCostCenter().getCCCode());
    getTempCustomer().setPrjId(getTempACCVector().getProject() == null ? 0 : getTempACCVector().getProject().getPCode());
  }

  @Override
  public void callACCVectorActivity(AccountTree ROOT, Object o) {
    if (o instanceof Account) {
      getTempACCVector().setAccount((Account) o);
    } else if (o instanceof DetailAcc) {
      getTempACCVector().setDetailAcc((DetailAcc) o);
    } else if (o instanceof CostCenter) {
      getTempACCVector().setCostCenter((CostCenter) o);
    } else if (o instanceof Project) {
      getTempACCVector().setProject((Project) o);
    }

    callACCVectorActivity(ROOT);
  }

  @Override
  public void callACCVectorActivity(AccountTree ROOT) {
    Log.e(APP_TAG, "CustomerBSDFragment - getTempACCVector() : " + getTempACCVector().getAccount().getName());

    Intent intent = new Intent(getActivity(), ACCVectorActivity.class);
    intent.putExtra(IntentKey.KEY_ACC_VECTOR.getKey(), getTempACCVector());
    intent.putExtra(IntentKey.KEY_ACC_VECTOR_ROOT.getKey(), ROOT);
    startActivity(intent);
  }

  @Override
  public void setAccCode(String accCode) {
    String fullId =
      getTempACCVector().getAccount().getId() == 0 || getTempACCVector().getAccount().getFullId().matches("0") ?
        " " : getTempACCVector().getAccount().getFullId();

    String detailCode = accCode == null || accCode.matches("0") ? " " : accCode;
    String ccCode = getTempACCVector().getCostCenter().getCCCode() == 0 ? " " : String.valueOf(getTempACCVector().getCostCenter().getCCCode());
    String pCode = getTempACCVector().getProject().getPCode() == 0 ? " " : String.valueOf(getTempACCVector().getProject().getPCode());

    getTempACCVector().setAccCode(String.format("%s - %s - %s - %s", fullId, detailCode, ccCode, pCode));
  }

  @Override
  public void enableRefresh(boolean isRefresh) {
    setPending(!isRefresh);
    animRefresh.setClickable(isRefresh);

    if (isRefresh) {
      animRefresh.setFrame(0);
      animRefresh.pauseAnimation();
    } else {
      animRefresh.loop(true);
      animRefresh.playAnimation();
    }
  }

  private void callSearchCustomer() {
    Intent intent = new Intent(getActivity(), SelectCustomerActivity.class);
    Bundle bundle = new Bundle();
    bundle.putBoolean(IntentKey.KEY_SHOW_CREDIT.getKey(), true);
    bundle.putBoolean(IntentKey.KEY_SORTABLE.getKey(), true);
    bundle.putSerializable(IntentKey.KEY_ACC_VECTOR.getKey(), getTempACCVector());
    intent.putExtras(bundle);
    startActivity(intent);
  }

  private void sendDialogResult() {
    if (getTargetFragment() == null) {
      return;
    }

    if (isEmptyCustomer()) {
      return;
    }

    setCustomer(getTempCustomer());

    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    bundle.putSerializable(IntentKey.KEY_CUSTOMER.getKey(), customer);
    intent.putExtras(bundle);

    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    dismiss();
  }

  private boolean isEmptyCustomer() {
    return getTempCustomer() == null || getTempCustomer().getId() == 0;
  }

  private boolean isEmptyACCVector() {
    return getTempACCVector().getAccount() == null &&
      getTempACCVector().getDetailAcc() == null &&
      getTempACCVector().getCostCenter() == null &&
      getTempACCVector().getProject() == null;
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

  /*private void setACCVector(ACCVector accVector) {
    this.accVector = accVector;
  }

  private ACCVector getACCVector() {
    if (accVector == null) {
      accVector = new ACCVector();
    }
    return accVector;
  }*/

  private void setCustomer(Customer customer) {
    this.customer = customer;
  }

  private Customer getCustomer() {
    return customer;
  }

  @Override
  public ACCVector getTempACCVector() {
    return tmp_accVector;
  }

  private void setTempACCVector(ACCVector tmp_accVector) {
    this.tmp_accVector = tmp_accVector;
  }

  @Override
  public Customer getTempCustomer() {
    return tmp_customer;
  }

  private void setTempCustomer(Customer tmp_customer) {
    this.tmp_customer = tmp_customer;
  }

  @Override
  public Mode getMode() {
    return mode;
  }

  private void setMode(Mode mode) {
    this.mode = mode;
  }

  private boolean isPending() {
    return isPending;
  }

  private void setPending(boolean pending) {
    isPending = pending;
  }
  //endregion Getter Setter --------------------------------------------------------------------------
}
