package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.ErrorStatusDao;
import com.sppcco.core.data.model.ErrorStatus;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 06/18/18.
 *
 */

public class ErrorStatusDataSource implements ErrorStatusRepository {

  //private static volatile ErrorStatusDataSource INSTANCE;

  private ErrorStatusDao errorStatusDao;
  private AppExecutors appExecutors;

  @Inject
  public ErrorStatusDataSource(AppExecutors appExecutors, ErrorStatusDao errorStatusDao) {
    this.errorStatusDao = errorStatusDao;
    this.appExecutors = appExecutors;
  }

  /*public static ErrorStatusDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                  @NonNull ErrorStatusDao errorStatusDao) {
    if (INSTANCE == null) {
      synchronized (ErrorStatusDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new ErrorStatusDataSource(appExecutors, errorStatusDao);
        }
      }
    }
    return INSTANCE;
  }*/


  @Override
  public void getErrorStatuses(@NonNull GetErrorStatusesCallback callback) {
    Runnable runnable = () -> {
      final List<ErrorStatus> errorStatuses = errorStatusDao.getAllErrorStatus();

      appExecutors.mainThread().execute(() -> {
        if (errorStatuses != null)
          callback.onErrorStatuesLoaded(errorStatuses);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getErrorStatusByIdAndArticleId(int id, int articleId, int docType, @NonNull GetErrorStatusesCallback callback) {
    Runnable runnable = () -> {
      final List<ErrorStatus> errorStatuses = errorStatusDao.getErrorStatusByIdAndArticleId(id, articleId, docType);

      appExecutors.mainThread().execute(() -> {
        if (errorStatuses != null)
          callback.onErrorStatuesLoaded(errorStatuses);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getErrorStatusById(int id, int docType, @NonNull GetErrorStatusesCallback callback) {
    Runnable runnable = () -> {
      final List<ErrorStatus> errorStatuses = errorStatusDao.getErrorStatusById(id, docType);

      appExecutors.mainThread().execute(() -> {
        if (errorStatuses != null)
          callback.onErrorStatuesLoaded(errorStatuses);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getErrorStatusDocId(int id, int docType, @NonNull GetErrorStatusesCallback callback) {
    Runnable runnable = () -> {
      final List<ErrorStatus> errorStatuses = errorStatusDao.getErrorStatusDocId(id, docType);

      appExecutors.mainThread().execute(() -> {
        if (errorStatuses != null)
          callback.onErrorStatuesLoaded(errorStatuses);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getErrorStatusDocArticleId(int id, int docType, @NonNull GetErrorStatusesCallback callback) {
    Runnable runnable = () -> {
      final List<ErrorStatus> errorStatuses = errorStatusDao.getErrorStatusDocArticleId(id, docType);

      appExecutors.mainThread().execute(() -> {
        if (errorStatuses != null)
          callback.onErrorStatuesLoaded(errorStatuses);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertErrorStatuses(List<ErrorStatus> errorStatuses, @NonNull InsertErrorStatusesCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = errorStatusDao.insertErrorStatuses(errorStatuses);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onErrorStatusesInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertErrorStatus(ErrorStatus errorStatus, @NonNull InsertErrorStatusCallback callback) {
    Runnable runnable = () -> {
      final long lUnitId = errorStatusDao.insertErrorStatus(errorStatus);

      appExecutors.mainThread().execute(() -> {
        if (lUnitId != 0)
          callback.onErrorStatusInserted(lUnitId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateErrorStatuses(@NonNull UpdateErrorStatusesCallback callback, ErrorStatus... errorStatuses) {
    Runnable runnable = () -> {
      final int i = errorStatusDao.updateErrorStatuses(errorStatuses);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onErrorStatusesUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateErrorStatus(ErrorStatus errorStatus, @NonNull UpdateErrorStatusCallback callback) {
    Runnable runnable = () -> {
      final int i = errorStatusDao.updateErrorStatus(errorStatus);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onErrorStatusUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteErrorStatuses(@NonNull DeleteErrorStatusesCallback callback, ErrorStatus... errorStatuses) {
    Runnable runnable = () -> {
      final int i = errorStatusDao.deleteErrorStatuses(errorStatuses);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onErrorStatusesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllErrorStatus(@NonNull DeleteErrorStatusesCallback callback) {
    Runnable runnable = () -> {
      final int i = errorStatusDao.deleteAllErrorStatus();

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onErrorStatusesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteErrorStatusByIdAndArticleId(int id, int articleId, int docType, @NonNull DeleteErrorStatusesCallback callback) {
    Runnable runnable = () -> {
      final int i = errorStatusDao.deleteErrorStatusByIdAndArticleId(id, articleId, docType);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onErrorStatusesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteErrorStatusById(int id, int docType, @NonNull DeleteErrorStatusesCallback callback) {
    Runnable runnable = () -> {
      final int i = errorStatusDao.deleteErrorStatusById(id, docType);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onErrorStatusesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getErrorStatusCount(@NonNull GetErrorStatusCountCallback callback) {
    Runnable runnable = () -> {
      final int count = errorStatusDao.getErrorStatusCount();

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onErrorStatusCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getErrorStatusCountByIdAndArticleId(int id, int articleId, int docType, @NonNull GetErrorStatusCountCallback callback) {
    Runnable runnable = () -> {
      final int count = errorStatusDao.getErrorStatusCountByIdAndArticleId(id, articleId, docType);

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onErrorStatusCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getErrorStatusCountById(int id, int docType, @NonNull GetErrorStatusCountCallback callback) {
    Runnable runnable = () -> {
      final int count = errorStatusDao.getErrorStatusCountById(id, docType);

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onErrorStatusCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getErrorStatusCountByDocId(int id, int docType, @NonNull GetErrorStatusCountCallback callback) {
    Runnable runnable = () -> {
      final int count = errorStatusDao.getErrorStatusCountByDocId(id, docType);

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onErrorStatusCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getErrorStatusCountByDocArticleId(int id, int docType, @NonNull GetErrorStatusCountCallback callback) {
    Runnable runnable = () -> {
      final int count = errorStatusDao.getErrorStatusCountByDocArticleId(id, docType);

      appExecutors.mainThread().execute(() -> {
        if(count != -1)
          callback.onErrorStatusCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }
}
