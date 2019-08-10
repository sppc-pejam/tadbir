package com.sppcco.helperlibrary.snackbar;

import android.content.Context;
import android.os.Build;
import androidx.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devspark.appmsg.AppMsg;
import com.google.android.material.snackbar.Snackbar;
import com.sppcco.helperlibrary.R;
import com.sppcco.helperlibrary.message.enums.MessageDuration;
import com.sppcco.helperlibrary.message.enums.MessageType;

public class SnackbarMangerBuilder {

  private View mCustomView;
  private TextView tvContent;
  private ImageView imgIcon;

  private final Context mContext;
  private final View mView;
  private final String mContent;
  private final MessageType mType;
  private final MessageDuration mDuration;
  @DrawableRes
  private final int mResId;
  private final boolean mInternalContent;

  public static class Builder {
    private Context context;
    private View view;
    private String content;
    private MessageType type;
    private MessageDuration duration;
    @DrawableRes
    private int resId;
    private boolean internalContent;

    public SnackbarMangerBuilder.Builder setContext(Context context) {
      this.context = context;
      return this;
    }

    public SnackbarMangerBuilder.Builder setView(View view) {
      this.view = view;
      return this;
    }

    public SnackbarMangerBuilder.Builder setMessage(String content) {
      this.content = content;
      return this;
    }

    public SnackbarMangerBuilder.Builder setType(MessageType type) {
      this.type = type;
      return this;
    }

    public SnackbarMangerBuilder.Builder setDuration(MessageDuration duration) {
      this.duration = duration;
      return this;
    }

    public SnackbarMangerBuilder.Builder setResId(int resId) {
      this.resId = resId;
      return this;
    }

    public SnackbarMangerBuilder.Builder setInternalContent(boolean internalContent) {
      this.internalContent = internalContent;
      return this;
    }

    public SnackbarMangerBuilder build() {
      return new SnackbarMangerBuilder(this);
    }
  }

  public SnackbarMangerBuilder(SnackbarMangerBuilder.Builder builder) {

    mContext = builder.context;
    mView = builder.view;
    mContent = builder.content;
    mType = builder.type;
    mDuration = builder.duration;
    mResId = builder.resId;
    mInternalContent = builder.internalContent;

    Snackbar snackbar = Snackbar.make(mView, mInternalContent ? mContent : "", getDuration(mDuration));

    prepareContent(mContext, snackbar);

    View view = snackbar.getView();
    view.setBackgroundColor(mContext.getResources().getColor(getBackgroundColor(mType)));

    if (!mInternalContent) {
      initLayout(mContext, snackbar);
      tvContent.setText(mContent);
      imgIcon.setImageResource(mResId);
    }

    snackbar.show();
  }

  private void prepareContent(Context context, Snackbar snackbar) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      TextView view = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
      view.setTextColor(context.getResources().getColor(R.color.md_white_1000));
      view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }
  }

  private void initLayout(Context context, Snackbar snackbar) {
    ViewGroup viewGroup = null;
    Snackbar.SnackbarLayout snackbarView = (Snackbar.SnackbarLayout) snackbar.getView();
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View customView = inflater.inflate(R.layout.custom_snack_bar, viewGroup);

    tvContent = customView.findViewById(R.id.tv_content);
    imgIcon = customView.findViewById(R.id.img_icon);
    snackbarView.addView(customView, 0);
  }

  public int getBackgroundColor(MessageType messageType) {
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

  private int getDuration(MessageDuration messageDuration) {
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

  public int getBackgroundDrawable(MessageType messageType) {
    if (messageType == null)
      return R.drawable.vector_drawable_danger;

    switch (messageType) {

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
