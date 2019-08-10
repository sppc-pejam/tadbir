package com.sppcco.core.data.local.db.dao;


import com.sppcco.core.data.model.SPArticle;

import java.util.List;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

//import androidx.paging.LivePagedListProvider;

@Dao
public interface SPArticleDao {

  // Get

  @Query("SELECT * FROM __SPArticle__")
  List<SPArticle> getAllSPArticle();

  @Query("SELECT * FROM __SPArticle__ WHERE SPID = :spId AND FPID = :fpId ORDER BY _id")
  DataSource.Factory<Integer, SPArticle> getAllSPArticlesBySPId(int spId, int fpId);

  @Query("SELECT * FROM __SPArticle__ WHERE SPID = :spId OR FPID = :fpId")
  List<SPArticle> getAllSPArticlesBySPIdAndFPId(int spId, int fpId);

  @Query("SELECT * FROM __SPArticle__ WHERE _id = :spArticleId")
  SPArticle getSPArticleById(int spArticleId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Long[] insertSPArticles(List<SPArticle> spArticles);

  @Insert
  long insertSPArticle(SPArticle spArticle);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateSPArticles(List<SPArticle> spArticles);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateSPArticles(SPArticle... spArticles);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateSPArticle(SPArticle spArticle);

  // Delete

  @Delete
  int deleteSPArticles(SPArticle... spArticles);

  @Query("DELETE FROM __SPArticle__")
  int deleteAllSPArticle();

  @Query("DELETE FROM __SPArticle__ WHERE _id = :spArticleId")
  int deleteSPArticleById(int spArticleId);

  // Other Method

  @Query("SELECT MAX(_id) FROM __SPArticle__")
  int getNextSPArticleId();

  @Query("SELECT COUNT(_id) FROM __SPArticle__ WHERE SPId = :SPId AND FPId = :FPId")
  int getSPArticleCount(int SPId, int FPId);

  @Query("SELECT * FROM __SPArticle__ WHERE SPId = :SPId AND FPId = :FPId")
  List<SPArticle> getSPArticlesBySPId(int SPId, int FPId);

  @Query("UPDATE __SPArticle__ SET " +
    "[MerchandiseName] = (SELECT Name FROM __Merchandise__  WHERE _id = __SPArticle__.MerchandiseId AND FPId = :FPId), " +
    "[MerchandiseCode] = (SELECT Code FROM __Merchandise__  WHERE _id = __SPArticle__.MerchandiseId AND FPId = :FPId), " +
    "[UnitName] = (SELECT Name FROM __Unit__  WHERE _id = __SPArticle__.UnitId AND FPId = :FPId) ")
  int updateSPArticleDetails(int FPId);

  @Query("SELECT COUNT(_id) FROM __SPArticle__ WHERE SPId = :spId AND MerchandiseId = :merchId AND " +
    " StockRoomId = :stockId AND FPId = :fpId  AND _id <> :articleId")
  int getCountMerchInSP(int spId, int articleId, int merchId, int stockId, int fpId);

}
