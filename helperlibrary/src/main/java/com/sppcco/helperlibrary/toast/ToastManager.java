package com.sppcco.helperlibrary.toast;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.devspark.appmsg.AppMsg;
import com.sppcco.helperlibrary.R;
import com.sppcco.helperlibrary.message.enums.MessageDuration;
import com.sppcco.helperlibrary.message.enums.MessageType;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by Behzad on 7/2/2018.
 *
 */

public class ToastManager {

  private static View mView;
  private static ImageView imgIcon;
  private static TextView tvMessage;

  public static void generate(Activity activity, String message) {
    @DrawableRes int icon = 0;
    MessageType type = null;

    initLayout(activity);
    updateView(activity, message, type, icon);

    AppMsg appMsg = AppMsg.makeText(activity, R.layout.custom_toast, AppMsg.STYLE_INFO);
    appMsg.setText(message);
    appMsg.setDuration(1000);
    appMsg.show();
  }

  public static void generate(Activity activity, String message, MessageType type) {
    @DrawableRes int icon = 0;

    initLayout(activity);
    updateView(activity, message, type, icon);

    AppMsg appMsg = AppMsg.makeText(activity, R.layout.custom_toast, AppMsg.STYLE_INFO);
    appMsg.setText(message);
    appMsg.show();
  }

  public static void generate(Activity activity, String message, MessageDuration duration) {
    @DrawableRes int icon = 0;
    MessageType type = null;

    initLayout(activity);
    updateView(activity, message, type, icon);

    AppMsg appMsg = AppMsg.makeText(activity, R.layout.custom_toast, AppMsg.STYLE_INFO);
    appMsg.setText(message);
    appMsg.setDuration(getDuration(duration));
    appMsg.show();
  }

  public static void generate(Activity activity, String message, MessageDuration duration,
                              int animationIn, int animationOut) {

    @DrawableRes int icon = 0;
    MessageType type = null;

    initLayout(activity);
    updateView(activity, message, type, icon);

    AppMsg appMsg = AppMsg.makeText(activity, R.layout.custom_toast, AppMsg.STYLE_INFO);
    appMsg.setText(message);
    appMsg.setAnimation(animationIn, animationOut);
    appMsg.setDuration(getDuration(duration));
    appMsg.show();
  }

  public static void generate(Activity activity, String message, MessageDuration duration,
                              Animation animationIn, Animation animationOut) {
    @DrawableRes int icon = 0;
    MessageType type = null;

    initLayout(activity);
    updateView(activity, message, type, icon);

    AppMsg appMsg = AppMsg.makeText(activity, R.layout.custom_toast, AppMsg.STYLE_INFO);
    appMsg.setText(message);
    appMsg.setAnimation(animationIn, animationOut);
    appMsg.setDuration(getDuration(duration));
    appMsg.show();
  }

  public static void generate(Activity activity, String message, MessageDuration duration, MessageType type) {
    @DrawableRes int icon = 0;

    initLayout(activity);
    updateView(activity, message, type, icon);

    AppMsg appMsg = AppMsg.makeText(activity, R.layout.custom_toast, getStyle(type));
    appMsg.setDuration(getDuration(duration));
    appMsg.show();
  }

  public static void generate(Activity activity, String message, MessageDuration duration, MessageType type,
                              @DrawableRes int icon) {
    initLayout(activity);
    updateView(activity, message, type, icon);

    AppMsg appMsg = AppMsg.makeText(activity, R.layout.custom_toast, getStyle(type));
    appMsg.setDuration(getDuration(duration));
    appMsg.show();
  }

  public static void generate(Activity activity, String message, MessageDuration duration, MessageType type,
                              int animationIn, int animationOut, @DrawableRes int icon) {
    initLayout(activity);
    updateView(activity, message, type, icon);

    AppMsg appMsg = AppMsg.makeText(activity, R.layout.custom_toast, getStyle(type));
    appMsg.setAnimation(animationIn, animationOut);
    appMsg.setDuration(getDuration(duration));
    appMsg.show();
  }

  public static void generate(Activity activity, String message, MessageDuration duration, MessageType type,
                              Animation animationIn, Animation animationOut, @DrawableRes int icon) {
    initLayout(activity);
    updateView(activity, message, type, icon);

    AppMsg appMsg = AppMsg.makeText(activity, R.layout.custom_toast, getStyle(type));
    appMsg.setAnimation(animationIn, animationOut);
    appMsg.setDuration(getDuration(duration));
    appMsg.setView(mView);
    appMsg.show();
  }

  public static void generate(Activity activity, String message, MessageType type, @DrawableRes int icon) {
    initLayout(activity);
    updateView(activity, message, type, icon);

    AppMsg appMsg = AppMsg.makeText(activity, R.layout.custom_toast, getStyle(type));
    appMsg.setView(mView);
    appMsg.show();
  }

  private static void initLayout(Activity activity) {
    ViewGroup viewGroup = null;
    LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    mView = inflater.inflate(R.layout.custom_toast, viewGroup);

    TypedValue tv = new TypedValue();
    int actionBarHeight = 0;
    if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
      actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
    }

    ConstraintLayout toastLayout = (ConstraintLayout) mView.findViewById(R.id.cl_toast);
    ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, actionBarHeight);
    toastLayout.setLayoutParams(params);
    toastLayout.requestLayout();

    imgIcon = mView.findViewById(R.id.img_icon);
    tvMessage = mView.findViewById(R.id.tv_message);
  }

  private static void updateView(Activity activity, String message, MessageType type, @DrawableRes int icon) {
    if(message != null)
      tvMessage.setText(message);

    if(icon != 0)
      imgIcon.setImageResource(icon);
    else
      imgIcon.setVisibility(View.GONE);

    if(type != null)
      mView.setBackgroundColor(activity.getResources().getColor(getBackgroundColor(type)));
  }

  public static int getBackgroundColor(MessageType messageType) {
    switch (messageType) {

      case SUCCESS:
        return R.color.bts_success_color;

      case DANGER:
        return R.color.bts_danger_color;

      case WARNING:
        return R.color.bts_warning_color;

      case INFO:
        return R.color.bts_info_color;

    }
    return R.color.bts_info_color;
  }

  private static int getDuration(MessageDuration messageDuration) {
    int duration = 0;
    switch (messageDuration) {

      case STICKY:
        duration = AppMsg.LENGTH_STICKY;
        break;

      case SHORT:
        duration = AppMsg.LENGTH_SHORT;
        break;

      case LONG:
        duration = AppMsg.LENGTH_LONG;
        break;
    }
    return duration;
  }

  private static AppMsg.Style getStyle(MessageType type) {
    AppMsg.Style style = null;
    switch (type) {

      case SUCCESS:
      case WARNING:
        style = AppMsg.STYLE_CONFIRM;
        break;

      case DANGER:
        style = AppMsg.STYLE_ALERT;
        break;

      case INFO:
        style = AppMsg.STYLE_INFO;
        break;
    }
    return style;
  }
}
