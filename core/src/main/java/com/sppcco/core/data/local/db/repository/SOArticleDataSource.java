package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.SOArticleDao;
import com.sppcco.core.data.model.SOArticle;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public class SOArticleDataSource implements SOArticleRepository {

  //private static volatile SOArticleDataSource INSTANCE;

  private SOArticleDao sOArticleDao;
  private AppExecutors appExecutors;

  @Inject
  public SOArticleDataSource(AppExecutors appExecutors, SOArticleDao sOArticleDao) {
    this.sOArticleDao = sOArticleDao;
    this.appExecutors = appExecutors;
  }

  /*public static SOArticleDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                @NonNull SOArticleDao sOArticleDao) {
    if (INSTANCE == null) {
      synchronized (SOArticleDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new SOArticleDataSource(appExecutors, sOArticleDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getSOArticles(@NonNull final GetSOArticlesCallback callback) {
    Runnable runnable = () -> {
      final List<SOArticle> sOArticles = sOArticleDao.getAllSOArticle();

      appExecutors.mainThread().execute(() -> {
        if (sOArticles != null)
          callback.onSOArticlesLoaded(sOArticles);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllSOArticlesBySOIdAndFPId(int soId, int fpId, @NonNull GetSOArticlesCallback callback) {
    Runnable runnable = () -> {
      final List<SOArticle> sOArticles = sOArticleDao.getAllSOArticlesBySOIdAndFPId(soId, fpId);

      appExecutors.mainThread().execute(() -> {
        if (sOArticles != null)
          callback.onSOArticlesLoaded(sOArticles);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSOArticle(final int sOArticleId, @NonNull final GetSOArticleCallback callback) {
    Runnable runnable = () -> {
      final SOArticle sOArticle = sOArticleDao.getSOArticleById(sOArticleId);

      appExecutors.mainThread().execute(() -> {
        if (sOArticle != null)
          callback.onSOArticleLoaded(sOArticle);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSOArticles(final List<SOArticle> sOArticles, @NonNull final InsertSOArticlesCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = sOArticleDao.insertSOArticles(sOArticles);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onSOArticlesInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSOArticle(final SOArticle sOArticle, @NonNull final InsertSOArticleCallback callback) {
    Runnable runnable = () -> {
      final long lSOArticleId = sOArticleDao.insertSOArticle(sOArticle);

      appExecutors.mainThread().execute(() -> {
        if (lSOArticleId != 0)
          callback.onSOArticleInserted(lSOArticleId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSOArticles(@NonNull final UpdateSOArticlesCallback callback, final SOArticle... sOArticles) {
    Runnable runnable = () -> {
      final int i = sOArticleDao.updateSOArticles(sOArticles);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSOArticlesUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSOArticle(final SOArticle sOArticle, @NonNull final UpdateSOArticleCallback callback) {
    Runnable runnable = () -> {
      final int i = sOArticleDao.updateSOArticle(sOArticle);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSOArticleUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSOArticles(@NonNull final DeleteSOArticlesCallback callback, final SOArticle... sOArticles) {
    Runnable runnable = () -> {
      final int i = sOArticleDao.deleteSOArticles(sOArticles);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSOArticlesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllSOArticle(@NonNull final DeleteAllSOArticleCallback callback) {
    Runnable runnable = () -> {
      final int i = sOArticleDao.deleteAllSOArticle();

      appExecutors.mainThread().execute(() -> {
        if (i >= 0)
          callback.onSOArticlesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSOArticleById(final int sOArticleId, @NonNull final DeleteSOArticleCallback callback) {
    Runnable runnable = () -> {
      final int i = sOArticleDao.deleteSOArticleById(sOArticleId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSOArticleDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getNextSOArticleId(@NonNull GetNextSOArticleIdCallback callback) {
    Runnable runnable = () -> {
      final int i = sOArticleDao.getNextSOArticleId();

      appExecutors.mainThread().execute(() -> {
        if (i < 0)
          callback.onDataNotAvailable();
        else
          callback.onNextSOArticleIdLoaded(i + 1);
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSOArticleCount(int SOId, int FPId, @NonNull final GetSOArticleCountCallback callback) {
    Runnable runnable = () -> {
      final int i = sOArticleDao.getSOArticleCount(SOId, FPId);

      appExecutors.mainThread().execute(() -> {
        if (i >= 0)
          callback.onSOArticleCountLoaded(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSOArticlesBySOId(int SOId, int FPId, @NonNull GetSOArticlesBySOIdCallback callback) {
    Runnable runnable = () -> {
      final List<SOArticle> sOArticles = sOArticleDao.getSOArticlesBySOId(SOId, FPId);

      appExecutors.mainThread().execute(() -> {
        if (sOArticles != null)
          callback.onSOArticleBySOIdLoaded(sOArticles);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSOArticleDetails(int FPId, @NonNull UpdateSOArticleDetailsCallback callback) {
    Runnable runnable = () -> {
      final int i = sOArticleDao.updateSOArticleDetails(FPId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSOArticleDetailsUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void existMerchInSO(int soId, int articleId, int merchId, int fpId, @NonNull GetCountMerchCallback callback) {
    Runnable runnable = () -> {
      final int i = sOArticleDao.getCountMerchInSP(soId, articleId, merchId, fpId);

      appExecutors.mainThread().execute(() -> {

        callback.onMerchCounted(i);
      });
    };
    appExecutors.diskIO().execute(runnable);
  }


}
