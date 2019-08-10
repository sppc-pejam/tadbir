package com.sppcco.customer.ui.create.load;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sppcco.core.data.sub_model.PostedCustomerInfo;
import com.sppcco.core.enums.CustomerRightPos;
import com.sppcco.core.enums.IntentKey;
import com.sppcco.core.enums.MessageCode;
import com.sppcco.core.enums.Mode;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.fragment.BaseFragment;
import com.sppcco.core.util.message.Message;
import com.sppcco.customer.R;
import com.sppcco.customer.R2;
import com.sppcco.customer.ui.create.add.AddCustomerActivity;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by m_pejam on 01/21/18.
 * AddCustomerFragment
 */

public class LoadCustomerFragment extends BaseFragment implements LoadCustomerContract.View {

  private final String TAG = LoadCustomerFragment.class.getSimpleName();

  //region Views

  @BindView(R2.id.rcl_articles)
  RecyclerView rclArticles;
  @BindView(R2.id.cl_no_item)
  ConstraintLayout clNoItem;

  //endregion Views


  private LoadCustomerContract.Presenter mPresenter;
  private View mParentView;
  private LoadCustomerAdapter mAdapter;

  private ProgressDialog mProgressDialog;

  @NonNull
  static LoadCustomerFragment newInstance() {
    return new LoadCustomerFragment();
  }

  @Override
  public void setPresenter(LoadCustomerContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void onAttach(@NonNull Context context) {

    super.onAttach(context);
    Log.i(TAG, "onAttach");
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    if (savedInstanceState != null)
      setExtras(savedInstanceState);
    else {
      Bundle extras = getArguments();
      if (extras != null)
        setExtras(extras);
    }

  }

  private void setExtras(Bundle bundle) {

  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    mParentView = inflater.inflate(R.layout.fragment_load_customer, container, false);
    ButterKnife.bind(this, mParentView);

    initData();
    initLayout();

    return mParentView;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mPresenter.start();
  }


  @Override
  public void initLayout() {
    // set option menu
    setHasOptionsMenu(true);
    initToolbar((AppCompatActivity) getActivity(), mParentView,R.id.toolbar ,true);
    Objects.requireNonNull(getActivity()).setTitle((R.string.cpt_status_of_posted_customer));

    initRecyclerView();

    clNoItem.setVisibility(View.GONE);

  }

  private void initRecyclerView() {

    rclArticles.setHasFixedSize(true);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    rclArticles.setLayoutManager(layoutManager);

    mAdapter = getAdapterInstance();
    rclArticles.setAdapter(mAdapter);
  }

  @Override
  public boolean updateModel() {

    return false;
  }

  @Override
  public void initData() {

  }

  @Override
  public boolean validData(boolean showMsg) {

    return true;
  }

  @Override
  public boolean updateView() {

    //loading list view item with this function
    hideProgress();

    if (mAdapter.getItemCount() == 0)
      clNoItem.setVisibility(View.VISIBLE);
    else
      clNoItem.setVisibility(View.GONE);

    return false;
  }

  @Override
  public void loadRecyclerViewItem() {
    mAdapter.notifyDataSetChanged();
  }

  private LoadCustomerAdapter getAdapterInstance() {
    if (mAdapter == null) {
      mAdapter = new LoadCustomerAdapter(mPresenter, this);
    }
    //mAdapter.loadAdapterData();
    return mAdapter;
  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();

    Log.i(TAG, "onDestroyView");
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    Log.i(TAG, "onDestroy");
  }

  @Override
  public void onDetach() {
    super.onDetach();

    Log.i(TAG, "onDetach");
  }

  @OnClick({R2.id.fab_add, R2.id.cl_no_item})
  public void onViewClicked(View view) {
    int i = view.getId();
    if (i == R.id.cl_no_item || i == R.id.fab_add) {
      callAddCustomerActivity();
    }
  }


  private void callAddCustomerActivity() {

    if (mPresenter.getAccessRight() == null || mPresenter.getAccessRight().length() == 0) {
      snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_USER_NOT_ACCESS), null);
      return;
    } else {
      if (!mPresenter.getRight(CustomerRightPos.APPEND.getValue())) {
        snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_USER_NOT_ALLOW_APPEND), null);
        return;
      }

      if (mPresenter.getRight(CustomerRightPos.NOT_BUYER.getValue())) {
        snackBarActionCard(mParentView, Message.getMessageForCode(MessageCode.E_USER_NOT_ALLOW_NOT_BUYER), null);
        return;
      }
    }


    Intent intent = new Intent(getActivity(), AddCustomerActivity.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable(IntentKey.KEY_MODE.getKey(), Mode.NEW);
    bundle.putSerializable(IntentKey.KEY_CUSTOMER.getKey(), null);
    intent.putExtras(bundle);
    startActivityForResult(intent, 1);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {

    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 1) {
      if (resultCode == Activity.RESULT_OK) {
        PostedCustomerInfo postedCustomerInfo = (PostedCustomerInfo) data.getSerializableExtra("RESULT_INTENT");
        mPresenter.getPostedCustomerList().add(0, postedCustomerInfo);
        mAdapter.notifyDataSetChanged();
        updateView();
      }
    }
  }


  //region OptionsMenu

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    menu.clear();
    inflater.inflate(R.menu.menu_load_customer, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    // Handle item selection
    int i = item.getItemId();
    if (i == android.R.id.home) {
      Objects.requireNonNull(getActivity()).finish();
      return true;
    } else if (i == R.id.refresh) {
      onRefreshSelected();
      return true;
    } else if (i == R.id.sync) {
      onSyncSelected();
      return true;
    }
    return true;
  }

  //endregion OptionsMenu

  //region ProgressDialog

  @Override
  public void showProgress() {
    if (getProgress().isShowing()) return;
    getProgress().show();
  }

  private ProgressDialog getProgress() {
    if (mProgressDialog == null) {
      mProgressDialog = new ProgressDialog(getActivity());
      mProgressDialog.setMessage(getResources().getString(R.string.msg_waiting));
      mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      mProgressDialog.setIndeterminate(false);
      mProgressDialog.setCanceledOnTouchOutside(false);
    }
    return mProgressDialog;
  }

  @Override
  public void hideProgress() {
    if (getProgress().isShowing()) getProgress().dismiss();
  }

  //endregion ProgressDialog

  private void onRefreshSelected() {
    mPresenter.getCountOfRowsThatNeedSync(integer -> {
      if (integer > 0)
        mPresenter.refreshPostedCustomersInfoList(this::updateView);
      else {
        hideProgress();
        showSendMessage(Message.getMessageForCode(MessageCode.W_NO_ITEM_NEED_SYNC), 0);
      }
    });
  }

  private void onSyncSelected() {
    mPresenter.getCountOfRowsThatNeedSync(integer -> {
      if (integer > 0)
        mPresenter.syncRowsOfTablesRelatedToPostedCustomers(this::updateView);
      else {
        hideProgress();
        showSendMessage(Message.getMessageForCode(MessageCode.W_NO_ITEM_NEED_SYNC), 0);
      }
    });
  }

  @Override
  public void onAgainApproveRequest(PostedCustomerInfo postedCustomerInfo, int nAdapterPosition) {
    mPresenter.ControlRemoteStatusCustomer(postedCustomerInfo, nAdapterPosition);
  }

  @Override
  public void onDeleteApproveRequest(int nId, String strName, int nAdapterPosition) {
    mPresenter.deleteRemotePostedCustomer(nId, strName, nAdapterPosition,
      () -> {
        updateAdapter(false, true, nAdapterPosition);
        updateView();
      }
    );
  }

  @Override
  public void updateAdapter(boolean isUpdate, boolean isDelete, int nPosition) {

    if (isUpdate) {
      mAdapter.notifyItemChanged(nPosition);
    } else if (isDelete) {
      mPresenter.getPostedCustomerList().remove(nPosition);
      mAdapter.notifyDataSetChanged();
    }

    updateView();
  }

  @Override
  public void showSendMessage(Message message, int nPeriodicTime) {

    if (message.getMessageCode() == MessageCode.S_SENT)
      message.setContent(String.format(BaseApplication.getResourceString(R.string.msg_send_in_periodic_time), ++nPeriodicTime));

    snackBarActionCard(mParentView, message, null);
    updateView();
  }

  //region Getter Setter


  //endregion Getter Setter
}
