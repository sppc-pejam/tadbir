package com.sppcco.helperlibrary.manager;


import android.annotation.SuppressLint;

import com.sppcco.helperlibrary.util.calender.JCal;
import com.sppcco.helperlibrary.util.calender.JalaliCalendar;
import com.sppcco.helperlibrary.util.calender.YearMonthDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import androidx.annotation.NonNull;

/**
 * Created by m_pejam on 01/21/18.
 */

public class CalenderManager {

  private static final String strDateStampTimeFormat = "yyyy-MM-dd'T'HH:mm:ss";
  private static String strDateFormat = "yyyy-MM-dd";
  private static String strTimeFormat = "HH:mm:ss";


  @SuppressLint("SimpleDateFormat")
  public static String MiladiToShamsi(@NonNull String strDate) {
    SimpleDateFormat df = new SimpleDateFormat(strDateFormat);
    Date d = new Date();

    try {
      d = df.parse(strDate);

    } catch (ParseException e) {
      e.printStackTrace();

    }
    return JalaliCalendar.getJalaliDate(d).replace("010", "10");
  }


  public static String ShamsiToMiladi(@NonNull String strJalaliDate) {
    return JalaliCalendar.getGregorianDate(strJalaliDate);
  }


  public static boolean isValidDateFormat(String strDate) {

    Date date = null;
    try {
      @SuppressLint("SimpleDateFormat")
      SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
      date = sdf.parse(strDate);
      if (!strDate.equals(sdf.format(date))) {
        date = null;
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date != null;

  }


  public static int getJalaliYearMonthDate(YearMonthDate yearMonthDate) {
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    JalaliCalendar.YearMonthDate ymd = new JalaliCalendar.YearMonthDate(
      calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
    ymd = JalaliCalendar.gregorianToJalali(ymd);

    if (yearMonthDate == YearMonthDate.YEAR)
      return ymd.getYear();
    if (yearMonthDate == YearMonthDate.MONTH)
      return (ymd.getMonth() + 1);
    if (yearMonthDate == YearMonthDate.DATE)
      return ymd.getDate();
    else
      return 0;
  }

  public static Calendar convertStringToCalender(String strDate) {
    Calendar cal = Calendar.getInstance();
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
    try {
      String strTest = ShamsiToMiladi(strDate);
      cal.setTime(sdf.parse(strTest));// all done
      return cal;
    } catch (ParseException e) {
      e.printStackTrace();
      return Calendar.getInstance();
    }
  }


  public static String getCurrentDate() {

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat df = new SimpleDateFormat(strDateFormat);
    String strCurrentDate = df.format(Calendar.getInstance().getTime());
    return ACharToEChar(strCurrentDate);
  }


  private static String ACharToEChar(String number) {
    char[] chars = new char[number.length()];
    for (int i = 0; i < number.length(); i++) {
      char ch = number.charAt(i);
      if (ch >= 0x0660 && ch <= 0x0669)
        ch -= 0x0660 - '0';
      else if (ch >= 0x06f0 && ch <= 0x06F9)
        ch -= 0x06f0 - '0';
      chars[i] = ch;
    }
    return new String(chars).replace("٫", ".");
  }


  /////////////////////////////////////////////////////////////////////////////////////////////////////

  //@SuppressLint("SimpleDateFormat")
  public static Date getCurrentDateStampTime() {

    Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
    return localCalendar.getTime();

    /*SimpleDateFormat sdf = new SimpleDateFormat(strDateStampTimeFormat, Locale.US);
    sdf.setTimeZone(TimeZone.getTimeZone("GMT+3:30"));
    return stringToDateStampTime(sdf.format(Calendar.getInstance().getTime()));*/

  }

  @SuppressLint("SimpleDateFormat")
  public static Date getDateStampTime() {

    SimpleDateFormat df = new SimpleDateFormat(strDateFormat);
    Date today = new Date();
    try {
      return df.parse(df.format(today));
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }

  }


  private static boolean isStampTime(Date date) {
    return DateFormat.getDateInstance().format(date).equals(strDateStampTimeFormat);
  }

  @SuppressLint("SimpleDateFormat")
  public static String getDateStampTime(Date date, boolean isShamsi) {

    SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);
    String strTemp;

    if (isShamsi) {
      strTemp = MiladiToShamsi(dateFormat.format(date));
      return strTemp.replace("010", "10");
    } else {
      strTemp = ShamsiToMiladi(dateFormat.format(date));
      return strTemp.replace("010", "10");
    }
  }

  @SuppressLint("SimpleDateFormat")
  public static String getDateStampTime2(Date date, boolean isShamsi) {

    Calendar calendar = stampTimeDateToCalendar(date);

    JCal jCal = new JCal(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)));
    return jCal.toString();
  }

  @SuppressLint("SimpleDateFormat")
  public static String getTimeStampTime(Date date) {

    SimpleDateFormat timeFormat = new SimpleDateFormat(strTimeFormat);
    return timeFormat.format(date);

  }

  private static Date stringToDateStampTime(String strDate) {

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat format = new SimpleDateFormat(strDateStampTimeFormat);
    try {
      return format.parse(strDate);

    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Date stringToDate(String strDate) {

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat format = new SimpleDateFormat(strDateFormat);
    try {
      return format.parse(strDate);

    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Date changeToStampTimeFormat(Date date) {
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dateFormat = new SimpleDateFormat(strDateStampTimeFormat);
    String strDate = dateFormat.format(date);
    return stringToDateStampTime(strDate);
  }

  public static Calendar stampTimeDateToCalendar(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal;
  }

  public static String stampDateToString(Date date) {
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dateFormat = new SimpleDateFormat(strDateStampTimeFormat);

    return dateFormat.format(date);
  }

  public static String getCurrentTime() {
    Date currentTime = Calendar.getInstance().getTime();
    String hour = currentTime.getHours() <= 9 ? "0" + currentTime.getHours() : String.valueOf(currentTime.getHours());
    String min = currentTime.getMinutes() <= 9 ? "0" + currentTime.getMinutes() : String.valueOf(currentTime.getMinutes());
    return String.format("%s:%s", hour, min);
  }

}
