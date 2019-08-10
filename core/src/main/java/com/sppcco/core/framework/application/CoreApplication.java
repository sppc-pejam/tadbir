package com.sppcco.core.framework.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.sppcco.core.data.remote.service.ApiService;
import com.sppcco.core.di.component.CoreAppComponent;
import com.sppcco.core.di.component.CoreDBComponent;
import com.sppcco.core.di.component.CoreNetComponent;
import com.sppcco.core.di.component.CorePrefComponent;
import com.sppcco.core.di.component.DaggerCoreAppComponent;
import com.sppcco.core.di.component.DaggerCoreDBComponent;
import com.sppcco.core.di.component.DaggerCorePrefComponent;
import com.sppcco.core.di.module.CoreDBModule;
import com.sppcco.core.di.module.CoreNetModule;
import com.sppcco.core.di.module.CorePrefModule;
import com.sppcco.core.di.subcomponent.DaggerNetComponent;
import com.sppcco.core.di.subcomponent.NetComponent;
import com.sppcco.core.di.subcomponent.NetModule;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.content.res.AppCompatResources;


public class CoreApplication extends Application {

  private static CoreApplication mCoreApplication;

  private static CoreAppComponent mCoreAppComponent;

  private static CoreDBComponent mCoreDBComponent;

  private static CorePrefComponent mCorePrefComponent;

  private static CoreNetComponent mCoreNetComponent;

  private static NetComponent mNetComponent;


  @Override
  public void onCreate() {
    super.onCreate();

    mCoreApplication = this;

    setCoreAppComponent(DaggerCoreAppComponent.builder()
      .application(this)
      .build());

    setCoreDBComponent(DaggerCoreDBComponent.builder().
      coreDBModule(new CoreDBModule(getCoreAppComponent().getApplication(),
        getCoreAppComponent().getAppExecutors())).
      build());

    setCorePrefComponent(DaggerCorePrefComponent.builder().
      corePrefModule(new CorePrefModule(getCoreAppComponent().getApplication()))
      .build());

    setNetComponent(DaggerNetComponent.builder().netModule(
      new NetModule(getCoreAppComponent().getApplication()))
      .build());

    setCoreNetComponent(getNetComponent().
      coreNetComponentBuilder().coreNetModule(new CoreNetModule()).
      build());


  }

  public static CoreApplication getCoreApplication() {
    return mCoreApplication;
  }

  public static void setCoreApplication(CoreApplication mCoreApplication) {
    CoreApplication.mCoreApplication = mCoreApplication;
  }

  public static CoreAppComponent getCoreAppComponent() {
    return mCoreAppComponent;
  }

  public static void setCoreAppComponent(CoreAppComponent mCoreAppComponent) {
    CoreApplication.mCoreAppComponent = mCoreAppComponent;
  }

  public static CoreDBComponent getCoreDBComponent() {
    return mCoreDBComponent;
  }

  public static void setCoreDBComponent(CoreDBComponent mCoreDBComponent) {
    CoreApplication.mCoreDBComponent = mCoreDBComponent;
  }

  public static CorePrefComponent getCorePrefComponent() {
    return mCorePrefComponent;
  }

  public static void setCorePrefComponent(CorePrefComponent mCorePrefComponent) {
    CoreApplication.mCorePrefComponent = mCorePrefComponent;
  }

  public static CoreNetComponent getCoreNetComponent() {
    return mCoreNetComponent;
  }

  public static void setCoreNetComponent(CoreNetComponent mCoreNetComponent) {
    CoreApplication.mCoreNetComponent = mCoreNetComponent;
  }

  public static NetComponent getNetComponent() {
    return mNetComponent;
  }

  public static void setNetComponent(NetComponent mNetComponent) {
    CoreApplication.mNetComponent = mNetComponent;
  }

  public static Context getContext() {
    return getCoreAppComponent().getContext();
  }

  public static Resources getCoreResources() {
    return getCoreAppComponent().getResources();
  }

  public static String getResourceString(@StringRes int resId) {
    return getCoreResources().getString(resId);
  }

  public static Drawable getResourceDrawable(@DrawableRes int resId) {
    return AppCompatResources.getDrawable(getContext(), resId);
  }

  public static ApiService getApiService() {
    return getCoreNetComponent().apiService();
  }

}
