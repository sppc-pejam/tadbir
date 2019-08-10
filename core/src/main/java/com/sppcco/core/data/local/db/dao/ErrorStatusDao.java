package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.ErrorStatus;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by m_pejam on 01/04/18.
 *
 */

@Dao
public interface ErrorStatusDao {

  // Get

  @Query("SELECT * FROM __ErrorStatus__")
  List<ErrorStatus> getAllErrorStatus();

  @Query("SELECT * FROM __ErrorStatus__ WHERE Id = :id AND ArticleId = :articleId AND DocType = :docType")
  List<ErrorStatus> getErrorStatusByIdAndArticleId(int id, int articleId, int docType);

  @Query("SELECT * FROM __ErrorStatus__ WHERE Id = :id AND DocType = :docType")
  List<ErrorStatus> getErrorStatusById(int id, int docType);

  @Query("SELECT * FROM __ErrorStatus__ WHERE Id = :id AND ArticleId = 0 AND DocType = :docType")
  List<ErrorStatus> getErrorStatusDocId(int id, int docType);

  @Query("SELECT * FROM __ErrorStatus__ WHERE Id = :id AND ArticleId <> 0 AND DocType = :docType")
  List<ErrorStatus> getErrorStatusDocArticleId(int id, int docType);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertErrorStatuses(List<ErrorStatus> errorStatuses);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertErrorStatus(ErrorStatus errorStatus);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateErrorStatuses(ErrorStatus... errorStatuses);

  @Update
  int updateErrorStatus(ErrorStatus errorStatus);

  // Delete

  @Delete
  int deleteErrorStatuses(ErrorStatus... errorStatuses);

  @Query("DELETE FROM __ErrorStatus__")
  int deleteAllErrorStatus();

  @Query("DELETE FROM __ErrorStatus__ WHERE Id = :id AND ArticleId = :articleId AND DocType = :docType")
  int deleteErrorStatusByIdAndArticleId(int id, int articleId, int docType);

  @Query("DELETE FROM __ErrorStatus__ WHERE Id = :id AND DocType = :docType")
  int deleteErrorStatusById(int id, int docType);

  // Other Method

  @Query("SELECT COUNT(*) FROM __ErrorStatus__")
  int getErrorStatusCount();

  @Query("SELECT COUNT(*) FROM __ErrorStatus__ WHERE Id = :id AND ArticleId = :articleId AND DocType = :docType")
  int getErrorStatusCountByIdAndArticleId(int id, int articleId, int docType);

  @Query("SELECT COUNT(*) FROM __ErrorStatus__ WHERE Id = :id AND DocType = :docType")
  int getErrorStatusCountById(int id, int docType);

  @Query("SELECT COUNT(*) FROM __ErrorStatus__ WHERE Id = :id AND ArticleId = 0 AND DocType = :docType")
  int getErrorStatusCountByDocId(int id, int docType);

  @Query("SELECT COUNT(*) FROM __ErrorStatus__ WHERE Id = :id AND ArticleId <> 0 AND DocType = :docType")
  int getErrorStatusCountByDocArticleId(int id, int docType);
}