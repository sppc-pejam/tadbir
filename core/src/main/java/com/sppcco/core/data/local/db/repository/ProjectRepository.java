package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.model.Project;
import com.sppcco.core.data.sub_model.AccVectorInfo;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public interface ProjectRepository {

  // ________________________________________ CRUD ________________________________________ //

  interface GetProjectsCallback {
    void onProjectsLoaded(List<Project> projects);
    void onDataNotAvailable();
  }
  void getProjects(@NonNull GetProjectsCallback callback);


  interface GetProjectCallback {
    void onProjectLoaded(Project project);
    void onDataNotAvailable();
  }
  void getProject(int projectId, @NonNull GetProjectCallback callback);

  interface GetProjectByPCodeCallback {
    void onProjectLoaded(Project project);
    void onDataNotAvailable();
  }
  void getProjectByPCode(int pCode, @NonNull GetProjectByPCodeCallback callback);


  // Insert
  interface InsertProjectsCallback {
    void onProjectsInserted(Long[] longs);
    void onDataNotAvailable();
  }
  void insertProjects(List<Project> projects, @NonNull InsertProjectsCallback callback);

  interface InsertProjectCallback {
    void onProjectInserted(long projectId);
    void onDataNotAvailable();
  }
  void insertProject(Project project, @NonNull InsertProjectCallback callback);


  // Update

  interface UpdateProjectsCallback {
    void onProjectsUpdated(int i);
    void onDataNotAvailable();
  }
  void updateProjects(@NonNull UpdateProjectsCallback callback, Project... projects);


  interface UpdateProjectCallback {
    void onProjectUpdated(int i);
    void onDataNotAvailable();
  }
  void updateProject(Project project, @NonNull UpdateProjectCallback callback);

  // Delete


  interface DeleteProjectsCallback {
    void onProjectsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteProjects(@NonNull DeleteProjectsCallback callback, Project... projects);


  interface DeleteAllProjectCallback {
    void onProjectsDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteAllProject(@NonNull DeleteAllProjectCallback callback);


  interface DeleteProjectCallback {
    void onProjectDeleted(int i);
    void onDataNotAvailable();
  }
  void deleteProjectById(int projectId, @NonNull DeleteProjectCallback callback);

  // ________________________________________ CRUD ________________________________________ //

  // Other Method

  interface GetProjectNameCallback {
    void onProjectNameLoaded(String projectName);
    void onDataNotAvailable();
  }
  void getProjectNameFromProjectId(int projectId, @NonNull GetProjectNameCallback callback);

  // Count(*)
  interface GetCountProjectCallback {
    void onProjectCounted(int count);
    void onDataNotAvailable();
  }
  void getCountProject(@NonNull GetCountProjectCallback callback);

  interface GetCountProjectByFullIdCallback {
    void onProjectCounted(int count);
    void onDataNotAvailable();
  }
  void getCountProjectByFullId(String fullId, @NonNull GetCountProjectByFullIdCallback callback);

  interface GetProjectVectorInfoByPCodeCallback {
    void VectorInfo(AccVectorInfo vectorInfo);
    void onDataNotAvailable();
  }
  void getProjectVectorInfoByPCode(int pCode, @NonNull GetProjectVectorInfoByPCodeCallback callback);

  interface GetProjectVectorInfoByIdCallback {
    void VectorInfo(AccVectorInfo vectorInfo);
    void onDataNotAvailable();
  }
  void getProjectVectorInfoById(int Id, @NonNull GetProjectVectorInfoByIdCallback callback);

}
