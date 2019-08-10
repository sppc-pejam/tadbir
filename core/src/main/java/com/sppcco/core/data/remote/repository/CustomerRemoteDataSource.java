package com.sppcco.core.data.remote.repository;

import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.sub_model.PostedCustomerInfo;
import com.sppcco.core.enums.EventLogDesc;
import com.sppcco.core.enums.FormId;
import com.sppcco.core.enums.ResponseType;
import com.sppcco.core.enums.SubsystemsId;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.helperlibrary.converter.DC;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by b_nematzadeh on 8/31/2018.
 */

public class CustomerRemoteDataSource implements CustomerRemoteRepository {


  @Override
  public Disposable getCustomerCredit(Customer customer, String startDate,
                                      @NotNull LoadStringArrayCallback callback) {

    CompositeDisposable disposable = new CompositeDisposable();

    final int CREDIT_BALANCE_INDEX = 0;
    final int ACCOUNT_BALANCE_INDEX = 1;

    Observable<ResponseBody> observable = BaseApplication.getApiService().getCustomerCredit(
      BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(), customer.getAccId(),
      customer.getFAccId(), customer.getCCId(), customer.getPrjId(), customer.getId(),
      startDate, BaseApplication.getFPId(), BaseApplication.getApiKey());

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadValidationAccessCallback<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {
          disposable.add(d);
        }

        @Override
        public void onNext(ResponseBody responseBody) {
          try {
            String[] credit = new String[2];
            String res = responseBody.string();
            if (res != null && !res.matches("null") && !res.matches("")) {
              String[] result = DC.jsnToStrArr(res, "CreditBalance", "AccountBalance");

              double creditBalance = Double.parseDouble(result[CREDIT_BALANCE_INDEX]);
              double accountBalance = Double.parseDouble(result[ACCOUNT_BALANCE_INDEX]);
              DecimalFormat formatter = new DecimalFormat("#,###");

              credit[CREDIT_BALANCE_INDEX] = formatter.format(creditBalance);
              credit[ACCOUNT_BALANCE_INDEX] = formatter.format(accountBalance);

              callback.onResponse(credit);
            } else {
              RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
              callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
            }
          } catch (NullPointerException e) {
            e.printStackTrace();
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          } catch (IOException e) {
            e.printStackTrace();
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }

        @Override
        public void onComplete() {

        }
      });

    return disposable;
  }

  @Override
  public Disposable getAccVectorBalance(Customer customer, String startDate,
                                        @NotNull LoadStringArrayCallback callback) {

    CompositeDisposable disposable = new CompositeDisposable();

    final int CREDIT_BALANCE_INDEX = 0;
    final int ACCOUNT_BALANCE_INDEX = 1;

    Observable<ResponseBody> observable = BaseApplication.getApiService().getAccVectorBalance(
      BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(), customer.getAccId(),
      customer.getFAccId(), customer.getCCId(), customer.getPrjId(), customer.getId(),
      startDate, BaseApplication.getFPId(), BaseApplication.getApiKey());

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadValidationAccessCallback<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {
          disposable.add(d);
        }

        @Override
        public void onNext(ResponseBody responseBody) {
          try {
            String[] credit = new String[2];
            String res = responseBody.string();
            if (res != null && !res.matches("null") && !res.matches("")) {
              String[] result = DC.jsnToStrArr(res, "CreditBalance", "AccountBalance");

              double creditBalance = 0;
              double accountBalance = 0;
              if (result != null) {
                creditBalance = Double.parseDouble(result[CREDIT_BALANCE_INDEX]);
                accountBalance = Double.parseDouble(result[ACCOUNT_BALANCE_INDEX]);
              }
              DecimalFormat formatter = new DecimalFormat("#,###");

              credit[CREDIT_BALANCE_INDEX] = formatter.format(creditBalance);
              credit[ACCOUNT_BALANCE_INDEX] = formatter.format(accountBalance);

              callback.onResponse(credit);
            } else {
              RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
              callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
            }
          } catch (NullPointerException e) {
            e.printStackTrace();
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          } catch (IOException e) {
            e.printStackTrace();
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }

        @Override
        public void onComplete() {

        }
      });

    return disposable;
  }

  @Override
  public void getValidationCustomerResult(Customer customer, @NonNull LoadStringCallback callback) {

    Observable<ResponseBody> observable = BaseApplication.getApiService().getValidationCustomerResult(
      BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(), customer, BaseApplication.getApiKey());

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadConsumerValidationAccessCallback<ResponseBody>() {
        @Override
        public void onNext(ResponseBody responseBody) {
          try {
            if (responseBody != null) {
              callback.onResponse(responseBody.string());
            } else {
              RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
              callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
            }
          } catch (IOException e) {
            e.printStackTrace();
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }
      });

  }

  @Override
  public void insertCustomer(Customer customer, @NonNull LoadVoidCallback callback) {

    Observable<ResponseBody> observable = BaseApplication.getApiService().insertCustomer(
      BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(), customer,
      BaseApplication.getDatabaseName(), BaseApplication.getWSId(), SubsystemsId.SALESPURCHASE_SYS.getValue(), FormId.SP_CUSTOMER.getValue(),
      EventLogDesc.INSERT_SP_CUSTOMER.getValue(), BaseApplication.getApiKey());

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadConsumerValidationAccessCallback<ResponseBody>() {
        @Override
        public void onNext(ResponseBody responseBody) {
          if (responseBody != null)
            callback.onResponse();
          else {
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }
      });
  }

  @Override
  public void getLastPostedCustomerInfo(String name, @NonNull LoadStringCallback callback) {
    Observable<ResponseBody> observable = BaseApplication.getApiService().getLastPostedCustomerInfo(BaseApplication.getDatabaseName(),
      CoreConstants.getServiceVersion(), name, BaseApplication.getUserId(), BaseApplication.getFPId(), BaseApplication.getApiKey());

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadConsumerValidationAccessCallback<ResponseBody>() {
        @Override
        public void onNext(ResponseBody responseBody) {
          if (responseBody != null) {
            try {
              callback.onResponse(responseBody.string());
            } catch (IOException e) {
              e.printStackTrace();
              RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
              callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
            }
          } else {
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }
      });
  }

  @Override
  public void deletePostedCustomerInfo(int id, String name, @NonNull LoadStringCallback callback) {
    Observable<ResponseBody> observable = BaseApplication.getApiService().deletePostedCustomerInfo(BaseApplication.getDatabaseName(),
      CoreConstants.getServiceVersion(), id, name, BaseApplication.getUserId(), BaseApplication.getFPId(), BaseApplication.getApiKey());

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadConsumerValidationAccessCallback<ResponseBody>() {
        @Override
        public void onNext(ResponseBody responseBody) {
          if (responseBody != null) {
            try {
              callback.onResponse(responseBody.string());
            } catch (IOException e) {
              e.printStackTrace();
              RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
              callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
            }
          } else {
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }
      });
  }

  @Override
  public void controlStatusCustomer(int id, String name, @NonNull LoadStringCallback callback) {
    Observable<ResponseBody> observable = BaseApplication.getApiService().controlStatusCustomer(BaseApplication.getDatabaseName(),
      CoreConstants.getServiceVersion(), id, name, BaseApplication.getFPId(), BaseApplication.getApiKey());

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadConsumerValidationAccessCallback<ResponseBody>() {
        @Override
        public void onNext(ResponseBody responseBody) {
          if (responseBody != null) {
            try {
              callback.onResponse(responseBody.string());
            } catch (IOException e) {
              e.printStackTrace();
              RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
              callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
            }
          } else {
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }
      });
  }

  @Override
  public void againApproveRequestForPostedCustomer(int id, String name, int numberOfRequest, @NonNull LoadStringCallback callback) {
    Observable<ResponseBody> observable = BaseApplication.getApiService().againApproveRequestForPostedCustomer(BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(),
      id, name, numberOfRequest, BaseApplication.getUserId(), BaseApplication.getFPId(), BaseApplication.getApiKey());

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadConsumerValidationAccessCallback<ResponseBody>() {
        @Override
        public void onNext(ResponseBody responseBody) {
          if (responseBody != null) {
            try {
              callback.onResponse(responseBody.string());
            } catch (IOException e) {
              e.printStackTrace();
              RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
              callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
            }
          } else {
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }
      });
  }


  @Override
  public void getCountOfPostedCustomerInfo(@NonNull LoadStringCallback callback) {
    Observable<ResponseBody> observable = BaseApplication.getApiService().getCountOfPostedCustomerInfo(BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(),
      BaseApplication.getUserId(), BaseApplication.getFPId(), BaseApplication.getApiKey());

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadConsumerValidationAccessCallback<ResponseBody>() {
        @Override
        public void onNext(ResponseBody responseBody) {
          if (responseBody != null) {
            try {
              callback.onResponse(responseBody.string());
            } catch (IOException e) {
              e.printStackTrace();
              RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
              callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
            }
          } else {
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }
      });
  }


  @Override
  public void getCountOfRowsThatNeedSync(@NonNull LoadStringCallback callback) {
    Observable<ResponseBody> observable = BaseApplication.getApiService().getCountOfRowsThatNeedSync(BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(),
      BaseApplication.getUserId(), BaseApplication.getFPId(), BaseApplication.getApiKey());

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadConsumerValidationAccessCallback<ResponseBody>() {
        @Override
        public void onNext(ResponseBody responseBody) {
          if (responseBody != null) {
            try {
              callback.onResponse(responseBody.string());
            } catch (IOException e) {
              e.printStackTrace();
              RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
              callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
            }
          } else {
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }
      });
  }


  @Override
  public void updateRowsThatNeedSync(int status, @NonNull LoadStringCallback callback) {
    Observable<ResponseBody> observable = BaseApplication.getApiService().updateRowsThatNeedSync(BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(),
      status, BaseApplication.getUserId(), BaseApplication.getFPId(), BaseApplication.getApiKey());

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadConsumerValidationAccessCallback<ResponseBody>() {
        @Override
        public void onNext(ResponseBody responseBody) {
          if (responseBody != null) {
            try {
              callback.onResponse(responseBody.string());
            } catch (IOException e) {
              e.printStackTrace();
              RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
              callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
            }
          } else {
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }
      });
  }

  @Override
  public Disposable loadPostedCustomersInfo(@NonNull LoadPostedCustomerTableCallback<PostedCustomerInfo> callback) {
    CompositeDisposable disposable = new CompositeDisposable();

    Observable<List<PostedCustomerInfo>> observable = BaseApplication.getApiService().getPostedCustomersInfo(
      BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(), BaseApplication.getUserId(), BaseApplication.getFPId(), BaseApplication.getApiKey());

    List<PostedCustomerInfo> postedCustomerInfos = new ArrayList<>();
    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadValidationAccessCallback<List<PostedCustomerInfo>>() {
        @Override
        public void onSubscribe(Disposable d) {
          disposable.add(d);
        }

        @Override
        public void onNext(List<PostedCustomerInfo> postedCustomerInfoList) {
          postedCustomerInfos.addAll(postedCustomerInfoList);
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }

        @Override
        public void onComplete() {
          callback.onResponse(postedCustomerInfos);

        }
      });

    return disposable;
  }


  @Override
  public Disposable loadRowsThatNeedSync(@NonNull LoadPostedCustomerTableCallback<PostedCustomerInfo> callback) {
    CompositeDisposable disposable = new CompositeDisposable();

    Observable<List<PostedCustomerInfo>> observable = BaseApplication.getApiService().getRowsThatNeedSync(
      BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(), BaseApplication.getUserId(), BaseApplication.getFPId(), BaseApplication.getApiKey());

    List<PostedCustomerInfo> postedCustomerInfos = new ArrayList<>();
    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadValidationAccessCallback<List<PostedCustomerInfo>>() {
        @Override
        public void onSubscribe(Disposable d) {
          disposable.add(d);
        }

        @Override
        public void onNext(List<PostedCustomerInfo> postedCustomerInfoList) {
          postedCustomerInfos.addAll(postedCustomerInfoList);
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }

        @Override
        public void onComplete() {
          callback.onResponse(postedCustomerInfos);

        }
      });

    return disposable;
  }

  @Override
  public Disposable getCustomerInfoCreated(String url, @NonNull LoadStringCallback callback) {
    CompositeDisposable disposable = new CompositeDisposable();
    Observable<ResponseBody> observable = BaseApplication.getApiService().getCustomerInfoCreated(url);

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable,
      new AccessRemoteDataRepository.LoadValidationAccessCallback<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {
          disposable.add(d);
        }

        @Override
        public void onNext(ResponseBody responseBody) {
          try {
            callback.onResponse(responseBody.string());
          } catch (NullPointerException e) {
            e.printStackTrace();
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }

        @Override
        public void onComplete() {

        }
      });

    return disposable;
  }


}
