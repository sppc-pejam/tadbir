package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.ImageDao;
import com.sppcco.core.data.model.Image;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class ImageDataSource implements ImageRepository {

  //private static volatile ImageDataSource INSTANCE;

  private ImageDao imageDao;
  private AppExecutors appExecutors;

  @Inject
  public ImageDataSource(AppExecutors appExecutors, ImageDao imageDao) {
    this.imageDao = imageDao;
    this.appExecutors = appExecutors;
  }

  /*public static ImageDataSource getInstance(@NonNull AppExecutors appExecutors,
                                            @NonNull ImageDao imageDao) {
    if (INSTANCE == null) {
      synchronized (ImageDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new ImageDataSource(appExecutors, imageDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getImages(@NonNull GetImagesCallback callback) {
    Runnable runnable = () -> {
      final List<Image> images = imageDao.getAllImage();

      appExecutors.mainThread().execute(() -> {
        if (images != null)
          callback.onBinAppendicesLoaded(images);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  /*@Override
  public void getImageCodeMap(int sysId, int formId, int fpid, @NonNull GetImageCodeMapCallback callback) {
    Runnable runnable = () -> {
      final HashMap<Integer, String> hashMap = imageDao.getImageCodeMap(sysId, formId, fpid);

      appExecutors.mainThread().execute(() -> {
        if(hashMap != null)
          callback.onBinAppendicesLoaded(hashMap);
        else
          callback.onFailure();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }*/

  @Override
  public void getImageByMerchId(int merchId, @NonNull GetImageByMerchIdCallback callback) {
    Runnable runnable = () -> {
      final Image image = imageDao.getImageByMerchId(merchId);

      appExecutors.mainThread().execute(() -> {
        if (image != null)
          callback.onImageLoaded(image);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getImageBySysId(int sysId, @NonNull GetImageBySysIdCallback callback) {
    Runnable runnable = () -> {
      final Image image = imageDao.getImageBySysId(sysId);

      appExecutors.mainThread().execute(() -> {
        if (image != null)
          callback.onImageLoaded(image);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getImageByFormId(int formId, @NonNull GetImageByFormIdCallback callback) {
    Runnable runnable = () -> {
      final Image image = imageDao.getImageByFormId(formId);

      appExecutors.mainThread().execute(() -> {
        if (image != null)
          callback.onImageLoaded(image);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getImageByMerchIdSysId(int merchId, int sysId, @NonNull GetImageByMerchIdSysIdCallback callback) {
    Runnable runnable = () -> {
      final Image image = imageDao.getImageByMerchIdSysId(merchId, sysId);

      appExecutors.mainThread().execute(() -> {
        if (image != null)
          callback.onImageLoaded(image);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getImageByMerchIdFormId(int merchId, int formId, @NonNull GetImageByMerchIdFormIdCallback callback) {
    Runnable runnable = () -> {
      final Image image = imageDao.getImageByMerchIdFormId(merchId, formId);

      appExecutors.mainThread().execute(() -> {
        if (image != null)
          callback.onImageLoaded(image);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getImageBySysIdFormId(int sysId, int formId, @NonNull GetImageBySysIdFormIdCallback callback) {
    Runnable runnable = () -> {
      final Image image = imageDao.getImageBySysIdFormId(sysId, formId);

      appExecutors.mainThread().execute(() -> {
        if (image != null)
          callback.onImageLoaded(image);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertImages(List<Image> images, @NonNull InsertImagesCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = imageDao.insertImages(images);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onImagesInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertImage(Image image, @NonNull InsertImageCallback callback) {
    Runnable runnable = () -> {
      final long lImageId = imageDao.insertImage(image);

      appExecutors.mainThread().execute(() -> {
        if (lImageId != 0)
          callback.onImageInserted(lImageId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateImages(@NonNull UpdateImagesCallback callback, Image... images) {
    Runnable runnable = () -> {
      final int i = imageDao.updateImages(images);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onImagesUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateImage(Image image, @NonNull UpdateImageCallback callback) {
    Runnable runnable = () -> {
      final int i = imageDao.updateImage(image);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onImageUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteImages(@NonNull DeleteImagesCallback callback, Image... images) {
    Runnable runnable = () -> {
      final int i = imageDao.deleteImages(images);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onImagesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllImage(@NonNull DeleteAllImageCallback callback) {
    Runnable runnable = () -> {
      final int i = imageDao.deleteAllImage();

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onImagesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteImageByMerchId(int merchId, @NonNull DeleteImageByMerchIdCallback callback) {
    Runnable runnable = () -> {
      final int i = imageDao.deleteImageByMerchId(merchId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onImageDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getHQImage(int merchId, int fpid, int sysId, int formId, @NonNull GetImageHQImageDataCallback callback) {
    Runnable runnable = () -> {
      final String image = imageDao.getHQImage(merchId, fpid, sysId, formId);

      appExecutors.mainThread().execute(() -> {
        if (image != null)
          callback.onImageHQImageDataLoaded(image);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getLQImage(int merchId, int fpid, int sysId, int formId, @NonNull GetImageLQThumbnailCallback callback) {
    Runnable runnable = () -> {
      final String image = imageDao.getLQImage(merchId, fpid, sysId, formId);

      appExecutors.mainThread().execute(() -> {
        if (image != null)
          callback.onImageLQThumbnailLoaded(image);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountImage(@NonNull GetCountImageCallback callback) {
    Runnable runnable = () -> {
      final int count = imageDao.getCountImage();

      appExecutors.mainThread().execute(() -> {
        if (count != -1)
          callback.onGetImageCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountImageByMerchId(int merchId, @NonNull GetCountImageCallback callback) {
    Runnable runnable = () -> {
      final int count = imageDao.getCountImageByMerchId(merchId);

      appExecutors.mainThread().execute(() -> {
        if (count != -1)
          callback.onGetImageCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllObjectId(@NonNull GetObjectIdesCallback callback) {
    Runnable runnable = () -> {
      final int[] objectIdes = imageDao.getAllObjectId();

      appExecutors.mainThread().execute(() -> {
        if (objectIdes != null)
          callback.onObjectIdesLoaded(objectIdes);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getThumbnailFormMerchandiseId(int merchId, int fpId, @NonNull GetThumbnailCallback callback) {
    Runnable runnable = () -> {
      final List<String> tumbnailList = imageDao.getThumbnailFormMerchandiseId(merchId, fpId);

      appExecutors.mainThread().execute(() -> {
        if (tumbnailList.size() != 0)
          callback.onGetThumbnailLoaded(tumbnailList);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }
}
