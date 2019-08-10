
package com.sppcco.customer.ui.create.load;

import android.view.View;


import com.sppcco.core.data.sub_model.PostedCustomerInfo;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.customer.R;
import com.sppcco.customer.R2;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by m_pejam on 09/09/18.
 * PrefactorViewHolder
 */

class LoadCustomerViewHolder extends RecyclerView.ViewHolder {

  private final int INACTIVE = 0;
  private final int ACTIVE = 1;
  private final int DELETED = 2;
  //---------------------------------------
  private LoadCustomerContract.View mView;
  private LoadCustomerContract.Presenter mPresenter;
  private LoadCustomerAdapter mAdapter;
  //---------------------------------------
  private PostedCustomerInfo mPostedCustomerInfo;

  //region Views

  @BindView(R2.id.view_r_column)
  View viewRColumn;
  @BindView(R2.id.tv_code)
  AppCompatTextView tvCode;
  @BindView(R2.id.tv_name)
  AppCompatTextView tvName;
  @BindView(R2.id.tv_subscription_no)
  AppCompatTextView tvSubscriptionNo;
  @BindView(R2.id.tv_status)
  AppCompatTextView tvStatus;
  @BindView(R2.id.btn_action)
  AppCompatTextView btnAction;


  //endregion

  LoadCustomerViewHolder(View parentView,
                         LoadCustomerAdapter adapter,
                         LoadCustomerContract.Presenter presenter,
                         LoadCustomerContract.View view) {
    super(parentView);
    ButterKnife.bind(this, parentView);

    mAdapter = adapter;
    mPresenter = presenter;
    mView = view;

    initLayout();
  }

  void bindTo(PostedCustomerInfo postedCustomerInfo, int position) {

    setPostedCustomer(postedCustomerInfo);

    tvCode.setText(String.valueOf(getPostedCustomer().getId()));
    tvName.setText(getPostedCustomer().getName());
    tvSubscriptionNo.setText(getPostedCustomer().getSubscriptionNo());

    if (getPostedCustomer().getStatus() == INACTIVE) {
      viewRColumn.setBackground(BaseApplication.getResourceDrawable(R.drawable.crd_r_column_gray));
      tvStatus.setTextColor(BaseApplication.getCoreResources().getColor(R.color.grey_500));
      tvStatus.setText(BaseApplication.getResourceString(R.string.cpt_in_approve_list));
      btnAction.setVisibility(View.VISIBLE);
      btnAction.setText(BaseApplication.getResourceString(R.string.cpt_again_request));

    } else if (getPostedCustomer().getStatus() == ACTIVE) {
      viewRColumn.setBackground(BaseApplication.getResourceDrawable(R.drawable.crd_r_column_green));
      tvStatus.setTextColor(BaseApplication.getCoreResources().getColor(R.color.green_500));
      if (getPostedCustomer().getNeedSync() == 1)
        tvStatus.setText(BaseApplication.getResourceString(R.string.cpt_approved_need_sync));
      else if (getPostedCustomer().getNeedSync() == 0)
        tvStatus.setText(BaseApplication.getResourceString(R.string.cpt_approved));
      btnAction.setVisibility(View.GONE);

    } else if (getPostedCustomer().getStatus() == DELETED) {
      viewRColumn.setBackground(BaseApplication.getResourceDrawable(R.drawable.crd_r_column_red));
      tvStatus.setTextColor(BaseApplication.getCoreResources().getColor(R.color.red_500));
      tvStatus.setText(BaseApplication.getResourceString(R.string.cpt_remove_status));
      btnAction.setVisibility(View.VISIBLE);
      btnAction.setText(BaseApplication.getResourceString(R.string.cpt_remove_from_list));

    }

  }

  void clear() {

    itemView.invalidate();
    viewRColumn.invalidate();
    tvCode.invalidate();
    tvName.invalidate();
    tvSubscriptionNo.invalidate();
    tvStatus.invalidate();
    btnAction.invalidate();
  }


  private void initLayout() {
  }

  @OnClick(R2.id.btn_action)
  public void onViewClicked() {

    if (getPostedCustomer().getStatus() == INACTIVE) {
      mView.onAgainApproveRequest(getPostedCustomer(), getAdapterPosition());
    } else if (getPostedCustomer().getStatus() == DELETED) {
      mView.onDeleteApproveRequest(getPostedCustomer().getId(), getPostedCustomer().getName(), getAdapterPosition());
    }
  }

  //region Getter Setter

  private PostedCustomerInfo getPostedCustomer() {
    return mPostedCustomerInfo;
  }

  private void setPostedCustomer(PostedCustomerInfo postedCustomerInfo) {
    this.mPostedCustomerInfo = postedCustomerInfo;
  }

  //endregion

}

