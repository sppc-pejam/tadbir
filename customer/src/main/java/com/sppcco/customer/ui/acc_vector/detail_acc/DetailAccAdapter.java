package com.sppcco.customer.ui.acc_vector.detail_acc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sppcco.core.data.sub_model.DetailAccVectorInfo;
import com.sppcco.customer.R;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

public class DetailAccAdapter extends PagedListAdapter<DetailAccVectorInfo, DetailAccViewHolder> {

  private DetailAccContract.View mView;
  private DetailAccContract.Presenter mPresenter;

  DetailAccAdapter(DetailAccContract.Presenter presenter,
                    DetailAccContract.View view) {

    super(DetailAccVectorInfo.DIFF_CALLBACK);

    mView = view;
    mPresenter = presenter;

  }


  @NonNull
  @Override
  public DetailAccViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext())
      .inflate(R.layout.crd_detail_acc, viewGroup, false);

    return new DetailAccViewHolder(view, this, mPresenter, mView);
  }


  @Override
  public void onBindViewHolder(@NonNull DetailAccViewHolder holder, int position) {
    DetailAccVectorInfo accVectorInfo = getItem(position);

    if (accVectorInfo != null) {
      holder.bindTo(accVectorInfo);
    } else {
      holder.clear();
    }
  }
}