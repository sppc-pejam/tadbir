package com.sppcco.customer.ui.select;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sppcco.core.data.model.Customer;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.customer.R;
import com.sppcco.customer.R2;
import com.sppcco.customer.util.AsyncCustomerFilter;
import com.sppcco.customer.util.AsyncResponse;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCustomerAdapter extends RecyclerView.Adapter<SelectCustomerAdapter.ViewHolder> implements Filterable, AsyncResponse {


  private SelectCustomerAdapter mSelectCustomerAdapter;
  private SelectCustomerContract.View mView;
  private SelectCustomerContract.Presenter mPresenter;

  private List<Customer> mCustomerList;
  private List<Customer> mFilteredCustomerList;

  private SparseArray<CustomerMoreInfo> mSparseCustomerIdAndCustomerMoreInfo;

  SelectCustomerAdapter(
    SelectCustomerContract.Presenter searchDialogPresenter,
    SelectCustomerContract.View searchDialogView) {

    mSelectCustomerAdapter = this;
    mView = searchDialogView;
    mPresenter = searchDialogPresenter;
  }

  public void loadAdapterData() {

    if (mSparseCustomerIdAndCustomerMoreInfo == null)
      mSparseCustomerIdAndCustomerMoreInfo = new SparseArray<>();

    mCustomerList = mPresenter.getCustomerList();
    mFilteredCustomerList = mPresenter.getCustomerList();
  }


  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.crd_search_customer, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

    Customer customer = mFilteredCustomerList.get(position);

    viewHolder.tvName.setText(customer.getName());
    viewHolder.tvCode.setText(String.valueOf(customer.getId()));
    viewHolder.tvSubscriptionNo.setText(String.valueOf(customer.getSubscriptionNo()));

    viewHolder.tvMobile.setText(customer.getMobileNo());
    viewHolder.tvAddress.setText(customer.getAddress());


    if (mSparseCustomerIdAndCustomerMoreInfo != null)
      if (mSparseCustomerIdAndCustomerMoreInfo.indexOfKey(customer.getId()) >= 0) {
        viewHolder.expCustomerDetails.expand();

        viewHolder.updateData();
      } else {
        viewHolder.expCustomerDetails.collapse(false);
        viewHolder.btnMore.setText(BaseApplication.getResourceString(R.string.cpt_more_information));
      }


    viewHolder.clCustomer.setOnClickListener(v -> mView.getObject(mFilteredCustomerList.get(viewHolder.getLayoutPosition())));
    viewHolder.clMore.setOnClickListener(new clMoreClickListener(viewHolder));
    viewHolder.btnReceive.setOnClickListener(new btnReceiveClickListener(viewHolder));
  }

  public class clMoreClickListener implements View.OnClickListener {

    ViewHolder viewHolder;

    clMoreClickListener(ViewHolder viewHolder) {
      this.viewHolder = viewHolder;
    }

    @Override
    public void onClick(View v) {
      viewHolder.expCustomerDetails.toggle();

      if (viewHolder.expCustomerDetails.isExpanded()) {
        CustomerMoreInfo customerMoreInfo = CustomerMoreInfo.getCustomerMoreInfoWithDefaultValue();
        customerMoreInfo.setAdapterPosition(viewHolder.getAdapterPosition());
        mSparseCustomerIdAndCustomerMoreInfo.append(mFilteredCustomerList.get(viewHolder.getAdapterPosition()).getId(),
          customerMoreInfo);
      } else {
        mSparseCustomerIdAndCustomerMoreInfo.delete(mFilteredCustomerList.get(viewHolder.getAdapterPosition()).getId());
      }

      mSelectCustomerAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
    }
  }

  public class btnReceiveClickListener implements View.OnClickListener {

    ViewHolder viewHolder;

    btnReceiveClickListener(ViewHolder viewHolder) {

      this.viewHolder = viewHolder;
    }

    @Override
    public void onClick(View v) {

      /*if (!Connectivity.isConnectedOrConnecting(UApp.getAppContext())) {
        mView.showSnackMessage(ResponseType.ERR_CLIENT_NO_CONNECTION);
        return;
      }*/

      viewHolder.tvCreditBalance.setVisibility(View.INVISIBLE);
      viewHolder.tvAccountBalance.setVisibility(View.INVISIBLE);
      viewHolder.prgCredit.setVisibility(View.VISIBLE);

      Customer customer = mFilteredCustomerList.get(viewHolder.getAdapterPosition());

      mPresenter.getCustomerCredit(customer);

    }
  }

  /*private void setAnimation(View view, int position) {
    Animation animation = AnimationUtils.loadAnimation(UApp.getAppContext(), R.anim.fadein);
    view.startAnimation(animation);
  }

  private void setScaleAnimation(View viewToAnimate, int position) {
    // If the bound view wasn't previously displayed on screen, it's animated
    if (position > mLastPosition) {
      ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
      anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
      viewToAnimate.startAnimation(anim);
      mLastPosition = position;
    }
  }*/

  int getAdapterPositionFromCustomerId(int customerId) {
    if (mSparseCustomerIdAndCustomerMoreInfo.indexOfKey(customerId) >= 0) {
      return mSparseCustomerIdAndCustomerMoreInfo.get(customerId).getAdapterPosition();
    } else {
      return -1;
    }
  }

  void setCustomerCreditValues(ViewHolder viewHolder, int customerId, String creditBalance, String accountBalance) {
    if (mSparseCustomerIdAndCustomerMoreInfo.indexOfKey(customerId) < 0)
      return;

    CustomerMoreInfo customerMoreInfo = mSparseCustomerIdAndCustomerMoreInfo.get(customerId);
    customerMoreInfo.setCreditBalance(creditBalance);
    customerMoreInfo.setAccountBalance(accountBalance);

    viewHolder.tvCreditBalance.setVisibility(View.VISIBLE);
    viewHolder.tvAccountBalance.setVisibility(View.VISIBLE);
    viewHolder.prgCredit.setVisibility(View.INVISIBLE);

    mSelectCustomerAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
  }

  @Override
  public int getItemCount() {

    return mFilteredCustomerList.size();

  }

  @Override
  public Filter getFilter() {

    return new Filter() {
      @Override
      protected FilterResults performFiltering(CharSequence charSequence) {

        String charString = charSequence.toString();
        if (charString.isEmpty()) {
          mFilteredCustomerList = mCustomerList;
        } else {
          new AsyncCustomerFilter(mSelectCustomerAdapter, mCustomerList, charString).execute();
        }
        return null;
      }

      @Override
      protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
      }
    };
  }

  @Override
  public void processFinish(List response) {
    mFilteredCustomerList = response;
    notifyDataSetChanged();
  }

/*  public ViewHolder getViewHolder() {
    return mViewHolder;
  }

  public void setViewHolder(ViewHolder viewHolder) {
    this.mViewHolder = viewHolder;
  }*/

  public class ViewHolder extends RecyclerView.ViewHolder {


    @BindView(R2.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R2.id.tv_code)
    AppCompatTextView tvCode;
    @BindView(R2.id.tv_subscription_no)
    AppCompatTextView tvSubscriptionNo;
    @BindView(R2.id.tv_mobile)
    AppCompatTextView tvMobile;
    @BindView(R2.id.tv_address)
    AppCompatTextView tvAddress;
    @BindView(R2.id.tv_credit_balance)
    AppCompatTextView tvCreditBalance;
    @BindView(R2.id.tv_account_balance)
    AppCompatTextView tvAccountBalance;
    @BindView(R2.id.prg_credit)
    ProgressBar prgCredit;
    @BindView(R2.id.btn_receive)
    Button btnReceive;
    @BindView(R2.id.cl_credit)
    ConstraintLayout clCredit;
    @BindView(R2.id.cl_customer_details)
    ConstraintLayout clCustomerDetails;
    @BindView(R2.id.exp_customer_details)
    ExpandableLayout expCustomerDetails;
    @BindView(R2.id.cl_details)
    ConstraintLayout clDetails;
    @BindView(R2.id.img_divider2)
    ImageView imgDivider2;
    @BindView(R2.id.btn_more)
    AppCompatTextView btnMore;
    @BindView(R2.id.cl_more)
    ConstraintLayout clMore;
    @BindView(R2.id.cl_customer)
    ConstraintLayout clCustomer;
    @BindView(R2.id.crd_customer)
    CardView crdCustomer;


    public ViewHolder(View view) {
      super(view);

      ButterKnife.bind(this, view);
      initLayout();
    }

    private void initLayout() {

      if (!mView.isShowCredit())
        clCredit.setVisibility(View.GONE);

      expCustomerDetails.collapse(false);

      tvCreditBalance.setVisibility(View.VISIBLE);
      tvAccountBalance.setVisibility(View.VISIBLE);
      prgCredit.setVisibility(View.INVISIBLE);
    }

    private void updateData() {
      Customer customer = mFilteredCustomerList.get(this.getAdapterPosition());
      CustomerMoreInfo customerMoreInfo = mSparseCustomerIdAndCustomerMoreInfo.get(customer.getId());
      customerMoreInfo.setAdapterPosition(this.getAdapterPosition());

      if (mView.isShowCredit()) {
        tvCreditBalance.setText(customerMoreInfo.getCreditBalance());
        tvAccountBalance.setText(customerMoreInfo.getAccountBalance());
      } else {
        clCredit.setVisibility(View.GONE);
      }

      btnMore.setText(BaseApplication.getResourceString(R.string.cpt_close));
    }

    /*@OnClick({R.id.img_photo, R.id.cl_detail})
    public void onViewClicked(View view) {
      switch (view.getId()) {
        case R.id.img_photo:
          break;
      }
    }*/

    public void setVisibility() {
      tvCreditBalance.setVisibility(View.INVISIBLE);
      tvAccountBalance.setVisibility(View.INVISIBLE);
      prgCredit.setVisibility(View.INVISIBLE);
    }

  }

  public static class CustomerMoreInfo {
    int adapterPosition;
    String creditBalance;
    String accountBalance;

    CustomerMoreInfo() {

    }

    static CustomerMoreInfo getCustomerMoreInfoWithDefaultValue() {

      CustomerMoreInfo customerMoreInfo = new CustomerMoreInfo();

      customerMoreInfo.setAdapterPosition(-1);
      customerMoreInfo.setCreditBalance("");
      customerMoreInfo.setAccountBalance("");

      return customerMoreInfo;
    }

    int getAdapterPosition() {
      return adapterPosition;
    }

    void setAdapterPosition(int adapterPosition) {
      this.adapterPosition = adapterPosition;
    }

    String getCreditBalance() {
      return creditBalance;
    }

    void setCreditBalance(String creditBalance) {
      this.creditBalance = creditBalance;
    }

    String getAccountBalance() {
      return accountBalance;
    }

    void setAccountBalance(String accountBalance) {
      this.accountBalance = accountBalance;
    }


  }

}