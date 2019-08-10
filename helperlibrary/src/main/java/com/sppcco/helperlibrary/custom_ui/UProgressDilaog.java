package com.sppcco.helperlibrary.custom_ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.util.DialogUtils;
import com.afollestad.materialdialogs.util.TypefaceHelper;
import com.sppcco.helperlibrary.R;

import java.text.NumberFormat;

public class UProgressDilaog extends Dialog{

  protected final ProgressDialogBuilder builder;
  private final Handler handler;
  private TextView content;
  private ProgressBar prgHorizontal;
  private ProgressBar prgSpinner;
  private TextView progressLabel;
  private TextView progressMinMax;

  public UProgressDilaog(@NonNull Context context, ProgressDialogBuilder builder) {
    super(context);
    this.builder = builder;
    handler = new Handler();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.custom_progress);
    initLayout();
    init(this);
  }

  public void initLayout(){
    content = findViewById(R.id.tv_content);
    prgHorizontal = findViewById(R.id.prg_horizontal);
    prgSpinner = findViewById(R.id.prg_spinner);
    progressLabel = findViewById(R.id.tv_prg_num);
    progressMinMax = findViewById(R.id.tv_percent);
  }

  public void init(UProgressDilaog dialog){
    final ProgressDialogBuilder builder = dialog.builder;

    // Set cancelable flag and dialog background color
    dialog.setCancelable(builder.cancelable);
    dialog.setCanceledOnTouchOutside(builder.canceledOnTouchOutside);

    dialog.content = dialog.findViewById(R.id.tv_content);

    // Setup content
    if (dialog.content != null) {
      dialog.content.setMovementMethod(new LinkMovementMethod());
      dialog.setTypeface(dialog.content, builder.regularFont);
      /*dialog.content.setLineSpacing(0f, builder.contentLineSpacingMultiplier);
      if (builder.linkColor == null) {
        dialog.content.setLinkTextColor(
          DialogUtils.resolveColor(dialog.getContext(), android.R.attr.textColorPrimary));
      } else {
        dialog.content.setLinkTextColor(builder.linkColor);
      }
      dialog.content.setTextColor(builder.contentColor);*/
      dialog.content.setGravity(builder.contentGravity.getGravityInt());
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        //noinspection ResourceType
        dialog.content.setTextAlignment(builder.contentGravity.getTextAlignment());
      }

      if (builder.content != null) {
        dialog.content.setText(builder.content);
        dialog.content.setVisibility(View.VISIBLE);
      } else {
        dialog.content.setVisibility(View.GONE);
      }
    }
  }

  public final void setTypeface(TextView target, Typeface t) {
    if (t == null) {
      return;
    }
    int flags = target.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG;
    target.setPaintFlags(flags);
    target.setTypeface(t);
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

  @SuppressWarnings("unused")
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

  @SuppressWarnings("unused")
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

  public static class ProgressDialogBuilder{

    protected Context context;
    protected CharSequence content;
    protected GravityEnum contentGravity = GravityEnum.START;

    protected View customView;
    protected Typeface mediumFont;
    protected Typeface regularFont;

    protected boolean indeterminateProgress;
    protected boolean showMinMax;
    protected int progress = -2;
    protected int progressMax = 0;

    protected String progressNumberFormat;
    protected NumberFormat progressPercentFormat;
    protected boolean indeterminateIsHorizontalProgress;

    protected boolean cancelable = true;
    protected boolean canceledOnTouchOutside = true;

    public ProgressDialogBuilder(Context context) {
      this.context = context;

      this.progressPercentFormat = NumberFormat.getPercentInstance();
      this.progressNumberFormat = "%1d/%2d";

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

    public ProgressDialogBuilder content(@StringRes int contentRes) {
      return content(contentRes, false);
    }

    public ProgressDialogBuilder content(@StringRes int contentRes, boolean html) {
      CharSequence text = this.context.getText(contentRes);
      if (html) {
        text = Html.fromHtml(text.toString().replace("\n", "<br/>"));
      }
      return content(text);
    }

    public ProgressDialogBuilder content(@NonNull CharSequence content) {
      if (this.customView != null) {
        throw new IllegalStateException("You cannot set content() " +
          "when you're using a custom view.");
      }
      this.content = content;
      return this;
    }

    public CharSequence getContent(){
      return this.content;
    }

    @SuppressWarnings("unused")
    public ProgressDialogBuilder progress(boolean indeterminate, int max) {
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
    public ProgressDialogBuilder progress(boolean indeterminate, int max, boolean showMinMax) {
      this.showMinMax = showMinMax;
      return progress(indeterminate, max);
    }

    @SuppressWarnings("unused")
    public ProgressDialogBuilder progressNumberFormat(@NonNull String format) {
      this.progressNumberFormat = format;
      return this;
    }

    @SuppressWarnings("unused")
    public ProgressDialogBuilder progressPercentFormat(@NonNull NumberFormat format) {
      this.progressPercentFormat = format;
      return this;
    }

    @SuppressWarnings("unused")
    public ProgressDialogBuilder progressIndeterminateStyle(boolean horizontal) {
      this.indeterminateIsHorizontalProgress = horizontal;
      return this;
    }

    @SuppressWarnings("unused")
    public ProgressDialogBuilder cancelable(boolean cancelable) {
      this.cancelable = cancelable;
      this.canceledOnTouchOutside = cancelable;
      return this;
    }

    @SuppressWarnings("unused")
    public ProgressDialogBuilder canceledOnTouchOutside(boolean canceledOnTouchOutside) {
      this.canceledOnTouchOutside = canceledOnTouchOutside;
      return this;
    }

    @SuppressWarnings("unused")
    public ProgressDialogBuilder typeface(@Nullable Typeface medium, @Nullable Typeface regular) {
      this.mediumFont = medium;
      this.regularFont = regular;
      return this;
    }

    @SuppressWarnings("unused")
    public ProgressDialogBuilder typeface(@Nullable String medium, @Nullable String regular) {
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

    @UiThread
    public UProgressDilaog build() {
      return new UProgressDilaog(context, this);
    }

    @UiThread
    public UProgressDilaog show() {
      UProgressDilaog dialog = build();
      dialog.show();
      return dialog;
    }
  }
}
