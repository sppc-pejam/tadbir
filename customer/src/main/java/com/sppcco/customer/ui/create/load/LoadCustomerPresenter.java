package com.sppcco.customer.ui.create.load;

import android.net.Uri;
import android.util.Log;

import com.sppcco.core.data.local.db.repository.OptionRepository;
import com.sppcco.core.data.model.AccVsCC;
import com.sppcco.core.data.model.AccVsDetail;
import com.sppcco.core.data.model.AccVsPrj;
import com.sppcco.core.data.model.Account;
import com.sppcco.core.data.model.CostCenter;
import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.model.DetailAcc;
import com.sppcco.core.data.model.Project;
import com.sppcco.core.data.remote.repository.CustomerRemoteRepository;
import com.sppcco.core.data.remote.service.Webservice;
import com.sppcco.core.data.sub_model.PostedCustomerInfo;
import com.sppcco.core.enums.FormId;
import com.sppcco.core.enums.MessageCode;
import com.sppcco.core.enums.OptionId;
import com.sppcco.core.enums.ResponseType;
import com.sppcco.core.enums.SubsystemsId;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.core.listener.DoneResponseListener;
import com.sppcco.core.listener.GenericResponseListener;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.core.util.message.Message;
import com.sppcco.helperlibrary.converter.DC;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by m_pejam on 02/09/18.
 * LoadCustomerPresenter
 */

public class LoadCustomerPresenter extends BasePresenter implements LoadCustomerContract.Presenter {

  private final LoadCustomerContract.View mView;
  //----------------------------------
  private String mAccessRight;
  //----------------------------------
  private List<PostedCustomerInfo> mPostedCustomerInfoList = null;
  //----------------------------------
  private List<PostedCustomerInfo> mPostedCustomerThatNeedSyncList = null;

  private final CompositeDisposable disposables = new CompositeDisposable();
  //----------------------------------


  private LoadCustomerPresenter(@NonNull LoadCustomerContract.View view) {
    mView = view;
    mView.setPresenter(this);
  }

  static LoadCustomerContract.Presenter getPresenterInstance(@NonNull LoadCustomerContract.View view) {

    return new LoadCustomerPresenter(view);
  }

  @Override
  public void destroy() {
    disposables.clear();
  }

  @Override
  public void start() {

    loadPostedCustomersInfo(() -> initData(mView::updateView));
  }

  @Override
  public void loadPostedCustomersInfo(DoneResponseListener doneResponseListener) {
    mView.showProgress();

    getCoreNet().customerRemoteControlRepository().getCountOfPostedCustomerInfo(
      new CustomerRemoteRepository.LoadStringCallback() {
        @Override
        public void onResponse(String response) {

          String[] result = DC.jsnToStrArr(response, "CountOfRows");
          assert result != null;
          if (Integer.parseInt(result[0]) > 0) {
            loadPostedCustomerList(doneResponseListener);

          } else {

            setPostedCustomerInfoList(null);
            doneResponseListener.onDone();
          }
        }

        @Override
        public void onFailure(ResponseType responseType) {

        }
      });

  }

  private void loadPostedCustomerList(DoneResponseListener doneResponseListener) {

    disposables.add(getCoreNet().customerRemoteControlRepository().loadPostedCustomersInfo(
      new CustomerRemoteRepository.LoadPostedCustomerTableCallback<PostedCustomerInfo>() {
        @Override
        public void onResponse(List<PostedCustomerInfo> list) {
          setPostedCustomerInfoList(list);
          mView.loadRecyclerViewItem();
          doneResponseListener.onDone();
        }

        @Override
        public void onFailure(ResponseType responseType) {
          setPostedCustomerInfoList(null);
          doneResponseListener.onDone();
        }
      }));
  }

  public void initData(DoneResponseListener doneResponseListener) {

    Completable.fromAction(() ->
      setAccessRight(
        getCoreDB().rightsDao().getAccessRight(SubsystemsId.SALESPURCHASE_SYS.getValue(), FormId.SP_CUSTOMER.getValue()))
    )
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnComplete(doneResponseListener::onDone)
      .subscribe();

  }

  @Override
  public void getCountOfRowsThatNeedSync(GenericResponseListener<Integer> doneResponseListener) {
    mView.showProgress();

    getCoreNet().customerRemoteControlRepository().getCountOfRowsThatNeedSync(
      new CustomerRemoteRepository.LoadStringCallback() {
        @Override
        public void onResponse(String response) {

          String[] result = DC.jsnToStrArr(response, "NeedSyncRowCount");
          assert result != null;
          doneResponseListener.onResponse(Integer.parseInt(result[0]));
        }

        @Override
        public void onFailure(ResponseType responseType) {

        }
      });
  }


  @Override
  public void refreshPostedCustomersInfoList(DoneResponseListener doneResponseListener) {

    disposables.add(getCoreNet().customerRemoteControlRepository().loadRowsThatNeedSync(
      new CustomerRemoteRepository.LoadPostedCustomerTableCallback<PostedCustomerInfo>() {
        @Override
        public void onResponse(List<PostedCustomerInfo> list) {
          for (int i = 0; i < list.size(); i++)
            for (int j = 0; j < getPostedCustomerList().size(); j++)
              if (list.get(i).getId() == getPostedCustomerList().get(j).getId()) {
                getPostedCustomerList().set(j, list.get(i));
                break;
              }


          mView.loadRecyclerViewItem();
          doneResponseListener.onDone();
        }

        @Override
        public void onFailure(ResponseType responseType) {
          doneResponseListener.onDone();
        }
      }));
  }


  @Override
  public void deleteRemotePostedCustomer(int id, String name, int nPosition, DoneResponseListener doneResponseListener) {
    //mView.showProgress();
    getCoreNet().customerRemoteControlRepository().deletePostedCustomerInfo(id, name,
      new CustomerRemoteRepository.LoadStringCallback() {
        @Override
        public void onResponse(String response) {

          String[] result = DC.jsnToStrArr(response, "DeletedRowCount");
          assert result != null;
          if (Integer.parseInt(result[0]) == 1) {
            //mView.hideProgress();
            doneResponseListener.onDone();
          } else {

            mView.updateAdapter(false, true, nPosition);
          }

        }

        @Override
        public void onFailure(ResponseType responseType) {
          //mView.hideProgress();
        }
      });
  }


  @Override
  public void ControlRemoteStatusCustomer(PostedCustomerInfo postedCustomerInfo, int nPosition) {
    mView.showProgress();

    getCoreNet().customerRemoteControlRepository().controlStatusCustomer(
      postedCustomerInfo.getId(), postedCustomerInfo.getName(),
      new CustomerRemoteRepository.LoadStringCallback() {
        @Override
        public void onResponse(String response) {

          String[] result = DC.jsnToStrArr(response, "StateRow");
          assert result != null;
          boolean isActivatedRow = Integer.parseInt(result[0]) == 1;
          boolean isDeletedRow = Integer.parseInt(result[0]) == 2;

          if (isActivatedRow) {
            //mView.hideProgress();
            getPostedCustomerList().get(nPosition).setStatus(1);
            getPostedCustomerList().get(nPosition).setNeedSync(1);
            mView.updateAdapter(true, false, nPosition);
          } else if (isDeletedRow) {
            //mView.hideProgress();
            getPostedCustomerList().get(nPosition).setStatus(2);
            getPostedCustomerList().get(nPosition).setNeedSync(1);
            mView.updateAdapter(true, false, nPosition);
          } else {
            AgainApproveRequestForPostedCustomer(postedCustomerInfo, nPosition);
          }

        }

        @Override
        public void onFailure(ResponseType responseType) {
          mView.hideProgress();
        }
      });
  }

  private void AgainApproveRequestForPostedCustomer(PostedCustomerInfo postedCustomerInfo, int nPosition) {

    getCoreNet().customerRemoteControlRepository().againApproveRequestForPostedCustomer(
      postedCustomerInfo.getId(), postedCustomerInfo.getName(), postedCustomerInfo.getNumberOfRequest(),
      new CustomerRemoteRepository.LoadStringCallback() {
        @Override
        public void onResponse(String response) {

          String[] result = DC.jsnToStrArr(response, "UpdatedRowCount");
          assert result != null;
          boolean isUpdatedRow = Integer.parseInt(result[0]) == 1;

          if (isUpdatedRow) {
            getPeriodicTimeForShowApproveReq(integer -> mView.showSendMessage(Message.getMessageForCode(MessageCode.S_SENT), integer));
          } else {
            mView.showSendMessage(Message.getMessageForCode(MessageCode.E_NOT_SENT), 0);
          }

        }

        @Override
        public void onFailure(ResponseType responseType) {
          mView.hideProgress();
        }
      });
  }


  //TODO
  @Override
  public void syncRowsOfTablesRelatedToPostedCustomers(DoneResponseListener doneResponseListener) {
    String url;
    url = new Uri.Builder()
      .scheme("http")
      .encodedAuthority(BaseApplication.getIPServer() + ":" + BaseApplication.getBusinessServicePort())
      .appendPath(Webservice.BUSINESS_ENTITY)
      .appendPath(Webservice.BUSINESS_CUSTOMER_INFO_CREATED_ID_FUNC)
      .appendQueryParameter("IP", BaseApplication.getIPServer())
      .appendQueryParameter("Port", BaseApplication.getDataBaseServicePort())
      .appendQueryParameter("Database", BaseApplication.getDatabaseName())
      .appendQueryParameter("Version", CoreConstants.getServiceVersion())
      .appendQueryParameter("UserId", String.valueOf(BaseApplication.getUserId()))
      .appendQueryParameter("FPId", String.valueOf(BaseApplication.getFPId()))
      .appendQueryParameter("ServiceKey", BaseApplication.getApiKey())
      .appendQueryParameter("key", BaseApplication.getApiKey())
      .toString();

    disposables.add(getCoreNet().customerRemoteControlRepository().getCustomerInfoCreated(url,
      new CustomerRemoteRepository.LoadStringCallback() {
        @Override
        public void onResponse(String response) {
          try {
            JSONObject jsonObject = new JSONObject(response);
            List<CompletableSource> list = new ArrayList<>();

            List<Customer> customerList = DC.jsonArrayToListModel(jsonObject, Customer.class);
            list.add(getCoreDB().customerDao().insertRXCustomers(customerList));

            List<Account> accountList = DC.jsonArrayToListModel(jsonObject, Account.class);
            if (accountList.size() != 0)
              list.add(getCoreDB().accountDao().insertRXAccounts(accountList));

            List<DetailAcc> detailAccList = DC.jsonArrayToListModel(jsonObject, DetailAcc.class);
            if (detailAccList.size() != 0)
              list.add(getCoreDB().detailAccDao().insertRXDetailAccs(detailAccList));


            List<CostCenter> costCenterList = DC.jsonArrayToListModel(jsonObject, CostCenter.class);
            if (costCenterList.size() != 0)
              list.add(getCoreDB().costCenterDao().insertRXCostCenters(costCenterList));


            List<Project> projectList = DC.jsonArrayToListModel(jsonObject, Project.class);
            if (projectList.size() != 0)
              list.add(getCoreDB().projectDao().insertRXProjects(projectList));


            List<AccVsDetail> AccVsDetailList = DC.jsonArrayToListModel(jsonObject, AccVsDetail.class);
            if (AccVsDetailList.size() != 0)
              list.add(getCoreDB().accVsDetailDao().insertRXAccVsDetails(AccVsDetailList));


            List<AccVsCC> AccVsCCList = DC.jsonArrayToListModel(jsonObject, AccVsCC.class);
            if (AccVsCCList.size() != 0)
              list.add(getCoreDB().accVsCCDao().insertRXAccVsCCs(AccVsCCList));


            List<AccVsPrj> accVsPrjList = DC.jsonArrayToListModel(jsonObject, AccVsPrj.class);
            if (accVsPrjList.size() != 0)
              list.add(getCoreDB().accVsPrjDao().insertRXAccVsPrjs(accVsPrjList));

            CompletableSource[] sources = new CompletableSource[list.size()];
            for (int i = 0; i < list.size(); i++) {
              sources[i] = list.get(i);
            }
            saveRowsOfTablesRelatedToPostedCustomers(sources, doneResponseListener);
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }

        @Override
        public void onFailure(ResponseType responseType) {

        }
      }));

  }

  private void saveRowsOfTablesRelatedToPostedCustomers(CompletableSource[] sources, DoneResponseListener doneResponseListener) {
    Completable.concatArray(sources)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.single())
      .doOnComplete(() -> {
        updateRowsThatNeedSyncInPostedCustomerInfo();
        refreshRowsThatNeedSyncInPostedCustomerInfo(doneResponseListener);
        Log.i("cust_tag", "saveData - onComplete - saveRowsOfTablesRelatedToPostedCustomers");
      })
      .doOnError(throwable -> Log.i("cust_tag", "saveData - onError"))
      .subscribe();

  }

  private void updateRowsThatNeedSyncInPostedCustomerInfo() {
    getCoreNet().customerRemoteControlRepository().updateRowsThatNeedSync(1 /*status of Activated*/,
      new CustomerRemoteRepository.LoadStringCallback() {
        @Override
        public void onResponse(String response) {
          // response is count of rows updated by AS "UpdatedRowCount"
        }

        @Override
        public void onFailure(ResponseType responseType) {

        }
      });
  }

  private void refreshRowsThatNeedSyncInPostedCustomerInfo(DoneResponseListener doneResponseListener) {
    //final int INACTIVE = 0;
    final int ACTIVE = 1;
    //final int DELETED = 2;

    for (int j = 0; j < getPostedCustomerList().size(); j++) {
      if (getPostedCustomerList().get(j).getStatus() == ACTIVE && getPostedCustomerList().get(j).getNeedSync() == 1) {
        getPostedCustomerList().get(j).setNeedSync(0);
      }
    }

    mView.loadRecyclerViewItem();
    doneResponseListener.onDone();
  }

  private void getPeriodicTimeForShowApproveReq(GenericResponseListener<Integer> integerResponseListener) {
    getCoreDB().optionRepository().getOptionValueById(
      OptionId.OPT_APP_APPROVE_REQ_PERIODIC_TIME.getValue(), new OptionRepository.GetOptionValueCallback() {
        @Override
        public void onOptionValueLoaded(String optionValue) {
          integerResponseListener.onResponse(Integer.parseInt(optionValue));
        }

        @Override
        public void onDataNotAvailable() {
        }
      });
  }


  //region Getter Setter


  @Override
  public boolean getRight(int position) {
    return getAccessRight().charAt(position) == '1';
  }

  @Override
  public List<PostedCustomerInfo> getPostedCustomerList() {
    if (mPostedCustomerInfoList == null)
      mPostedCustomerInfoList = new ArrayList<>();
    return mPostedCustomerInfoList;
  }

  private void setPostedCustomerInfoList(List<PostedCustomerInfo> postedCustomerInfoList) {
    this.mPostedCustomerInfoList = postedCustomerInfoList;
  }

  @Override
  public String getAccessRight() {
    return mAccessRight;
  }

  private void setAccessRight(String mAccessRight) {
    this.mAccessRight = mAccessRight;
  }

  //endregion Getter Setter

}