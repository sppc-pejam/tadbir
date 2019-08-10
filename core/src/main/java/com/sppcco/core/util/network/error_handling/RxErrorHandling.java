package com.sppcco.core.util.network.error_handling;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeoutException;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RxErrorHandling extends CallAdapter.Factory {

  private final RxJava2CallAdapterFactory original;

  private RxErrorHandling() {
    original = RxJava2CallAdapterFactory.create();
  }

  @Nullable
  @Override
  public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
    return new RxCallAdapterWrapper<>(retrofit, original.get(returnType, annotations, retrofit));
  }

  public static CallAdapter.Factory create() {
    return new RxErrorHandling();
  }

  private static class RxCallAdapterWrapper<R> implements CallAdapter<R, Observable<R>> {
    private final Retrofit retrofit;
    private final CallAdapter<R, ?> wrapped;

    public RxCallAdapterWrapper(Retrofit retrofit, final CallAdapter<R, ?> wrapped) {
      this.retrofit = retrofit;
      this.wrapped = wrapped;
    }

    @Override
    public Type responseType() {
      return wrapped.responseType();
    }

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public Observable<R> adapt(@NonNull Call<R> call) {


      Object adaptedCall = wrapped.adapt(call);

      if (adaptedCall instanceof Completable) {
        return ((Observable) adaptedCall).onErrorResumeNext(
          (ObservableSource) throwable -> Completable.error(asRetrofitException((Throwable) throwable)));
      }

      if (adaptedCall instanceof Single) {
        return ((Observable) adaptedCall).onErrorResumeNext(
          (ObservableSource) throwable -> Single.error(asRetrofitException((Throwable) throwable)));
      }

      if (adaptedCall instanceof Observable) {
        return ((Observable) adaptedCall).onErrorResumeNext(
          (Function<? super Throwable, ? extends ObservableSource>)
            throwable -> Observable.error(asRetrofitException(throwable)));
      }

      throw new RuntimeException("Observable Type not supported");

      /*return ((Observable) wrapped.adapt(call)).onErrorResumeNext(throwable -> {
        return Observable.error(asRetrofitException((Throwable) throwable));
      });*/
    }

    private RetrofitException asRetrofitException(Throwable throwable) {
      // We had non-200 http error
      if (throwable instanceof HttpException) {
        HttpException httpException = (HttpException) throwable;
        Response response = httpException.response();
        return RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit);
      }

      // A network error happened
      if (throwable instanceof TimeoutException) {
        return RetrofitException.networkError((IOException) throwable);
      }

      if (throwable instanceof IOException) {
        return RetrofitException.networkError((IOException) throwable);
      }

      // We don't know what happened. We need to simply convert to an unknown error
      return RetrofitException.unexpectedError(throwable);
    }
  }
}
