package com.sppcco.customer.ui.acc_vector.detail_acc;

import android.view.View;


import com.sppcco.core.data.sub_model.DetailAccVectorInfo;
import com.sppcco.customer.R2;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailAccViewHolder extends RecyclerView.ViewHolder {

  @BindView(R2.id.cl_detail_acc)
  ConstraintLayout clDetailAcc;

  @BindView(R2.id.tv_name)
  AppCompatTextView tvName;

  @BindView(R2.id.tv_code)
  AppCompatTextView tvCode;

  @BindView(R2.id.tv_acc_parent)
  AppCompatTextView tvAccParent;

  private DetailAccContract.View mView;
  private DetailAccContract.Presenter mPresenter;
  private DetailAccAdapter mAdapter;

  private DetailAccVectorInfo mAccVectorInfo;

  DetailAccViewHolder(View view,
                      DetailAccAdapter detailAccAdapter,
                      DetailAccContract.Presenter detailAccPresenter,
                      DetailAccContract.View detailAccView) {
    super(view);
    ButterKnife.bind(this, view);

    mAdapter = detailAccAdapter;
    mView = detailAccView;
    mPresenter = detailAccPresenter;

    initLayout();
  }

  private void initLayout() {
    tvName.setVisibility(View.VISIBLE);
    tvCode.setVisibility(View.VISIBLE);
    tvAccParent.setVisibility(View.VISIBLE);
  }

  void bindTo(DetailAccVectorInfo accVectorInfo) {

    setAccVectorInfo(accVectorInfo);

    itemView.setTag(getAccVectorInfo().getAccountName());
    tvCode.setText(getAccVectorInfo().getCode());
    tvName.setText(getAccVectorInfo().getAccountName());
    tvAccParent.setText(getAccVectorInfo().getParentAccount());
  }

  void clear() {

    itemView.invalidate();
    tvCode.invalidate();
    tvName.invalidate();
    tvAccParent.invalidate();
  }

  @OnClick(R2.id.cl_detail_acc)
  void onClDetailAccClickListener() {
    mView.selectDetailAcc(getAccVectorInfo());
  }

  private DetailAccVectorInfo getAccVectorInfo() {
    return mAccVectorInfo;
  }

  private void setAccVectorInfo(DetailAccVectorInfo accVectorInfo) {
    this.mAccVectorInfo = accVectorInfo;
  }
}
