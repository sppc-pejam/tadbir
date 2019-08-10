package com.sppcco.customer.ui.acc_vector.project;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.customer.IBasePresenter;
import com.sppcco.customer.IBaseView;
import com.sppcco.customer.IBaseViewModel;
import com.sppcco.customer.ui.acc_vector.ACCVectorContract;

import java.util.List;

public interface ProjectContract {

  interface View extends IBaseView<Presenter> {
    void initViewModel();

    void selectProject(AccVectorInfo data);

    void previousStep();

    void nextStep(AccVectorInfo data);

    void skipNextStep();

    void postValue();

    ACCVectorContract.View getACC();

    ACCVector getTempACCVector();

    ACCVector getACCVector();

    AccountTree getRoot();

    String getFilter();

    int getSortPosition();
  }

  interface Presenter extends IBasePresenter {
    void prepareListData();

    void setProject(AccVectorInfo data);

    List<String> getSortList();
  }

  interface ViewModel extends IBaseViewModel<View, Presenter> {
  }

  interface Listener {
    void onChangeBranch(StateProgressBar.StateNumber destinationNumber);
  }
}
