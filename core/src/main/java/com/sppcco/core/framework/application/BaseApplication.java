package com.sppcco.core.framework.application;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.sppcco.core.R;
import com.sppcco.core.framework.activity.BaseAppCompatActivity;
import com.sppcco.core.framework.fragment.BaseFragment;

import androidx.appcompat.widget.Toolbar;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends CoreApplication {

  private static BaseAppCompatActivity mCurrentActivity;
  private static BaseFragment mCurrentFragment;

  private static Typeface mFaNumTypeface;
  private static Typeface mEnNumTypeface;

  private static Toolbar mToolbar;


  @Override
  public void onCreate() {
    super.onCreate();

    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
      .setDefaultFontPath(getResourceString(R.string.iranian_sans_fa_num))
      .setFontAttrId(R.attr.fontPath)
      .build()
    );

    AssetManager mgr = getAssets();
    mFaNumTypeface = Typeface.createFromAsset(mgr, getResourceString(R.string.iranian_sans_fa_num));
    mEnNumTypeface = Typeface.createFromAsset(mgr, getResourceString(R.string.iranian_sans_en_num));


    //TODO
    getCorePrefComponent().getPrefImplementation().setWSId(4);
    getCorePrefComponent().getPrefImplementation().setCurrentUserId(9);
    getCorePrefComponent().getPrefImplementation().setFPId(1);
    getCorePrefComponent().getPrefImplementation().setDatabaseName("TESTANDRIOD");
    getCorePrefComponent().getPrefImplementation().setKey("1234");
    getCorePrefComponent().getPrefImplementation().setBaseUrl("http:/130.185.76.7:9020/");
    getCorePrefComponent().getPrefImplementation().setIpAddress("130.185.76.7");
    getCorePrefComponent().getPrefImplementation().setPortNumber("9020");
    getCorePrefComponent().getPrefImplementation().setWebservicePortNumber("9010");
  }

  public static void setCurrentActivity(BaseAppCompatActivity currentActivity) {
    mCurrentActivity = currentActivity;
  }

  public static BaseAppCompatActivity getCurrentActivity() {
    return mCurrentActivity;
  }

  public static void setCurrentFragment(BaseFragment fragment) {
    mCurrentFragment = fragment;
  }

  public static BaseFragment getCurrentFragment() {
    return mCurrentFragment;
  }

  public static Typeface getFaNumTypeface() {

    return mFaNumTypeface;
  }

  public static Typeface getEnNumTypeface() {
    return mEnNumTypeface;
  }

  public static Toolbar getToolbar() {
    return mToolbar;
  }

  public static void setToolbar(Toolbar toolbar) {
    mToolbar = toolbar;
  }

  public static int getUserId() {
    return getCorePrefComponent().getPrefImplementation().getCurrentUserId();
  }

  public static int getFPId() {
    return getCorePrefComponent().getPrefImplementation().getFPId();
  }

  public static int getWSId() {
    return getCorePrefComponent().getPrefImplementation().getWSId();
  }

  public static String getDatabaseName() {
    return getCorePrefComponent().getPrefImplementation().getDatabaseName();
  }

  public static String getApiKey() {
    return getCorePrefComponent().getPrefImplementation().getKey();
  }

  public static String getBaseUrl() {
    return getCorePrefComponent().getPrefImplementation().getBaseUrl();
  }

  public static String getIPServer() {
    return getCorePrefComponent().getPrefImplementation().getIpAddress();
  }

  public static String getDataBaseServicePort() {
    return getCorePrefComponent().getPrefImplementation().getPortNumber();
  }

  public static String getBusinessServicePort() {
    //TODO
    return "9040";
  }



}
