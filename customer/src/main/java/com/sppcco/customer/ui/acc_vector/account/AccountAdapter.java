package com.sppcco.customer.ui.acc_vector.account;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.customer.R;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

public class AccountAdapter extends PagedListAdapter<AccVectorInfo, AccountViewHolder> {

  private AccountContract.View mView;
  private AccountContract.Presenter mPresenter;

  AccountAdapter(AccountContract.Presenter presenter,
                 AccountContract.View view) {

    super(AccVectorInfo.DIFF_CALLBACK);

    mView = view;
    mPresenter = presenter;

  }


  @NonNull
  @Override
  public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext())
      .inflate(R.layout.crd_account, viewGroup, false);

    return new AccountViewHolder(view, this, mPresenter, mView);
  }


  @Override
  public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
    AccVectorInfo accVectorInfo = getItem(position);

    if (accVectorInfo != null) {
      holder.bindTo(accVectorInfo);
    } else {
      holder.clear();
    }
  }

  @Override
  public int getItemCount() {
    return super.getItemCount();
  }
}