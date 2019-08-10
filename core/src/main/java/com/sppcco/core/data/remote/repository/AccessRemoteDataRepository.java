package com.sppcco.core.data.remote.repository;

import com.sppcco.core.enums.ResponseType;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public interface AccessRemoteDataRepository {

  interface LoadValidationAccessCallback<T>{
    void onSubscribe(@NonNull Disposable disposable);
    void onNext(@NonNull T t);
    void onError(@NonNull ResponseType responseType);
    void onComplete();
  }
  <O, T> void getAccessPreRequest(Observable<O> observable, LoadValidationAccessCallback<T> callback);

  interface LoadConsumerValidationAccessCallback<T>{
    void onNext(@NonNull T t);
    void onError(@NonNull ResponseType responseType);
  }
  <O, T> void getAccessPreRequest(Observable<O> observable, boolean showMessage, LoadConsumerValidationAccessCallback<T> callback);
  <O, T> void getAccessPreRequest(Observable<O> observable, LoadConsumerValidationAccessCallback<T> callback);

}
