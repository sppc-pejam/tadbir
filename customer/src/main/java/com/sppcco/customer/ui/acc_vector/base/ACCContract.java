package com.sppcco.customer.ui.acc_vector.base;

import com.kofigyan.stateprogressbar.StateProgressBar;

/**
 * Created by b_nematzadeh on 1/19/2019.
 */
public interface ACCContract {

  interface Listener {
    void onChangeBranch(StateProgressBar.StateNumber destinationNumber);
  }
}
