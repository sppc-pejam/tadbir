package com.sppcco.helperlibrary.manager;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.Theme;
import com.afollestad.materialdialogs.util.DialogUtils;
import com.afollestad.materialdialogs.util.TypefaceHelper;
import com.sppcco.helperlibrary.R;

import java.text.NumberFormat;

public class ProgressDialogManager extends Dialog {

  protected final Builder builder;
  private final Handler handler;
  protected TextView title;
  protected TextView content;
  ProgressBar prgHorizontal , prgSpinner;
  TextView progressMinMax;
  TextView progressLabel;

  public ProgressDialogManager(@NonNull Context context, Builder builder) {
    super(context, R.style.AppCompatAlertDialogStyle);
    //super(context, getTheme(builder));
    handler = new Handler();
    this.builder = builder;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.custom_progress);
    initLayout();
    init(this);
  }

  public final Builder getBuilder() {
    return builder;
  }

  public static int getTheme(@NonNull Builder builder) {
    boolean darkTheme = DialogUtils
      .resolveBoolean(builder.context, com.afollestad.materialdialogs.R.attr.md_dark_theme, builder.theme == Theme.LIGHT);
    builder.theme = darkTheme ? Theme.LIGHT : Theme.DARK;
    return darkTheme ? com.afollestad.materialdialogs.R.style.MD_Light : com.afollestad.materialdialogs.R.style.MD_Dark;
  }

  @SuppressWarnings("unused")
  public final void setTypeface(TextView target, Typeface t) {
    if (t == null) {
      return;
    }
    int flags = target.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG;
    target.setPaintFlags(flags);
    target.setTypeface(t);
  }

  public void initLayout(){
    prgHorizontal = findViewById(R.id.prg_horizontal);
    prgSpinner = findViewById(R.id.prg_spinner);
    content = findViewById(R.id.tv_content);
    progressLabel = findViewById(R.id.tv_prg_num);
    progressMinMax = findViewById(R.id.tv_percent);
  }


  @SuppressWarnings("ConstantConditions")
  @UiThread
  public static void init(final ProgressDialogManager dialog) {
    final Builder builder = dialog.builder;

    // Set cancelable flag and dialog background color
    dialog.setCancelable(builder.cancelable);
    dialog.setCanceledOnTouchOutside(builder.canceledOnTouchOutside);

    /*if (builder.backgroundColor == 0) {
      builder.backgroundColor = DialogUtils
        .resolveColor(builder.context, com.afollestad.materialdialogs.R.attr.md_background_color,
          DialogUtils.resolveColor(dialog.getContext(), com.afollestad.materialdialogs.R.attr.colorBackgroundFloating));
    }
    if (builder.backgroundColor != 0) {
      GradientDrawable drawable = new GradientDrawable();
      drawable.setCornerRadius(
        builder.context.getResources().getDimension(com.afollestad.materialdialogs.R.dimen.md_bg_corner_radius));
      drawable.setColor(builder.backgroundColor);
      dialog.getWindow().setBackgroundDrawable(drawable);
    }*/

    // Retrieve default content colors
    if (!builder.contentColorSet) {
      final int contentColorFallback = DialogUtils
        .resolveColor(dialog.getContext(), android.R.attr.textColorSecondary);
      builder.contentColor = DialogUtils
        .resolveColor(builder.context, com.afollestad.materialdialogs.R.attr.md_content_color, contentColorFallback);
    }

    // Setup content
    if (dialog.content != null) {
      dialog.setContent(builder.content);
      dialog.setTypeface(dialog.content, builder.regularFont);
      dialog.setContentGravity(builder.contentGravity);
      dialog.content.setTextColor(builder.contentColor);
      dialog.content.setMovementMethod(new LinkMovementMethod());
      dialog.content.setLineSpacing(0f, builder.contentLineSpacingMultiplier);
      /*if (builder.linkColor == null) {
        dialog.content.setLinkTextColor(
          DialogUtils.resolveColor(dialog.getContext(), android.R.attr.textColorPrimary));
      } else {
        dialog.content.setLinkTextColor(builder.linkColor);
      }
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        //noinspection ResourceType
        dialog.content.setTextAlignment(builder.contentGravity);
      }*/

    }

    if(dialog.prgHorizontal != null){
      dialog.setProgress(builder.progress);
      dialog.setMaxProgress(builder.progressMax);
    }
  }


  @UiThread
  public final void setContent(CharSequence newContent) {
    content.setText(newContent);
    content.setVisibility(TextUtils.isEmpty(newContent) ? View.GONE : View.VISIBLE);
  }

  @UiThread
  public final void setContent(@StringRes int newContentRes) {
    setContent(builder.context.getString(newContentRes));
  }

  @SuppressWarnings("unused")
  @UiThread
  public final void setContent(@StringRes int newContentRes, @Nullable Object... formatArgs) {
    setContent(builder.context.getString(newContentRes, formatArgs));
  }

  @UiThread
  public final void setContentGravity(int gravity) {
    content.setGravity(gravity);
  }

  public final int getCurrentProgress() {
    if (prgHorizontal == null) {
      return -1;
    }
    return prgHorizontal.getProgress();
  }

  @SuppressWarnings("unused")
  public ProgressBar getProgressBar() {
    return prgHorizontal;
  }

  @SuppressWarnings("unused")
  public final void incrementProgress(final int by) {
    setProgress(getCurrentProgress() + by);
  }

  public final void setProgress(final int progress) {
    if (builder.progress <= -2) {
      Log.w("MaterialDialog", "Calling setProgress(int) on an " +
        "indeterminate progress dialog has no effect!");
      return;
    }
    prgHorizontal.setProgress(progress);
    handler.post(() -> {
      if (progressLabel != null) {
        progressLabel.setText(builder.progressPercentFormat.format(
          (float) getCurrentProgress() / (float) getMaxProgress()));
      }
      if (progressMinMax != null) {
        progressMinMax.setText(String.format(builder.progressNumberFormat,
          getCurrentProgress(), getMaxProgress()));
      }
    });
  }

  @SuppressWarnings("unused")
  public final boolean isIndeterminateProgress() {
    return builder.indeterminateProgress;
  }

  @SuppressWarnings("unused")
  public final void setIndeterminateProgress(boolean indeterminateProgress){
    builder.indeterminateProgress = indeterminateProgress;
  }

  public final int getMaxProgress() {
    if (prgHorizontal == null) {
      return -1;
    }
    return prgHorizontal.getMax();
  }

  @SuppressWarnings("unused")
  public final void setMaxProgress(final int max) {
    if (builder.progress <= -2) {
      throw new IllegalStateException("Cannot use setMaxProgress() on this dialog.");
    }
    prgHorizontal.setMax(max);
  }

  @SuppressWarnings("unused")
  public final void setProgressPercentFormat(NumberFormat format) {
    builder.progressPercentFormat = format;
    setProgress(getCurrentProgress()); // invalidates display
  }

  @SuppressWarnings("unused")
  public final void setProgressNumberFormat(String format) {
    builder.progressNumberFormat = format;
    setProgress(getCurrentProgress()); // invalidates display
  }

  public static class Builder {
    protected final Context context;
    protected CharSequence title;

    protected int titleGravity = Gravity.START;
    protected int contentGravity = Gravity.START;

    protected Theme theme = Theme.LIGHT;
    protected boolean cancelable = true;
    protected boolean canceledOnTouchOutside = true;

    protected View customView;
    protected int backgroundColor;

    protected int contentColor = -1;
    protected CharSequence content;

    protected Typeface regularFont;
    protected Typeface mediumFont;

    protected boolean indeterminateProgress;
    protected boolean showMinMax;
    protected int progress = -2;
    protected int progressMax = 0;

    protected String progressNumberFormat;
    protected NumberFormat progressPercentFormat;
    protected boolean indeterminateIsHorizontalProgress;

    protected boolean contentColorSet = false;
    protected ColorStateList linkColor;
    protected float contentLineSpacingMultiplier = 1.2f;

    public Builder(@NonNull Context context) {
      this.context = context;

      /*final int materialBlue = DialogUtils.getColor(context, com.afollestad.materialdialogs.R.color.md_material_blue_600);

      this.widgetColor = DialogUtils.resolveColor(context,
        com.afollestad.materialdialogs.R.attr.colorAccent, materialBlue);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.widgetColor = DialogUtils.resolveColor(context,
          android.R.attr.colorAccent, this.widgetColor);
      }*/

      this.progressPercentFormat = NumberFormat.getPercentInstance();
      this.progressNumberFormat = "%1d/%2d";

      // Set the default theme based on the Activity theme's primary color darkness (more white or more black)
      final int primaryTextColor = DialogUtils.resolveColor(context,
        android.R.attr.textColorPrimary);
      this.theme = DialogUtils.isColorDark(primaryTextColor) ? Theme.LIGHT : Theme.DARK;

      // Retrieve gravity settings from global theme attributes if needed
      this.contentGravity = Gravity.START;

      final String mediumFont = DialogUtils.resolveString(context,
        com.afollestad.materialdialogs.R.attr.md_medium_font);
      final String regularFont = DialogUtils.resolveString(context,
        com.afollestad.materialdialogs.R.attr.md_regular_font);
      typeface(mediumFont, regularFont);

      if (this.mediumFont == null) {
        try {
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.mediumFont = Typeface.create("sans-serif-medium", Typeface.NORMAL);
          } else {
            this.mediumFont = Typeface.create("sans-serif", Typeface.BOLD);
          }
        } catch (Exception ignored) {
          this.mediumFont = Typeface.DEFAULT_BOLD;
        }
      }
      if (this.regularFont == null) {
        try {
          this.regularFont = Typeface.create("sans-serif", Typeface.NORMAL);
        } catch (Exception ignored) {
          this.regularFont = Typeface.SANS_SERIF;
          if (this.regularFont == null) {
            this.regularFont = Typeface.DEFAULT;
          }
        }
      }
    }

    public final Context getContext() {
      return context;
    }

    public final Typeface getRegularFont() {
      return regularFont;
    }

    public Builder theme(@NonNull Theme theme) {
      this.theme = theme;
      return this;
    }

    public Builder cancelable(boolean cancelable) {
      this.cancelable = cancelable;
      this.canceledOnTouchOutside = cancelable;
      return this;
    }

    public Builder canceledOnTouchOutside(boolean canceledOnTouchOutside) {
      this.canceledOnTouchOutside = canceledOnTouchOutside;
      return this;
    }

    public Builder title(@StringRes int titleRes) {
      title(this.context.getText(titleRes));
      return this;
    }

    public Builder title(@NonNull CharSequence title) {
      this.title = title;
      return this;
    }

    @SuppressWarnings("unused")
    public Builder titleGravity(int gravity) {
      this.titleGravity = gravity;
      return this;
    }

    @SuppressWarnings("unused")
    public Builder typeface(@Nullable Typeface medium, @Nullable Typeface regular) {
      this.mediumFont = medium;
      this.regularFont = regular;
      return this;
    }

    @SuppressWarnings("unused")
    public Builder typeface(@Nullable String medium, @Nullable String regular) {
      if (medium != null) {
        this.mediumFont = TypefaceHelper.get(this.context, medium);
        if (this.mediumFont == null) {
          throw new IllegalArgumentException("No font asset found for " + medium);
        }
      }
      if (regular != null) {
        this.regularFont = TypefaceHelper.get(this.context, regular);
        if (this.regularFont == null) {
          throw new IllegalArgumentException("No font asset found for " + regular);
        }
      }
      return this;
    }

    public Builder content(@StringRes int contentRes) {
      return content(contentRes, false);
    }

    public Builder content(@StringRes int contentRes, boolean html) {
      CharSequence text = this.context.getText(contentRes);
      if (html) {
        text = Html.fromHtml(text.toString().replace("\n", "<br/>"));
      }
      return content(text);
    }

    public Builder content(@NonNull CharSequence content) {
      if (this.customView != null) {
        throw new IllegalStateException("You cannot set content() " +
          "when you're using a custom view.");
      }
      this.content = content;
      return this;
    }

    public Builder content(@StringRes int contentRes, Object... formatArgs) {
      String str = String.format(this.context.getString(contentRes), formatArgs)
        .replace("\n", "<br/>");
      //noinspection deprecation
      return content(Html.fromHtml(str));
    }

    public void contentColor(@ColorInt int color) {
      this.contentColor = color;
      this.contentColorSet = true;
    }

    @SuppressWarnings("unused")
    public Builder contentColorRes(@ColorRes int colorRes) {
      contentColor(DialogUtils.getColor(this.context, colorRes));
      return this;
    }

    @SuppressWarnings("unused")
    public Builder contentColorAttr(@AttrRes int colorAttr) {
      contentColor(DialogUtils.resolveColor(this.context, colorAttr));
      return this;
    }

    @SuppressWarnings("unused")
    public Builder contentGravity(int gravity) {
      this.contentGravity = gravity;
      return this;
    }

    @SuppressWarnings("unused")
    public Builder contentLineSpacing(float multiplier) {
      this.contentLineSpacingMultiplier = multiplier;
      return this;
    }

    @SuppressWarnings("unused")
    public Builder progress(boolean indeterminate, int max) {
      if (this.customView != null) {
        throw new IllegalStateException(
          "You cannot set progress() when you're using a custom view.");
      }
      if (indeterminate) {
        this.indeterminateProgress = true;
        this.progress = -2;
      } else {
        this.indeterminateIsHorizontalProgress = false;
        this.indeterminateProgress = false;
        this.progress = -1;
        this.progressMax = max;
      }
      return this;
    }

    @SuppressWarnings("unused")
    public Builder progress(boolean indeterminate, int max, boolean showMinMax) {
      this.showMinMax = showMinMax;
      return progress(indeterminate, max);
    }

    @SuppressWarnings("unused")
    public Builder progressNumberFormat(@NonNull String format) {
      this.progressNumberFormat = format;
      return this;
    }

    @SuppressWarnings("unused")
    public Builder progressPercentFormat(@NonNull NumberFormat format) {
      this.progressPercentFormat = format;
      return this;
    }

    @SuppressWarnings("unused")
    public Builder progressIndeterminateStyle(boolean horizontal) {
      this.indeterminateIsHorizontalProgress = horizontal;
      return this;
    }


    @UiThread
    public ProgressDialogManager build() {
      return new ProgressDialogManager(context, this);
    }

    @UiThread
    public ProgressDialogManager show() {
      ProgressDialogManager dialog = build();
      dialog.show();
      return dialog;
    }
  }
}
