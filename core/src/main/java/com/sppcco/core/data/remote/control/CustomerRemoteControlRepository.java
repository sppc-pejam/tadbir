package com.sppcco.core.data.remote.control;


import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.remote.repository.CustomerRemoteRepository;
import com.sppcco.core.data.sub_model.PostedCustomerInfo;

import androidx.annotation.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by b_nematzadeh on 8/31/2018.
 */

public interface CustomerRemoteControlRepository {

  Disposable getCustomerCredit(Customer customer, String startDate,
                               CustomerRemoteRepository.LoadStringArrayCallback callback);

  Disposable getAccVectorBalance(Customer customer, String startDate,
                                 CustomerRemoteRepository.LoadStringArrayCallback callback);


  void getValidationCustomerResult(Customer customer, @NonNull CustomerRemoteRepository.LoadStringCallback callback);

  void insertCustomer(Customer customer, @NonNull CustomerRemoteRepository.LoadVoidCallback callback);

  void getLastPostedCustomerInfo(String name, @NonNull CustomerRemoteRepository.LoadStringCallback callback);

  void deletePostedCustomerInfo(int id, String name, @NonNull CustomerRemoteRepository.LoadStringCallback callback);

  void controlStatusCustomer(int id, String name, @NonNull CustomerRemoteRepository.LoadStringCallback callback);

  void againApproveRequestForPostedCustomer(int id, String name, int numberOfRequest, @NonNull CustomerRemoteRepository.LoadStringCallback callback);

  void getCountOfPostedCustomerInfo(@NonNull CustomerRemoteRepository.LoadStringCallback callback);

  void getCountOfRowsThatNeedSync(@NonNull CustomerRemoteRepository.LoadStringCallback callback);

  void updateRowsThatNeedSync(int status, @NonNull CustomerRemoteRepository.LoadStringCallback callback);

  Disposable loadPostedCustomersInfo(@NonNull CustomerRemoteRepository.LoadPostedCustomerTableCallback<PostedCustomerInfo> callback);

  Disposable loadRowsThatNeedSync(@NonNull CustomerRemoteRepository.LoadPostedCustomerTableCallback<PostedCustomerInfo> callback);

  Disposable getCustomerInfoCreated(String url, @NonNull CustomerRemoteRepository.LoadStringCallback callback);

}
