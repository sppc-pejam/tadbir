package com.sppcco.customer.ui.acc_vector.cost_center;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.customer.R;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

public class CostCenterAdapter extends PagedListAdapter<AccVectorInfo, CostCenterViewHolder> {

  private CostCenterContract.View mView;
  private CostCenterContract.Presenter mPresenter;

  CostCenterAdapter(CostCenterContract.Presenter presenter,
                 CostCenterContract.View view) {

    super(AccVectorInfo.DIFF_CALLBACK);

    mView = view;
    mPresenter = presenter;

  }


  @NonNull
  @Override
  public CostCenterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext())
      .inflate(R.layout.crd_cost_center, viewGroup, false);

    return new CostCenterViewHolder(view, this, mPresenter, mView);
  }


  @Override
  public void onBindViewHolder(@NonNull CostCenterViewHolder holder, int position) {
    AccVectorInfo accVectorInfo = getItem(position);

    if (accVectorInfo != null) {
      holder.bindTo(accVectorInfo);
    } else {
      holder.clear();
    }
  }
}