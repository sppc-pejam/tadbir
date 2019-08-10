package com.sppcco.helperlibrary.manager;

/*
 * Created by m_pejam on 12/30/17.
 */

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import android.util.Log;


/**
 * Created by m_pejam on 12/30/17.
 *
 */

public class ApplicationPermission {
  /**
   * Used to get storage Permission
   *
   * @param activity context
   */
  public static boolean isStoragePermissionGranted(Activity activity) {
    if (Build.VERSION.SDK_INT >= 23) {
      if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        == PackageManager.PERMISSION_GRANTED) {
        Log.v("Permission", "Permission is granted");
        return true;
      } else {

        Log.v("Permission", "Permission is revoked");
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        return false;
      }
    } else { //permission is automatically granted on sdk<23 upon installation
      Log.v("Permission", "Permission is granted");
      return true;
    }
  }

  /**
   * Used to get location Permission
   *
   * @param activity context
   */
  public static boolean isLocationPermissionGranted(Activity activity) {
    if (Build.VERSION.SDK_INT >= 23) {
      if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        Log.v("Permission", "Permission is granted");
        return true;
      } else {

        Log.v("Permission", "Permission is revoked");
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        return false;
      }
    } else { //permission is automatically granted on sdk<23 upon installation
      Log.v("Permission", "Permission is granted");
      return true;
    }
  }


  /**
   * Used to get Telephony Permission
   *
   * @param activity context
   */
  public static boolean isTelephonePermissionGranted(Activity activity) {
    if (Build.VERSION.SDK_INT >= 23) {
      if (activity.checkSelfPermission(Manifest.permission.CALL_PHONE)
        == PackageManager.PERMISSION_GRANTED) {
        Log.v("Permission", "Permission is granted");
        return true;
      } else {

        Log.v("Permission", "Permission is revoked");
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1);
        return false;
      }
    } else { //permission is automatically granted on sdk<23 upon installation
      Log.v("Permission", "Permission is granted");
      return true;
    }
  }


  /**
   * Used to get Camera Permission
   *
   * @param activity a
   */
  public static boolean isCameraPermissionGranted(Activity activity) {
    if (Build.VERSION.SDK_INT >= 23) {
      if (activity.checkSelfPermission(Manifest.permission.CAMERA)
        == PackageManager.PERMISSION_GRANTED && activity.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
        Log.v("Permission", "Permission is granted");
        return true;
      } else {

        Log.v("Permission", "Permission is revoked");
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 1);
        return false;
      }
    } else { //permission is automatically granted on sdk<23 upon installation
      Log.v("Permission", "Permission is granted");
      return true;
    }
  }

}
