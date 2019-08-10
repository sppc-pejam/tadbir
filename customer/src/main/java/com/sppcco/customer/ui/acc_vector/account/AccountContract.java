package com.sppcco.customer.ui.acc_vector.account;


import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.data.sub_model.AccVectorInfo;
import com.sppcco.customer.IBasePresenter;
import com.sppcco.customer.IBaseView;
import com.sppcco.customer.IBaseViewModel;
import com.sppcco.core.enums.AccountTree;

import java.util.List;

public interface AccountContract {

  interface View extends IBaseView<Presenter> {
    void initViewModel();

    void selectAccount(AccVectorInfo data);

    void previousStep();

    void nextStep(AccVectorInfo data);

    void skipNextStep();

    void postValue();

    ACCVector getTempACCVector();

    ACCVector getACCVector();

    AccountTree getRoot();

    String getFilter();

    int getSortPosition();
  }

  interface Presenter extends IBasePresenter {
    void prepareListData();

    void setAccount(AccVectorInfo data);

    List<String> getSortList();
  }

  interface ViewModel extends IBaseViewModel<View, Presenter> {
  }

  interface Listener {
    void onChangeBranch(StateProgressBar.StateNumber destinationNumber);
  }

}
