package com.sppcco.customer.ui.select;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.fragment.BaseFragment;
import com.sppcco.customer.R;
import com.sppcco.customer.R2;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by m_pejam on 01/21/18.
 * SelectCustomerFragment
 */

public class SelectCustomerFragment extends BaseFragment implements SelectCustomerContract.View {

  private SelectCustomerContract.Presenter mPresenter;

  @BindView(R2.id.recyclerView)
  RecyclerView recyclerView;
  @BindView(R2.id.cl_header)
  ConstraintLayout clHeader;
  @BindView(R2.id.img_below_shadow)
  ImageView imgBelowShadow;

  private SelectCustomerAdapter mAdapter;
  private ACCVector mACCVector;
  private boolean mShowCredit;
  private boolean mSortable;

  @NonNull
  static SelectCustomerFragment newInstance() {
    return new SelectCustomerFragment();
  }


  @Override
  public void onAttach(@NonNull Context context) {

    super.onAttach(context);
    Log.i("LOGLOG", "onAttach ---------------");
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    mPresenter.start();
    Log.i("LOGLOG", "onCreate ---------------");
  }


  @Override
  public void setPresenter(SelectCustomerContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_search_customer, container, false);
    ButterKnife.bind(this, view);

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if (savedInstanceState != null) {

      mShowCredit = savedInstanceState.getBoolean(IntentKey.KEY_SHOW_CREDIT.getKey());
      mSortable = savedInstanceState.getBoolean(IntentKey.KEY_SORTABLE.getKey());

      setAccVector((ACCVector) savedInstanceState.getSerializable(IntentKey.KEY_ACC_ID.getKey()));

    } else {
      Bundle extras = Objects.requireNonNull(getActivity()).getIntent().getExtras();
      if (extras != null) {
        mShowCredit = extras.getBoolean(IntentKey.KEY_SHOW_CREDIT.getKey());
        mSortable = extras.getBoolean(IntentKey.KEY_SORTABLE.getKey());
        setAccVector((ACCVector) extras.getSerializable(IntentKey.KEY_ACC_VECTOR.getKey()));
      }
    }

    initLayout();
  }


  @Override
  public void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);

    outState.putBoolean(IntentKey.KEY_SHOW_CREDIT.getKey(), mShowCredit);
    outState.putBoolean(IntentKey.KEY_SORTABLE.getKey(), mSortable);
    outState.putSerializable(IntentKey.KEY_ACC_ID.getKey(), getAccVector());
  }


  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    menu.clear();

    inflater.inflate(R.menu.menu_search_customer, menu);

    MenuItem search = menu.findItem(R.id.search);
    SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
    searchView.setQueryHint(BaseApplication.getResourceString(R.string.cpt_search_by_customer));

    search(searchView);
  }

  private void search(SearchView searchView) {

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {

        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {

        if (mAdapter != null) {
          mAdapter.getFilter().filter(newText);
          if (newText.isEmpty())
            mAdapter.notifyDataSetChanged();
        }

        return true;
      }
    });
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    return super.onOptionsItemSelected(item);
  }


  @Override
  public void initLayout() {
    setHasOptionsMenu(true);

    Objects.requireNonNull(getActivity()).setTitle(R.string.cpt_search_by_customer);


    recyclerView.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);

    clHeader.setVisibility(View.GONE);
    imgBelowShadow.setVisibility(View.GONE);

    //if (!isSortable())
    //imgSort.setEnabled(false);

  }


  @Override
  public boolean updateModel() {

    return false;
  }

  @Override
  public void initData() {

  }

  @Override
  public boolean validData(boolean showMsg) {

    return true;
  }

  @Override
  public boolean updateView() {

    //loading list view item with this function
    loadRecyclerViewItem();

    return false;
  }

  private void loadRecyclerViewItem() {
    mAdapter = getAdapterInstance();
    recyclerView.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();
  }

  private SelectCustomerAdapter getAdapterInstance() {
    if (mAdapter == null) {
      mAdapter = new SelectCustomerAdapter(mPresenter, this);
    }
    mAdapter.loadAdapterData();
    return mAdapter;
  }


  public void getObject(Object object) {

    EventBus.getDefault().post(object);
    finishView();

    /*Bundle bundle = new Bundle();
    bundle.putSerializable(IntentKey.KEY_CUSTOMER.getKey(), (Customer)object);
    CustomerBSDFragment fragment = new CustomerBSDFragment();
    fragment.setArguments(bundle);
    fragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), fragment.getTag());*/
  }

  @Override
  public void updateCustomerCreditValues(int customerId, String creditBalance, String accountBalance) {
    int nAdapterPosition = mAdapter.getAdapterPositionFromCustomerId(customerId);
    if (nAdapterPosition < 0)
      return;

    mAdapter.setCustomerCreditValues(
      (SelectCustomerAdapter.ViewHolder) recyclerView.findViewHolderForAdapterPosition(nAdapterPosition),
      customerId,
      creditBalance,
      accountBalance
    );
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();

    Log.i("LOGLOG", "onDestroyView ---------------");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.i("LOGLOG", "onDestroy ---------------");
  }

  @Override
  public void onDetach() {
    super.onDetach();

    Log.i("LOGLOG", "onDetach  ---------------");
  }

  @Override
  public void finishView() {
    Objects.requireNonNull(getActivity()).finish();
  }

  //-----------------------------------------------------------------

  public boolean isShowCredit() {
    return mShowCredit;
  }

  public boolean isSortable() {
    return mSortable;
  }

  @Override
  public boolean getPrimaryData() {
    if (mACCVector == null)
      return false;
    return
      !(getAccVector().getAccount().getFullId().matches("0"))
        || getAccVector().getDetailAcc().getId() != 0
        || getAccVector().getCostCenter().getId() != 0
        || getAccVector().getProject().getId() != 0;
  }

  @Override
  public ACCVector getAccVector() {
    return mACCVector;
  }

  private void setAccVector(ACCVector accVector) {
    mACCVector = accVector;
  }
}
