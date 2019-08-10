package com.sppcco.core.data.remote.repository;

import com.sppcco.core.enums.ResponseType;
import com.sppcco.core.util.network.error_handling.RetrofitException;
import com.sppcco.helperlibrary.message.enums.MessageType;

import org.json.JSONException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

import io.reactivex.exceptions.CompositeException;

/**
 * Created by Behzad on 7/23/2018.
 */

public class RemoteData {

  public static ResponseType ErrorType(Throwable e) {
    ResponseType responseType;
    if (e instanceof JSONException || e instanceof IOException || e instanceof NullPointerException || e instanceof CompositeException) {
      responseType = ResponseType.ERR_MISMATCH_DATA;

    } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
      responseType = ResponseType.ERR_CLIENT_NO_CONNECTION;

    } else if (e instanceof RetrofitException) {

      if (((RetrofitException) e).getResponse() != null) {
        int code = ((RetrofitException) e).getResponse().code();
        if (code == 504 || code == 404) {
          responseType = ResponseType.ERR_INVALID_CONFIG;
        } else /*if (code == 500)*/ {
          responseType = ResponseType.ERR_REQUEST_NOT_FOUND;
        }
      } else if (((RetrofitException) e).getKind() == RetrofitException.Kind.NETWORK) {
        responseType = ResponseType.ERR_CLIENT_NO_CONNECTION;
      } else if (((RetrofitException) e).getKind() == RetrofitException.Kind.HTTP) {
        responseType = ResponseType.ERR_CLIENT_INVALID_DATA;

      } else if (((RetrofitException) e).getKind() == RetrofitException.Kind.UNEXPECTED) {
        responseType = ResponseType.ERR_SERVER_INVALID_DATA;

      } else //if (((RetrofitException) e).getKind() == RetrofitException.Kind.NETWORK) {
        responseType = ResponseType.ERR_SERVER_NOT_FOUND;

      //}
    } else {
      responseType = ResponseType.ERR_CLIENT_NO_CONNECTION;
    }

    return responseType;
  }

  public static void showErrorMessage(ResponseType responseType) {


    //TODO
    /*LayoutInflater inflater = BaseApplication.getCurrentActivity().getLayoutInflater();
    View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup)
      BaseApplication.getCurrentActivity().findViewById(R.id.toast_layout_root));

    int color = 0;
    if (responseType.getMessageType() == MessageType.SUCCESS)
      color = R.color.bts_success_color;
    else if (responseType.getMessageType() == MessageType.DANGER)
      color = R.color.bts_danger_color;
    else if (responseType.getMessageType() == MessageType.WARNING)
      color = R.color.bts_warning_color;
    else if (responseType.getMessageType() == MessageType.INFO)
      color = R.color.bts_info_color;

    layout.setBackgroundColor(UApp.getCurrentActivity().getResources().getColor(color));

    ImageView image = (ImageView) layout.findViewById(R.id.image);
    image.setImageResource(responseType.getIcon());
    TextView text = (TextView) layout.findViewById(R.id.text);
    text.setText(responseType.getContent());

    Toast toast = new Toast(UApp.getAppContext());
    //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    toast.setDuration(Toast.LENGTH_LONG);
    toast.setView(layout);
    toast.show();*/

  }

  public static void showErrorMessage(String message, MessageType messageType) {


    //TODO
    /*LayoutInflater inflater = UApp.getCurrentActivity().getLayoutInflater();
    View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup)
      UApp.getCurrentActivity().findViewById(R.id.toast_layout_root));

    int color = 0;
    if (messageType == MessageType.SUCCESS)
      color = R.color.bts_success_color;
    else if (messageType == MessageType.DANGER)
      color = R.color.bts_danger_color;
    else if (messageType == MessageType.WARNING)
      color = R.color.bts_warning_color;
    else if (messageType == MessageType.INFO)
      color = R.color.bts_info_color;

    layout.setBackgroundColor(UApp.getCurrentActivity().getResources().getColor(color));

    ImageView image = (ImageView) layout.findViewById(R.id.image);
    image.setVisibility(View.GONE);
    TextView text = (TextView) layout.findViewById(R.id.text);
    text.setText(message);

    Toast toast = new Toast(UApp.getAppContext());
    toast.setDuration(Toast.LENGTH_LONG);
    toast.setView(layout);
    toast.show();*/
  }
}
