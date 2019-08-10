package com.sppcco.customer.ui.acc_vector.cost_center;

import android.view.View;


import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.customer.R2;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CostCenterViewHolder extends RecyclerView.ViewHolder {

  @BindView(R2.id.cl_cost_center)
  ConstraintLayout clCostCenter;

  @BindView(R2.id.tv_name)
  AppCompatTextView tvName;

  @BindView(R2.id.tv_code)
  AppCompatTextView tvCode;

  //@BindView(R.id.tv_acc_parent)
  //AppCompatTextView tvAccParent;

  private CostCenterContract.View mView;
  private CostCenterContract.Presenter mPresenter;
  private CostCenterAdapter mAdapter;

  private AccVectorInfo mAccVectorInfo;

  CostCenterViewHolder(View view,
                       CostCenterAdapter costCenterAdapter,
                       CostCenterContract.Presenter costCenterPresenter,
                       CostCenterContract.View costCenterView) {
    super(view);
    ButterKnife.bind(this, view);

    mAdapter = costCenterAdapter;
    mView = costCenterView;
    mPresenter = costCenterPresenter;

    initLayout();
  }

  private void initLayout() {
    tvName.setVisibility(View.VISIBLE);
    tvCode.setVisibility(View.VISIBLE);
    //tvAccParent.setVisibility(View.VISIBLE);
  }

  void bindTo(AccVectorInfo accVectorInfo) {

    setAccVectorInfo(accVectorInfo);

    itemView.setTag(getAccVectorInfo().getAccountName());
    tvCode.setText(getAccVectorInfo().getCode());
    tvName.setText(getAccVectorInfo().getAccountName());
    //tvAccParent.setText(getAccVectorInfo().getParentAccount());
  }

  void clear() {

    itemView.invalidate();
    tvCode.invalidate();
    tvName.invalidate();
    //tvAccParent.invalidate();
  }

  @OnClick(R2.id.cl_cost_center)
  void onClCostCenterClickListener() {
    mView.selectCostCenter(getAccVectorInfo());
  }

  private AccVectorInfo getAccVectorInfo() {
    return mAccVectorInfo;
  }

  private void setAccVectorInfo(AccVectorInfo accVectorInfo) {
    this.mAccVectorInfo = accVectorInfo;
  }
}
