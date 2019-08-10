package com.sppcco.helperlibrary.custom_ui;

import android.content.Context;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by m_pejam on 12/30/17.
 */

public class UTextView extends AppCompatTextView {

  public UTextView(Context context) {
    super(context);
    setType(context);
  }

  public UTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setType(context);
  }

  public UTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    setType(context);
  }

  private void setType(Context context) {
    Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/iranian-sans-light.ttf");
    setTypeface(typeface);
  }
}
