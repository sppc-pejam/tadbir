package com.sppcco.core.framework.presenter;

import com.sppcco.core.di.component.CoreDBComponent;
import com.sppcco.core.di.component.CoreNetComponent;
import com.sppcco.core.di.component.CorePrefComponent;
import com.sppcco.core.framework.application.CoreApplication;

public class CorePresenter {

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
