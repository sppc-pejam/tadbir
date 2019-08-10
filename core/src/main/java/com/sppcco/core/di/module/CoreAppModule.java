package com.sppcco.core.di.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.sppcco.core.util.app.AppExecutors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by m_pejam on 01/14/18.
 * AppModule
 */


@Module
public class CoreAppModule {


  @Provides
  @Singleton
  public static Context provideContext(Application application) {
    return application.getApplicationContext();
  }

  @Provides
  @Singleton
  public static Resources provideResources(Application application) {
    return application.getResources();
  }

  @Provides
  @Singleton
  public static AppExecutors providesAppExecutors() {
    return new AppExecutors();
  }

}
