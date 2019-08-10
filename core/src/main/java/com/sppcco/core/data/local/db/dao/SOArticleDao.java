package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.SOArticle;

import java.util.List;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by m_pejam on 01/16/18.
 *
 */
@Dao
public interface SOArticleDao {

  // Get

  @Query("SELECT * FROM __SOArticle__")
  List<SOArticle> getAllSOArticle();

  @Query("SELECT * FROM __SOArticle__ WHERE SOID = :soId AND FPID = :fpId ORDER BY _id")
  DataSource.Factory<Integer, SOArticle> getAllSOArticlesBySOId(int soId, int fpId);

  @Query("SELECT * FROM __SOArticle__ WHERE SOID = :soId AND FPID = :fpId")
  List<SOArticle> getAllSOArticlesBySOIdAndFPId(int soId, int fpId);

  @Query("SELECT * FROM __SOArticle__ WHERE _id = :sOArticleId")
  SOArticle getSOArticleById(int sOArticleId);

  // Insert

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Long[] insertSOArticles(List<SOArticle> soArticles);

  @Insert
  long insertSOArticle(SOArticle soArticle);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateSOArticles(SOArticle... soArticles);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateSOArticle(SOArticle soArticle);

  // Delete

  @Delete
  int deleteSOArticles(SOArticle... soArticles);

  @Query("DELETE FROM __SOArticle__")
  int deleteAllSOArticle();

  @Query("DELETE FROM __SOArticle__ WHERE _id = :sOArticleId")
  int deleteSOArticleById(int sOArticleId);

  // Other Method

  @Query("SELECT MAX(_id) FROM __SOArticle__")
  int getNextSOArticleId();

  @Query("SELECT COUNT(_id) FROM __SOArticle__ WHERE SOId = :SOId AND FPId = :FPId")
  int getSOArticleCount(int SOId, int FPId);

  @Query("SELECT * FROM __SOArticle__ WHERE SOId = :SOId AND FPId = :FPId")
  List<SOArticle> getSOArticlesBySOId(int SOId, int FPId);

  @Query("UPDATE __SOArticle__ SET " +
    "[MerchandiseName] = (SELECT Name FROM __Merchandise__  WHERE _id = __SOArticle__.MerchandiseId AND FPId = :FPId), " +
    "[MerchandiseCode] = (SELECT Code FROM __Merchandise__  WHERE _id = __SOArticle__.MerchandiseId AND FPId = :FPId), " +
    "[UnitName] = (SELECT Name FROM __Unit__  WHERE _id = __SOArticle__.UnitId AND FPId = :FPId) ")
  int updateSOArticleDetails(int FPId);

  @Query("SELECT COUNT(_id) FROM __SOArticle__ WHERE SOId = :soId AND MerchandiseId = :merchId AND " +
    " FPId = :fpId  AND _id <> :articleId")
  int getCountMerchInSP(int soId, int articleId, int merchId, int fpId);

}
