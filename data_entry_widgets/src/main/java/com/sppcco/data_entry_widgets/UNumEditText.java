package com.sppcco.data_entry_widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Objects;

import androidx.appcompat.widget.AppCompatEditText;

public class UNumEditText extends AppCompatEditText {

  private boolean isDecimal;
  private int integerLength;
  private int decimalLength;
  private boolean showCommas;

  public UNumEditText(Context context) {
    super(context);
    initView(context, null);
  }

  public UNumEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    initView(context, attrs);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    updateValue(Objects.requireNonNull(getText()).toString());
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
      @SuppressLint("CustomViewStyleable") final TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.DataEntryWidgets, 0, 0);
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

    // Add Text Watcher for Decimal formatting
    initTextWatchers();
  }

  private void initTextWatchers() {
    this.addTextChangedListener(new TextWatcher() {
      private String strBeforeTextChanged;

      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        strBeforeTextChanged = Objects.requireNonNull(getText()).toString();
      }

      @SuppressLint("SetTextI18n")
      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        UNumEditText.this.removeTextChangedListener(this);
        String backupString = charSequence.toString();


        try {
          String originalString = charSequence.toString();

          long longval;

          originalString = getValueString();
          longval = (Long.parseLong(originalString));
          String formattedString = DC.faToEn(getDecoratedStringFromNumber(longval));

          //setting text after format to EditText
          setText(formattedString);
          setSelection(Objects.requireNonNull(getText()).length());

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
          setSelection(getText().length());
        }

        UNumEditText.this.addTextChangedListener(this);

      }

      @Override
      public void afterTextChanged(Editable editable) {
        // get text with sep -----------------------
        boolean isChanged = false;
        String originalString = Objects.requireNonNull(getText()).toString();

        if ((strBeforeTextChanged.equals("0") && originalString.endsWith(".0")) ||
          (strBeforeTextChanged.endsWith(".0") && originalString.endsWith(".0")) ||
          (!strBeforeTextChanged.endsWith(".0") && originalString.endsWith(".0")) ) {
          isChanged = true;
          originalString = originalString.replace(".0", "");
        }
        if ((strBeforeTextChanged.equals("0") && originalString.endsWith(".00")) ||
          strBeforeTextChanged.endsWith(".00") && originalString.endsWith(".00") ||
          !strBeforeTextChanged.endsWith(".00") && originalString.endsWith(".00")) {
          isChanged = true;
          originalString = originalString.replace(".00", "");
        }

        if (isChanged) {
          UNumEditText.this.removeTextChangedListener(this);
          setText(originalString);
          UNumEditText.this.addTextChangedListener(this);
        }
      }
    });
  }


  private void updateValue(String text) {
    setText(text);
  }

  private String getDecoratedStringFromNumber(long number) {
    String numberPattern = "###,###,###,###,###.###";

    DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
    if (isShowCommas()) {
      formatter.applyPattern(numberPattern);
      return formatter.format(number);
    } else {
      return number + "";
    }

  }


  public String getFormattedString() {
    return Objects.requireNonNull(getText()).toString();
  }


  public void showCommas() {
    setShowCommas(true);
    updateValue(Objects.requireNonNull(getText()).toString());
  }


  public void hideCommas() {
    setShowCommas(false);
    updateValue(Objects.requireNonNull(getText()).toString());
  }


  public boolean isEmpty() {

    return (
      Objects.requireNonNull(getText()).toString().trim().length() == 0 ||
        (getText().toString().trim().length() == 1 && Objects.requireNonNull(getText()).charAt(0) == '0') ||
        (getText().toString().trim().length() == 1 && Objects.requireNonNull(getText()).charAt(0) == '۰') ||
        (getText().toString().trim().length() == 1 && Objects.requireNonNull(getText()).charAt(0) == '.') ||
        (getText().toString().trim().length() == 1 && Objects.requireNonNull(getText()).charAt(0) == '-')
    );
  }


  public boolean isEmpty(boolean checkZero) {

    if (checkZero)
      return (
        Objects.requireNonNull(getText()).toString().trim().length() == 0 ||
          (getText().toString().trim().length() == 1 && Objects.requireNonNull(getText()).charAt(0) == '0') ||
          (getText().toString().trim().length() == 1 && Objects.requireNonNull(getText()).charAt(0) == '۰') ||
          (getText().toString().trim().length() == 1 && Objects.requireNonNull(getText()).charAt(0) == '.') ||
          (getText().toString().trim().length() == 1 && Objects.requireNonNull(getText()).charAt(0) == '-')
      );
    else
      return (
        Objects.requireNonNull(getText()).toString().trim().length() == 0 ||
          (getText().toString().trim().length() == 1 && Objects.requireNonNull(getText()).charAt(0) == '.') ||
          (getText().toString().trim().length() == 1 && Objects.requireNonNull(getText()).charAt(0) == '-')
      );
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
    setText(strTemp);
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
