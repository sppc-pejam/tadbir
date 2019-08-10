package com.sppcco.core.util.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import io.reactivex.Observable;

public class NetworkUtil {

  public static String WIFI_STATE_CHANGE = "android.net.wifi.STATE_CHANGE";
  public static String CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";

  private static int TYPE_WIFI = 1;
  private static int TYPE_MOBILE = 2;
  private static int TYPE_NOT_CONNECTED = 0;

  private static final int NETWORK_STATUS_NOT_CONNECTED = 0, NETWORK_STAUS_WIFI = 1, NETWORK_STATUS_MOBILE = 2;

  private static int getConnectivityStatus(Context context) {

    int connectionType = 0;
    if (Connectivity.isConnected(context))
      connectionType = TYPE_NOT_CONNECTED;
    else if (Connectivity.isConnectedWifi(context))
      connectionType = TYPE_WIFI;
    else if (Connectivity.isConnectedMobile(context))
      connectionType = TYPE_MOBILE;

    return connectionType;
  }

  public static int getConnectivityStatusChanged(Context context) {
    int conn = NetworkUtil.getConnectivityStatus(context);
    int status = NETWORK_STATUS_NOT_CONNECTED;
    if (conn == NetworkUtil.TYPE_WIFI) {
      status = NETWORK_STAUS_WIFI;
    } else if (conn == NetworkUtil.TYPE_MOBILE) {
      status = NETWORK_STATUS_MOBILE;
    }
    return status;
  }

  private static boolean isNetworkAvailable(Context context) {
    ConnectivityManager connectivityManager =
      (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    assert connectivityManager != null;
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }

  public static Observable<Boolean> isNetworkAvailableObservable(Context context) {
    return Observable.just(NetworkUtil.isNetworkAvailable(context));
  }
}
