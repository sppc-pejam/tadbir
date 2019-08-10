package com.sppcco.customer.ui.acc_vector;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.kofigyan.stateprogressbar.listeners.OnStateItemClickListener;
import com.sppcco.core.data.model.BranchStatus;
import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.framework.activity.BaseAppCompatActivity;
import com.sppcco.customer.R;
import com.sppcco.customer.R2;
import com.sppcco.customer.ui.acc_vector.account.AccountContract;
import com.sppcco.customer.ui.acc_vector.account.AccountFragment;
import com.sppcco.customer.ui.acc_vector.cost_center.CostCenterContract;
import com.sppcco.customer.ui.acc_vector.cost_center.CostCenterFragment;
import com.sppcco.customer.ui.acc_vector.detail_acc.DetailAccContract;
import com.sppcco.customer.ui.acc_vector.detail_acc.DetailAccFragment;
import com.sppcco.customer.ui.acc_vector.project.ProjectContract;
import com.sppcco.customer.ui.acc_vector.project.ProjectFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.sppcco.core.util.app.CoreConstants.APP_TAG;


/**
 * Created by b_nematzadeh on 9/19/2018.
 */
public class ACCVectorActivity extends BaseAppCompatActivity implements ACCVectorContract.View {

  ACCVectorContract.Presenter mPresenter;

  @BindView(R2.id.cl_acc_vector)
  ConstraintLayout clAccVector;

  @BindView(R2.id.tv_first_cell)
  TextView tvFirstCell;

  @BindView(R2.id.tv_second_cell)
  TextView tvSecondCell;

  @BindView(R2.id.tv_third_cell)
  TextView tvThirdCell;

  @BindView(R2.id.tv_fourth_cell)
  TextView tvFourthCell;

  @BindView(R2.id.state_progress)
  StateProgressBar stateProgress;

  //----------------------------------------
  private ACCVector mACCVector = null;
  private ACCVector tmpACCVector = null;

  private BranchStatus mBranchStatus;
  private AccountTree mRoot;
  //TODO
  private StateProgressBar.StateNumber mCurrentPosition = StateProgressBar.StateNumber.ONE;
  //----------------------------------------

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      getArguments(extras);

      new ActivityViewBuilder(this)
        .requestFeatures()
        .contentView(R.layout.activity_vector)
        .setNav(R.id.nav_host, R.navigation.nav_acc_vector, getDestination(), getACCVectorBundle())
        .build();

      ButterKnife.bind(this);
      stateProgress.setMaxStateNumber(StateProgressBar.StateNumber.FOUR);
      mCurrentPosition = StateProgressBar.StateNumber.ONE;
      stateProgress.setStateSize(48f);
      stateProgress.setStateDescriptionData(getRoot().getNameStep());
      //stateProgress.setOnStateItemClickListener(stateProgressItemClickListener);
      stateProgress.setStateDescriptionTypeface(getString(R.string.iranian_sans_en_num));

      mPresenter = ACCVectorPresenter.getAccountVectorPresenterInstance(this);
    }
  }

  private void getArguments(Bundle extras) {
    setACCVector((ACCVector) extras.getSerializable(IntentKey.KEY_ACC_VECTOR.getKey()));
    setTempACCVector(getACCVector());

    Log.e(APP_TAG, "ACCVectorActivity - getTempACCVector() : " + getTempACCVector().getAccount().getName());
    Log.e(APP_TAG, "ACCVectorActivity - getTempACCVector() : " + getTempACCVector().getDetailAcc().getName());

    setRoot((AccountTree) extras.getSerializable(IntentKey.KEY_ACC_VECTOR_ROOT.getKey()));
  }

  private Bundle getACCVectorBundle() {
    Bundle bundle = new Bundle();
    bundle.putSerializable(IntentKey.KEY_ACC_VECTOR_ROOT.getKey(), getACCVector());
    return bundle;
  }

  private int getDestination() {
    @IdRes
    int startDestId = 0;

    switch (getRoot()) {
      case ACCOUNT:
        startDestId = R.id.accountFragment;
        break;
      case DETAIL_ACC:
        startDestId = R.id.detailAccFragment;
        break;
      case COST_CENTER:
        startDestId = R.id.costCenterFragment;
        break;
      case PROJECT:
        startDestId = R.id.projectFragment;
        break;
    }

    return startDestId;
  }

  OnStateItemClickListener stateProgressItemClickListener =
    (stateProgressBar, stateItem, stateNumber, isCurrentState) -> {
      if (stateItem.isStateChecked() && !isCurrentState) {
        getDestinationFromPosition(
          stateNumber == 1 ? StateProgressBar.StateNumber.ONE :
            (stateNumber == 2 ? StateProgressBar.StateNumber.TWO :
              (stateNumber == 3 ? StateProgressBar.StateNumber.THREE : StateProgressBar.StateNumber.FOUR))
        );
      }
    };

  @Override
  public void setPresenter(ACCVectorContract.Presenter mPresenter) {
    this.mPresenter = mPresenter;
  }

  @Override
  public void postValue() {
    setACCVector(getTempACCVector());
    EventBus.getDefault().post(getACCVector());
    finish();
  }

  @Override
  public void updateView(String detCode) {
    String fullId, detId, ccCode, pCode;

    fullId = getACCVector().getAccount().getFullId();
    detId = detCode;
    ccCode = String.valueOf(getACCVector().getCostCenter().getCCCode());
    pCode = String.valueOf(getACCVector().getProject().getPCode());

    switch (getRoot()) {
      case ACCOUNT: // ACCOUNT -> DETAIL_ACC -> COST_CENTER -> PROJECT
        tvFirstCell.setText(fullId);
        tvSecondCell.setText(detId);
        tvThirdCell.setText(ccCode);
        tvFourthCell.setText(pCode);
        break;
      case DETAIL_ACC: // DETAIL_ACC -> ACCOUNT -> COST_CENTER -> PROJECT
        tvFirstCell.setText(detId);
        tvSecondCell.setText(fullId);
        tvThirdCell.setText(ccCode);
        tvFourthCell.setText(pCode);
        break;
      case COST_CENTER: // COST_CENTER -> ACCOUNT -> DETAIL_ACC -> PROJECT
        tvFirstCell.setText(ccCode);
        tvSecondCell.setText(fullId);
        tvThirdCell.setText(detId);
        tvFourthCell.setText(pCode);
        break;
      case PROJECT: // PROJECT -> ACCOUNT -> DETAIL_ACC -> COST_CENTER
        tvFirstCell.setText(pCode);
        tvSecondCell.setText(fullId);
        tvThirdCell.setText(detId);
        tvFourthCell.setText(ccCode);
        break;
    }
  }

  @Override
  protected void onStart() {
    super.onStart();
    mPresenter.start();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    menu.clear();
    return true;
  }

  @Override
  public void onBackPressed() {
    /*if (mCurrentPosition == StateProgressBar.StateNumber.ONE)
      super.onBackPressed();
    else*/
    close();
  }

  private void close() {
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
    LayoutInflater inflater = this.getLayoutInflater();
    //TODO
    @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.dialog_close, null);
    dialogBuilder.setView(dialogView);

    TextView tvExit = dialogView.findViewById(R.id.tv_exit);
    TextView tvCancel = dialogView.findViewById(R.id.tv_cancel);

    AlertDialog alertDialog = dialogBuilder.create();
    alertDialog.setCanceledOnTouchOutside(true);
    alertDialog.show();

    tvExit.setOnClickListener(view -> {
      EventBus.getDefault().post(new ACCVector());
      alertDialog.dismiss();
      finish();
    });

    tvCancel.setOnClickListener(view ->
      alertDialog.dismiss());
  }

  @Override
  public void onNextStep(StateProgressBar.StateNumber stepNumber, String schema) {
    updateCell(mCurrentPosition, schema);
    mCurrentPosition = stepNumber;

    if (mCurrentPosition != null && stepNumber.getValue() <= StateProgressBar.StateNumber.FOUR.getValue())
      stateProgress.setCurrentStateNumber(mCurrentPosition);

    stateProgress.setAnimationDuration(400);
  }

  @Override
  public void onPreviousStep(StateProgressBar.StateNumber stepNumber) {
    mCurrentPosition = stepNumber;
    if (mCurrentPosition != null)
      stateProgress.setCurrentStateNumber(mCurrentPosition);
  }

  private void onChangeStep(StateProgressBar.StateNumber stateNumber) {
    switch (stateNumber) {
      case ONE:
        mCurrentPosition = null;
        break;
      case TWO:
        mCurrentPosition = StateProgressBar.StateNumber.ONE;
        break;
      case THREE:
        mCurrentPosition = StateProgressBar.StateNumber.TWO;
        break;
      case FOUR:
        mCurrentPosition = StateProgressBar.StateNumber.THREE;
        break;
    }
    if (mCurrentPosition != null)
      stateProgress.setCurrentStateNumber(mCurrentPosition);
  }

  @Override
  public void updateCell(StateProgressBar.StateNumber stateNumber, String value) {
    if (stateNumber != null) {
      switch (stateNumber) {
        case ONE:
          tvFirstCell.setText(value);
          break;
        case TWO:
          tvSecondCell.setText(value);
          break;
        case THREE:
          tvThirdCell.setText(value);
          break;
        case FOUR:
          tvFourthCell.setText(value);
          break;
      }
    }
  }

  private void getDestinationFromPosition(StateProgressBar.StateNumber destinationNumber) {
    updateCell(mCurrentPosition, null);

    if (mCurrentPosition == StateProgressBar.StateNumber.FOUR) {
      if (destinationNumber == StateProgressBar.StateNumber.TWO) {
        tvThirdCell.setText(null);
      } else if (destinationNumber == StateProgressBar.StateNumber.ONE) {
        tvSecondCell.setText(null);
        tvThirdCell.setText(null);
      }
    }
    if (mCurrentPosition == StateProgressBar.StateNumber.THREE) {
      if (destinationNumber == StateProgressBar.StateNumber.ONE) {
        tvSecondCell.setText(null);
      }
    }

    Fragment fragment = getCurrentFragment();
    if (fragment instanceof AccountFragment) {
      AccountContract.Listener accountListener = (AccountContract.Listener) (fragment);
      accountListener.onChangeBranch(destinationNumber);

    } else if (fragment instanceof DetailAccFragment) {
      DetailAccContract.Listener detailAccListener = (DetailAccContract.Listener) (fragment);
      detailAccListener.onChangeBranch(destinationNumber);

    } else if (fragment instanceof CostCenterFragment) {
      CostCenterContract.Listener costCenterListener = (CostCenterContract.Listener) (fragment);
      costCenterListener.onChangeBranch(destinationNumber);

    } else if (fragment instanceof ProjectFragment) {
      ProjectContract.Listener projectListener = (ProjectContract.Listener) (fragment);
      projectListener.onChangeBranch(destinationNumber);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  private Fragment getCurrentFragment() {
    return Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.nav_host))
      .getChildFragmentManager().getFragments().get(0);
  }

  @Override
  public void setTempACCVector(ACCVector accVector) {
    tmpACCVector = accVector;
  }

  @Override
  public ACCVector getTempACCVector() {
    return tmpACCVector;
  }

  public void setACCVector(ACCVector mACCVector) {
    this.mACCVector = mACCVector;
  }

  @Override
  public ACCVector getACCVector() {
    return mACCVector;
  }

  @Override
  public AccountTree getRoot() {
    return mRoot;
  }

  public void setRoot(AccountTree root) {
    mRoot = root;
  }

  public BranchStatus getBranchStatus() {
    return mBranchStatus;
  }

  public void setBranchStatus(BranchStatus mBranchStatus) {
    this.mBranchStatus = mBranchStatus;
  }
}
