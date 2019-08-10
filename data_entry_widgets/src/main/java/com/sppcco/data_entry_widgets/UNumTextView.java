package com.sppcco.data_entry_widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Objects;

import androidx.appcompat.widget.AppCompatTextView;


public class UNumTextView extends AppCompatTextView {

  private boolean isDecimal;
  private int integerLength;
  private int decimalLength;
  private boolean showCommas;

  public UNumTextView(Context context) {
    super(context);
    initView(context, null);
  }

  public UNumTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initView(context, attrs);
  }

  private void initView(Context context, AttributeSet attrs) {
    // Setting Default Parameters
    setShowCommas(true);
    setDecimal(true);
    setIntegerLength(9);
    setDecimalLength(0);

    // Check for the attributes
    if (attrs != null) {
      // Attribute initialization
      @SuppressLint("CustomViewStyleable")
      final TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.DataEntryWidgets, 0, 0);
      try {
        setShowCommas(attrArray.getBoolean(R.styleable.DataEntryWidgets_show_commas, true));
        setIntegerLength(attrArray.getInt(R.styleable.DataEntryWidgets_integer_length, 9));
        setDecimalLength(attrArray.getInt(R.styleable.DataEntryWidgets_decimal_length, 0));
      } finally {
        attrArray.recycle();
      }
    }

    decimalDigitsInputFilter(getIntegerLength(), getDecimalLength());
    if (isDecimal())
      this.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    else
      this.setInputType(InputType.TYPE_CLASS_NUMBER);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    setValue(getText().toString());
  }

  private String getDecoratedStringFromNumber(double number) {
    String numberPattern = "###,###,###,###,###.###";

    DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
    if (isShowCommas()) {
      formatter.applyPattern(numberPattern);
      return formatter.format(number);
    } else {
      return number + "";
    }
  }

  @SuppressLint("SetTextI18n")
  public void setValue(String valueStr) {
    String backupString = valueStr;
    try {
      String originalString = valueStr;

      double longval = 0;

      //originalString = getValueString();
      longval = (Double.parseDouble(originalString));
      String formattedString = DC.faToEn(getDecoratedStringFromNumber(longval));

      //setting text after format to EditText
      setText(formattedString);

    } catch (NumberFormatException nfe) {
      nfe.printStackTrace();
      setText(backupString);

      String valStr = getValueString();

      if (valStr.equals("")) {
        long val = 0;
        setText(getDecoratedStringFromNumber(val));
      } else {
        // Some decimal number
        if (valStr.contains(".")) {
          if (valStr.indexOf(".") == valStr.length() - 1) {
            // decimal has been currently put
            String front = getDecoratedStringFromNumber(Long.parseLong(valStr.substring(0, valStr.length() - 1)));
            setText(front + ".");
          } else {
            String[] nums = getValueString().split("\\.");
            String front = getDecoratedStringFromNumber(Long.parseLong(nums[0]));
            setText(front + "." + nums[1]);
          }
        }
      }
    }
  }


  public double getDouble() throws NumberFormatException {

    if (!isEmpty()) {
      return Double.parseDouble(getValueString());
    } else
      return 0.0;
  }

  public String getValueString() {

    String string = DC.faToEn(Objects.requireNonNull(getText()).toString());

    if (string.contains(",")) {
      string = string.replace(",", "");
      string = string.replace("٬", "");
      string = string.replace("٫", ".");
    }
    if (string.contains(" ")) {
      string = string.substring(string.indexOf(" ") + 1, string.length());
    }
    return string;
  }


  public boolean isEmpty() {

    return (
      Objects.requireNonNull(getText()).toString().trim().length() == 0 ||
        (getText().toString().trim().length() == 1 && Objects.requireNonNull(getText()).charAt(0) == '.') ||
        (getText().toString().trim().length() == 1 && Objects.requireNonNull(getText()).charAt(0) == '-')
    );
  }


  public String getFormattedString() {
    setValue(getText().toString());
    return getText().toString();
  }


  public void showCommas() {
    setShowCommas(false);
    setValue(getText().toString());
  }


  public void hideCommas() {
    setShowCommas(false);
    setValue(getText().toString());
  }

  public String getString() throws NumberFormatException {
    if (!isEmpty())
      return dtostr(getDouble());
    else
      return null;
  }

  public String dtostr(double d) {
    return String.valueOf(DC.dtostr(d));
  }

  public void setString(double d) {
    String strTemp = dtostr(d);
    setValue(strTemp);
  }

  private void decimalDigitsInputFilter(int integerLength, int decimalLength) {

    setIntegerLength(integerLength);
    setDecimalLength(decimalLength);
    if (decimalLength == 0)
      setDecimal(false);

    InputFilter[] inputFilter = new InputFilter[]{new DigitsInputFilter(
      integerLength, decimalLength, Double.POSITIVE_INFINITY)};
    super.setFilters(inputFilter);
  }


  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public boolean isDecimal() {
    return isDecimal;
  }

  public void setDecimal(boolean decimal) {
    isDecimal = decimal;
  }

  public int getIntegerLength() {
    return integerLength;
  }

  public void setIntegerLength(int integerLength) {
    this.integerLength = integerLength;
  }

  public int getDecimalLength() {
    return decimalLength;
  }

  public void setDecimalLength(int decimalLength) {
    this.decimalLength = decimalLength;
  }

  public boolean isShowCommas() {
    return showCommas;
  }

  public void setShowCommas(boolean showCommas) {
    this.showCommas = showCommas;
  }
}
