package com.sppcco.helperlibrary.manager;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import java.util.Objects;

/**
 * Created by m_pejam on 02/01/18.
 *
 */

public class ViewManager {


  public static void closeKeyboard(Activity activity) {
    InputMethodManager inputManager = (InputMethodManager)
      activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    assert inputManager != null;
    inputManager.hideSoftInputFromWindow(Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(),
      InputMethodManager.HIDE_NOT_ALWAYS);
  }

  public static boolean isKeyboardShown(Activity activity) {
    InputMethodManager inputManager = (InputMethodManager)
      activity.getSystemService(Context.INPUT_METHOD_SERVICE);

    assert inputManager != null;
    return inputManager.isAcceptingText();
  }

  public static void showKeyboard(Activity activity) {
    InputMethodManager inputManager = (InputMethodManager)
      activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    assert inputManager != null;
    inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
  }

}
