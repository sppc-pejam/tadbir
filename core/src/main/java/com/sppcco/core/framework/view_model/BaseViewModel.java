package com.sppcco.core.framework.view_model;

import com.sppcco.core.di.component.CoreDBComponent;
import com.sppcco.core.di.component.CoreNetComponent;
import com.sppcco.core.di.component.CorePrefComponent;
import com.sppcco.core.framework.application.CoreApplication;

import androidx.lifecycle.ViewModel;

/**
 * Created by m_pejam on 09/03/18.
 * UViewModel
 */

public class BaseViewModel extends ViewModel {

  public CoreDBComponent getCoreDB() {
    return CoreApplication.getCoreDBComponent();
  }

  public CorePrefComponent getCorePref() {
    return CoreApplication.getCorePrefComponent();
  }

  public CoreNetComponent getCoreNet() {
    return CoreApplication.getCoreNetComponent();
  }

}
