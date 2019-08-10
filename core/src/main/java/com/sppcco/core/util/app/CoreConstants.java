

package com.sppcco.core.util.app;


import com.sppcco.core.BuildConfig;

public final class CoreConstants {

  private CoreConstants() {
    // This utility class is not publicly instantiable
  }

  // 97.04.20
  public static final int MAJOR_VERSION = Integer.parseInt(getAppVersion().substring(0, getAppVersion().indexOf('.')));
  public static final int MINOR_VERSION = Integer.parseInt(getAppVersion().substring(getAppVersion().indexOf('.') + 1, getAppVersion().lastIndexOf('.')));
  public static final int PATCH_VERSION = Integer.parseInt(getAppVersion().substring(getAppVersion().lastIndexOf('.') + 1, getAppVersion().length()));


/*  public static final int MAJOR_VERSION = 3;
  public static final int MINOR_VERSION = 1;
  public static final int PATCH_VERSION = 0;*/

  public static String getAppVersion() {
    return BuildConfig.VERSION_NAME;
  }

  public static String getServiceVersion(){
    return  String.format("V%s.%s", CoreConstants.MAJOR_VERSION, CoreConstants.MINOR_VERSION);
  }

  // URL Rel
  public static final String FAKE_URL = "http://www.google.com/";
  public static final String SPPC_SERVER = "https://www.sppcco.com/application/tadbirspapp/version/";
  public static final String SPPC_APP_DOWNLOAD = SPPC_SERVER + "apk/";
  public static final String SPPC_APP_LAST_VERSION = SPPC_SERVER + "index.php";
  public static final String SPPC_APP_VERSION_INFO = SPPC_SERVER + "versions-info.php";

  // DB Rel
  public static final int DB_VERSION = BuildConfig.VERSION_CODE;
  public static final String DB_NAME = "core.db";
  public static final String PREF_NAME = "core_pref";

  // CONST
  public static final String APP_TAG = "Tadbir Core Library Tag";

  // محدودیت ثبت آرتیکل
  public static final int ROW_LIMITED = 50;


  // update application Constant
  public static final String INTENT_AUTHORITY = BuildConfig.APPLICATION_ID + ".fileprovider";
  public static final String INTENT_TYPE = "application/vnd.android.package-archive";

}
