package com.sppcco.customer.ui.acc_vector.project;

import android.view.View;


import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.customer.R2;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectViewHolder extends RecyclerView.ViewHolder {

  @BindView(R2.id.cl_project)
  ConstraintLayout clProject;

  @BindView(R2.id.tv_name)
  AppCompatTextView tvName;

  @BindView(R2.id.tv_code)
  AppCompatTextView tvCode;

  //@BindView(R.id.tv_acc_parent)
  //AppCompatTextView tvAccParent;

  private ProjectContract.View mView;
  private ProjectContract.Presenter mPresenter;
  private ProjectAdapter mAdapter;

  private AccVectorInfo mAccVectorInfo;

  ProjectViewHolder(View view,
                    ProjectAdapter projectAdapter,
                    ProjectContract.Presenter projectPresenter,
                    ProjectContract.View projectView) {
    super(view);
    ButterKnife.bind(this, view);

    mAdapter = projectAdapter;
    mView = projectView;
    mPresenter = projectPresenter;

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

  @OnClick(R2.id.cl_project)
  void onClProjectClickListener() {
    mView.selectProject(getAccVectorInfo());
  }

  private AccVectorInfo getAccVectorInfo() {
    return mAccVectorInfo;
  }

  private void setAccVectorInfo(AccVectorInfo accVectorInfo) {
    this.mAccVectorInfo = accVectorInfo;
  }
}
