package com.sppcco.customer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sppcco.core.data.sub_model.OtherCustomer;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.fragment.BaseFragment;
import com.sppcco.customer.R;
import com.sppcco.customer.R2;
import com.sppcco.customer.ui.customer_bsd.CustomerBSDFragment;
import com.sppcco.customer.ui.create.add.AddCustomerActivity;
import com.sppcco.customer.ui.create.load.LoadCustomerActivity;
import com.sppcco.customer.ui.other_customer_bsd.OtherCustomerBSDFragment;
import com.sppcco.customer.ui.select.SelectCustomerActivity;
import com.sppcco.merchandise.ui.select.SelectMerchandiseActivity;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends BaseFragment implements MainContract.View {

  private MainContract.Presenter mPresenter;
  private View mParentView;


  @BindView(R2.id.btn_add)
  AppCompatButton btnAdd;

  //region implementation methods

  @NonNull
  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Override
  public void setPresenter(MainContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void initData() {

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

  //endregion implementation methods


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    mParentView = inflater.inflate(R.layout.fragment_main, container, false);
    ButterKnife.bind(this, mParentView);


    initLayout();
    return mParentView;
  }

  @Override
  public void initLayout() {


    //btnAdd = mParentView.findViewById(R.id.btn_add);
    //btnAdd.setOnClickListener(this);

  }

  @OnClick({R2.id.btn_add, R2.id.btn_load, R2.id.btn_search, R2.id.btn_bsd, R2.id.btn_merch})
  public void onViewClicked(View view) {
    int i = view.getId();
    if (i == R.id.btn_add)
      getActivity().startActivity(new Intent(BaseApplication.getContext(), AddCustomerActivity.class));
    if (i == R.id.btn_load)
      getActivity().startActivity(new Intent(BaseApplication.getContext(), LoadCustomerActivity.class));
    if (i == R.id.btn_search){
      Intent intent = new Intent(BaseApplication.getContext(), SelectCustomerActivity.class);
      Bundle bundle = new Bundle();
      bundle.putBoolean(IntentKey.KEY_SHOW_CREDIT.getKey(), true);
      bundle.putBoolean(IntentKey.KEY_SORTABLE.getKey(), true);
      bundle.putSerializable(IntentKey.KEY_ACC_VECTOR.getKey(), null);
      intent.putExtras(bundle);
      startActivity(intent);
    }
    if((i == R.id.btn_bsd)){
      Bundle bundle = new Bundle();
      bundle.putSerializable(IntentKey.KEY_CUSTOMER.getKey(), null);

       final int CUSTOMER_REQUEST_CODE = 1;

      CustomerBSDFragment fragment = new CustomerBSDFragment();
      fragment.setArguments(bundle);
      fragment.setTargetFragment(this, CUSTOMER_REQUEST_CODE);
      fragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), fragment.getTag());
    }
    if((i == R.id.btn_other_bsd)){
      Bundle bundle = new Bundle();
      bundle.putSerializable(IntentKey.KEY_OTHER_CUSTOMER.getKey(), null);

      OtherCustomer otherCustomer = new OtherCustomer();

      OtherCustomerBSDFragment fragment = new OtherCustomerBSDFragment();
      fragment.setArguments(bundle);
      fragment.setTargetFragment(this, 2);
      fragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), fragment.getTag());
    }
    if(i == R.id.btn_merch){
      Intent intent = new Intent(getActivity(), SelectMerchandiseActivity.class);
      Bundle bundle = new Bundle();

      bundle.putSerializable(IntentKey.KEY_MODE.getKey(), Mode.NEW );
      //---------------------------------------------------------------------------------------------
      bundle.putBoolean(IntentKey.KEY_ALL_STOCK.getKey(), false);
      bundle.putBoolean(IntentKey.KEY_MERCH_STOCK.getKey(), false);
      bundle.putBoolean(IntentKey.KEY_SHOW_INVENTORY.getKey(), true);
      //---------------------------------------------------------------------------------------------
      bundle.putBoolean(IntentKey.KEY_SORTABLE.getKey(), true);
      bundle.putBoolean(IntentKey.KEY_HAS_ERROR_STATUS.getKey(), false);
      //---------------------------------------------------------------------------------------------
      bundle.putSerializable(IntentKey.KEY_OBJECT.getKey(), null);
      bundle.putSerializable(IntentKey.KEY_OBJECT_ARTICLE.getKey(), null);
      bundle.putSerializable(IntentKey.KEY_MERCH_INFO.getKey(), null);
      //---------------------------------------------------------------------------------------------
      bundle.putInt(IntentKey.KEY_ARTICLE_COUNT.getKey(), 4);

      intent.putExtras(bundle);
      startActivity(intent);
    }
      //getActivity().startActivity(new Intent(BaseApplication.getContext(), SelectCustomerActivity.class));
  }

  /*@Override
  public void onClick(View view) {
    getActivity().startActivity(new Intent(BaseApplication.getContext(), AddCustomerActivity.class));

  }*/
}
