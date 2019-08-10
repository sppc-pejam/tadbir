package com.sppcco.core.data.remote.control;

import com.sppcco.core.data.remote.repository.ImageRemoteRepository;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by b_nematzadeh on 8/31/2018.
 */

public interface ImageRemoteControlRepository {

  Disposable getImagePagesCount(ImageRemoteRepository.LoadStringArrayCallback callback);

  Disposable syncImage(Observable[] observables, ImageRemoteRepository.LoadSyncCallback callback);

  Disposable getBase64ImageById(int merchId, boolean showAll, ImageRemoteRepository.LoadListStringCallback callback);

  Disposable GetImageIds(int merchId, ImageRemoteRepository.LoadListIntegerCallback callback);

}
