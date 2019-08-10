package com.sppcco.customer.ui.acc_vector.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sppcco.core.data.model.Account;
import com.sppcco.core.data.model.BranchStatus;
import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.model.DetailAcc;
import com.sppcco.core.data.model.Project;
import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.fragment.BaseFragment;
import com.sppcco.core.util.converter.PersianUtil;
import com.sppcco.customer.R;
import com.sppcco.customer.R2;
import com.sppcco.customer.ui.acc_vector.ACCVectorActivity;
import com.sppcco.customer.ui.acc_vector.ACCVectorContract;
import com.sppcco.customer.ui.acc_vector.ScrollingAccountTree;
import com.sppcco.customer.ui.acc_vector.account.AccountFragment;
import com.sppcco.customer.ui.acc_vector.cost_center.CostCenterFragment;
import com.sppcco.customer.ui.acc_vector.detail_acc.DetailAccFragment;
import com.sppcco.customer.ui.acc_vector.project.ProjectFragment;
import com.sppcco.helperlibrary.converter.CK;
import com.sppcco.helperlibrary.converter.DC;
import com.sppcco.helperlibrary.dialog.material_dialog.listener.SelectItemResponseListener;
import com.sppcco.helperlibrary.dialog.material_dialog.manager.MDialogManager;

import java.util.List;
import java.util.Objects;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by b_nematzadeh on 11/11/2018.
 */
public abstract class ACCFragment extends BaseFragment implements ACCContract.Listener {

  //region Views

  @BindView(R2.id.recyclerView)
  RecyclerView recyclerView;

  @BindView(R2.id.img_search)
  AppCompatImageView imgSearch;

  @BindView(R2.id.et_search)
  AppCompatEditText etSearch;

  @BindView(R2.id.img_clear_search)
  AppCompatImageView imgClearSearch;

  @BindView(R2.id.img_sort)
  AppCompatImageView imgSort;

  //endregion Views

  private Unbinder unbinder;
  private View mView;

  @AnimRes
  @AnimatorRes
  private int mEnterAnim = 0;

  @AnimRes
  @AnimatorRes
  private int mExitAnim = 0;

  private int mSortPosition;
  private String strFilter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    if (getLayoutResourceId() != 0) {
      mView = inflater.inflate(getLayoutResourceId(), container, false);
      unbinder = ButterKnife.bind(this, mView);
    }

    return mView;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    //if (getArguments() != null)
    determinePath();

    etSearch.setOnEditorActionListener(searchEditorActionListener);
    etSearch.addTextChangedListener(searchTextChangeListener);

    //hideKeyBoard();
  }

  private TextView.OnEditorActionListener searchEditorActionListener = (textView, i, keyEvent) -> {
    if (textView.length() > 0) {
      search();
      return true;
    }
    return false;
  };

  private TextWatcher searchTextChangeListener = new TextWatcher() {

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      /*if (getMode() == Mode.NEW) {
        imgClearSearch.setVisibility(View.INVISIBLE);
      } else {
        if (charSequence.length() != 0) {
          imgClearSearch.setVisibility(View.VISIBLE);
        } else {
          imgClearSearch.setVisibility(View.INVISIBLE);
        }
      }*/

      if (charSequence.length() == 0)
        setFilter(null);
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
  };

  @OnClick({R2.id.img_clear_search, R2.id.img_search, R2.id.img_sort})
  public void onViewClicked(View view) {
    int i = view.getId();
    if (i == R.id.img_clear_search) {
      if (etSearch.length() > 0)
        clearSearch();
    } else if (i == R.id.img_search) {
      if (etSearch.length() > 0)
        search();
    }
  }

  public void search() {
    getACC().updateCell(getBranchStatus().getCurrentBranchPosition(), null);

    if (CK.isIntegerNum(Objects.requireNonNull(etSearch.getText()).toString())) {
      if (!PersianUtil.isENum(Objects.requireNonNull(etSearch.getText()).toString()))
        setFilter(DC.FNumToENum(etSearch.getText().toString()));
      else
        setFilter(Objects.requireNonNull(etSearch.getText()).toString());
    } else {
      setFilter(Objects.requireNonNull(etSearch.getText()).toString());
    }
    //hideKeyBoard();
  }

  public void clearSearch() {
    etSearch.setText(null);
    setFilter(null);
    //hideKeyBoard();
    getACC().updateCell(getBranchStatus().getCurrentBranchPosition(), null);
  }

  private void determinePath() {
    setBranchStatus(ScrollingAccountTree.scrolling(getACC().getRoot(), getCurrentBranchName()));
  }

  public void postValue() {
    //setACCVector(getTempACCVector());
    getACC().postValue();
  }

  public void previousStep() {
    //getBranchInfo().setDirection(Direction.PREVIOUS);
    getACC().onPreviousStep(getBranchStatus().getPreviousBranchPosition());
    getACC().updateCell(getBranchStatus().getCurrentBranchPosition(), null);
    setPreviousStepAnim();
    navigate(getBranchStatus().getPreviousBranchIdRes());
  }

  public void nextStep(String data) {
    getACC().onNextStep(getBranchStatus().getNextBranchPosition(), data);

    if (getBranchStatus().getNextBranchName() == AccountTree.END) {
      postValue();
    } else {
      //getBranchInfo().setDirection(Direction.NEXT);
      setNextStepAnim();
      navigate(getBranchStatus().getNextBranchIdRes());
    }
  }

  private void navigate(@IdRes int destinationIdRes) {
    //hideKeyBoard();

    NavOptions.Builder navBuilder = new NavOptions.Builder();
    NavOptions navOptions = navBuilder
      .setEnterAnim(mEnterAnim)
      .setExitAnim(mExitAnim)
      .build();
    Navigation.findNavController(Objects.requireNonNull(getView())).navigate(destinationIdRes, null, navOptions);
  }

  private void setNextStepAnim() {
    mEnterAnim = R.anim.slide_in_left;
    mExitAnim = R.anim.slide_out_right;
  }

  private void setPreviousStepAnim() {
    mEnterAnim = R.anim.slide_in_right;
    mExitAnim = R.anim.slide_out_left;
  }

  @Override
  public void onChangeBranch(StateProgressBar.StateNumber destinationNumber) {
    BranchStatus branchStatus = ScrollingAccountTree.onChangeBranch(
      getACC().getRoot(), getBranchStatus().getCurrentBranchPosition(), destinationNumber);
    branchStatus.setPreviousBranchPosition(destinationNumber);
    setBranchStatus(branchStatus);
    previousStep();
  }

  protected void showSortListDialog(List<String> items) {
    int nPreSelectedSortIndex = getSortPosition();

    MDialogManager.basicListDialog(
      BaseApplication.getContext(), getActivity(), items,
      BaseApplication.getResourceString(R.string.cpt_sort_by), getSortPosition(),
      new SelectItemResponseListener() {
        @Override
        public void onItemSelected(String content, int position) {

          if (position != nPreSelectedSortIndex) {
            setSortPosition(position);

            if (getChildFragment() instanceof AccountFragment)
              ((AccountFragment) getChildFragment()).onChangedItemSelected(content, position);

            if (getChildFragment() instanceof DetailAccFragment)
              ((DetailAccFragment) getChildFragment()).onChangedItemSelected(content, position);

            if (getChildFragment() instanceof CostCenterFragment)
              ((CostCenterFragment) getChildFragment()).onChangedItemSelected(content, position);

            if (getChildFragment() instanceof ProjectFragment)
              ((ProjectFragment) getChildFragment()).onChangedItemSelected(content, position);

          } else {
            if (getChildFragment() instanceof AccountFragment)
              ((AccountFragment) getChildFragment()).onItemSelected();

            if (getChildFragment() instanceof DetailAccFragment)
              ((DetailAccFragment) getChildFragment()).onItemSelected();

            if (getChildFragment() instanceof CostCenterFragment)
              ((CostCenterFragment) getChildFragment()).onItemSelected();

            if (getChildFragment() instanceof ProjectFragment)
              ((ProjectFragment) getChildFragment()).onItemSelected();
          }
        }

        @Override
        public void onDisAgree() {
          setSortPosition(nPreSelectedSortIndex);
          ((AccountFragment) getChildFragment()).onDisAgree();
        }
      }
    );
  }

  protected void disableSearch() {
    etSearch.setEnabled(false);
    etSearch.setFocusable(false);
    etSearch.setCursorVisible(false);
    etSearch.setKeyListener(null);
    etSearch.setBackgroundColor(Color.TRANSPARENT);
    etSearch.setText(getAccount().getName());

    imgClearSearch.setClickable(false);
    imgClearSearch.setVisibility(View.INVISIBLE);
    imgSearch.setClickable(false);
    imgSort.setClickable(false);
  }

  private void hideKeyBoard() {
    InputMethodManager inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Activity.INPUT_METHOD_SERVICE);
    if (inputMethodManager != null) {
      inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
    //Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(), 0);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (unbinder != null) {
      unbinder.unbind();
    }
  }

  //======================================
  protected BranchStatus getBranchStatus() {
    return ((ACCVectorActivity) Objects.requireNonNull(getActivity())).getBranchStatus();
  }

  private void setBranchStatus(BranchStatus branchStatus) {
    getACC().setBranchStatus(branchStatus);
  }

  //======================================
  public ACCVector getACCVector() {
    return ((ACCVectorActivity) Objects.requireNonNull(getActivity())).getACCVector();
  }

  protected void setACCVector(ACCVector accVector) {
    ((ACCVectorActivity) Objects.requireNonNull(getActivity())).setACCVector(accVector);
  }

  public ACCVector getTempACCVector() {
    return ((ACCVectorActivity) Objects.requireNonNull(getActivity())).getTempACCVector();
  }

  private void setTempACCVector(ACCVector accVector) {
    ((ACCVectorActivity) Objects.requireNonNull(getActivity())).setTempACCVector(accVector);
  }

  //======================================
  public Account getAccount() {
    return getTempACCVector().getAccount();
  }

  protected void setAccount(Account account) {

    getTempACCVector().setAccount(account);
  }

  //======================================
  //======================================
  public DetailAcc getDetailAcc() {
    return getTempACCVector().getDetailAcc();
  }

  protected void setDetailAcc(DetailAcc detailAcc) {

    getTempACCVector().setDetailAcc(detailAcc);
  }

  //======================================

  public CostCenter getCostCenter() {
    return getTempACCVector().getCostCenter();
  }

  protected void setCostCenter(CostCenter costCenter) {

    getTempACCVector().setCostCenter(costCenter);
  }

  //======================================

  public Project getProject() {
    return getTempACCVector().getProject();
  }

  protected void setProject(Project project) {

    getTempACCVector().setProject(project);
  }

  //======================================

  public String getFilter() {
    return strFilter;
  }

  protected void setFilter(String strFilter) {
    this.strFilter = strFilter;
  }

  //======================================
  public int getSortPosition() {
    return mSortPosition;
  }

  protected void setSortPosition(int sortPosition) {
    this.mSortPosition = sortPosition;
  }

  //==============================================================================
  protected abstract ACCVectorContract.View getACC();

  protected abstract int getLayoutResourceId();

  protected abstract AccountTree getCurrentBranchName();

  protected abstract Fragment getChildFragment();
}
