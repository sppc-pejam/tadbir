package com.sppcco.helperlibrary.manager;

import android.content.Context;

/**
 * Created by m_pejam on 12/30/17.
 */

public class ApplicationManager {

  /**
   *
   * @param context
   * @return
   */
  public static String getPackageName(Context context) {
    return context.getPackageName();
  }

}
