package com.sppcco.helperlibrary.toast;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import androidx.annotation.DrawableRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devspark.appmsg.AppMsg;
import com.sppcco.helperlibrary.R;
import com.sppcco.helperlibrary.message.enums.MessageDuration;
import com.sppcco.helperlibrary.message.enums.MessageType;

public class ToastMangaerBuilder {

  private final Activity activity;
  private final String message;
  private final MessageType type;
  private final MessageDuration duration;
  private final int animationIn;
  private final int animationOut;
  @DrawableRes
  private final int resId;

  private View mView;
  private ImageView imgIcon;
  private TextView tvMessage;

  public Activity getActivity() {
    return activity;
  }

  public String getMessage() {
    return message;
  }

  public MessageType getType() {
    return type;
  }

  public MessageDuration getDuration() {
    return duration;
  }

  public int getAnimationIn() {
    return animationIn;
  }

  public int getAnimationOut() {
    return animationOut;
  }

  public int getResId() {
    return resId;
  }

  public static class Builder {
    private Activity activity;
    private String message;
    private MessageType type;
    private MessageDuration duration;
    private int animationIn;
    private int animationOut;
    @DrawableRes
    private int resId;

    public Builder setActivity(Activity activity) {
      this.activity = activity;
      return this;
    }

    public Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    public Builder setType(MessageType type) {
      this.type = type;
      return this;
    }

    public Builder setDuration(MessageDuration duration) {
      this.duration = duration;
      return this;
    }

    public Builder setAnimationIn(int animationIn) {
      this.animationIn = animationIn;
      return this;
    }

    public Builder setAnimationOut(int animationOut) {
      this.animationOut = animationOut;
      return this;
    }

    public Builder setResId(int resId) {
      this.resId = resId;
      return this;
    }

    public ToastMangaerBuilder build() {
      return new ToastMangaerBuilder(this);
    }
  }

  public ToastMangaerBuilder(Builder builder) {

    this.activity = builder.activity;
    this.message = builder.message;
    this.type = builder.type;
    this.duration = builder.duration;
    this.animationIn = builder.animationIn;
    this.animationOut = builder.animationOut;
    this.resId = builder.resId;

    initLayout(activity);
    updateView(activity, message, type, resId);

    AppMsg appMsg = AppMsg.makeText(activity, R.layout.custom_toast, getStyle(type));
    appMsg.setText(message);
    appMsg.setDuration(getDuration(duration));

    if(animationOut != 0 && animationIn != 0)
      appMsg.setAnimation(animationIn, animationOut);

    appMsg.setView(mView);
    appMsg.show();
  }

  private void initLayout(Activity activity) {
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
    tvMessage.setTextColor(activity.getResources().getColor(R.color.md_white_1000));
  }

  private void updateView(Activity activity, String message, MessageType type, @DrawableRes int resId) {
    if (message != null)
      tvMessage.setText(message);

    if (resId != 0)
      imgIcon.setImageResource(resId);
    else
      imgIcon.setVisibility(View.GONE);

    if (type != null)
      mView.setBackgroundColor(activity.getResources().getColor(getBackgroundColor(type)));
  }

  public static int getBackgroundColor(MessageType messageType) {

    if (messageType == null)
      return R.color.bts_info_color;

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
    int duration = AppMsg.LENGTH_SHORT;
    if (messageDuration == null)
      return duration;

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
    AppMsg.Style style = AppMsg.STYLE_CONFIRM;
    if (type == null)
      return style;

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
