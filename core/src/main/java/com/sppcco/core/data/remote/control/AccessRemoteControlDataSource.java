package com.sppcco.core.data.remote.control;



import com.sppcco.core.data.remote.repository.AccessRemoteDataRepository;
import com.sppcco.core.framework.application.BaseApplication;

import io.reactivex.Observable;

import static dagger.internal.Preconditions.checkNotNull;


public class AccessRemoteControlDataSource implements AccessRemoteControlRepository {


  @Override
  public <O, T> void getAccessPreRequest(Observable<O> observable, AccessRemoteDataRepository.LoadValidationAccessCallback<T> callback) {
    checkNotNull(callback);
    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable, callback);
  }

  @Override
  public <O, T> void getAccessPreRequest(Observable<O> observable, AccessRemoteDataRepository.LoadConsumerValidationAccessCallback<T> callback) {
    checkNotNull(callback);
    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(observable, callback);
  }
}
