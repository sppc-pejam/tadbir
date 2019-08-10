package com.sppcco.customer.ui.acc_vector;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.sppcco.core.data.model.BranchStatus;
import com.sppcco.core.data.sub_model.ACCVector;
import com.sppcco.core.enums.AccountTree;
import com.sppcco.customer.IBasePresenter;


public interface ACCVectorContract {

  interface View {
    void setPresenter(ACCVectorContract.Presenter presenter);

    void postValue();

    void updateView(String detCode);

    void updateCell(StateProgressBar.StateNumber stateNumber, String value);

    void onPreviousStep(StateProgressBar.StateNumber stepNumber);

    void onNextStep(StateProgressBar.StateNumber stepNumber, String data);

    void setTempACCVector(ACCVector accVector);

    ACCVector getTempACCVector();

    void setACCVector(ACCVector accVector);

    ACCVector getACCVector();

    BranchStatus getBranchStatus();

    void setBranchStatus(BranchStatus branchStatus);

    AccountTree getRoot();

  }

  interface Presenter extends IBasePresenter {
  }
}
