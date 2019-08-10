package com.sppcco.core.data.remote.repository;

import com.sppcco.core.data.model.Customer;
import com.sppcco.core.data.sub_model.PostedCustomerInfo;
import com.sppcco.core.enums.ResponseType;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by b_nematzadeh on 8/31/2018.
 */

public interface CustomerRemoteRepository {

  interface LoadStringArrayCallback {
    void onResponse(String... response);

    void onFailure(ResponseType responseType);
  }

  Disposable getCustomerCredit(Customer customer, String startDate,
                               @NotNull LoadStringArrayCallback callback);

  Disposable getAccVectorBalance(Customer customer, String startDate,
                                 @NotNull LoadStringArrayCallback callback);

  interface LoadStringCallback {
    void onResponse(String response);

    void onFailure(ResponseType responseType);
  }

  void getValidationCustomerResult(Customer customer, @NonNull LoadStringCallback callback);

  Disposable getCustomerInfoCreated(String url, @NonNull LoadStringCallback callback);

  interface LoadVoidCallback {
    void onResponse();

    void onFailure(ResponseType responseType);
  }

  void insertCustomer(Customer customer, @NonNull LoadVoidCallback callback);


  void getLastPostedCustomerInfo(String name, @NonNull LoadStringCallback callback);

  void deletePostedCustomerInfo(int id, String name, @NonNull LoadStringCallback callback);

  void controlStatusCustomer(int id, String name, @NonNull LoadStringCallback callback);

  void againApproveRequestForPostedCustomer(int id, String name, int numberOfRequest, @NonNull LoadStringCallback callback);

  void getCountOfPostedCustomerInfo(@NonNull LoadStringCallback callback);

  void getCountOfRowsThatNeedSync(@NonNull LoadStringCallback callback);

  void updateRowsThatNeedSync(int status, @NonNull LoadStringCallback callback);


  interface LoadPostedCustomerTableCallback<T> {
    void onResponse(List<T> list);

    void onFailure(ResponseType responseType);
  }

  Disposable loadPostedCustomersInfo(@NonNull LoadPostedCustomerTableCallback<PostedCustomerInfo> callback);

  Disposable loadRowsThatNeedSync(@NonNull LoadPostedCustomerTableCallback<PostedCustomerInfo> callback);

}
