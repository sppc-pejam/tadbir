package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.SPArticleDao;
import com.sppcco.core.data.model.SPArticle;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;


public class SPArticleDataSource implements SPArticleRepository {

  //private static volatile SPArticleDataSource INSTANCE;

  private SPArticleDao sPArticleDao;
  private AppExecutors appExecutors;

  @Inject
  public SPArticleDataSource(AppExecutors appExecutors, SPArticleDao sPArticleDao) {
    this.sPArticleDao = sPArticleDao;
    this.appExecutors = appExecutors;
  }

  /*public static SPArticleDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                @NonNull SPArticleDao sPArticleDao) {
    if (INSTANCE == null) {
      synchronized (SPArticleDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new SPArticleDataSource(appExecutors, sPArticleDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getSPArticles(@NonNull final GetSPArticlesCallback callback) {
    Runnable runnable = () -> {
      final List<SPArticle> sPArticles = sPArticleDao.getAllSPArticle();

      appExecutors.mainThread().execute(() -> {
        if (sPArticles != null)
          callback.onSPArticlesLoaded(sPArticles);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getAllSPArticlesBySPId(int spId, int fpId, @NonNull GetSPArticlesCallback callback) {
    Runnable runnable = () -> {
      final List<SPArticle> sPArticles = sPArticleDao.getAllSPArticlesBySPIdAndFPId(spId, fpId);

      appExecutors.mainThread().execute(() -> {
        if (sPArticles != null)
          callback.onSPArticlesLoaded(sPArticles);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSPArticle(final int sPArticleId, @NonNull final GetSPArticleCallback callback) {
    Runnable runnable = () -> {
      final SPArticle sPArticle = sPArticleDao.getSPArticleById(sPArticleId);

      appExecutors.mainThread().execute(() -> {
        if (sPArticle != null)
          callback.onSPArticleLoaded(sPArticle);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSPArticles(final List<SPArticle> sPArticles, @NonNull final InsertSPArticlesCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = sPArticleDao.insertSPArticles(sPArticles);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onSPArticlesInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertSPArticle(final SPArticle sPArticle, @NonNull final InsertSPArticleCallback callback) {
    Runnable runnable = () -> {
      final long lSPArticleId = sPArticleDao.insertSPArticle(sPArticle);

      appExecutors.mainThread().execute(() -> {
        if (lSPArticleId != 0)
          callback.onSPArticleInserted(lSPArticleId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSPArticles(List<SPArticle> spArticles, @NonNull UpdateSPArticleCallback callback) {
    Runnable runnable = () -> {
      final int i = sPArticleDao.updateSPArticles(spArticles);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSPArticleUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSPArticles(@NonNull final UpdateSPArticlesCallback callback, final SPArticle... sPArticles) {
    Runnable runnable = () -> {
      final int i = sPArticleDao.updateSPArticles(sPArticles);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSPArticlesUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSPArticle(final SPArticle sPArticle, @NonNull final UpdateSPArticleCallback callback) {
    Runnable runnable = () -> {
      final int i = sPArticleDao.updateSPArticle(sPArticle);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSPArticleUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSPArticles(@NonNull final DeleteSPArticlesCallback callback, final SPArticle... sPArticles) {
    Runnable runnable = () -> {
      final int i = sPArticleDao.deleteSPArticles(sPArticles);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSPArticlesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllSPArticle(@NonNull final DeleteAllSPArticleCallback callback) {
    Runnable runnable = () -> {
      final int i = sPArticleDao.deleteAllSPArticle();

      appExecutors.mainThread().execute(() -> {
        if (i >= 0)
          callback.onSPArticlesDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteSPArticleById(final int sPArticleId, @NonNull final DeleteSPArticleCallback callback) {
    Runnable runnable = () -> {
      final int i = sPArticleDao.deleteSPArticleById(sPArticleId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSPArticleDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getNextSPArticleId(@NonNull GetNextSPArticleIdCallback callback) {
    Runnable runnable = () -> {
      final int i = sPArticleDao.getNextSPArticleId();

      appExecutors.mainThread().execute(() -> {
        if (i < 0)
          callback.onDataNotAvailable();
        else
          callback.onNextSPArticleIdLoaded(i + 1);
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSPArticleCount(int SPId, int FPId, @NonNull final GetSPArticleCountCallback callback) {
    Runnable runnable = () -> {
      final int i = sPArticleDao.getSPArticleCount(SPId, FPId);

      appExecutors.mainThread().execute(() -> {
        if (i >= 0)
          callback.onSPArticleCountLoaded(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getSPArticlesBySPId(int SPId, int FPId, @NonNull GetSPArticlesBySPIdCallback callback) {
    Runnable runnable = () -> {
      final List<SPArticle> sPArticles = sPArticleDao.getSPArticlesBySPId(SPId, FPId);

      appExecutors.mainThread().execute(() -> {
        if (sPArticles != null)
          callback.onSPArticleBySPIdLoaded(sPArticles);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateSPArticleDetails(int FPId, @NonNull UpdateSPArticleDetailsCallback callback) {
    Runnable runnable = () -> {
      final int i = sPArticleDao.updateSPArticleDetails(FPId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onSPArticleDetailsUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void existMerchInSP(int spId, int articleId, int merchId, int stockId, int fpId, @NonNull GetCountMerchCallback callback) {
    Runnable runnable = () -> {
      final int i = sPArticleDao.getCountMerchInSP(spId, articleId, merchId, stockId, fpId);

      appExecutors.mainThread().execute(() -> {

        callback.onMerchCounted(i);
      });
    };
    appExecutors.diskIO().execute(runnable);
  }


}
