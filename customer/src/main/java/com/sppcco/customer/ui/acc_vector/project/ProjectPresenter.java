package com.sppcco.customer.ui.acc_vector.project;

import com.sppcco.core.data.local.db.repository.ProjectRepository;
import com.sppcco.core.data.model.Project;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.framework.application.BaseApplication;
import com.sppcco.core.framework.presenter.BasePresenter;
import com.sppcco.customer.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;


public class ProjectPresenter extends BasePresenter implements ProjectContract.Presenter {

  private static ProjectPresenter INSTANCE;
  private final ProjectContract.View mView;

  private List<String> mSortList;

  private ProjectPresenter(@NonNull ProjectContract.View view) {
    mView = view;
    mView.setPresenter(this);
  }


  static ProjectContract.Presenter getProjectPresenterInstance(
    @NonNull ProjectContract.View view) {
    if (INSTANCE == null) {
      INSTANCE = new ProjectPresenter(view);
    }
    return INSTANCE;
  }

  @Override
  public void destroy() {
    INSTANCE = null;
  }

  @Override
  public void start() {
    loadSortList();
  }

  @Override
  public void prepareListData() {
    getCountProjectByFullId();
  }

  private void getCountProjectByFullId() {
    getCoreDB().projectRepository().getCountProjectByFullId(
      mView.getTempACCVector().getAccount().getFullId(),
      new ProjectRepository.GetCountProjectByFullIdCallback() {
        @Override
        public void onProjectCounted(int count) {
          if (count != 0) {
            mView.initData();
            mView.initLayout();
          } else {
            mView.skipNextStep();
          }
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  @Override
  public void setProject(AccVectorInfo data) {
    getCoreDB().projectRepository().getProjectByPCode(Integer.valueOf(data.getCode()),
      new ProjectRepository.GetProjectByPCodeCallback() {
        @Override
        public void onProjectLoaded(Project project) {
          mView.getTempACCVector().setProject(project);
          mView.nextStep(data);
        }

        @Override
        public void onDataNotAvailable() {

        }
      });
  }

  private void loadSortList() {
    if (getSortList() != null)
      return;

    mSortList = new ArrayList<>();
    mSortList.add(BaseApplication.getResourceString(R.string.cpt_code));
    mSortList.add(BaseApplication.getResourceString(R.string.cpt_name));
  }

  @Override
  public List<String> getSortList() {
    return mSortList;
  }
}