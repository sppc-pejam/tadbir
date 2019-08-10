package com.sppcco.core.data.local.db.dao;

import com.sppcco.core.data.model.Project;
import com.sppcco.core.data.sub_model.AccVectorInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.CompletableSource;
import io.reactivex.Single;

/**
 * Created by m_pejam on 01/16/18.
 */
@Dao
public interface ProjectDao {

  // Get

  @Query("SELECT * FROM __Project__")
  List<Project> getAllProject();

  @Query("SELECT * FROM __Project__ WHERE _id = :projectId")
  Project getProjectById(int projectId);

  @Query("SELECT * FROM __Project__ WHERE PCode = :pCode")
  Project getProjectByPCode(int pCode);

  //@Query("SELECT * FROM __Project__ WHERE PCode = :pCode")
  @Query("SELECT * FROM __Project__ WHERE PCode = :pCode UNION ALL " +
    "SELECT 0 AS Id, 0 AS PCode, '' AS Name, '1900-01-01 00:00:00.000' AS StartDate, '1900-01-01 00:00:00.000' AS EndDate " +
    ",0 AS Progress, '' AS Mojri, '' AS KarFarma, '' AS PDesc,0 AS Debit, 0 AS Credit, 0 AS Budget, 0 AS FPId, 0 AS CurrencyBudget, 0 AS CurrencyVal " +
    ",0 AS CurrencyId WHERE :pCode = 0")
  Single<Project> getRXProjectByPCode(int pCode);

  // Insert

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Long[] insertProjects(List<Project> projects);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  CompletableSource insertRXProjects(List<Project> projects);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insertProject(Project project);

  // Update

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int updateProjects(Project... projects);

  @Update
  int updateProject(Project project);

  // Delete

  @Delete
  int deleteProjects(Project... projects);

  @Query("DELETE FROM __Project__")
  int deleteAllProject();

  @Query("DELETE FROM __Project__ WHERE _id = :projectId")
  int deleteProjectById(int projectId);

  // Other Method

  @Query("SELECT Name FROM __Project__ WHERE _id = :projectId ")
  String getProjectNameFromProjectId(int projectId);

  @Query("SELECT COUNT(*) FROM __Project__")
  int getCountProject();

  @Query("SELECT COUNT(*) FROM (SELECT PCode AS code, Name AS accountName, _id AS parentAccount FROM __Project__ INNER JOIN __AccVsPrj__ " +
    "ON ( [__Project__]._id = [__AccVsPrj__].PrjId AND [__Project__].FPId = [__AccVsPrj__].FPId ) " +
    "WHERE FullId = :fullId)")
  int getCountProjectByFullId(String fullId);

  @Query("SELECT PCode AS code, Name AS accountName, _id AS parentAccount FROM __Project__ INNER JOIN __AccVsPrj__ " +
    "ON ( [__Project__]._id = [__AccVsPrj__].PrjId AND [__Project__].FPId = [__AccVsPrj__].FPId ) " +
    "WHERE PCode = :pCode")
  AccVectorInfo getProjectVectorInfoByPCode(int pCode);

  @Query("SELECT PCode AS code, Name AS accountName, _id AS parentAccount FROM __Project__ INNER JOIN __AccVsPrj__ " +
    "ON ( [__Project__]._id = [__AccVsPrj__].PrjId AND [__Project__].FPId = [__AccVsPrj__].FPId ) " +
    "WHERE [__Project__]._id = :Id")
  AccVectorInfo getProjectVectorInfoById(int Id);

}
