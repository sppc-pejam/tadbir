package com.sppcco.helperlibrary.snackbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import androidx.annotation.DrawableRes;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.sppcco.helperlibrary.R;
import com.sppcco.helperlibrary.message.enums.MessageDuration;
import com.sppcco.helperlibrary.message.enums.MessageType;

/**
 * Created by m_pejam on 05/12/18.
 *
 */

public class SnackbarManager {

  @SuppressLint("StaticFieldLeak")
  private static View customView;
  @SuppressLint("StaticFieldLeak")
  private static TextView tvContent;
  @SuppressLint("StaticFieldLeak")
  private static ImageView imgIcon;



  public static void generate(Context context, View parentView, String strContent, MessageType snackbarType) {
    Snackbar snackbar = Snackbar.make(parentView, strContent, Snackbar.LENGTH_LONG);
    prepareContent(context, snackbar);
    View view = snackbar.getView();
    view.setBackgroundColor(context.getResources().getColor(getBackgroundColor(snackbarType)));
    snackbar.show();

  }



  public static void generate(Context context, View parentView, String strContent, MessageType snackbarType,
                              MessageDuration snackbarDuration) {
    int duration = Snackbar.LENGTH_LONG;
    if (snackbarDuration != null)
      if (snackbarDuration == MessageDuration.SHORT)
        duration = Snackbar.LENGTH_SHORT;

    Snackbar snackbar = Snackbar.make(parentView, strContent, duration);
    prepareContent(context, snackbar);
    View view = snackbar.getView();

    view.setBackgroundColor(context.getResources().getColor(getBackgroundColor(snackbarType)));
    snackbar.show();
  }



  public static void generate(Context context, View parentView, String strContent, MessageType snackbarType, @DrawableRes int icon) {
    Snackbar snackbar = Snackbar.make(parentView, ""/*strContent*/, Snackbar.LENGTH_LONG);
    prepareContent(context, snackbar);
    View view = snackbar.getView();
    view.setBackgroundColor(context.getResources().getColor(getBackgroundColor(snackbarType)));

    initLayout(context, snackbar);
    tvContent.setText(strContent);
    imgIcon.setImageResource(icon);

    snackbar.show();

  }


  private static void prepareContent(Context context, Snackbar snackbar) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      TextView view = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
      view.setTextColor(context.getResources().getColor(R.color.md_white_1000));
      view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }
  }

  private static void initLayout(Context context, Snackbar snackbar) {
    Snackbar.SnackbarLayout snackbarView = (Snackbar.SnackbarLayout) snackbar.getView();
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View customView = inflater.inflate(R.layout.custom_snack_bar, null);
    tvContent = customView.findViewById(R.id.tv_content);

    imgIcon = customView.findViewById(R.id.img_icon);

    snackbarView.addView(customView, 0);
  }

  private static void setAnimation(Context context, View view) {
    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
    view.setLayoutParams(params);
    Animation animation = AnimationUtils.loadAnimation(context, R.anim.fadein);
    view.startAnimation(animation);
  }

  public static int getBackgroundColor(MessageType snackbarType) {
    switch (snackbarType) {

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

  public static int getBackgroundDrawable(MessageType snackbarType) {
    switch (snackbarType) {

      case SUCCESS:
        return R.drawable.vector_drawable_success;

      case DANGER:
        return R.drawable.vector_drawable_danger;

      case WARNING:
        return R.drawable.vector_drawable_warning;

      case INFO:
        return R.drawable.vector_drawable_warning;
    }
    return R.drawable.vector_drawable_danger;
  }
}
