package com.sppcco.core.data.local.db.repository;

import com.sppcco.core.data.local.db.dao.ProjectDao;
import com.sppcco.core.data.model.Project;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.util.app.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/16/18.
 */

public class ProjectDataSource implements ProjectRepository {

  //private static volatile ProjectDataSource INSTANCE;

  private ProjectDao projectDao;
  private AppExecutors appExecutors;

  @Inject
  public ProjectDataSource(AppExecutors appExecutors, ProjectDao projectDao) {
    this.projectDao = projectDao;
    this.appExecutors = appExecutors;
  }

  /*public static ProjectDataSource getInstance(@NonNull AppExecutors appExecutors,
                                              @NonNull ProjectDao projectDao) {
    if (INSTANCE == null) {
      synchronized (ProjectDataSource.class) {
        if (INSTANCE == null) {
          INSTANCE = new ProjectDataSource(appExecutors, projectDao);
        }
      }
    }
    return INSTANCE;
  }*/

  @Override
  public void getProjects(@NonNull final GetProjectsCallback callback) {
    Runnable runnable = () -> {
      final List<Project> projects = projectDao.getAllProject();

      appExecutors.mainThread().execute(() -> {
        if (projects != null)
          callback.onProjectsLoaded(projects);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getProject(final int projectId, @NonNull final GetProjectCallback callback) {
    Runnable runnable = () -> {
      final Project project = projectDao.getProjectById(projectId);

      appExecutors.mainThread().execute(() -> {
        if (project != null)
          callback.onProjectLoaded(project);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getProjectByPCode(int pCode, @NonNull GetProjectByPCodeCallback callback) {
    Runnable runnable = () -> {
      final Project project = projectDao.getProjectByPCode(pCode);

      appExecutors.mainThread().execute(() -> {
        if (project != null)
          callback.onProjectLoaded(project);
        else
          callback.onDataNotAvailable();
      });
    };

    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertProjects(final List<Project> projects, @NonNull final InsertProjectsCallback callback) {
    Runnable runnable = () -> {
      final Long[] longs = projectDao.insertProjects(projects);

      appExecutors.mainThread().execute(() -> {
        if (longs != null)
          callback.onProjectsInserted(longs);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void insertProject(final Project project, @NonNull final InsertProjectCallback callback) {
    Runnable runnable = () -> {
      final long lProjectId = projectDao.insertProject(project);

      appExecutors.mainThread().execute(() -> {
        if (lProjectId != 0)
          callback.onProjectInserted(lProjectId);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateProjects(@NonNull final UpdateProjectsCallback callback, final Project... projects) {
    Runnable runnable = () -> {
      final int i = projectDao.updateProjects(projects);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onProjectsUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void updateProject(final Project project, @NonNull final UpdateProjectCallback callback) {
    Runnable runnable = () -> {
      final int i = projectDao.updateProject(project);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onProjectUpdated(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteProjects(@NonNull final DeleteProjectsCallback callback, final Project... projects) {
    Runnable runnable = () -> {
      final int i = projectDao.deleteProjects(projects);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onProjectsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteAllProject(@NonNull final DeleteAllProjectCallback callback) {
    Runnable runnable = () -> {
      final int i = projectDao.deleteAllProject();

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onProjectsDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void deleteProjectById(final int projectId, @NonNull final DeleteProjectCallback callback) {
    Runnable runnable = () -> {
      final int i = projectDao.deleteProjectById(projectId);

      appExecutors.mainThread().execute(() -> {
        if (i != 0)
          callback.onProjectDeleted(i);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getProjectNameFromProjectId(final int projectId, @NonNull final GetProjectNameCallback callback) {
    Runnable runnable = () -> {
      final String s = projectDao.getProjectNameFromProjectId(projectId);

      appExecutors.mainThread().execute(() -> {
        if (s != null)
          callback.onProjectNameLoaded(s);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountProject(@NonNull GetCountProjectCallback callback) {
    Runnable runnable = () -> {
      final int count = projectDao.getCountProject();

      appExecutors.mainThread().execute(() -> {
        if (count != -1)
          callback.onProjectCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getCountProjectByFullId(String fullId, @NonNull GetCountProjectByFullIdCallback callback) {
    Runnable runnable = () -> {
      final int count = projectDao.getCountProjectByFullId(fullId);

      appExecutors.mainThread().execute(() -> {
        if (count != -1)
          callback.onProjectCounted(count);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getProjectVectorInfoByPCode(int pCode, @NonNull GetProjectVectorInfoByPCodeCallback callback) {
    Runnable runnable = () -> {
      final AccVectorInfo vectorInfo = projectDao.getProjectVectorInfoByPCode(pCode);

      appExecutors.mainThread().execute(() -> {
        if (vectorInfo != null)
          callback.VectorInfo(vectorInfo);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

  @Override
  public void getProjectVectorInfoById(int Id, @NonNull GetProjectVectorInfoByIdCallback callback) {
    Runnable runnable = () -> {
      final AccVectorInfo vectorInfo = projectDao.getProjectVectorInfoById(Id);

      appExecutors.mainThread().execute(() -> {
        if (vectorInfo != null)
          callback.VectorInfo(vectorInfo);
        else
          callback.onDataNotAvailable();
      });
    };
    appExecutors.diskIO().execute(runnable);
  }

}
