package com.sppcco.core.util.converter;

import java.util.regex.Pattern;

/**
 * Created by b_nematzadeh on 11/9/2018.
 */
public final class PersianUtil {

  private static final Pattern VALID_NAME_PATTERN_REGEX = Pattern.compile("[0-9]+$");
  private static final char[] DIGITS = {'\u0660', '\u0661', '\u0662', '\u0663', '\u0664', '\u0665', '\u0666', '\u0667', '\u0668', '\u0669'};
  private static final char[] URDU_FARSI = {'\u06f0', '\u06f1', '\u06f2', '\u06f3', '\u06f4', '\u06f5', '\u06f6', '\u06f7', '\u06f8', '\u06f9'};

  public static boolean isENum(String string) {
    return VALID_NAME_PATTERN_REGEX.matcher(string).find();
  }

  /**
   * Returns <code>true</code> if the provided language code uses arabic characters; othersise <code>false</code>.
   *
   * @param lang ISO language code.
   * @return <code>true</code> if the provided language code uses arabic characters; othersise <code>false</code>
   */
  public static boolean isFNum(String lang) {
    return "ar".equals(lang) || "fa".equals(lang) || "ur".equals(lang);
  }

  /**
   * Convert digits in the specified string to arabic digits.
   */
  public static String convertDigits(String str) {
    if (str == null || str.length() == 0) return str;

    char[] s = new char[str.length()];
    for (int i = 0; i < s.length; i++)
      s[i] = toDigit(str.charAt(i));

    return new String(s);
  }

  /**
   * Convert single digit in the specified string to arabic digit.
   */
  public static char toDigit(char ch) {
    int n = Character.getNumericValue((int) ch);
    return n >= 0 && n < 10 ? URDU_FARSI[n] : ch;
  }

  /**
   * Convert an int into arabic string.
   */
  public static String toString(int num) {
    return convertDigits(Integer.toString(num));
  }
}
