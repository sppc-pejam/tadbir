package com.sppcco.core.di.module;

import android.app.Application;


import com.sppcco.core.data.local.pref.IPrefContract;
import com.sppcco.core.data.local.pref.PrefImplementation;
import com.sppcco.core.util.app.CoreConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = CoreAppModule.class)
public class CorePrefModule {

  private Application mApplication;

  public CorePrefModule(Application application) {

    mApplication = application;
  }


  @Provides
  @Singleton
  IPrefContract providePrefImplementation() {

    return new PrefImplementation(mApplication, CoreConstants.PREF_NAME);
  }
}
