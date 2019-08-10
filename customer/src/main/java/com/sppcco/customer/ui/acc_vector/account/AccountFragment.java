package com.sppcco.customer.ui.acc_vector.account;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sppcco.core.data.model.Account;
import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.customer.R;
import com.sppcco.customer.R2;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.customer.ui.acc_vector.ACCVectorActivity;
import com.sppcco.customer.ui.acc_vector.ACCVectorContract;
import com.sppcco.customer.ui.acc_vector.SortDialog;
import com.sppcco.customer.ui.acc_vector.base.ACCFragment;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;


public class AccountFragment extends ACCFragment implements AccountContract.View, SortDialog {

  private AccountContract.Presenter mPresenter;

  private AccountViewModel mViewModel;
  private AccountAdapter mAdapter;

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

  public static AccountFragment newInstance() {
    return new AccountFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = AccountPresenter.getAccountPresenterInstance(this);
  }

  @Override
  public void setPresenter(AccountContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    etSearch.addTextChangedListener(searchTextChangeListener);

    updateView();

    if (getRoot() == AccountTree.ACCOUNT && getACCVector().getAccount().getId() != 0) {
      super.disableSearch();
    } else if (getRoot() == AccountTree.ACCOUNT) {
      setFilter(getACCVector().getAccount().getName());
    } else {
      mPresenter.prepareListData();
    }

    if (getRoot() == AccountTree.ACCOUNT) {
      etSearch.setText(getACCVector().getAccount().getName());
      initData();
      initLayout();
    }


  }

  private TextWatcher searchTextChangeListener = new TextWatcher() {

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      if (charSequence.length() == 0) {
        initData();
        initRecyclerView();
      }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
  };

  @OnClick({R2.id.img_clear_search, R2.id.img_search, R2.id.img_sort})
  public void onViewClicked(View view) {
    super.onViewClicked(view);
    if (view.getId() == R.id.img_sort) {
      showSortListDialog(mPresenter.getSortList());
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    mPresenter.start();
  }

  @Override
  public void initLayout() {
    initRecyclerView();
  }

  /*-------initRecyclerView-------*/
  private void initRecyclerView() {
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.setAdapter(mAdapter);
  }
  /*-------initRecyclerView-------*/

  @Override
  public void initData() {
    if (getSortPosition() == 0)
      setSortPosition(0);

    if (getFilter() == null) {
      setFilter(null);
      imgClearSearch.setVisibility(View.INVISIBLE);
    }

    mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(AccountViewModel.class);
    mViewModel.setView(this);
    mViewModel.setPresenter(mPresenter);
    mViewModel.ViewModelObserveProviders();

    mAdapter = new AccountAdapter(mPresenter, this);

    mViewModel.getAccountList().observe(this, accVectorInfos -> mAdapter.submitList(accVectorInfos));

    initViewModel();

  }

  @Override
  public void initViewModel() {
    mViewModel.initData();
  }

  @Override
  public boolean updateModel() {
    return false;
  }

  @Override
  public boolean updateView() {
    return false;
  }

  @Override
  public boolean validData(boolean showMsg) {
    return false;
  }

  @Override
  public void selectAccount(AccVectorInfo accVectorInfo) {
    if (getRoot() == getBranchStatus().getCurrentBranchName()) {
      if (getACCVector().getDetailAcc().getId() == 0)
        getACC().updateCell(StateProgressBar.StateNumber.TWO, null);
    }
    getACC().updateCell(StateProgressBar.StateNumber.THREE, null);
    getACC().updateCell(StateProgressBar.StateNumber.FOUR, null);
    mPresenter.setAccount(accVectorInfo);
  }

  @Override
  public void previousStep() {
    setAccount(new Account());
    getAccount().setFullId("0");
    setFilter(null);
    super.previousStep();
  }

  @Override
  public void nextStep(AccVectorInfo accVectorInfo) {
    getAccount().setFullId(accVectorInfo.getCode());
    super.nextStep(accVectorInfo.getCode());
  }

  @Override
  public void skipNextStep() {
    setAccount(new Account());
    getAccount().setFullId("0");
    super.nextStep(getAccount().getFullId());
  }

  @Override
  public void postValue() {
    Account account = new Account();
    account.setFullId("0");
    setAccount(account);
    super.postValue();
  }

  @Override
  public void onChangeBranch(StateProgressBar.StateNumber destinationNumber) {
    super.onChangeBranch(destinationNumber);
  }

  public void search() {
    super.search();
    initViewModel();
  }

  public void clearSearch() {
    super.clearSearch();
    initViewModel();
  }

  public void onItemSelected() {
    mAdapter.submitList(null);
    mViewModel.initData();
  }

  @Override
  public void onItemSelected(String content, int position) {
  }

  @Override
  public void onChangedItemSelected(String content, int position) {
    mAdapter.submitList(null);
    mViewModel.initData();
  }

  public void onDisAgree() {

  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mPresenter.destroy();
  }

  //==============================================================================
  @Override
  public ACCVector getTempACCVector() {
    return ((ACCVectorActivity) Objects.requireNonNull(this.getActivity())).getTempACCVector();
    //return getACC().getTempACCVector();
  }

  @Override
  public ACCVector getACCVector() {
    return ((ACCVectorActivity) Objects.requireNonNull(this.getActivity())).getACCVector();
    //return getACC().getACCVector();
  }

  @Override
  public AccountTree getRoot() {
    return ((ACCVectorActivity) Objects.requireNonNull(this.getActivity())).getRoot();
    //return getACC().getRoot();
  }

  @Override
  protected int getLayoutResourceId() {
    return R.layout.fragment_account;
  }

  @Override
  protected AccountTree getCurrentBranchName() {
    return AccountTree.ACCOUNT;
  }

  @Override
  protected Fragment getChildFragment() {
    return this;
  }

  /*@Override
  protected ACCVectorContract.View getACC() {
    return (ACCVectorContract.View) getActivity();
  }*/
  protected ACCVectorContract.View getACC() {
    return (ACCVectorContract.View) Objects.requireNonNull(this.getActivity());
  }

  //==============================================================================
}
