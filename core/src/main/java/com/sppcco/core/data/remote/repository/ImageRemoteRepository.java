package com.sppcco.core.data.remote.repository;

import com.sppcco.core.enums.ResponseType;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by b_nematzadeh on 8/31/2018.
 */

public interface ImageRemoteRepository {

  interface LoadStringArrayCallback {
    void onResponse(String... response);

    void onFailure(ResponseType responseType);
  }

  Disposable getImagePagesCount(LoadStringArrayCallback callback);

  interface LoadSyncCallback<T> {
    void onResponse(List<T> list);

    void onProgress();

    void onFailure(ResponseType responseType);
  }

  Disposable syncImage(Observable[] observables, LoadSyncCallback callback);

  interface LoadListStringCallback {
    void onResponse(List<String> imagesList);

    void onFailure(ResponseType responseType);
  }

  Disposable getBase64ImageById(int merchId, boolean showAll, LoadListStringCallback callback);

  interface LoadListIntegerCallback {
    void onResponse(List<Integer> imagesList);

    void onFailure(ResponseType responseType);
  }
  Disposable GetImageIds(int merchId, LoadListIntegerCallback callback);
}
