package com.sppcco.core.data.remote.control;

import com.sppcco.core.data.remote.repository.AccessRemoteDataRepository;

import io.reactivex.Observable;

public interface AccessRemoteControlRepository {

  <O, T> void getAccessPreRequest(Observable<O> observable, AccessRemoteDataRepository.LoadValidationAccessCallback<T> callback);

  <O, T> void getAccessPreRequest(Observable<O> observable, AccessRemoteDataRepository.LoadConsumerValidationAccessCallback<T> callback);
}
