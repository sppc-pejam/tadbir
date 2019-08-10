package com.sppcco.core.data.remote.repository;

import com.sppcco.core.data.model.Image;
import com.sppcco.core.enums.ResponseType;
import com.sppcco.core.enums.StockForm;
import com.sppcco.core.enums.SubsystemsId;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.util.app.CoreConstants;
import com.sppcco.helperlibrary.converter.IterateSerializeObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by b_nematzadeh on 8/31/2018.
 */

public class ImageRemoteDataSource implements ImageRemoteRepository {


  @Override
  public Disposable getImagePagesCount(LoadStringArrayCallback callback) {

    //Observable<ResponseBody> binAppxObservable = BaseApplication.getApiService().getBinAppendixPagesCount(BaseApplication.getDatabaseName(), SubsystemsId.INVENTORY_SYS.getValue(), StockForm.INF_MERCHANDISE.getValue(), BaseApplication.getFPId(), BaseApplication.getApiKey());
    Observable<ResponseBody> imageObservable = BaseApplication.getApiService().getImagePagesCount(BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(), SubsystemsId.INVENTORY_SYS.getValue(), StockForm.INF_MERCHANDISE.getValue(), BaseApplication.getFPId(), BaseApplication.getApiKey());

    String result[] = new String[1];

    //final int BINAPPENDIX_INDEX = 0;
    final int IMAGE_INDEX = 0;

    CompositeDisposable disposable = new CompositeDisposable();

    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(
      Observable.fromArray(/*binAppxObservable, */imageObservable).flatMap(Observable::concatArray),
      new AccessRemoteDataRepository.LoadValidationAccessCallback<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {
          disposable.add(d);
        }

        @Override
        public void onNext(ResponseBody responseBody) {
          try {
            JSONArray jsonarray = new JSONArray(responseBody.string());
            JSONObject jsonobject = jsonarray.getJSONObject(0);
            String pageName = jsonobject.keys().next();

            switch (pageName) {
              /*case "BinAppendixPages":
                result[BINAPPENDIX_INDEX] = jsonobject.getString("BinAppendixPages");
                break;*/
              case "ImagePages":
                result[IMAGE_INDEX] = jsonobject.getString("ImagePages");
                break;
            }

          } catch (JSONException e) {
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
          if (result[IMAGE_INDEX] != null) {
            callback.onResponse(result);
          } else {
            RemoteData.showErrorMessage(ResponseType.ERR_MISMATCH_DATA);
            callback.onFailure(ResponseType.ERR_MISMATCH_DATA);
          }
        }
      });
    return disposable;
  }

  @Override
  public Disposable syncImage(Observable[] observables, LoadSyncCallback callback) {

    List<Image> imageList = new ArrayList<>();

    CompositeDisposable disposable = new CompositeDisposable();
    BaseApplication.getCoreNetComponent().accessRemoteDataRepository().getAccessPreRequest(
      Observable.fromArray(observables).flatMap(Observable::concatArray),
      new AccessRemoteDataRepository.LoadValidationAccessCallback<List<?>>() {
        @Override
        public void onSubscribe(Disposable d) {
          disposable.add(d);
        }

        @Override
        public void onNext(List<?> serializables) {
          Object object = serializables.iterator().next();

          if (object instanceof Image) {
            Iterator iterator = serializables.iterator();
            List<Image> images = IterateSerializeObject.iterateReponse(iterator);
            imageList.addAll(images);
            callback.onProgress();
          }
        }

        @Override
        public void onError(ResponseType responseType) {
          callback.onFailure(responseType);
        }

        @Override
        public void onComplete() {
          callback.onResponse(imageList);
        }
      });
    return disposable;
  }

  @Override
  public Disposable getBase64ImageById(int merchId, boolean showAll, LoadListStringCallback callback) {
    CompositeDisposable disposable = new CompositeDisposable();

    Observable<ResponseBody> observable = BaseApplication.getApiService().getImageById(
      BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(), merchId, SubsystemsId.INVENTORY_SYS.getValue(), StockForm.INF_MERCHANDISE.getValue(), BaseApplication.getFPId(),
      showAll ? 1 : 0, BaseApplication.getApiKey());

    observable
      .subscribeOn(Schedulers.io())
      //.timeout(20, TimeUnit.SECONDS)
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {
          disposable.add(d);
        }

        @Override
        public void onNext(ResponseBody responseBody) {
          try {
            JSONArray jsonarray = new JSONArray(responseBody.string());
            List<String> base64StringArr = new ArrayList<>(jsonarray.length());
            for (int index = 0; index < jsonarray.length(); index++) {
              if (!jsonarray.getJSONObject(index).getString("Thumbnail").equals("null"))
                base64StringArr.add(jsonarray.getJSONObject(index).getString("Thumbnail"));
            }
            callback.onResponse(base64StringArr);
          } catch (IOException e) {
            e.printStackTrace();
            callback.onFailure(RemoteData.ErrorType(e));
          } catch (JSONException e) {
            e.printStackTrace();
            callback.onFailure(RemoteData.ErrorType(e));
          }

        }

        @Override
        public void onError(Throwable e) {
          callback.onFailure(RemoteData.ErrorType(e));
        }

        @Override
        public void onComplete() {

        }
      });

    return disposable;
  }

  @Override
  public Disposable GetImageIds(int merchId, LoadListIntegerCallback callback) {
    CompositeDisposable disposable = new CompositeDisposable();

    Observable<ResponseBody> observable = BaseApplication.getApiService().GetImageIds(
      BaseApplication.getDatabaseName(), CoreConstants.getServiceVersion(), merchId,
      SubsystemsId.INVENTORY_SYS.getValue(), StockForm.INF_MERCHANDISE.getValue(), BaseApplication.getFPId(), BaseApplication.getApiKey());

    observable
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {
          disposable.add(d);
        }

        @Override
        public void onNext(ResponseBody responseBody) {
          try {
            JSONArray jsonarray = new JSONArray(responseBody.string());
            List<Integer> integers = new ArrayList<>(jsonarray.length());
            for (int index = 0; index < jsonarray.length(); index++) {
              if (!jsonarray.getJSONObject(index).getString("Id").equals("null"))
                integers.add(Integer.valueOf(jsonarray.getJSONObject(index).getString("Id")));
            }
            callback.onResponse(integers);
          } catch (IOException e) {
            e.printStackTrace();
            callback.onFailure(RemoteData.ErrorType(e));
          } catch (JSONException e) {
            e.printStackTrace();
            callback.onFailure(RemoteData.ErrorType(e));
          }

        }

        @Override
        public void onError(Throwable e) {
          callback.onFailure(RemoteData.ErrorType(e));
        }

        @Override
        public void onComplete() {

        }
      });

    return disposable;
  }
}
