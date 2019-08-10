package com.sppcco.core.di.component;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;


import com.sppcco.core.di.module.CoreAppModule;
import com.sppcco.core.util.app.AppExecutors;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = CoreAppModule.class)
public interface CoreAppComponent {

  @Component.Builder
  interface Builder {
    CoreAppComponent build();

    @BindsInstance
    Builder application(Application application);
  }

  Application getApplication();

  Context getContext();

  Resources getResources();

  AppExecutors getAppExecutors();

}
