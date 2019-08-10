package com.sppcco.customer.ui.acc_vector.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.customer.R;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

public class ProjectAdapter  extends PagedListAdapter<AccVectorInfo, ProjectViewHolder> {


  private ProjectContract.View mView;
  private ProjectContract.Presenter mPresenter;

  ProjectAdapter(ProjectContract.Presenter presenter,
                   ProjectContract.View view) {

    super(AccVectorInfo.DIFF_CALLBACK);

    mView = view;
    mPresenter = presenter;

  }


  @NonNull
  @Override
  public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext())
      .inflate(R.layout.crd_project, viewGroup, false);

    return new ProjectViewHolder(view, this, mPresenter, mView);
  }


  @Override
  public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
    AccVectorInfo accVectorInfo = getItem(position);

    if (accVectorInfo != null) {
      holder.bindTo(accVectorInfo);
    } else {
      holder.clear();
    }
  }

}