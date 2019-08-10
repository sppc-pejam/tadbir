package com.sppcco.customer.ui.create.load;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sppcco.core.data.sub_model.PostedCustomerInfo;
import com.sppcco.customer.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by m_pejam on 01/24/18.
 */

public class LoadCustomerAdapter extends RecyclerView.Adapter<LoadCustomerViewHolder> {

  private final LoadCustomerContract.View mView;
  private final LoadCustomerContract.Presenter mPresenter;


  LoadCustomerAdapter(LoadCustomerContract.Presenter presenter,
                      LoadCustomerContract.View view) {

    mPresenter = presenter;
    mView = view;
  }


  @NonNull
  @Override
  public LoadCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext())
      .inflate(R.layout.crd_load_customer, viewGroup, false);

    return new LoadCustomerViewHolder(view, this, mPresenter, mView);
  }


  @Override
  public void onBindViewHolder(@NonNull LoadCustomerViewHolder holder, int position) {
    PostedCustomerInfo postedCustomerInfo = mPresenter.getPostedCustomerList().get(position);

    if (postedCustomerInfo != null) {
      holder.bindTo(postedCustomerInfo, position);
    } else {
      holder.clear();
    }
  }

  @Override
  public int getItemCount() {
    if (mPresenter.getPostedCustomerList() != null)
      return mPresenter.getPostedCustomerList().size();
    else
      return 0;
  }
}