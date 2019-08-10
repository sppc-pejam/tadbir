package com.sppcco.helperlibrary.cue;

import android.app.Activity;
import androidx.annotation.StringRes;
import android.view.Gravity;
import android.view.View;

import com.fxn.cue.Cue;
import com.fxn.cue.enums.Duration;
import com.fxn.cue.enums.Type;
import com.sppcco.helperlibrary.R;
import com.sppcco.helperlibrary.cue.enums.CueType;

/**
 * Created by m_pejam on 02/28/18.
 */


public class CueToast {


  public static void generate(Activity activity, String strContent, CueType cueType, int nGravity) {
    Cue.init()
      .with(activity)
      .setMessage(strContent)
      .setGravity(nGravity)
      .setType(findType(cueType))
      .setPadding(10)
      .setTextSize(16)
      .show();
  }

  public static void generateShort(Activity activity, String strContent, CueType cueType) {
    Cue.init()
      .with(activity)
      .setMessage(strContent)
      .setGravity(Gravity.CENTER )
      .setType(findType(cueType))
      .setPadding(8)
      .setTextSize(16)
      .setDuration(Duration.SHORT)
      .show();
  }

  public static void generate(Activity activity, String strContent, CueType cueType) {
    Cue.init()
      .with(activity)
      .setMessage(strContent)
      .setGravity(Gravity.CENTER )
      .setType(findType(cueType))
      .setPadding(8)
      .setTextSize(16)
      .setDuration(Duration.LONG)
      .show();
  }




  public static Type findType(CueType cueType) {
    switch (cueType) {
      case PRIMARY:
        return Type.PRIMARY;

      case SECONDARY:
        return Type.SECONDARY;

      case SUCCESS:
        return Type.SUCCESS;

      case DANGER:
        return Type.DANGER;

      case WARNING:
        return Type.WARNING;

      case INFO:
        return Type.INFO;

      case LIGHT:
        return Type.LIGHT;

      case DARK:
        return Type.DARK;

    }
    return Type.PRIMARY;
  }

}
