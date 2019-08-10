package com.sppcco.customer.ui.acc_vector.account;

import android.view.View;


import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.customer.R2;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountViewHolder extends RecyclerView.ViewHolder {

  @BindView(R2.id.cl_account)
  ConstraintLayout clAccount;

  @BindView(R2.id.tv_name)
  AppCompatTextView tvName;

  @BindView(R2.id.tv_code)
  AppCompatTextView tvCode;

  @BindView(R2.id.tv_acc_parent)
  AppCompatTextView tvAccParent;

  private AccountContract.View mView;
  private AccountContract.Presenter mPresenter;
  private AccountAdapter mAdapter;

  private AccVectorInfo mAccVectorInfo;

  AccountViewHolder(View view,
                    AccountAdapter accountAdapter,
                    AccountContract.Presenter accountPresenter,
                    AccountContract.View accountView) {
    super(view);
    ButterKnife.bind(this, view);

    mAdapter = accountAdapter;
    mView = accountView;
    mPresenter = accountPresenter;

    initLayout();
  }

  private void initLayout() {
    tvName.setVisibility(View.VISIBLE);
    tvCode.setVisibility(View.VISIBLE);
    tvAccParent.setVisibility(View.VISIBLE);
  }

  void bindTo(AccVectorInfo accVectorInfo) {

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

  @OnClick(R2.id.cl_account)
  void onClAccountClickListener() {
    mView.selectAccount(getAccVectorInfo());
  }

  private AccVectorInfo getAccVectorInfo() {
    return mAccVectorInfo;
  }

  private void setAccVectorInfo(AccVectorInfo accVectorInfo) {
    this.mAccVectorInfo = accVectorInfo;
  }
}
