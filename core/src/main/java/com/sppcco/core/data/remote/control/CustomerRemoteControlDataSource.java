package com.sppcco.core.data.remote.control;



import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.remote.repository.CustomerRemoteRepository;
import com.sppcco.core.data.sub_model.PostedCustomerInfo;
import com.sppcco.core.framework.application.BaseApplication;

import androidx.annotation.NonNull;
import io.reactivex.disposables.Disposable;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by b_nematzadeh on 8/31/2018.
 */

public class CustomerRemoteControlDataSource implements CustomerRemoteControlRepository {

  @Override
  public Disposable getCustomerCredit(Customer customer, String startDate,
                                      CustomerRemoteRepository.LoadStringArrayCallback callback) {
    checkNotNull(callback);
    return BaseApplication.getCoreNetComponent().customerRemoteRepository().getCustomerCredit(customer, startDate, callback);
  }

  @Override
  public Disposable getAccVectorBalance(Customer customer, String startDate,
                                        CustomerRemoteRepository.LoadStringArrayCallback callback) {
    checkNotNull(callback);
    return BaseApplication.getCoreNetComponent().customerRemoteRepository().getAccVectorBalance(customer, startDate, callback);
  }

  @Override
  public void getValidationCustomerResult(Customer customer, @NonNull CustomerRemoteRepository.LoadStringCallback callback) {
    checkNotNull(callback);
    BaseApplication.getCoreNetComponent().customerRemoteRepository().getValidationCustomerResult(customer, callback);
  }

  @Override
  public void insertCustomer(Customer customer, @NonNull CustomerRemoteRepository.LoadVoidCallback callback) {
    checkNotNull(callback);
    BaseApplication.getCoreNetComponent().customerRemoteRepository().insertCustomer(customer, callback);
  }

  @Override
  public void getLastPostedCustomerInfo(String name, @NonNull CustomerRemoteRepository.LoadStringCallback callback) {
    checkNotNull(callback);
    BaseApplication.getCoreNetComponent().customerRemoteRepository().getLastPostedCustomerInfo(name, callback);
  }

  @Override
  public void deletePostedCustomerInfo(int id, String name, @NonNull CustomerRemoteRepository.LoadStringCallback callback) {
    checkNotNull(callback);
    BaseApplication.getCoreNetComponent().customerRemoteRepository().deletePostedCustomerInfo(id, name, callback);
  }

  @Override
  public void controlStatusCustomer(int id, String name, @NonNull CustomerRemoteRepository.LoadStringCallback callback) {
    checkNotNull(callback);
    BaseApplication.getCoreNetComponent().customerRemoteRepository().controlStatusCustomer(id, name, callback);
  }

  @Override
  public void againApproveRequestForPostedCustomer(int id, String name, int numberOfRequest, @NonNull CustomerRemoteRepository.LoadStringCallback callback) {
    checkNotNull(callback);
    BaseApplication.getCoreNetComponent().customerRemoteRepository().againApproveRequestForPostedCustomer(id, name, numberOfRequest, callback);
  }

  @Override
  public void getCountOfPostedCustomerInfo(@NonNull CustomerRemoteRepository.LoadStringCallback callback) {
    checkNotNull(callback);
    BaseApplication.getCoreNetComponent().customerRemoteRepository().getCountOfPostedCustomerInfo(callback);
  }

  @Override
  public void getCountOfRowsThatNeedSync(@NonNull CustomerRemoteRepository.LoadStringCallback callback) {
    checkNotNull(callback);
    BaseApplication.getCoreNetComponent().customerRemoteRepository().getCountOfRowsThatNeedSync(callback);
  }

  @Override
  public void updateRowsThatNeedSync(int status, @NonNull CustomerRemoteRepository.LoadStringCallback callback) {
    checkNotNull(callback);
    BaseApplication.getCoreNetComponent().customerRemoteRepository().updateRowsThatNeedSync(status, callback);
  }

  @Override
  public Disposable loadPostedCustomersInfo(@NonNull CustomerRemoteRepository.LoadPostedCustomerTableCallback<PostedCustomerInfo> callback) {
    checkNotNull(callback);
    return BaseApplication.getCoreNetComponent().customerRemoteRepository().loadPostedCustomersInfo(callback);
  }

  @Override
  public Disposable loadRowsThatNeedSync(@NonNull CustomerRemoteRepository.LoadPostedCustomerTableCallback<PostedCustomerInfo> callback) {
    checkNotNull(callback);
    return BaseApplication.getCoreNetComponent().customerRemoteRepository().loadRowsThatNeedSync(callback);
  }

  @Override
  public Disposable getCustomerInfoCreated(String url, @NonNull CustomerRemoteRepository.LoadStringCallback callback) {
    checkNotNull(callback);
    return BaseApplication.getCoreNetComponent().customerRemoteRepository().getCustomerInfoCreated(url, callback);
  }
}
