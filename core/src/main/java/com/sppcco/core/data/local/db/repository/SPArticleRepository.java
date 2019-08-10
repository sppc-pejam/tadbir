package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.SPArticle;

import java.util.List;

import androidx.annotation.NonNull;


public interface SPArticleRepository {


  // ________________________________________ CRUD ________________________________________ //

  interface GetSPArticlesCallback {
    void onSPArticlesLoaded(List<SPArticle> sPArticles);
    void onDataNotAvailable();
  }

  void getSPArticles(@NonNull GetSPArticlesCallback callback);
  void getAllSPArticlesBySPId(int spId, int fpId, GetSPArticlesCallback getSPArticlesCallback);


  interface GetSPArticleCallback {
    void onSPArticleLoaded(SPArticle sPArticle);
    void onDataNotAvailable();
  }

  void getSPArticle(int sPArticleId, @NonNull GetSPArticleCallback callback);


  // Insert
  interface InsertSPArticlesCallback {
    void onSPArticlesInserted(Long[] longs);
    void onDataNotAvailable();
  }

  void insertSPArticles(List<SPArticle> sPArticles, @NonNull InsertSPArticlesCallback callback);

  interface InsertSPArticleCallback {
    void onSPArticleInserted(long sPArticleId);
    void onDataNotAvailable();
  }

  void insertSPArticle(SPArticle sPArticle, @NonNull InsertSPArticleCallback callback);


  // Update

  interface UpdateSPArticlesCallback {
    void onSPArticlesUpdated(int i);
    void onDataNotAvailable();
  }

  void updateSPArticles(List<SPArticle> spArticles, @NonNull UpdateSPArticleCallback callback);

  void updateSPArticles(@NonNull UpdateSPArticlesCallback callback, SPArticle... sPArticles);


  interface UpdateSPArticleCallback {
    void onSPArticleUpdated(int i);
    void onDataNotAvailable();
  }

  void updateSPArticle(SPArticle sPArticle, @NonNull UpdateSPArticleCallback callback);

  // Delete


  interface DeleteSPArticlesCallback {
    void onSPArticlesDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSPArticles(@NonNull DeleteSPArticlesCallback callback, SPArticle... sPArticles);


  interface DeleteAllSPArticleCallback {
    void onSPArticlesDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllSPArticle(@NonNull DeleteAllSPArticleCallback callback);


  interface DeleteSPArticleCallback {
    void onSPArticleDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteSPArticleById(int sPArticleId, @NonNull DeleteSPArticleCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetNextSPArticleIdCallback {
    void onNextSPArticleIdLoaded(int spArticleId);
    void onDataNotAvailable();
  }
  void getNextSPArticleId(@NonNull GetNextSPArticleIdCallback callback);

  interface GetSPArticleCountCallback {
    void onSPArticleCountLoaded(int count);
    void onDataNotAvailable();
  }
  void getSPArticleCount(int SPId, int FPId, @NonNull GetSPArticleCountCallback callback);


  interface GetSPArticlesBySPIdCallback {
    void onSPArticleBySPIdLoaded(List<SPArticle> sPArticles);
    void onDataNotAvailable();
  }
  void getSPArticlesBySPId(int SPId, int FPId, @NonNull GetSPArticlesBySPIdCallback callback);


  interface UpdateSPArticleDetailsCallback {
    void onSPArticleDetailsUpdated(int i);
    void onDataNotAvailable();
  }

  void updateSPArticleDetails(int FPId, @NonNull UpdateSPArticleDetailsCallback callback);

  interface GetCountMerchCallback {
    void onMerchCounted(int count);
  }
  void existMerchInSP(int spId, int articleId, int merchId, int stockId, int fpId, @NonNull GetCountMerchCallback callback);

}
