package com.sppcco.customer.ui.acc_vector.detail_acc;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.data.sub_model.DetailAccVectorInfo;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.customer.IBasePresenter;
import com.sppcco.customer.IBaseView;
import com.sppcco.customer.IBaseViewModel;

import java.util.List;

public interface DetailAccContract {

  interface View extends IBaseView<Presenter> {
    void initViewModel();

    void selectDetailAcc(DetailAccVectorInfo data);

    void previousStep();

    void nextStep(DetailAccVectorInfo data);

    void skipNextStep();

    void postValue();

    void errorMessage();

    ACCVector getTempACCVector();

    ACCVector getACCVector();

    AccountTree getRoot();

    String getFilter();

    int getSortPosition();
  }

  interface Presenter extends IBasePresenter {
    void prepareListData();

    void setDetailAcc(DetailAccVectorInfo data);

    List<String> getSortList();
  }

  interface ViewModel extends IBaseViewModel<View, Presenter> {
  }

  interface Listener {
    void onChangeBranch(StateProgressBar.StateNumber destinationNumber);
  }

}
