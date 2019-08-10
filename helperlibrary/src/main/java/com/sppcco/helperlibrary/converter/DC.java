package com.sppcco.helperlibrary.converter;

import android.content.Context;
import android.util.DisplayMetrics;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
      .replace(",", ".")
      .replace("٫", ".")
      .replace("−", "-")
      .replace(".۰", "");
  }


  public static String enToFa(String num) {
    return num
      .replace("0", "۰")
      .replace("1", "۱")
      .replace("2", "۲")
      .replace("3", "۳")
      .replace("4", "۴")
      .replace("5", "۵")
      .replace("6", "۶")
      .replace("7", "۷")
      .replace("8", "۸")
      .replace("9", "۹");
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


  /**
   * This method converts dp unit to equivalent pixels, depending on device density.
   *
   * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
   * @param context Context to get resources and device specific display metrics
   * @return A float value to represent px equivalent to dp depending on device density
   */
  public static float convertDpToPixel(float dp, Context context) {
    return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
  }

  /**
   * This method converts device specific pixels to density independent pixels.
   *
   * @param px      A value in px (pixels) unit. Which we need to convert into db
   * @param context Context to get resources and device specific display metrics
   * @return A float value to represent dp equivalent to px value
   */
  public static float convertPixelsToDp(float px, Context context) {
    return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
  }


  public static <T> List<T> jsonArrayToListModel(JSONObject jsonObject, Class<T> tClass) {
    List<T> tList = null;
    try {
      JSONArray jsonArray = new JSONArray(jsonObject.get(tClass.getSimpleName()).toString());
      if (jsonArray.length() != 0) {
        tList = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
          Gson gson = new Gson();
          T clz = gson.fromJson(jsonArray.getString(i), tClass);
          tList.add((T) clz);
        }
      } else {
        tList = new ArrayList<>();
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return tList;
  }

}
