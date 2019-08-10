package com.sppcco.sp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sppcco.core.framework.fragment.BaseFragment;
import com.sppcco.sp.R;
import com.sppcco.sp.R2;

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

  @OnClick({R2.id.btn_add})
  public void onViewClicked(View view) {
    int i = view.getId();
    if (i == R.id.btn_add){}
      //getActivity().startActivity(new Intent(BaseApplication.getContext(), AddCustomerActivity.class));
  }
}
