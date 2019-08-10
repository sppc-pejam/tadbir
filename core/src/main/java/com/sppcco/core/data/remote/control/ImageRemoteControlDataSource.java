package com.sppcco.core.data.remote.control;

import com.sppcco.core.data.remote.repository.ImageRemoteRepository;
import com.sppcco.core.framework.application.BaseApplication;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by b_nematzadeh on 8/31/2018.
 */

public class ImageRemoteControlDataSource implements ImageRemoteControlRepository {

  @Override
  public Disposable getImagePagesCount(ImageRemoteRepository.LoadStringArrayCallback callback) {
    checkNotNull(callback);
    return BaseApplication.getCoreNetComponent().imageRemoteRepository().getImagePagesCount(callback);
  }

  @Override
  public Disposable syncImage(Observable[] observables, ImageRemoteRepository.LoadSyncCallback callback) {
    checkNotNull(callback);
    return BaseApplication.getCoreNetComponent().imageRemoteRepository().syncImage(observables, callback);
  }

  @Override
  public Disposable getBase64ImageById(int merchId, boolean showAll, ImageRemoteRepository.LoadListStringCallback callback) {
    checkNotNull(callback);
    return BaseApplication.getCoreNetComponent().imageRemoteRepository().getBase64ImageById(merchId, showAll, callback);
  }

  @Override
  public Disposable GetImageIds(int merchId, ImageRemoteRepository.LoadListIntegerCallback callback) {
    checkNotNull(callback);
    return BaseApplication.getCoreNetComponent().imageRemoteRepository().GetImageIds(merchId, callback);
  }
}
