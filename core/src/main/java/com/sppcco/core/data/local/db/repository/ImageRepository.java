package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.Image;

import java.util.List;

import androidx.annotation.NonNull;

public interface ImageRepository {

  interface GetImagesCallback {
    void onBinAppendicesLoaded(List<Image> images);

    void onDataNotAvailable();
  }

  void getImages(@NonNull ImageRepository.GetImagesCallback callback);

  /*interface GetImageCodeMapCallback {
    void onBinAppendicesLoaded(ArrayMap<Integer, String> hashMap);
    void onFailure();
  }

  void getImageCodeMap(int sysId, int formId, int fpid, @NonNull ImageRepository.GetImageCodeMapCallback callback);*/


  interface GetImageByMerchIdCallback {
    void onImageLoaded(Image image);

    void onDataNotAvailable();
  }

  void getImageByMerchId(int merchId, @NonNull ImageRepository.GetImageByMerchIdCallback callback);

  interface GetImageBySysIdCallback {
    void onImageLoaded(Image image);

    void onDataNotAvailable();
  }

  void getImageBySysId(int sysId, @NonNull ImageRepository.GetImageBySysIdCallback callback);

  interface GetImageByFormIdCallback {
    void onImageLoaded(Image image);

    void onDataNotAvailable();
  }

  void getImageByFormId(int formId, @NonNull ImageRepository.GetImageByFormIdCallback callback);


  interface GetImageByMerchIdSysIdCallback {
    void onImageLoaded(Image image);

    void onDataNotAvailable();
  }

  void getImageByMerchIdSysId(int merchId, int sysId, @NonNull ImageRepository.GetImageByMerchIdSysIdCallback callback);

  interface GetImageByMerchIdFormIdCallback {
    void onImageLoaded(Image image);

    void onDataNotAvailable();
  }

  void getImageByMerchIdFormId(int merchId, int formId, @NonNull ImageRepository.GetImageByMerchIdFormIdCallback callback);

  interface GetImageBySysIdFormIdCallback {
    void onImageLoaded(Image image);

    void onDataNotAvailable();
  }

  void getImageBySysIdFormId(int sysId, int formId, @NonNull ImageRepository.GetImageBySysIdFormIdCallback callback);

  // Insert
  interface InsertImagesCallback {
    void onImagesInserted(Long[] longs);

    void onDataNotAvailable();
  }

  void insertImages(List<Image> images, @NonNull ImageRepository.InsertImagesCallback callback);

  interface InsertImageCallback {
    void onImageInserted(long accountId);

    void onDataNotAvailable();
  }

  void insertImage(Image image, @NonNull ImageRepository.InsertImageCallback callback);


  // Update

  interface UpdateImagesCallback {
    void onImagesUpdated(int i);

    void onDataNotAvailable();
  }

  void updateImages(@NonNull ImageRepository.UpdateImagesCallback callback, Image... images);


  interface UpdateImageCallback {
    void onImageUpdated(int i);

    void onDataNotAvailable();
  }

  void updateImage(Image image, @NonNull ImageRepository.UpdateImageCallback callback);

  // Delete


  interface DeleteImagesCallback {
    void onImagesDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteImages(@NonNull ImageRepository.DeleteImagesCallback callback, Image... images);


  interface DeleteAllImageCallback {
    void onImagesDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteAllImage(@NonNull ImageRepository.DeleteAllImageCallback callback);


  interface DeleteImageByMerchIdCallback {
    void onImageDeleted(int i);

    void onDataNotAvailable();
  }

  void deleteImageByMerchId(int merchId, @NonNull ImageRepository.DeleteImageByMerchIdCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetImageHQImageDataCallback {
    void onImageHQImageDataLoaded(String ImageData);

    void onDataNotAvailable();
  }

  void getHQImage(int merchId, int fpid, int sysId, int formId, @NonNull ImageRepository.GetImageHQImageDataCallback callback);

  interface GetImageLQThumbnailCallback {
    void onImageLQThumbnailLoaded(String Thumbnail);

    void onDataNotAvailable();
  }

  void getLQImage(int merchId, int fpid, int sysId, int formId, @NonNull ImageRepository.GetImageLQThumbnailCallback callback);

  // Count(*)
  interface GetCountImageCallback {
    void onGetImageCounted(int count);

    void onDataNotAvailable();
  }

  void getCountImage(@NonNull ImageRepository.GetCountImageCallback callback);

  void getCountImageByMerchId(int merchId, @NonNull ImageRepository.GetCountImageCallback callback);

  interface GetObjectIdesCallback {
    void onObjectIdesLoaded(int[] objectIdes);

    void onDataNotAvailable();
  }

  void getAllObjectId(@NonNull GetObjectIdesCallback callback);

  interface GetThumbnailCallback {
    void onGetThumbnailLoaded(List<String> thumbnailL);

    void onDataNotAvailable();
  }

  void getThumbnailFormMerchandiseId(int merchId, int fpId, @NonNull GetThumbnailCallback callback);

}
