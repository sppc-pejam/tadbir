package com.sppcco.helperlibrary.manager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;

/**
 * Created by m_pejam on 12/30/17.
 *
 */

public class DeviceManager {

  /**
   * Used to check app is installed or not
   *
   * @param uri PackageName of application
   */
  public static boolean appInstalledOrNot(Context context, String uri) {
    PackageManager pm = context.getPackageManager();
    boolean app_installed = false;
    try {
      pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
      app_installed = true;
    } catch (PackageManager.NameNotFoundException e) {
      app_installed = false;
    }
    return app_installed;
  }

  public static String getKeyboardLanguage(Context context){
    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
    return ims.getLocale();
  }

}
