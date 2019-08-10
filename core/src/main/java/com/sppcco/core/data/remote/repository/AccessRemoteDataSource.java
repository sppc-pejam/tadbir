package com.sppcco.core.data.remote.repository;



import com.sppcco.core.enums.ResponseType;
import com.sppcco.core.enums.SubsystemUserAccess;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.core.util.network.NetworkUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AccessRemoteDataSource implements AccessRemoteDataRepository {



  private Observable<Boolean> isNetworkAvailable() {
    return NetworkUtil.isNetworkAvailableObservable(BaseApplication.getContext());
  }


  //TODO
  private Observable<Integer> isCompatibleVersion() {
    return BaseApplication.getApiService().isCompatibleVersion(
      BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(),
      CoreConstants.MAJOR_VERSION, CoreConstants.MINOR_VERSION, BaseApplication.getApiKey());
  }

  private Observable<Integer> userAccessPreRequest() {
    return BaseApplication.getApiService().getValidationAccessPreRequest(
      BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(),
      SubsystemUserAccess.MOBILE_APP.getValue(),
      BaseApplication.getUserId(), BaseApplication.getDatabaseName(), BaseApplication.getApiKey());
  }

  @SuppressWarnings("unchecked")
  public <O, T> void getAccessPreRequest(Observable<O> observable, boolean showMessage, LoadValidationAccessCallback<T> callback) {

    final int USER_FULL_ACCESS = 0;
    final int USER_SUBSYSTEM_ACCESS = 1;
    final int USER_COMPNAY_ACCESS = 2;

    isNetworkAvailable()
      .subscribeOn(Schedulers.io())
      .timeout(10, TimeUnit.SECONDS)
      .onErrorReturn((Throwable e) -> false)
      .observeOn(AndroidSchedulers.mainThread())
      .doOnSubscribe(callback::onSubscribe)
      .doOnNext(isConnected -> {
        if (!isConnected) {
          if (showMessage)
            RemoteData.showErrorMessage(ResponseType.ERR_CLIENT_NO_NETWORK_ACCESS);
          callback.onError(ResponseType.ERR_CLIENT_NO_NETWORK_ACCESS);
        } else {
          isCompatibleVersion()
            .subscribeOn(Schedulers.io())
            .timeout(20, TimeUnit.SECONDS)
            .onErrorReturn((Throwable e) -> -1)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(callback::onSubscribe)
            .doOnNext(isCompatible -> {
              if (isCompatible == -1) {
                if (showMessage)
                  RemoteData.showErrorMessage(ResponseType.ERR_CLIENT_NO_CONNECTION);
                callback.onError(ResponseType.ERR_CLIENT_NO_CONNECTION);
              } else if (isCompatible != 1) {
                if (showMessage)
                  RemoteData.showErrorMessage(ResponseType.ERR_INCOMPATIBLE_VERSION);
                callback.onError(ResponseType.ERR_INCOMPATIBLE_VERSION);
              } else {
                userAccessPreRequest()
                  .subscribeOn(Schedulers.io())
                  .timeout(20, TimeUnit.SECONDS)
                  .onErrorReturn((Throwable e) -> -1)
                  .observeOn(AndroidSchedulers.mainThread())
                  .doOnSubscribe(callback::onSubscribe)
                  .doOnNext(access -> {
                    if (access == -1) {
                      if (showMessage)
                        RemoteData.showErrorMessage(ResponseType.ERR_CLIENT_NO_CONNECTION);
                      callback.onError(ResponseType.ERR_CLIENT_NO_CONNECTION);
                    } else if (access != USER_FULL_ACCESS) {
                      ResponseType responseType = null;
                      switch (access) {
                        case USER_SUBSYSTEM_ACCESS:
                          responseType = ResponseType.ERR_SUBSYSTEM_ACCESS;
                          break;
                        case USER_COMPNAY_ACCESS:
                          responseType = ResponseType.ERR_USER_ACCESS;
                          break;
                      }
                      if (showMessage)
                        if (responseType != null) {
                          RemoteData.showErrorMessage(responseType);
                        }
                      callback.onError(responseType);
                    } else {
                      observable
                        .subscribeOn(Schedulers.io())
                        .timeout(20, TimeUnit.SECONDS)
                        .onErrorReturn((Throwable e) -> (O) new NullPointerException("Null Pointer Exception"))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(callback::onSubscribe)
                        .doOnNext(o -> {
                          if (o instanceof NullPointerException) {
                            if (showMessage)
                              RemoteData.showErrorMessage(ResponseType.ERR_TIME_OUT);
                            callback.onError(ResponseType.ERR_TIME_OUT);
                          } else {
                            callback.onNext((T) o);
                          }
                        })
                        .doOnError(e -> {
                          if (showMessage)
                            RemoteData.showErrorMessage(RemoteData.ErrorType(e));
                          callback.onError(RemoteData.ErrorType(e));
                        })
                        .doOnComplete(callback::onComplete)
                        .subscribe();
                    }
                  })
                  //.observeOn(AndroidSchedulers.mainThread())
                  .doOnError(e -> {
                    if (showMessage)
                      RemoteData.showErrorMessage(RemoteData.ErrorType(e));
                    callback.onError(RemoteData.ErrorType(e));
                  })
                  .subscribe();
              }
            })
            //.observeOn(AndroidSchedulers.mainThread())
            .doOnError(e -> {
              if (showMessage)
                RemoteData.showErrorMessage(RemoteData.ErrorType(e));
              callback.onError(RemoteData.ErrorType(e));
            })
            .subscribe();
        }
      })
      //.observeOn(AndroidSchedulers.mainThread())
      .doOnError(e -> {
        if (showMessage)
          RemoteData.showErrorMessage(RemoteData.ErrorType(e));
        callback.onError(RemoteData.ErrorType(e));
      })
      .subscribe();
  }

  @SuppressWarnings("unchecked")
  public <O, T> void getAccessPreRequest(Observable<O> observable, LoadValidationAccessCallback<T> callback) {

    final int USER_FULL_ACCESS = 0;
    final int USER_SUBSYSTEM_ACCESS = 1;
    final int USER_COMPNAY_ACCESS = 2;

    isNetworkAvailable()
      .subscribeOn(Schedulers.io())
      .timeout(10, TimeUnit.SECONDS)
      .onErrorReturn((Throwable e) -> false)
      .observeOn(AndroidSchedulers.mainThread())
      .doOnSubscribe(callback::onSubscribe)
      .doOnNext(isConnected -> {
        if (!isConnected) {
          RemoteData.showErrorMessage(ResponseType.ERR_CLIENT_NO_NETWORK_ACCESS);
          callback.onError(ResponseType.ERR_CLIENT_NO_NETWORK_ACCESS);
        } else {
          isCompatibleVersion()
            .subscribeOn(Schedulers.io())
            .timeout(20, TimeUnit.SECONDS)
            .onErrorReturn((Throwable e) -> -1)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(callback::onSubscribe)
            .doOnNext(isCompatible -> {
              if (isCompatible == -1) {
                RemoteData.showErrorMessage(ResponseType.ERR_CLIENT_NO_CONNECTION);
                callback.onError(ResponseType.ERR_CLIENT_NO_CONNECTION);
              } else if (isCompatible != 1) {
                RemoteData.showErrorMessage(ResponseType.ERR_INCOMPATIBLE_VERSION);
                callback.onError(ResponseType.ERR_INCOMPATIBLE_VERSION);
              } else {
                userAccessPreRequest()
                  .subscribeOn(Schedulers.io())
                  .timeout(20, TimeUnit.SECONDS)
                  .onErrorReturn((Throwable e) -> -1)
                  .observeOn(AndroidSchedulers.mainThread())
                  .doOnSubscribe(callback::onSubscribe)
                  .doOnNext(access -> {
                    if (access == -1) {
                      RemoteData.showErrorMessage(ResponseType.ERR_CLIENT_NO_CONNECTION);
                      callback.onError(ResponseType.ERR_CLIENT_NO_CONNECTION);
                    } else if (access != USER_FULL_ACCESS) {
                      ResponseType responseType = null;
                      switch (access) {
                        case USER_SUBSYSTEM_ACCESS:
                          responseType = ResponseType.ERR_SUBSYSTEM_ACCESS;
                          break;
                        case USER_COMPNAY_ACCESS:
                          responseType = ResponseType.ERR_USER_ACCESS;
                          break;
                      }
                      if (responseType != null) {
                        RemoteData.showErrorMessage(responseType);
                      }
                      callback.onError(responseType);
                    } else {
                      observable
                        .subscribeOn(Schedulers.io())
                        .timeout(20, TimeUnit.SECONDS)
                        .onErrorReturn((Throwable e) -> (O) new NullPointerException("Null Pointer Exception"))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(callback::onSubscribe)
                        .doOnNext(o -> {
                          if (o instanceof NullPointerException) {
                            RemoteData.showErrorMessage(ResponseType.ERR_TIME_OUT);
                            callback.onError(ResponseType.ERR_TIME_OUT);
                          } else {
                            callback.onNext((T) o);
                          }
                        })
                        .doOnError(e -> {
                          RemoteData.showErrorMessage(RemoteData.ErrorType(e));
                          callback.onError(RemoteData.ErrorType(e));
                        })
                        .doOnComplete(callback::onComplete)
                        .subscribe();
                    }
                  })
                  //.observeOn(AndroidSchedulers.mainThread())
                  .doOnError(e -> {
                    RemoteData.showErrorMessage(RemoteData.ErrorType(e));
                    callback.onError(RemoteData.ErrorType(e));
                  })
                  .subscribe();
              }
            })
            //.observeOn(AndroidSchedulers.mainThread())
            .doOnError(e -> {
              RemoteData.showErrorMessage(RemoteData.ErrorType(e));
              callback.onError(RemoteData.ErrorType(e));
            })
            .subscribe();
        }
      })
      //.observeOn(AndroidSchedulers.mainThread())
      .doOnError(e -> {
        RemoteData.showErrorMessage(RemoteData.ErrorType(e));
        callback.onError(RemoteData.ErrorType(e));
      })
      .subscribe();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <O, T> void getAccessPreRequest(Observable<O> observable, boolean showMessage, LoadConsumerValidationAccessCallback<T> callback) {
    final int USER_FULL_ACCESS = 0;
    final int USER_SUBSYSTEM_ACCESS = 1;
    final int USER_COMPNAY_ACCESS = 2;

    isNetworkAvailable()
      .subscribeOn(Schedulers.io())
      .timeout(10, TimeUnit.SECONDS)
      .onErrorReturn((Throwable e) -> false)
      .observeOn(AndroidSchedulers.mainThread())
      .doOnNext(isConnected -> {
        if (!isConnected) {
          if (showMessage)
            RemoteData.showErrorMessage(ResponseType.ERR_CLIENT_NO_CONNECTION);
          callback.onError(ResponseType.ERR_CLIENT_NO_CONNECTION);
        } else {
          isCompatibleVersion()
            .subscribeOn(Schedulers.io())
            .timeout(20, TimeUnit.SECONDS)
            .onErrorReturn((Throwable e) -> -1)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(isCompatible -> {
              if (isCompatible == -1) {
                if (showMessage)
                  RemoteData.showErrorMessage(ResponseType.ERR_CLIENT_NO_CONNECTION);
                callback.onError(ResponseType.ERR_CLIENT_NO_CONNECTION);
              } else if (isCompatible != 1) {
                if (showMessage)
                  RemoteData.showErrorMessage(ResponseType.ERR_INCOMPATIBLE_VERSION);
                callback.onError(ResponseType.ERR_INCOMPATIBLE_VERSION);
              } else {
                userAccessPreRequest()
                  .subscribeOn(Schedulers.io())
                  .timeout(20, TimeUnit.SECONDS)
                  .onErrorReturn((Throwable e) -> -1)
                  .observeOn(AndroidSchedulers.mainThread())
                  .doOnNext(access -> {
                    if (access == -1) {
                      if (showMessage)
                        RemoteData.showErrorMessage(ResponseType.ERR_CLIENT_NO_CONNECTION);
                      callback.onError(ResponseType.ERR_CLIENT_NO_CONNECTION);
                    } else if (access != USER_FULL_ACCESS) {
                      ResponseType responseType = null;
                      switch (access) {
                        case USER_SUBSYSTEM_ACCESS:
                          responseType = ResponseType.ERR_SUBSYSTEM_ACCESS;
                          break;
                        case USER_COMPNAY_ACCESS:
                          responseType = ResponseType.ERR_USER_ACCESS;
                          break;
                      }
                      if (showMessage)
                        if (responseType != null) {
                          RemoteData.showErrorMessage(responseType);
                        }
                      callback.onError(responseType);
                    } else {
                      observable
                        .subscribeOn(Schedulers.io())
                        .timeout(20, TimeUnit.SECONDS)
                        .onErrorReturn((Throwable e) -> (O) new NullPointerException("Null Pointer Exception"))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(o -> {
                          if (o instanceof NullPointerException) {
                            if (showMessage)
                              RemoteData.showErrorMessage(ResponseType.ERR_TIME_OUT);
                            callback.onError(ResponseType.ERR_TIME_OUT);
                          } else {
                            callback.onNext((T) o);
                          }
                        })
                        .doOnError(e -> {
                          if (showMessage)
                            RemoteData.showErrorMessage(RemoteData.ErrorType(e));
                          callback.onError(RemoteData.ErrorType(e));
                        })
                        .subscribe();
                    }
                  })
                  //.observeOn(AndroidSchedulers.mainThread())
                  .doOnError(e -> {
                    if (showMessage)
                      RemoteData.showErrorMessage(RemoteData.ErrorType(e));
                    callback.onError(RemoteData.ErrorType(e));
                  })
                  .subscribe();
              }
            })
            //.observeOn(AndroidSchedulers.mainThread())
            .doOnError(e -> {
              if (showMessage)
                RemoteData.showErrorMessage(RemoteData.ErrorType(e));
              callback.onError(RemoteData.ErrorType(e));
            })
            .subscribe();
        }
      })
      //.observeOn(AndroidSchedulers.mainThread())
      .doOnError(e -> {
        if (showMessage)
          RemoteData.showErrorMessage(RemoteData.ErrorType(e));
        callback.onError(RemoteData.ErrorType(e));
      })
      .subscribe();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <O, T> void getAccessPreRequest(Observable<O> observable, LoadConsumerValidationAccessCallback<T> callback) {

    final int USER_FULL_ACCESS = 0;
    final int USER_SUBSYSTEM_ACCESS = 1;
    final int USER_COMPNAY_ACCESS = 2;

    isNetworkAvailable()
      .subscribeOn(Schedulers.io())
      .timeout(10, TimeUnit.SECONDS)
      .onErrorReturn((Throwable e) -> false)
      .observeOn(AndroidSchedulers.mainThread())
      .doOnNext(isConnected -> {
        if (!isConnected) {
          RemoteData.showErrorMessage(ResponseType.ERR_CLIENT_NO_CONNECTION);
          callback.onError(ResponseType.ERR_CLIENT_NO_CONNECTION);
        } else {
          isCompatibleVersion()
            .subscribeOn(Schedulers.io())
            .timeout(20, TimeUnit.SECONDS)
            .onErrorReturn((Throwable e) -> -1)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(isCompatible -> {
              if (isCompatible == -1) {
                RemoteData.showErrorMessage(ResponseType.ERR_CLIENT_NO_CONNECTION);
                callback.onError(ResponseType.ERR_CLIENT_NO_CONNECTION);
              } else if (isCompatible != 1) {
                RemoteData.showErrorMessage(ResponseType.ERR_INCOMPATIBLE_VERSION);
                callback.onError(ResponseType.ERR_INCOMPATIBLE_VERSION);
              } else {
                userAccessPreRequest()
                  .subscribeOn(Schedulers.io())
                  .timeout(20, TimeUnit.SECONDS)
                  .onErrorReturn((Throwable e) -> -1)
                  .observeOn(AndroidSchedulers.mainThread())
                  .doOnNext(access -> {
                    if (access == -1) {
                      RemoteData.showErrorMessage(ResponseType.ERR_CLIENT_NO_CONNECTION);
                      callback.onError(ResponseType.ERR_CLIENT_NO_CONNECTION);
                    } else if (access != USER_FULL_ACCESS) {
                      ResponseType responseType = null;
                      switch (access) {
                        case USER_SUBSYSTEM_ACCESS:
                          responseType = ResponseType.ERR_SUBSYSTEM_ACCESS;
                          break;
                        case USER_COMPNAY_ACCESS:
                          responseType = ResponseType.ERR_USER_ACCESS;
                          break;
                      }
                      if (responseType != null) {
                        RemoteData.showErrorMessage(responseType);
                      }
                      callback.onError(responseType);
                    } else {
                      observable
                        .subscribeOn(Schedulers.io())
                        .timeout(20, TimeUnit.SECONDS)
                        .onErrorReturn((Throwable e) -> (O) new NullPointerException("Null Pointer Exception"))
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(o -> {
                          if (o instanceof NullPointerException) {
                            RemoteData.showErrorMessage(ResponseType.ERR_TIME_OUT);
                            callback.onError(ResponseType.ERR_TIME_OUT);
                          } else {
                            callback.onNext((T) o);
                          }
                        })
                        .doOnError(e -> {
                          RemoteData.showErrorMessage(RemoteData.ErrorType(e));
                          callback.onError(RemoteData.ErrorType(e));
                        })
                        .subscribe();
                    }
                  })
                  //.observeOn(AndroidSchedulers.mainThread())
                  .doOnError(e -> {
                    RemoteData.showErrorMessage(RemoteData.ErrorType(e));
                    callback.onError(RemoteData.ErrorType(e));
                  })
                  .subscribe();
              }
            })
            //.observeOn(AndroidSchedulers.mainThread())
            .doOnError(e -> {
              RemoteData.showErrorMessage(RemoteData.ErrorType(e));
              callback.onError(RemoteData.ErrorType(e));
            })
            .subscribe();
        }
      })
      //.observeOn(AndroidSchedulers.mainThread())
      .doOnError(e -> {
        RemoteData.showErrorMessage(RemoteData.ErrorType(e));
        callback.onError(RemoteData.ErrorType(e));
      })
      .subscribe();
  }
}
