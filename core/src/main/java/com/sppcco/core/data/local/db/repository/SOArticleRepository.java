package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.SOArticle;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 *
 */

public interface SOArticleRepository {


  // ________________________________________ CRUD ________________________________________ //

  interface GetSOArticlesCallback {
    void onSOArticlesLoaded(List<SOArticle> sOArticles);
    void onDataNotAvailable();
  }

  void getSOArticles(@NonNull GetSOArticlesCallback callback);
  void getAllSOArticlesBySOIdAndFPId(int soId, int fpId, GetSOArticlesCallback getSOArticlesCallback);


  interface GetSOArticleCallback {
    void onSOArticleLoaded(SOArticle sOArticle);
    void onDataNotAvailable();
  }

  void getSOArticle(int sOArticleId, @NonNull GetSOArticleCallback callback);


  // Insert
  interface InsertSOArticlesCallback {
    void onSOArticlesInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertSOArticles(List<SOArticle> sOArticles, @NonNull InsertSOArticlesCallback callback);

  interface InsertSOArticleCallback {
    void onSOArticleInserted(long sOArticleId);
    void onDataNotAvailable();
  }

  void insertSOArticle(SOArticle sOArticle, @NonNull InsertSOArticleCallback callback);


  // Update

  interface UpdateSOArticlesCallback {
    void onSOArticlesUpdated(int i);
    void onDataNotAvailable();
  }

  void updateSOArticles(@NonNull UpdateSOArticlesCallback callback, SOArticle... sOArticles);


  interface UpdateSOArticleCallback {
    void onSOArticleUpdated(int i);
    void onDataNotAvailable();
  }

  void updateSOArticle(SOArticle sOArticle, @NonNull UpdateSOArticleCallback callback);

  // Delete


  interface DeleteSOArticlesCallback {
    void onSOArticlesDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSOArticles(@NonNull DeleteSOArticlesCallback callback, SOArticle... sOArticles);


  interface DeleteAllSOArticleCallback {
    void onSOArticlesDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllSOArticle(@NonNull DeleteAllSOArticleCallback callback);


  interface DeleteSOArticleCallback {
    void onSOArticleDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSOArticleById(int sOArticleId, @NonNull DeleteSOArticleCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetNextSOArticleIdCallback {
    void onNextSOArticleIdLoaded(int soArticleId);
    void onDataNotAvailable();
  }
  void getNextSOArticleId(@NonNull GetNextSOArticleIdCallback callback);

  interface GetSOArticleCountCallback {
    void onSOArticleCountLoaded(int count);
    void onDataNotAvailable();
  }
  void getSOArticleCount(int SOId, int FPId, @NonNull GetSOArticleCountCallback callback);


  interface GetSOArticlesBySOIdCallback {
    void onSOArticleBySOIdLoaded(List<SOArticle> sOArticles);
    void onDataNotAvailable();
  }
  void getSOArticlesBySOId(int SOId, int FPId, @NonNull GetSOArticlesBySOIdCallback callback);


  interface UpdateSOArticleDetailsCallback {
    void onSOArticleDetailsUpdated(int i);
    void onDataNotAvailable();
  }

  void updateSOArticleDetails(int FPId, @NonNull UpdateSOArticleDetailsCallback callback);

  interface GetCountMerchCallback {
    void onMerchCounted(int count);
  }
  void existMerchInSO(int soId, int articleId, int merchId, int fpId, @NonNull GetCountMerchCallback callback);

}
