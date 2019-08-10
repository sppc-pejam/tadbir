package com.sppcco.data_entry_widgets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created by m_pejam on 05/21/18.
 * Datatype Converter Class
 */

public class DC {

  private static final Pattern VALID_NAME_PATTERN_REGEX = Pattern.compile("[0-9]+.+/+$");
  private static final char[] DIGITS = {'\u0660', '\u0661', '\u0662', '\u0663', '\u0664', '\u0665', '\u0666', '\u0667', '\u0668', '\u0669'};
  private static final char[] URDU_FARSI = {'\u06f0', '\u06f1', '\u06f2', '\u06f3', '\u06f4', '\u06f5', '\u06f6', '\u06f7', '\u06f8', '\u06f9'};
  private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";

  private static boolean isENum(String string) {
    return VALID_NAME_PATTERN_REGEX.matcher(string).find();
  }

  private static boolean isFNum(String lang) {
    boolean bTest = "ar".equals(lang) || "fa".equals(lang) || "ur".equals(lang);
    return bTest;
  }

  private static String convertFNumToENum(String number) {
    char[] chars = new char[number.length()];
    for (int i = 0; i < number.length(); i++) {
      char ch = number.charAt(i);
      if (ch >= 0x0660 && ch <= 0x0669)
        ch -= 0x0660 - '0';
      else if (ch >= 0x06f0 && ch <= 0x06F9)
        ch -= 0x06f0 - '0';
      chars[i] = ch;
    }
    return new String(chars);
  }

  public static String FNumToENum(String number) {
    if (CK.isDoubleNum(number)) {
      if (isENum(number))
        return number;
      else if (isFNum(number))
        return convertFNumToENum(number);

    } else {
      return number;
    }
    return number;
  }

  public static String faToEn(String num) {
    return num
      .replace("۰", "0")
      .replace("۱", "1")
      .replace("۲", "2")
      .replace("۳", "3")
      .replace("۴", "4")
      .replace("۵", "5")
      .replace("۶", "6")
      .replace("۷", "7")
      .replace("۸", "8")
      .replace("۹", "9")
      //.replace(",", ",")
      .replace("٫", ",")
      .replace("٬", ",")
      .replace("−", "-")
      .replace(".۰", "");
  }


//-----------------------------------------------------------------------------------------------------

  public static BigDecimal dtobd(double v) {
    return BigDecimal.valueOf(v);
  }

  public static String dtostr(double v) {
    return BigDecimal.valueOf(v).toPlainString();
  }

  public static String[] jsnToStrArr(String remoteResponse, String... objectName) {
    String[] result = new String[objectName.length];
    try {
      JSONArray jsonarray = new JSONArray(remoteResponse);
      for (int i = 0; i < jsonarray.length(); i++) {
        JSONObject jsonobject = jsonarray.getJSONObject(i);

        for (int res = 0; res < objectName.length; res++) {
          result[res] = jsonobject.getString(objectName[res]);
        }
      }
      return result;
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }
  }


}
